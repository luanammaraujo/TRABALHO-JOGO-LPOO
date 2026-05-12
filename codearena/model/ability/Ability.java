package codearena.model.ability;

/**
 * Interface que define o contrato para todas as habilidades especiais.
 * Demonstra o uso de interfaces em OO.
 */
public interface Ability {

    /**
     * Ativa a habilidade especial do personagem.
     * @return AbilityResult contendo os efeitos da habilidade
     */
    AbilityResult activate();

    /**
     * @return nome da habilidade
     */
    String getName();

    /**
     * @return descrição do que a habilidade faz
     */
    String getDescription();

}