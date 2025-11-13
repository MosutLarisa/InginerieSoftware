package game.board;

import game.building.House;
import game.character.Character;
import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private static GameBoard instance;
    private int width, height;
    private List<House> houses = new ArrayList<>();

    private GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public static GameBoard getInstance(int width, int height) {
        if(instance == null) {
            instance = new GameBoard(width, height);
        }
        return instance;
    }
    public void addHouse(House h) {
        houses.add(h);
    }
    public void clearHouses() {
        houses.clear();
    }

    public boolean canMove(int x, int y, int width, int height) {
        if (x < 0 || y < 0 || x + width > this.width || y + height > this.height) {
            return false;
        }
        for(House h : houses) {
            if(collides(x, y, width, height, h.getX(), h.getY(), h.getWidth(), h.getHeight())) {
                return false;
            }
        }
        return true;
    }
    private boolean collides(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        return !(x1 + w1 <= x2 || x2 + w2 <= x1 || y1 + h1 <= y2 || y2 + h2 <= y1);
    }

    public void render(List<Character> chars) {
        System.out.println("\nGame Board");
        System.out.println("Board Size: " + width + " x " + height);
        System.out.println("Houses:");
        for (House h : houses) {
            System.out.println(h.getType() + " at position (" + h.getX() + "," + h.getY() +
                    ") size: " + h.getWidth() + "x" + h.getHeight());
        }
        System.out.println("Characters:");
        for (Character c : chars) {
            System.out.println(c.getName() + " at position (" + c.getX() + "," + c.getY() +
                    ") size: " + c.getWidth() + "x" + c.getHeight() + " " + c.getInfo());
        }
        System.out.println("\n");
    }
}