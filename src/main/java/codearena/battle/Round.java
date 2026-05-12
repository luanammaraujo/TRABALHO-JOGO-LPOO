package codearena.battle;

import codearena.model.Player;
import codearena.model.character.Enemy;
import codearena.model.question.Question;
import codearena.model.question.AnswerEvaluator;
import codearena.model.ability.AbilityResult;

/**
 * Representa uma rodada da batalha.
 * Responsável por apresentar pergunta, receber resposta e aplicar resultado
 */
public class Round {

    private int roundNumber;
    private Question question;
    private Player player;
    private Enemy enemy;
    private AnswerEvaluator evaluator;
    private boolean abilityUsedThisRound;
    private AbilityResult abilityResult;

    public Round(int roundNumber, Question question, Player player, Enemy enemy) {
        this.roundNumber = roundNumber;
        this.question = question;
        this.player = player;
        this.enemy = enemy;
        this.evaluator = new AnswerEvaluator();
        this.abilityUsedThisRound = false;
    }

    /**
     * Processa o uso de habilidade especial antes da resposta.
     */
    public AbilityResult useAbility() {
        if (!player.isAbilityAvailable()) {
            return null;
        }
        abilityResult = player.getCharacter().getSpecialAbility().activate();
        player.useAbility();
        abilityUsedThisRound = true;

        // Aplica efeitos de estado
        switch (abilityResult.getType()) {
            case EXTRA_DAMAGE -> player.activateDamageMultiplier(abilityResult.getValue());
            case BLOCK_ERROR -> player.activateErrorBlock();
            default -> {}
        }
        return abilityResult;
    }

    /**
     * Processa a resposta do jogador e aplica os efeitos na batalha.
     * @return RoundResult com todos os detalhes do que aconteceu
     */
    public RoundResult processAnswer(String playerAnswer) {
        AnswerEvaluator.EvaluationOutcome outcome = evaluator.evaluate(
            question,
            playerAnswer,
            player.getDamageMultiplier(),
            player.isErrorBlockActive()
        );

        if (outcome.isCorrect()) {
            enemy.takeDamage(outcome.getDamageOrPenalty());
            player.recordCorrectAnswer();
            if (player.isDamageMultiplierActive()) {
                player.consumeDamageMultiplier();
            }
        } else {
            if (player.isErrorBlockActive()) {
                player.consumeErrorBlock();
            } else {
                player.getCharacter().takeDamage(outcome.getDamageOrPenalty());
            }
            player.recordWrongAnswer();
        }

        return new RoundResult(roundNumber, outcome, question, player, enemy);
    }

    // Getters
    public int getRoundNumber() { return roundNumber; }
    public Question getQuestion() { return question; }
    public boolean wasAbilityUsed() { return abilityUsedThisRound; }
    public AbilityResult getAbilityResult() { return abilityResult; }

    // Classe que encapsula o resultado da rodada
    public static class RoundResult {
        private final int roundNumber;
        private final AnswerEvaluator.EvaluationOutcome outcome;
        private final Question question;
        private final Player player;
        private final Enemy enemy;

        public RoundResult(int roundNumber, AnswerEvaluator.EvaluationOutcome outcome,
                          Question question, Player player, Enemy enemy) {
            this.roundNumber = roundNumber;
            this.outcome = outcome;
            this.question = question;
            this.player = player;
            this.enemy = enemy;
        }

        public int getRoundNumber() { return roundNumber; }
        public AnswerEvaluator.EvaluationOutcome getOutcome() { return outcome; }
        public Question getQuestion() { return question; }
        public Player getPlayer() { return player; }
        public Enemy getEnemy() { return enemy; }
        public boolean isPlayerAlive() { return player.getCharacter().isAlive(); }
        public boolean isEnemyAlive() { return enemy.isAlive(); }
    }
}