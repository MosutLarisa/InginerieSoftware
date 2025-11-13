package game.character;

import game.board.GameObject;

public abstract class Character implements GameObject {
    protected String name;
    protected int x, y;
    protected int age;
    protected int width, height;

    public Character(String name, int x, int y, int age, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.age = age;
        this.width = width;
        this.height = height;
    }

    public String getName() { return name; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getAge() { return age; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public abstract String getInfo();
}