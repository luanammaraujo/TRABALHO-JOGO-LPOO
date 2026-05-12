package codearena.model.ability;

//Habilidade do Alladin: elimina duas alternativas erradas da questão atual

public class HintAbility implements Ability {

    @Override
    public AbilityResult activate() {
        return new AbilityResult(
            AbilityResult.EffectType.HINT,
            2,
            " Alladin usou o Tapete Mágico! Duas alternativas erradas foram eliminadas!"
        );
    }

    @Override
    public String getName() { return "Tapete Mágico"; }

    @Override
    public String getDescription() { return "Elimina 2 alternativas incorretas da pergunta atual"; }


}