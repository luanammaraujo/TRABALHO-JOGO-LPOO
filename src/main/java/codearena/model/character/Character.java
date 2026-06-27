package codearena.model.character;

import codearena.model.ability.Ability;

/**
 * Classe abstrata base para personagens do jogo.
 * Implementa encapsulamento e serve como base para herança.
 */
public abstract class Character {

    private String name;
    private int maxHp;
    private int currentHp;
    private int defense;
    private String description;
    

    public Character(String name, int maxHp, int defense, String description) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.defense = defense;
        this.description = description;
    }

    // Define a habilidade especial do personagem
    public abstract Ability getSpecialAbility();

    //Descrição da habilidade
    public abstract String getAbilityDescription();

    public void takeDamage(int damage) {
        int actualDamage = damage;
        this.currentHp = Math.max(0, this.currentHp - actualDamage);
    }

    public void heal(int amount) {
        this.currentHp = Math.min(maxHp, this.currentHp + amount);
    }

    public boolean isAlive() {
        return currentHp > 0;
    }

    public double getHpPercentage() {
        return (double) currentHp / maxHp * 100;
    }

    public void resetHp() {
        this.currentHp = this.maxHp;
    }

    public String getName() { return name; }
    public int getMaxHp() { return maxHp; }
    public int getCurrentHp() { return currentHp; }
    public int getDefense() { return defense; }
    public String getDescription() { return description; }


    @Override
    public String toString() {
        return String.format("%s [HP: %d/%d | DEF: %d]",
                name, currentHp, maxHp, defense);
    }
}