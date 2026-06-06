package codearena.model.character;

import codearena.model.ability.Ability;
import codearena.model.ability.HintAbility;

public class Aladdin extends Character {

    private final Ability ability;

    public Aladdin() {
        super(
            "Alladin",
            100,
            20,
            10,
            "O jovem de rua esperto que sabe usar a inteligencia para vencer!"
        );
        this.ability = new HintAbility();
    }

    @Override
    public Ability getSpecialAbility() {
        return ability;
    }

    @Override
    public String getAbilityDescription() {
        return "Tapete Magico: Elimina 2 alternativas erradas";
    }
}