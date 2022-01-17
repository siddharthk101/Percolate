import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast {
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationBFS(int n) {
        super(n);
    }
    @Override
    protected void dfs(int row, int col){
        //out of bounds?
        if (! inBounds(row,col)) return;

        Queue<int[]> qp = new LinkedList<>();
        myGrid[row][col] = FULL;
        qp.add(new int[]{row,col});
        while (qp.size() != 0){
            int[] p = qp.remove();
            int r = p[0];
            int c = p[1];
            if(inBounds(r-1,c) && isOpen(r-1,c) && !isFull(r-1,c)){
                myGrid[r-1][c] = FULL;
                qp.add(new int[] {r-1,c});
            }
            if(inBounds(r+1,c) && isOpen(r+1,c) && !isFull(r+1,c)){
                myGrid[r+1][c] = FULL;
                qp.add(new int[] {r+1,c});
            }
            if(inBounds(r,c+1) && isOpen(r,c+1) && !isFull(r,c+1)){
                myGrid[r][c+1] = FULL;
                qp.add(new int[] {r,c+1});
            }
            if(inBounds(r,c-1) && isOpen(r,c-1) && !isFull(r,c-1)){
                myGrid[r][c-1] = FULL;
                qp.add(new int[] {r,c-1});
            }
        }


    }
}
