package game.character;

public class Dwarf extends Character {
    public Dwarf(String name, int x, int y) {
        super(name, x, y, 80, 1, 1);  // age=80, size=1x1
    }

    public String getInfo() {
        return "Dwarf age:" + age + " size:" + width + "x" + height;
    }
}