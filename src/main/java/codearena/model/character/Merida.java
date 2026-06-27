package codearena.model.character;

import codearena.model.ability.Ability;
import codearena.model.ability.DoubleDamageAbility;

public class Merida extends Character {

    private final Ability ability;

    public Merida() {
        super(
            "Merida",
            85,
            5,
            "A corajosa arqueira que dispara com precisao e forca devastadora!"
        );
        this.ability = new DoubleDamageAbility();
    }

    @Override
    public Ability getSpecialAbility() {
        return ability;
    }

    @Override
    public String getAbilityDescription() {
        return "Flecha Certeira: Proxima resposta causa dano duplo";
    }
}