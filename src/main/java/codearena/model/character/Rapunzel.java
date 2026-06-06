package codearena.model.character;

import codearena.model.ability.Ability;
import codearena.model.ability.ShieldAbility;

public class Rapunzel extends Character {

    private final Ability ability;

    public Rapunzel() {
        super(
            "Rapunzel",
            120,
            15,
            15,
            "A princesa da torre que usa seus longos cabelos magicos como escudo!"
        );
        this.ability = new ShieldAbility();
    }

    @Override
    public Ability getSpecialAbility() {
        return ability;
    }

    @Override
    public String getAbilityDescription() {
        return "Cabelos Magicos: Bloqueia a penalidade do proximo erro";
    }
}