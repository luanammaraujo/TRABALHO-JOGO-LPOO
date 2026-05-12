package codearena.model.character;

import codearena.model.ability.Ability;
import codearena.model.ability.DoubleDamageAbility;

/**
 * Personagem Merida: especialista em ataque, com defesa reduzida.
 * Habilidade: dano duplo no próximo acerto.
 */
public class Merida extends Character {

    public Merida() {
        super(
            "Merida",
            85,     // HP 
            30,     // Ataque 
            5,      // Defesa
            "A corajosa arqueira que dispara com precisão e força devastadora!"
        );
    }

    @Override
    public Ability getSpecialAbility() {
        return new DoubleDamageAbility();
    }

    @Override
    public String getAbilityDescription() {
        return "Flecha Certeira: Próxima resposta causa dano duplo";
    }
}