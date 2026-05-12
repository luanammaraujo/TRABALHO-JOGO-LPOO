package codearena.model.ability;

//Habilidade da Rapunzel: bloqueia a penalidade do próximo erro.

public class ShieldAbility implements Ability {

    @Override
    public AbilityResult activate() {
        return new AbilityResult(
            AbilityResult.EffectType.BLOCK_ERROR,
            1,
            "Rapunzel usou seus cabelos mágicos! O próximo erro não causará dano!"
        );
    }

    @Override
    public String getName() { return "Cabelos Mágicos"; }

    @Override
    public String getDescription() { return "Bloqueia a penalidade do próximo erro cometido"; }

}