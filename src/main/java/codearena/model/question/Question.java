package codearena.model.question;

/**
 * Classe abstrata base para perguntas.
 * Utiliza o padrão Template Method para definir o fluxo de avaliação.
 */
public abstract class Question {

    public enum Difficulty {
        FACIL, MEDIO, DIFICIL
    }

    private String statement;
    private String correctAnswer;
    private Difficulty difficulty;
    private String category;

    public Question(String statement, String correctAnswer, Difficulty difficulty, String category) {
        this.statement = statement;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
        this.category = category;
    }

    /**
     * Método abstrato para verificar resposta — polimorfismo em ação.
     */
    public abstract boolean checkAnswer(String playerAnswer);

    /**
     * Método abstrato para exibir a pergunta formatada.
     */
    public abstract String formatForDisplay();

    /**
     * Calcula o dano baseado na dificuldade.
     */
    public int getDamageValue() {
        return switch (difficulty) {
            case FACIL -> 20;
            case MEDIO -> 25;
            case DIFICIL -> 40;
        };
    }

    /**
     * Calcula a penalidade por erro.
     */
    public int getPenaltyValue() {
        return switch (difficulty) {
            case FACIL -> 10;
            case MEDIO -> 18;
            case DIFICIL -> 28;
        };
    }

    // Getters
    public String getStatement() { return statement; }
    public String getCorrectAnswer() { return correctAnswer; }
    public Difficulty getDifficulty() { return difficulty; }
    public String getCategory() { return category; }

    public String getDifficultyLabel() {
        return switch (difficulty) {
            case FACIL -> "Fácil";
            case MEDIO -> "Médio";
            case DIFICIL -> "Difícil";
        };
    }
}