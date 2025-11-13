package game.building;

import game.board.GameObject;

public class House implements GameObject {
    private int x, y;
    private int width, height;
    private String type;

    public House(int x, int y, int width, int height, String type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public String getType() { return type; }
}