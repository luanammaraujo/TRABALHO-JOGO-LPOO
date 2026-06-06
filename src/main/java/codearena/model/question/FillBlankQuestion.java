package codearena.model.question;


//Pergunta de completar lacuna

public class FillBlankQuestion extends Question {

    private String hint;

    public FillBlankQuestion(String statement, String correctAnswer,
                              String hint, Difficulty difficulty, String category) {
        super(statement, correctAnswer.trim().toLowerCase(), difficulty, category);
        this.hint = hint;
    }

    @Override
    public boolean checkAnswer(String playerAnswer) {
        if (playerAnswer == null) return false;
        return playerAnswer.trim().toLowerCase().equals(getCorrectAnswer());
    }

    @Override
    public String formatForDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("[COMPLETAR LACUNA] ").append(getDifficultyLabel()).append("\n");
        sb.append("----------------------------------------\n");
        sb.append(getStatement()).append("\n\n");
        sb.append("Dica: ").append(hint).append("\n");
        sb.append("Digite sua resposta: ");
        return sb.toString();
    }

    public String getHint() { return hint; }
}