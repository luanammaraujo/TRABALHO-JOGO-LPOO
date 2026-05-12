package codearena.model.character;

import codearena.model.ability.Ability;
import codearena.model.ability.ShieldAbility;

/**
 * Personagem Rapunzel: tanque defensivo, bloqueia erros.
 */
public class Rapunzel extends Character {

    public Rapunzel() {
        super(
            "Rapunzel",
            120,    // HP (maior!)
            15,     // Ataque (menor)
            15,     // Defesa (alta!)
            "A princesa da torre que usa seus longos cabelos mágicos como escudo!"
           
        );
    }

    @Override
    public Ability getSpecialAbility() {
        return new ShieldAbility();
    }

    @Override
    public String getAbilityDescription() {
        return "Cabelos Mágicos: Bloqueia a penalidade do próximo erro";
    }
}