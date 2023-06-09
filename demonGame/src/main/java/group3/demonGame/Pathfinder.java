package group3.demonGame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * 
 * @author zeyoup
 *
 */
public class Pathfinder {
	/**
	 * Create a bitmap for game map. It can find the coordinate or path for gameObj
	 * easily. Set 1 in each cell in bitmap.
	 */
	GameManager gm;
	public int bitmap[][];

	public Pathfinder(GameManager manager) {
		this.gm = manager;
		bitmap = new int[gm.windowHeight / gm.squareSize][gm.windowWidth / gm.squareSize];

		for (int i = 0; i < gm.windowHeight / gm.squareSize; i++) {
			for (int j = 0; j < gm.windowWidth / gm.squareSize; j++) {
				bitmap[i][j] = 1;
			}
		}
	}

	/**
	 * set flag for gameobj to judge collision easily.
	 * 
	 * @param x   X coordinate of gameobj
	 * @param y   Y coordinate of gameobj
	 * @param loc which gameobj from arraylist
	 * @param hd  a flag to judge collision
	 */

	public void set(int x, int y, int loc, int hd) {
		bitmap[y][x] = hd;

	}

	/**
	 * The method create a condition for path. If a cell contain 2, you cannot path
	 * the cell. Only path one cell for appropriate cell size
	 * 
	 * @param X coordinate of gameobj
	 * @param y Y coordinate of gameobj
	 * @return true if can path, false can't path
	 */

	public boolean canPath(int x, int y) {
		if (!(0 <= x && x < gm.windowWidth / gm.squareSize && 0 <= y && y < gm.windowHeight / gm.squareSize)) {
			return false;
		}
		if (bitmap[y][x] % 10 == 2) {
			return false;
		}
		return true;

	}

	/**
	 * find a path between demon and enemies, enemies will move approach to demon
	 * when in range.
	 * 
	 * @author zeyoup
	 * @param x    X coordinate of character
	 * @param y    Y coordinate of character
	 * @param c    1 current cost for path per cell
	 * @param e    except cost for path between characters
	 * @param hl   false
	 * @param cost total cost for path movement
	 */

	class queueEle {
		public int x;
		public int y;
		public int c;
		public int e;
		public boolean hl;
		public int cost = 0;

		public queueEle() {
			this.x = 0;
			this.y = 0;
			this.c = 0;
			this.e = 0;
			hl = false;
		}

		public queueEle(int x, int y, int c, int e) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.e = e;
			hl = false;
		}
	}

	static Comparator<queueEle> eleCmp = new Comparator<queueEle>() {
		@Override
		public int compare(queueEle o1, queueEle o2) {
			return (o1.c + o1.e) - (o2.c + o2.e);
		}
	};

	public boolean inRange(int x, int y) {
		if (0 <= x && x < gm.windowWidth / gm.squareSize && 0 <= y && y < gm.windowHeight / gm.squareSize)
			return true;
		return false;
	}

	public ArrayList<queueEle> getPath(int x, int y) {
		ArrayList<queueEle> path = new ArrayList<>();
		queueEle[][] tempMap = new queueEle[gm.windowHeight / gm.squareSize][gm.windowWidth / gm.squareSize];
		for (int i = 0; i < gm.windowHeight / gm.squareSize; i++) {
			for (int j = 0; j < gm.windowWidth / gm.squareSize; j++) {
				tempMap[i][j] = new queueEle();
				if (bitmap[i][j] % 10 == 2) {
					tempMap[i][j].cost = gm.squareSize;
				}
			}
		}
		/**
		 * find the smallest number in the cell around the given coordinate for demon cell(must !=0), the cell contain the number create a path.
		 * 
		 *@param dx X coordinate demon 
		 *@param dy Y coordinate of demon
		 **/
		PriorityQueue<queueEle> q = new PriorityQueue<>(eleCmp);
		int dx = gm.dm.x / gm.squareSize;
		int dy = gm.dm.y / gm.squareSize;
		int sx = x / gm.squareSize;
		int sy = y / gm.squareSize;
		q.add(new queueEle(sx, sy, 0, Math.abs(sx - dx) + Math.abs(sy - dy)));
		while (!q.isEmpty()) {
			queueEle temp = q.poll();
			if (temp.x == dx && temp.y == dy) {

				int total = temp.c;
				tempMap[temp.y][temp.x] = temp;
				tempMap[temp.y][temp.x].hl = true;
				for (int i = 0; i < total; i++) {
					path.add(temp);
					if (inRange(temp.x + 1, temp.y) && tempMap[temp.y][temp.x + 1].c == total - i - 1) {
						temp = tempMap[temp.y][temp.x + 1];
					} else if (inRange(temp.x - 1, temp.y) && tempMap[temp.y][temp.x - 1].c == total - i - 1) {
						temp = tempMap[temp.y][temp.x - 1];
					} else if (inRange(temp.x, temp.y + 1) && tempMap[temp.y + 1][temp.x].c == total - i - 1) {
						temp = tempMap[temp.y + 1][temp.x];
					} else if (inRange(temp.x, temp.y - 1) && tempMap[temp.y - 1][temp.x].c == total - i - 1) {
						temp = tempMap[temp.y - 1][temp.x];
					}

				}

				break;
			}
			if (!(0 <= temp.x && temp.x < gm.windowWidth / gm.squareSize && 0 <= temp.y
					&& temp.y < gm.windowHeight / gm.squareSize)
					|| tempMap[temp.y][temp.x].hl) {
				continue;
			}
			tempMap[temp.y][temp.x] = temp;
			tempMap[temp.y][temp.x].hl = true;

			if (inRange(temp.x + 1, temp.y) && !tempMap[temp.y][temp.x + 1].hl)
				q.add(new queueEle(temp.x + 1, temp.y, temp.c + 1 + tempMap[temp.y][temp.x + 1].cost,
						Math.abs(temp.x + 1 - dx) + Math.abs(sy - temp.y)));
			if (inRange(temp.x - 1, temp.y) && !tempMap[temp.y][temp.x - 1].hl)
				q.add(new queueEle(temp.x - 1, temp.y, temp.c + 1 + tempMap[temp.y][temp.x - 1].cost,
						Math.abs(temp.x - 1 - dx) + Math.abs(sy - temp.y)));
			if (inRange(temp.x, temp.y + 1) && !tempMap[temp.y + 1][temp.x].hl)
				q.add(new queueEle(temp.x, temp.y + 1, temp.c + 1 + tempMap[temp.y + 1][temp.x].cost,
						Math.abs(temp.x - dx) + Math.abs(sy - temp.y + 1)));
			if (inRange(temp.x, temp.y - 1) && !tempMap[temp.y - 1][temp.x].hl)
				q.add(new queueEle(temp.x, temp.y - 1, temp.c + 1 + tempMap[temp.y - 1][temp.x].cost,
						Math.abs(temp.x - dx) + Math.abs(sy - temp.y - 1)));
		}
		return path;
	}
}
