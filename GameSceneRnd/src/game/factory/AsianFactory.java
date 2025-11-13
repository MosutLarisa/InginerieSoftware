package game.factory;

import game.building.House;
import game.character.*;

public class AsianFactory {

    public House createHouse(int x, int y) {
        return new House(x, y, 2, 2, "Asian - Bamboo");
    }

    public Human createHuman(String name) {
        return new Human(name, 0, 0);
    }

    public Elf createElf(String name) {
        return new Elf(name, 0, 0);
    }

    public Dwarf createDwarf(String name) {
        return new Dwarf(name, 0, 0);
    }
}