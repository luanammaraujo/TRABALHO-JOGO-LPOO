package codearena.ui;

import codearena.model.Player;
import codearena.model.character.Enemy;
import codearena.model.question.Question;
import codearena.model.question.TrueFalseQuestion;
import codearena.model.question.MultipleChoiceQuestion;
import codearena.model.question.FillBlankQuestion;
import codearena.model.ability.AbilityResult;
import codearena.battle.Round;
import java.util.Scanner;


public class ConsoleUI {

    private Scanner scanner;

    public ConsoleUI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showWelcome() {
        clear();
      
        System.out.println("        Quem sabe mais no reino?        "); 
        System.out.println();
    }

    public String askPlayerName() {
        System.out.print("Digite seu nome: ");
        return scanner.nextLine().trim();
    }

    public int askCharacterChoice() {
        System.out.println("\nEscolha seu personagem:");
        System.out.println("  1) Alladin   - Equilibrado | Habilidade: elimina 2 alternativas erradas");
        System.out.println("  2) Merida    - Alto ataque | Habilidade: dano duplo no proximo acerto");
        System.out.println("  3) Rapunzel  - Alta defesa | Habilidade: bloqueia penalidade do proximo erro");
        System.out.print("\nEscolha (1-3): ");
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= 3) return choice;
            } catch (NumberFormatException ignored) {}
            System.out.print("Opcao invalida. Escolha 1, 2 ou 3: ");
        }
    }

    public void showPhaseStart(String phaseName, Enemy enemy) {
        clear();
        System.out.println("----------------------------------------");
        System.out.println("NOVA FASE: " + phaseName.toUpperCase());
        System.out.println("----------------------------------------");
        System.out.println("\n" + enemy.getName() + " apareceu!");
        System.out.println("\"" + enemy.getTaunt() + "\"");
        System.out.println("\n" + enemy);
        System.out.println("\nPressione ENTER para comecar...");
        scanner.nextLine();
    }

   public void showRoundHeader(int round, int totalRounds, Player player, Enemy enemy) {
    clear();
    System.out.println("|  Rodada " + round + "/" + totalRounds);
    System.out.println("|  " + player.getCharacter().getName() + 
        " HP: " + player.getCharacter().getCurrentHp() + 
        "/" + player.getCharacter().getMaxHp());
    System.out.println("|  " + enemy.getName() + 
        " HP: " + enemy.getCurrentHp() + 
        "/" + enemy.getMaxHp());
}

    public void showQuestion(Question question) {
        System.out.println("\n" + question.formatForDisplay());
    }

    public boolean askUseAbility(Player player) {
        if (!player.isAbilityAvailable()) return false;
        System.out.printf("\nUsar habilidade '%s' (%s)? (s/n): ",
            player.getCharacter().getSpecialAbility().getName(),
            player.getCharacter().getAbilityDescription());
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("s") || input.equals("sim");
    }

    public void showAbilityResult(AbilityResult result) {
        System.out.println("\n" + result.getMessage());
    }

    public void showEliminatedOptions(String[] eliminated, MultipleChoiceQuestion q) {
        System.out.print("Alternativas eliminadas: ");
        int count = 0;
        for (String letter : eliminated) {
            if (!letter.equals(q.getCorrectLetter()) && count < 2) {
                System.out.print(letter + " ");
                count++;
            }
        }
        System.out.println();
    }

    public String askAnswer(Question question) {
    if (question instanceof MultipleChoiceQuestion) {
        System.out.print("\nSua resposta (A/B/C/D): ");
    } else if (question instanceof TrueFalseQuestion) {
        System.out.print("\nSua resposta (V/F): ");
    } else {
        System.out.print("\nSua resposta: ");
    }
    return scanner.nextLine().trim();
}

    public void showRoundResult(Round.RoundResult result) {
        System.out.println("\n" + result.getOutcome().getFeedback());
        System.out.printf("  %s HP: %d/%d%n",
            result.getPlayer().getCharacter().getName(),
            result.getPlayer().getCharacter().getCurrentHp(),
            result.getPlayer().getCharacter().getMaxHp());
        System.out.printf("  %s HP: %d/%d%n",
            result.getEnemy().getName(),
            result.getEnemy().getCurrentHp(),
            result.getEnemy().getMaxHp());
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    public void showVictory(Player player, String phaseName) {
        System.out.println("\nVITORIA! Voce derrotou " + phaseName + "!");
        System.out.printf("Pontuacao atual: %d%n", player.getScore());
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    public void showDefeat(Player player) {
        System.out.println("\nDERROTA! Voce foi eliminado!");
        System.out.printf("Pontuacao final: %d%n", player.getScore());
        System.out.printf("Acertos: %d | Erros: %d%n",
            player.getCorrectAnswers(), player.getWrongAnswers());
    }

    public void showFinalVictory(Player player) {
        clear();
        
        System.out.println("      PARABENS! VOCE VENCEU!              ");
        System.out.println("    Derrotou todos os viloes do reino!    ");
        System.out.printf("%nJogador: %s%n", player.getName());
        System.out.printf("Personagem: %s%n", player.getCharacter().getName());
        System.out.printf("Pontuacao final: %d%n", player.getScore());
        System.out.printf("Acertos: %d | Erros: %d%n",
            player.getCorrectAnswers(), player.getWrongAnswers());
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    private void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush(); 
    }
}