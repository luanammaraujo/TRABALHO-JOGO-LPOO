package codearena.battle;

import codearena.model.Player;
import codearena.model.character.Enemy;
import codearena.model.question.Question;
import codearena.model.question.MultipleChoiceQuestion;
import codearena.model.ability.AbilityResult;
import codearena.ui.ConsoleUI;
import java.util.List;

public class BattleManager {

    private static final int ROUNDS_PER_PHASE = 5;

    private Player player;
    private ConsoleUI ui;

    public BattleManager(Player player, ConsoleUI ui) {
        this.player = player;
        this.ui = ui;
    }

    public boolean runPhase(Enemy enemy, List<Question> questions, String phaseName) {
        player.getCharacter().resetHp();
        enemy.resetHp();
        player.resetAbilityForPhase();

        ui.showPhaseStart(phaseName, enemy);

        for (int i = 0; i < Math.min(ROUNDS_PER_PHASE, questions.size()); i++) {
            Question question = questions.get(i);
            Round round = new Round(i + 1, question, player, enemy);

            ui.showRoundHeader(i + 1, ROUNDS_PER_PHASE, player, enemy);
            ui.showQuestion(question);

            // Pergunta se quer usar habilidade
            if (ui.askUseAbility(player)) {
                AbilityResult abilityResult = round.useAbility();
                ui.showAbilityResult(abilityResult);

                // Se for dica, mostra alternativas eliminadas
                if (abilityResult.getType() == AbilityResult.EffectType.HINT
                        && question instanceof MultipleChoiceQuestion mcq) {
                    ui.showEliminatedOptions(mcq.getEliminableOptions(), mcq);
                }
            }

            String answer = ui.askAnswer(question);
            Round.RoundResult result = round.processAnswer(answer);
            ui.showRoundResult(result);

            if (!result.isPlayerAlive()) {
                ui.showDefeat(player);
                return false;
            }

            if (!result.isEnemyAlive()) {
                ui.showVictory(player, enemy.getName());
                return true;
            }
        }

        if (enemy.isAlive()) {
            ui.showMessage("\n Rodadas esgotadas! O inimigo sobreviveu...");
            ui.showDefeat(player);
            return false;
        }

        return true;
    }
}