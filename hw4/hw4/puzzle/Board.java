package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {

    private static final int BLANK = 0;
    private int N;
    private int[][] tiles;

    /** Constructs a board from an N-by-N array of tiles where
        tiles[i][j] = tile at row i, column j */
    public Board(int[][] tiles) {
        this.N = tiles.length;
        this.tiles = new int[N][N];
        for (int row = 0; row < N; row += 1) {
            for (int col = 0; col < N; col += 1) {
                this.tiles[row][col] = tiles[row][col];
            }
        }
    }

    /** Returns value of tile at row i, column j (or 0 if blank) */
    public int tileAt(int i, int j) {
        if (i < 0 || j < 0 || i > N - 1 || j > N - 1) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    /** Returns the board size N */
    public int size() {
        return N;
    }

    /** @source http://joshh.ug/neighbors.html */
    /** Returns the neighbors of the current board */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /** Hamming estimate described below */
    public int hamming() {
        int esd = 0;
        int val = 1;
        for (int row = 0; row < N; row += 1) {
            for (int col = 0; col < N; col += 1) {
                if (tileAt(row, col) != BLANK) {
                    if (tileAt(row, col) != val) {
                        esd += 1;
                    }
                }
                val += 1;
            }
        }
        return esd;
    }

    /** Manhattan estimate described below */
    public int manhattan() {
        int esd = 0;
        for (int row = 0; row < N; row += 1) {
            for (int col = 0; col < N; col += 1) {
                int val = tileAt(row, col);
                if (val == 0) {
                    continue;
                }
                int esx = Math.abs((val - 1) / N - row);
                int esy = Math.abs((val - 1) % N - col);
                esd += (esx + esy);
            }
        }
        return esd;
    }

    /** Estimated distance to goal. This method should
        simply return the results of manhattan() when submitted to
        Gradescope. */
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /** Returns true if this board's tile values are the same
        position as y's */
    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }
        Board board = (Board) y;
        if (this.N != board.N) {
            return false;
        }
        for (int row = 0; row < N; row += 1) {
            for (int col = 0; col < N; col += 1) {
                if (this.tiles[row][col] != board.tiles[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Suggesting that we have overrided equals() method ... */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int n = size(); // prevent using 'N' that hides a field [HiddenField]
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
