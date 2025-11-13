package game.builder;

import game.board.GameBoard;
import game.building.House;

public class SceneBuilder {
    private GameBoard board;

    public SceneBuilder() {
        board = GameBoard.getInstance(0, 0);
    }

    public void buildScene(House house1, House house2, House house3) {
        board.addHouse(house1);
        board.addHouse(house2);
        board.addHouse(house3);
    }
}