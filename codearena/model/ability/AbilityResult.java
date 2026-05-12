package codearena.model.ability;

/**
 * Representa o resultado de uma habilidade ativada.
 * Encapsula os efeitos da habilidade para ser processado pelo BattleManager.
 */
public class AbilityResult {

    public enum EffectType {
        HINT,           // Elimina alternativas erradas
        EXTRA_DAMAGE,   // Aplica dano extra no próximo acerto
        BLOCK_ERROR,    // Bloqueia uma penalidade por erro
        HEAL,           // Recupera HP
        NONE
    }

    private EffectType type;
    private int value;
    private String message;
    private String[] eliminatedOptions; // Para habilidade de dica

    public AbilityResult(EffectType type, int value, String message) {
        this.type = type;
        this.value = value;
        this.message = message;
    }

    public AbilityResult(EffectType type, int value, String message, String[] eliminatedOptions) {
        this(type, value, message);
        this.eliminatedOptions = eliminatedOptions;
    }

    public EffectType getType() { return type; }
    public int getValue() { return value; }
    public String getMessage() { return message; }
    public String[] getEliminatedOptions() { return eliminatedOptions; }
}