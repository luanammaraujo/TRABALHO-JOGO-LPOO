package codearena.model.ability;

// Habilidade da Merida: próxima resposta correta causa dano dobrado

public class DoubleDamageAbility implements Ability {

    @Override
    public AbilityResult activate() {
        return new AbilityResult(
            AbilityResult.EffectType.EXTRA_DAMAGE,
            2,
            "Merida disparou uma flecha! Próximo acerto causa DANO DUPLO!"
        );
    }

    @Override
    public String getName() { return "Flecha Certeira"; }

    @Override
    public String getDescription() { return "Próxima resposta correta causa dano em dobro"; }

}