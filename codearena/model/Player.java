package codearena.model;

import codearena.model.character.Character;

//Estado do jogador durante a partida.

public class Player {

    private String name;
    private Character character;
    private int score;
    private int correctAnswers;
    private int wrongAnswers;
    private int abilitiesUsed;
    private boolean abilityAvailable;

    // Estados de habilidade ativa
    private boolean damageMultiplierActive;
    private boolean errorBlockActive;
    private int damageMultiplier;

    public Player(String name, Character character) {
        this.name = name;
        this.character = character;
        this.score = 0;
        this.correctAnswers = 0;
        this.wrongAnswers = 0;
        this.abilitiesUsed = 0;
        this.abilityAvailable = true;
        this.damageMultiplierActive = false;
        this.errorBlockActive = false;
        this.damageMultiplier = 1;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void recordCorrectAnswer() {
        correctAnswers++;
        addScore(100);
    }

    public void recordWrongAnswer() {
        wrongAnswers++;
    }

    public void useAbility() {
        this.abilityAvailable = false;
        this.abilitiesUsed++;
    }

    public void resetAbilityForPhase() {
        this.abilityAvailable = true;
        this.damageMultiplierActive = false;
        this.errorBlockActive = false;
        this.damageMultiplier = 1;
    }

    public void activateDamageMultiplier(int multiplier) {
        this.damageMultiplierActive = true;
        this.damageMultiplier = multiplier;
    }

    public void consumeDamageMultiplier() {
        this.damageMultiplierActive = false;
        this.damageMultiplier = 1;
    }

    public void activateErrorBlock() {
        this.errorBlockActive = true;
    }

    public void consumeErrorBlock() {
        this.errorBlockActive = false;
    }

    // Getters
    public String getName() { return name; }
    public Character getCharacter() { return character; }
    public int getScore() { return score; }
    public int getCorrectAnswers() { return correctAnswers; }
    public int getWrongAnswers() { return wrongAnswers; }
    public int getAbilitiesUsed() { return abilitiesUsed; }
    public boolean isAbilityAvailable() { return abilityAvailable; }
    public boolean isDamageMultiplierActive() { return damageMultiplierActive; }
    public boolean isErrorBlockActive() { return errorBlockActive; }
    public int getDamageMultiplier() { return damageMultiplier; }

    @Override
    public String toString() {
        return String.format("Jogador: %s | Personagem: %s | Pontos: %d",
                name, character.getName(), score);
    }
}