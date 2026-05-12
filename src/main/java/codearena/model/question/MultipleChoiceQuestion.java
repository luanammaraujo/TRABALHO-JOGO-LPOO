package codearena.model.question;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Pergunta de múltipla escolha.
 * Subclasse de Question.
 */
public class MultipleChoiceQuestion extends Question {

    private String[] options;   // A, B, C, D
    private String correctLetter; // "A", "B", "C" ou "D"

    public MultipleChoiceQuestion(String statement, String[] options,
                                   String correctLetter, Difficulty difficulty, String category) {
        super(statement, correctLetter.toUpperCase(), difficulty, category);
        this.options = options;
        this.correctLetter = correctLetter.toUpperCase();
    }

    @Override
    public boolean checkAnswer(String playerAnswer) {
        return playerAnswer != null &&
               playerAnswer.trim().toUpperCase().equals(correctLetter);
    }

    @Override
    public String formatForDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("[MÚLTIPLA ESCOLHA] ").append(getDifficultyLabel()).append("\n");
        sb.append(getStatement()).append("\n\n");
        String[] letters = {"A", "B", "C", "D"};
        for (int i = 0; i < options.length && i < 4; i++) {
            sb.append("  ").append(letters[i]).append(") ").append(options[i]).append("\n");
        }
        return sb.toString();
    }

    /**
     * Retorna as opções eliminadas (exceto a correta) — para a habilidade de dica.
     */
    public String[] getEliminableOptions() {
        List<String> eliminable = new ArrayList<>();
        String[] letters = {"A", "B", "C", "D"};
        for (int i = 0; i < options.length; i++) {
            if (!letters[i].equals(correctLetter)) {
                eliminable.add(letters[i]);
            }
        }
        return eliminable.toArray(new String[0]);
    }

    public String[] getOptions() { return options; }
    public String getCorrectLetter() { return correctLetter; }
}