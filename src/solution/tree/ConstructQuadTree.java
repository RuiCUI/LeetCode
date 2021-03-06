package solution.tree;

/**
 * @author By RuiCUI
 * 2018-08-16
 * Easy
 * Question 427:Construct Quad Tree. (四叉树)
 * We want to use quad trees to store an N x N boolean grid. Each cell in the grid can only be true or false. 
 * The root node represents the whole grid. 
 * For each node, it will be subdivided into four children nodes until the values in the region it represents are all the same.
 * Each node has another two boolean attributes : isLeaf and val. isLeaf is true if and only if the node is a leaf node. 
 * The val attribute for a leaf node contains the value of the region it represents.
 * Your task is to use a quad tree to represent a given grid. The following example may help you understand the problem better:
 * Given the 8 x 8 grid below, we want to construct the corresponding quad tree:
 * It can be divided according to the definition above:
 * The corresponding quad tree should be as following, where each node is represented as a (isLeaf, val) pair.
 * For the non-leaf nodes, val can be arbitrary, so it is represented as *.
 * Note:
 * N is less than 1000 and guaranteened to be a power of 2.
 * If you want to know more about the quad tree, you can refer to its wiki.
 */
public class ConstructQuadTree {

	/**
	 * 我自己写的方法,参考的别人的答案
	 * 时间复杂度：O(n^2)
	 * 空间复杂度：O(1)
	 * @param grid
	 * @return
	 */
	public static Node construct(int[][] grid) {
		return build(0, 0, grid.length - 1, grid.length - 1, grid);
	}

    private static Node build(int r1, int c1, int r2, int c2, int[][] g) {
        if (r1 > r2 || c1 > c2) return null;
        boolean isLeaf = true;
        int val = g[r1][c1];
        for (int i = r1; i <= r2; i++)
            for (int j = c1; j <= c2; j++)
                if (g[i][j] != val) {
                    isLeaf = false;
                    break;
                }
        if (isLeaf)
            return new Node(val == 1, true, null, null, null, null);
        int rowMid = (r1 + r2) / 2, colMid = (c1 + c2) / 2;
        return new Node(false, false,
            build(r1, c1, rowMid, colMid, g),//top left 
            build(r1, colMid + 1, rowMid, c2, g),//top right
            build(rowMid + 1, c1, r2, colMid, g),//bottom left 
            build(rowMid + 1, colMid + 1, r2, c2, g));//bottom right
    }
	
	/**
	 * 官网没有solution,这是其他人的答案
	 * 时间复杂度：O(n^2)
	 * 空间复杂度：O(1)
	 * @param grid
	 * @return
	 */
	public Node construct1(int[][] g) { 
		return build1(0, 0, g.length - 1, g.length - 1, g);
	}

    private Node build1(int r1, int c1, int r2, int c2, int[][] g) {
        if (r1 > r2 || c1 > c2) return null;
        boolean isLeaf = true;
        int val = g[r1][c1];
        for (int i = r1; i <= r2; i++)
            for (int j = c1; j <= c2; j++)
                if (g[i][j] != val) {
                    isLeaf = false;
                    break;
                }
        if (isLeaf)
            return new Node(val == 1, true, null, null, null, null);
        int rowMid = (r1 + r2) / 2, colMid = (c1 + c2) / 2;
        return new Node(false, false,
            build(r1, c1, rowMid, colMid, g),//top left 
            build(r1, colMid + 1, rowMid, c2, g),//top right
            build(rowMid + 1, c1, r2, colMid, g),//bottom left 
            build(rowMid + 1, colMid + 1, r2, c2, g));//bottom right
    }
	
	public static void main(String[] args) {
		int[][] grid = {{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0}};
		System.out.println(construct(grid));
	}

}
