package group3.demonGame;

import java.awt.Graphics2D;

/**
 * 
 * Used to keep track of player's score
 * and display it onto game screen.
 * 
 * @author myw11
 *
 */
public class Score {
	GameManager gm;
	private int score;

	public Score(GameManager manager) {
		this.gm = manager;
		this.score = 0;
	}

	/**
	 * get the score value
	 * 
	 * @return the player's current score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * add to the score's value
	 * 
	 * @param the value desired to increase the score by
	 */
	public void changeScore(int value) {
		score += value;
		if (score < 0) {
			this.gm.win = false;
			gm.gameDone = true;
		}
	}

	/**
	 * display the score on game screen (UI)
	 * 
	 * @param g
	 */
	public void draw(Graphics2D g) {
		if (!gm.gameDone) {
			g.drawString("Score:" + this.score, 200, 50); // y indicates baseline of text!!
		}
	}

}
