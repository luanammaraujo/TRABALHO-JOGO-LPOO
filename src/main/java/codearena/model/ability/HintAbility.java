package codearena.model.ability;

//Habilidade do Alladin: elimina duas alternativas erradas da questão atual

public class HintAbility implements Ability {

    @Override
    public AbilityResult activate() {
        return new AbilityResult(
            AbilityResult.EffectType.HINT,
            2,
            " Alladin usou o Tapete Magico!"
        );
    }

    @Override
    public String getName() { return "Tapete Mágico"; }

    @Override
    public String getDescription() { return "Da uma dica para ajudar na pergunta atual"; }


}