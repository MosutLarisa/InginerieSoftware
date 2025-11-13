package game.decorator;

import game.character.Character;

public class Strength extends Character {
    private Character character;
    private int power;

    public Strength(Character character, int power) {
        super(character.getName(), character.getX(), character.getY(), character.getAge(), character.getWidth(), character.getHeight());
        this.character = character;
        this.power = power;
    }

    public int getX() { return character.getX(); }
    public int getY() { return character.getY(); }
    public void move(int x, int y) { character.move(x, y); }
    public int getAge() { return character.getAge(); }
    public int getWidth() { return character.getWidth(); }
    public int getHeight() { return character.getHeight(); }

    public String getInfo() {
        return character.getInfo() + " +Strength(" + power + ")";
    }
}
