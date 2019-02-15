package arithmetic;

//深度优先搜索 岛屿的数量

import datastructure.MyCircularQueue;

/**
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
 * 你可以假设网格的四个边均被水包围。
 *
 * 示例 1:
 *
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * 输出: 1
 * 示例 2:
 *
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * 输出: 3
 */
public class DFS {
    public int numIslands(char[][] grid) {
        int count = 0;
        //判断grid不为空
        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        //定义一个数据 来标记该地域是否被访问过
        int[][] visited = new int[row][col];
        //引入一个队列 来保存刚刚遍历的点及其连通的点
        MyCircularQueue myCircularQueue = new MyCircularQueue(100);
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                //如果是陆地 进行遍历
                if (grid[i][j] == '1' && visited[i][j] == 0){
                    //将其加入队列 并且计数+1
                    myCircularQueue.enQueue(i+"_"+j);
                    count ++;
                    //判断周边岛屿
                    while (!myCircularQueue.isEmpty()){
                        String index =  myCircularQueue.deQueue();
                        String iNum = index.split("_")[0];
                        String jNum = index.split("_")[1];
                        int iN = Integer.valueOf(iNum);
                        int jN = Integer.valueOf(jNum);
                        //分辨判断上下左右是否有岛屿
                        dfsSearch(grid,visited,myCircularQueue,iN,jN,row,col);
                    }
                }
            }
        }

        return count;
    }

    // 每遇到'1'后, 开始向四个方向 递归搜索. 搜到后变为'0',
    // 因为相邻的属于一个island. 然后开始继续找下一个'1'.
    private void dfsSearch(char[][] grid, int[][] visted,MyCircularQueue myCircularQueue,
                           int i, int j, int rows, int cols) {
        if (i < 0 || i >= rows || j < 0 || j >= cols)
            return;
        if (grid[i][j] != '1' || visted[i][j] != 0)
            return;
        // 也可以才用一个visited数组，标记遍历过的岛屿
        visted[i][j] = 1;
        myCircularQueue.enQueue(i+"_"+j);
        dfsSearch(grid, visted,myCircularQueue,i + 1, j, rows, cols);
        dfsSearch(grid, visted,myCircularQueue,i - 1, j, rows, cols);
        dfsSearch(grid, visted,myCircularQueue,i, j + 1, rows, cols);
        dfsSearch(grid, visted,myCircularQueue,i, j - 1, rows, cols);
    }

    public static void main(String[] args){
        DFS dfs = new DFS();
        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        int num = dfs.numIslands(grid);
        System.out.println("num is"+num);
    }
}
