package codearena.model.character;

import codearena.model.ability.Ability;
import codearena.model.ability.AbilityResult;

//Representa os inimigos do jogo
//Herda de Character com comportamento controlado pelo sistema

public class Enemy extends Character {

    private String phase;
    private String taunt;   

    public Enemy(String name, int maxHp, int defense,
                 String description, String phase, String taunt) {
        super(name, maxHp, defense, description);
        this.phase = phase;
        this.taunt = taunt;
    }

    @Override
    public Ability getSpecialAbility() {
        // Inimigos não possuem habilidade especial no MVP
        return new Ability() {
            @Override
            public AbilityResult activate() {
                return new AbilityResult(AbilityResult.EffectType.NONE, 0, "");
            }
            @Override public String getName() { return "Sem habilidade"; }
            @Override public String getDescription() { return ""; }
        };
    }

    @Override
    public String getAbilityDescription() { return ""; }

    public String getPhase() { return phase; }
    public String getTaunt() { return taunt; }

    // Factory methods para criar inimigos predefinidos
    public static Enemy createRainhaMa() {
        return new Enemy(
            "Rainha Má",
            80,
            5,
            "A vaidade é minha maior força! Você não conseguirá me derrotar!",
        
            "FACIL",
            "Espelho, espelho meu... quem vai perder hoje? Você!"
        );
    }

    public static Enemy createScar() {
        return new Enemy(
            "Scar",
            80,
            8,
            "Lembre-se de quem é o rei! A inteligência é minha arma.",
           
            "MEDIO",
            "Que tragédia... você realmente achou que sabia a resposta!"
        );
    }

    public static Enemy createMalévola() {
        return new Enemy(
            "Malévola",
            80,
            12,
            "As fadas boas não têm nada sobre mim. Eu sou o mal perfeito!",
            
            "DIFICIL",
            "Sonho lindo... mas agora é hora de dormir para sempre!"
        );
    }
}