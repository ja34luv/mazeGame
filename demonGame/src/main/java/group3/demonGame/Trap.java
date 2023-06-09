package group3.demonGame;

import java.awt.*;

/**
 * create gameobj traps with a default size(50w x 50 h). When demon get traps,
 * lose 110 marks. If mark<0, game over.
 * 
 * @param x  X coordinate of traps
 * @param y  Y coordinate of traps
 * @param ig traps image
 * @param gm gamemanager
 * @author zeyoup
 */

public class Trap extends GameObj {
    private Score score;
    private int points = -110;

    public Trap(int x, int y, Image ig, GameManager gm, Score score) {
        super(x, y, ig, gm);
        this.score = score;
    }
    
    public int getPoints() {
        return this.points;
    }

    public void trapCatch() {
        score.changeScore(points);

    }
}
