public class PercolationUF implements IPercolate{
    private boolean[][] myGrid;
    private int myOpenCount;
    private IUnionFind myFinder;
    private final int VTOP;
    private final int VBOTTOM;

    public PercolationUF(IUnionFind finder, int size) {
        finder.initialize(size*size+2);
        myFinder = finder;
        VTOP = size*size;
        VBOTTOM = size*size+1;
        myOpenCount = 0;
        myGrid = new boolean[size][size];
    }

    //same method pulled from Percolation DFS
    protected boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[0].length) return false;
        return true;
    }

    @Override
    public void open(int row, int col) {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        int val = row*myGrid.length+col;
        if(!isOpen(row,col)){

            myGrid[row][col] = true;
            if(inBounds(row-1,col) && isOpen(row-1,col)){
                myFinder.union(val, (row-1)*myGrid.length+col);
            }
            if(inBounds(row+1,col) && isOpen(row+1,col)){
                myFinder.union(val, (row+1)*myGrid.length+col);
            }
            if(inBounds(row,col-1) && isOpen(row,col-1)){
                myFinder.union(val, (row)*myGrid.length+col-1);
            }
            if(inBounds(row,col+1) && isOpen(row,col+1)){
                myFinder.union(val, (row)*myGrid.length+col+1);
            }
            myOpenCount++;
        }
        if(row == 0) myFinder.union(val, VTOP);
        if(row == myGrid.length - 1) myFinder.union(val, VBOTTOM);

    }

    @Override
    public boolean isOpen(int row, int col) {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        int val = row*myGrid.length+col;

        return myFinder.connected(val, VTOP);
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }
}
