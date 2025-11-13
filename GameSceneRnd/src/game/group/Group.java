package game.group;

import game.character.Character;
import java.util.ArrayList;

public class Group {
    private String name;
    private ArrayList<Character> members;

    public Group(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }
    public void add(Character c) {
        members.add(c);
    }

    public void show() {
        System.out.println("Group: " + name);
        for (Character c : members) {
            System.out.println("- " + c.getName() + " (size: " + c.getWidth() + "x" + c.getHeight() + ")");
        }
    }

    public void moveAll(int newX, int newY) {
        System.out.println("Group'" + name + "' moved to (" + newX + ", " + newY + ")");
        for (Character c : members) {
            c.move(newX, newY);
        }
    }
}
