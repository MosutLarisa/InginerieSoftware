package game.command;

import game.board.GameBoard;
import game.character.Character;

public class MoveCommand {
    private Character character;
    private int oldX, oldY, newX, newY;
    private boolean done = false;

    public MoveCommand(Character c, int x, int y) {
        this.character = c;
        this.oldX = c.getX();
        this.oldY = c.getY();
        this.newX = x;
        this.newY = y;
    }

    public void execute() {
        GameBoard board = GameBoard.getInstance(0, 0);
        if (board.canMove(newX, newY, character.getWidth(), character.getHeight())) {
            character.move(newX, newY);
            done = true;
            System.out.println("✓ " + character.getName() + " moved to (" + newX + "," + newY + ")");
        } else {
            System.out.println("✗ Cannot move " + character.getName() + " to (" + newX + "," + newY + ")");
        }
    }
    public void undo() {
        if (done) {
            character.move(oldX, oldY);
            System.out.println(character.getName() + " returned to (" + oldX + "," + oldY + ")");
        }
    }
}