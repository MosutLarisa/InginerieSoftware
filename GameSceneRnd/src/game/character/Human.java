package game.character;

public class Human extends Character {
    public Human(String name, int x, int y) {
        super(name, x, y, 25, 1, 1);  
    }

    public String getInfo() {
        return "Human age:" + age + " size:" + width + "x" + height;
    }

}
