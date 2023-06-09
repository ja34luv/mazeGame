package group3.demonGame;

import java.awt.Image;

/**
 * image resource from
 * https://www.bing.com/images/search?view=detailV2&ccid=8ONaPRIK&id=9E904C9F463EEF473A7B9C95F3D877F5567EFDDC&thid=
 * OIP.8ONaPRIKofHQ3QxjHeCmyQAAAA&mediaurl=https%3A%2F%2Fimages-wixmp-ed30a86b8c4ca887773594c2.wixmp.com%
 * 2Ff%2F18527ba2-6cdb-42e8-b5bf-1a43b8ea5bd3%2Fdd9i6k4-9gm.squareSize77db9-9438-4da8-8b21-2ff540aca1bf.png%2Fv1%2Ffit%2Fw_1gm.squareSize%
 * 2Ch_1gm.squareSize%2Cstrp%2Ftactimon_sprite_by_hamurand22_dd9i6k4-1gm.squareSize.png%3Ftoken%3DeyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.ey
 * JzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MT
 * VlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9NDg4IiwicGF0aCI6IlwvZlwvMTg1MjdiYTItNmNkYi00MmU4LWI1YmYtMWE0M2I4ZWE1YmQzXC9kZ
 * DlpNms0LTk1MDc3ZGI5LTk0MzgtNGRhOC04YjIxLTJmZjU0MGFjYTFiZi5wbmciLCJ3aWR0aCI6Ijw9NDMyIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmltYWdl
 * Lm9wZXJhdGlvbnMiXX0.LDyhJdjNSdO95_XKO-a1P-ZLv1ZOkoXLMUyesm3asRo&exph=1gm.squareSize&expw=132&q=2d+hero&simid=607995712627485gm.squareSize9&form=IRPR
 * ST&ck=138EC4C6E0F72CB1D5C9EDF49F5F70CA&selectedindex=2&ajaxhist=0&ajaxserp=0&vt=0&sim=11&iss=VSI&cdnurl=https%3A%2F%2Fth.bing.co
 * m%2Fth%2Fid%2FR.f0e35a3d120aa1f1d0dd0c631de0a6c9%3Frik%3D3P1%252bVvV32POVnA%26pid%3DImgRaw%26r%3D0&pivotparams=insightsToken%3Dcci
 * d_YMTPuvqh*cp_48570C7B788D3DF7FD1795BC9EB32C51*mid_5FB80BE1EA7A4E013D8CD0769A4234E599DA5C9C*simid_607991945943396471*thid_OIP.YMTP
 * uvqhNxMF8fbuG6BPMAAAAA&ajaxhist=0&ajaxserp=0
 * 
 * @author zeyoup
 */

public class Demon extends GameObj {
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;

	/**
	 * Create gameObj demon by using default size setting(gm.squareSize w x
	 * gm.squareSize h). Set 3 in a cell that contain a demon.
	 * 
	 * @param x  X coordinate of demon
	 * @param y  Y coordinate of demon
	 * @param ig image of demon from Image repo
	 * @param h  height of demon image
	 * @param w  width of demon image
	 * @param gm GameManager
	 */

	public Demon(int x, int y, Image ig, GameManager gm) {
		super(x, y, ig, gm);
		gm.pf.set(x / gm.squareSize, y / gm.squareSize, gm.objList.size() - 1, 3);
	}

	public Demon(int x, int y, int w, int h, Image ig, GameManager gm) {
		super(x, y, w, h, ig, gm);
		gm.pf.set(x / gm.squareSize, y / gm.squareSize, gm.objList.size() - 1, 3);
	}

	/**
	 * Update demon movement to one cell.
	 * The movement can be up,down, left or right.
	 * When demon get a cell contain regularReward, get reward and remove
	 * regualrReward.
	 * When demon get a cell contain BonusReward, get reward and remove BonusReward.
	 * When demon get a cell contain Enemies, game over.
	 * When demon get a cell contain traps, get punishment and remove traps.
	 * When demon get a cell contain door, if get all regular, you pass the door and
	 * win.
	 */

	public void update() {
		if (up && gm.pf.canPath(x / gm.squareSize, y / gm.squareSize - 1)) {
			y -= gm.squareSize;
		}
		if (down && gm.pf.canPath(x / gm.squareSize, y / gm.squareSize + 1)) {
			y += gm.squareSize;
		}
		if (left && gm.pf.canPath(x / gm.squareSize - 1, y / gm.squareSize)) {
			x -= gm.squareSize;
		}
		if (right && gm.pf.canPath(x / gm.squareSize + 1, y / gm.squareSize)) {
			x += gm.squareSize;
		}
		// collision
		for (GameObj gameObj : gm.objList) {
			if (gameObj instanceof Demon)
				continue;

			if (gameObj instanceof RegularReward) {
				if ((gameObj.x == this.x) && (gameObj.y == this.y)) {
					RegularReward rR = (RegularReward) gameObj;
					rR.getReward();
					gm.objList.remove(gameObj);
					break;
				}
			} else if (gameObj instanceof BonusReward) {
				if ((gameObj.x == this.x) && (gameObj.y == this.y)) {
					BonusReward bR = (BonusReward) gameObj;
					bR.getReward();
					gm.objList.remove(gameObj);
					break;
				}
			} else if (gameObj instanceof Enemies) {
				if ((gameObj.x == this.x) && (gameObj.y == this.y)) {
					gm.win = false;
					gm.gameDone = true;
				}
			} else if (gameObj instanceof Trap) {
				if ((gameObj.x == this.x) && (gameObj.y == this.y)) {
					Trap tr = (Trap) gameObj;
					tr.trapCatch();
					gm.objList.remove(gameObj);
					break;
				}
			} else if (gameObj instanceof Door) {
				if ((gameObj.x == this.x) && (gameObj.y == this.y)) {

					boolean hasAllRegular = false;
					for (GameObj gameObj1 : gm.objList) {
						if (gameObj1 instanceof RegularReward) {
							hasAllRegular = true;
							break;
						}
					}
					if (!hasAllRegular) {
						gm.win = true;
						gm.gameDone = true;
					}
				}
			}
		}

	}

	/**
	 * Set keyboard listener for demon movement. It is true that demon move up or
	 * down or left or right if a keyboard kc is pressed.
	 * 
	 * @param kc a int number map to W A S D on keyboard when Judge the movement
	 *           direction.
	 */

	public void move(int kc) {
		if (kc == 87) { // W
			up = true;
		} else if (kc == 83) { // A
			down = true;
		} else if (kc == 65) { // S
			left = true;
		} else if (kc == 68) { // D
			right = true;
		}
	}

	/**
	 * Set keyboard listener for demon movement. It is false that demon move up or
	 * down or left or right if a keyboard kc is released.
	 * 
	 * @param kc a int number map to W A S D on keyboard when Judge the movement
	 *           direction
	 *
	 */

	public void release(int kc) {
		if (kc == 87) { // W
			up = false;
		} else if (kc == 83) { // A
			down = false;
		} else if (kc == 65) { // S
			left = false;
		} else if (kc == 68) { // D
			right = false;
		}
	}

}