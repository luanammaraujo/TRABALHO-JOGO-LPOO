package codearena.game;

import codearena.battle.BattleManager;
import codearena.model.Player;
import codearena.model.character.Character;
import codearena.model.character.Aladdin;
import codearena.model.character.Merida;
import codearena.model.character.Rapunzel;
import codearena.model.character.Enemy;
import codearena.model.question.Question;
import codearena.model.question.QuestionBank;
import codearena.ui.ConsoleUI;
import java.util.List;
import java.util.Scanner;

public class Game {

    private ConsoleUI ui;
    private QuestionBank questionBank;
    private Player player;
    private BattleManager battleManager;

    public Game(Scanner scanner) {
        this.ui = new ConsoleUI(scanner);
        this.questionBank = new QuestionBank();
    }

    public void start() {
        ui.showWelcome();

        String name = ui.askPlayerName();
        int characterChoice = ui.askCharacterChoice();
        Character character = createCharacter(characterChoice);
        player = new Player(name, character);
        battleManager = new BattleManager(player, ui);

        ui.showMessage("\nPersonagem escolhido: " + character.getName());
        ui.showMessage(character.getDescription());
        ui.showMessage("Habilidade: " + character.getAbilityDescription());
        ui.showMessage("\nPressione ENTER para comecar a aventura...");

        // FASE 1 - Facil - Rainha Ma
        List<Question> easyQuestions = questionBank.getQuestionsByDifficulty(Question.Difficulty.FACIL, 5);
        boolean phase1 = battleManager.runPhase(Enemy.createRainhaMa(), easyQuestions, "Fase 1 - O Espelho Magico");
        if (!phase1) return;

        // FASE 2 - Medio - Scar
        List<Question> mediumQuestions = questionBank.getQuestionsByDifficulty(Question.Difficulty.MEDIO, 5);
        boolean phase2 = battleManager.runPhase(Enemy.createScar(), mediumQuestions, "Fase 2 - As Terras do Orgulhoso");
        if (!phase2) return;

        // FASE 3 - Dificil - Malevola
        List<Question> hardQuestions = questionBank.getQuestionsByDifficulty(Question.Difficulty.DIFICIL, 5);
        boolean phase3 = battleManager.runPhase(Enemy.createMalévola(), hardQuestions, "Fase 3 - A Maldicao das Fadas");
        if (!phase3) return;

        ui.showFinalVictory(player);
    }

    private Character createCharacter(int choice) {
        return switch (choice) {
            case 1 -> new Aladdin();
            case 2 -> new Merida();
            case 3 -> new Rapunzel();
            default -> new Aladdin();
        };
    }
}