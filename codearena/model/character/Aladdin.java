package codearena.model.character;

import codearena.model.ability.Ability;
import codearena.model.ability.HintAbility;

/**
 * Personagem Alladin: equilibrado com habilidade de dica.
 * Herda de Character.
 */
public class Aladdin extends Character {

    public Aladdin() {
        super(
            "Alladin",
            100,    // HP
            20,     // Ataque
            10,     // Defesa
            "O jovem de rua esperto que sabe usar a inteligência para vencer!"
        );
    }

    @Override
    public Ability getSpecialAbility() {
        return new HintAbility();
    }

    @Override
    public String getAbilityDescription() {
        return "Tapete Mágico: Elimina 2 alternativas erradas";
    }
}