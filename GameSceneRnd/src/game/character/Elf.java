package game.character;

public class Elf extends Character {
    public Elf(String name, int x, int y) {
        super(name, x, y, 150, 1, 1);  
    }

    public String getInfo() {
        return "Elf age:" + age + " size:" + width + "x" + height;
    }

}
