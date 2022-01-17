public class PercolationDFSFast extends PercolationDFS{
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationDFSFast(int n) {
        super(n);
    }

    @Override
    public void updateOnOpen(int row, int col){
        if (! inBounds(row,col)) return;
        if(row == 0){
            dfs(row, col);
        }
        else if (myGrid[row - 1][col] == FULL){
            dfs(row, col);
        }
        else if (row != myGrid[row].length - 1 && myGrid[row+1][col] == FULL){
            dfs(row, col);
        }
        else if (col != 0 && myGrid[row][col - 1] == FULL){
            dfs(row, col);
        }
        else if (col != myGrid[col].length - 1 && myGrid[row][col + 1] == FULL){
            dfs(row, col);
        }
    }
}
