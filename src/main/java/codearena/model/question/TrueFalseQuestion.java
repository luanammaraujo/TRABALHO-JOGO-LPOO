package codearena.model.question;

//Subclasse de Question.
public class TrueFalseQuestion extends Question {

    public TrueFalseQuestion(String statement, boolean isTrue, Difficulty difficulty, String category) {
        super(statement, isTrue ? "V" : "F", difficulty, category);
    }

    @Override
    public boolean checkAnswer(String playerAnswer) {
        if (playerAnswer == null) return false;
        String normalized = playerAnswer.trim().toUpperCase();
        return normalized.equals(getCorrectAnswer()) ||
               normalized.equals("VERDADEIRO") && getCorrectAnswer().equals("V") ||
               normalized.equals("FALSO") && getCorrectAnswer().equals("F") ||
               normalized.equals("TRUE") && getCorrectAnswer().equals("V") ||
               normalized.equals("FALSE") && getCorrectAnswer().equals("F") ||
               normalized.equals("1") && getCorrectAnswer().equals("V") ||
               normalized.equals("0") && getCorrectAnswer().equals("F");
    }

    @Override
    public String formatForDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("VERDADEIRO OU FALSO ").append(getDifficultyLabel()).append("\n");
        sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        sb.append(getStatement()).append("\n\n");
        sb.append("  V) Verdadeiro\n");
        sb.append("  F) Falso\n");
        return sb.toString();
    }

    public String getWrongOption() {
        return isTrue() ? "F" : "V";
    }

    public boolean isTrue() {
        return getCorrectAnswer().equals("V");
    }
}