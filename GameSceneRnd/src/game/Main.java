package game;

import game.board.GameBoard;
import game.builder.SceneBuilder;
import game.character.Character;
import game.command.MoveCommand;
import game.decorator.Strength;
import game.factory.*;
import game.group.Group;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Character> chars = new ArrayList<>();

        int boardWidth = 20;
        int boardHeight = 15;
        System.out.println("Creating board: " + boardWidth + " x " + boardHeight);
        GameBoard board = GameBoard.getInstance(boardWidth, boardHeight);

        System.out.println("European Scene");
        EuropeanFactory euroFactory = new EuropeanFactory();
        SceneBuilder builder = new SceneBuilder();
        builder.buildScene(
                euroFactory.createHouse(2, 4),
                euroFactory.createHouse(5, 4),
                euroFactory.createHouse(8, 4)
        );
        board.render(chars);

        Character human = euroFactory.createHuman("Human");
        Character elf = euroFactory.createElf("Elf");
        Character dwarf = euroFactory.createDwarf("Dwarf");

        human = new Strength(human, 50);
        chars.add(human);
        chars.add(elf);
        chars.add(dwarf);
        board.render(chars);

        System.out.println("Creating group:");
        Group group = new Group("Group");
        group.add(human);
        group.add(elf);
        group.add(dwarf);
        group.show();

        System.out.println("Moving Characters ");
        MoveCommand m1 = new MoveCommand(human, 1, 2);
        m1.execute();
        MoveCommand m2 = new MoveCommand(elf, 10, 3);
        m2.execute();
        MoveCommand m3 = new MoveCommand(dwarf, 2, 4);
        m3.execute();
        MoveCommand m4 = new MoveCommand(dwarf, 6, 10);
        m4.execute();

        m4.undo();

        group.moveAll(12, 8);

        System.out.println("Asian Scene");
        board.clearHouses();
        AsianFactory asianFactory = new AsianFactory();
        builder.buildScene(
                asianFactory.createHouse(3, 3),
                asianFactory.createHouse(7, 3),
                asianFactory.createHouse(11, 3)
        );
        board.render(chars);

        System.out.println("African Scene");
        board.clearHouses();
        AfricanFactory africanFactory = new AfricanFactory();
        builder.buildScene(
                africanFactory.createHouse(2, 5),
                africanFactory.createHouse(6, 5),
                africanFactory.createHouse(10, 5)
        );
        board.render(chars);

    }
}