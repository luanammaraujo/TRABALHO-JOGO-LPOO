package codearena.model.question;

//Responsável por avaliar respostas e calcular efeito
//Princípio da Responsabilidade Única 

public class AnswerEvaluator {

    public enum EvaluationResult {
        CORRECT, INCORRECT
    }

    public static class EvaluationOutcome {
        private final EvaluationResult result;
        private final int damageOrPenalty;
        private final String feedback;

        public EvaluationOutcome(EvaluationResult result, int damageOrPenalty, String feedback) {
            this.result = result;
            this.damageOrPenalty = damageOrPenalty;
            this.feedback = feedback;
        }

        public EvaluationResult getResult() { return result; }
        public int getDamageOrPenalty() { return damageOrPenalty; }
        public String getFeedback() { return feedback; }
        public boolean isCorrect() { return result == EvaluationResult.CORRECT; }
    }

    /**
     * Avalia a resposta do jogador e retorna o resultado.
     * @param question A pergunta atual
     * @param playerAnswer A resposta dada pelo jogador
     * @param damageMultiplier Multiplicador de dano (para habilidade da Merida)
     * @param errorBlocked Se o erro está bloqueado (habilidade da Rapunzel)
     */
    public EvaluationOutcome evaluate(Question question, String playerAnswer,
                                      int damageMultiplier, boolean errorBlocked) {
        boolean correct = question.checkAnswer(playerAnswer);

        if (correct) {
            int damage = question.getDamageValue() * damageMultiplier;
            String feedback = buildCorrectFeedback(question, damage, damageMultiplier > 1);
            return new EvaluationOutcome(EvaluationResult.CORRECT, damage, feedback);
        } else {
            if (errorBlocked) {
                String feedback = "Errou! Mas Rapunzel bloqueou o dano! " +
                                  "Resposta correta: " + question.getCorrectAnswer();
                return new EvaluationOutcome(EvaluationResult.INCORRECT, 0, feedback);
            } else {
                int penalty = question.getPenaltyValue();
                String feedback = "Resposta incorreta! Você perdeu " + penalty + " HP. " +
                                  "Resposta correta: " + question.getCorrectAnswer();
                return new EvaluationOutcome(EvaluationResult.INCORRECT, penalty, feedback);
            }
        }
    }

    private String buildCorrectFeedback(Question question, int damage, boolean doubled) {
        String base = "Correto! Causou " + damage + " de dano";
        if (doubled) base += " (DANO DUPLO!)";
        return base + "!";
    }
}