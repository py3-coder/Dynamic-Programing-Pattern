/*
63. Unique Paths II
Medium

You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]).
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.

Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
The testcases are generated so that the answer will be less than or equal to 2 * 109.

Example 1:

Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right

Example 2:
Input: obstacleGrid = [[0,1],[0,0]]
Output: 1
 
Constraints:

m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] is 0 or 1.
*/

class Solution {
    static int[][] memo;
    public int uniquePathsWithObstacles(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        memo = new int[n+1][m+1];
        Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        if(grid[0][0]==1) return 0;
        //return solveRec(0,0,n-1,m-1,grid);
        
        return solveTab(n,m,grid);
    }
    public int solveRec(int r,int c,int n,int m,int[][] grid){
        //Base Case ::
        if(r == n && c == m){
            return 1;
        }
        if(r>n || c>m) return 0;
        
        
        if(memo[r][c]!=-1){
            return memo[r][c];
        }
        
        //right 
        int left=0,right=0;
        if(c+1<grid[0].length && grid[r][c+1]!=1){
            left+= solveRec(r,c+1,n,m,grid);
        }
        
        //down
        if(r+1<grid.length && grid[r+1][c]!=1){
            right+= solveRec(r+1,c,n,m,grid);
        }
        
        return memo[r][c]=left+right;
    }
    public int solveTab(int n,int m,int[][] obstacleGrid){
//         int[][] tab =new  int[n][m];
//         Arrays.stream(tab).forEach(a->Arrays.fill(a,-1));
        
        
//         for(int i=0;i<n;i++){
//             for(int j=0;j<m;j++){
//                 if(i>0 && j>0 && grid[i][j]==1){
//                     tab[i][j]=0;
//                     continue;
//                 }
//                 if(i==0 && j==0) {
//                     tab[i][j] = 1;
//                     continue;
//                 }
                
//                 int up=0;
//                 int left=0;
                
//                 if(i>0){
//                     up = tab[i-1][j];
//                 }
//                 if(j>0){
//                     left  = tab[i][j-1];
//                 }
                
//                 tab[i][j] = up+left;
//             }
//         }
//         return tab[n-1][m-1];
        
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(obstacleGrid[i][j] == 1)
                    obstacleGrid[i][j] = 0;
                else if(i == 0 && j == 0)
                    obstacleGrid[i][j] = 1;
                else if(i == 0)
                    obstacleGrid[i][j] = obstacleGrid[i][j - 1] * 1;
                else if(j == 0)
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] * 1;
                else
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
            }
        }
        
        return obstacleGrid[n - 1][m - 1];
    }
}
