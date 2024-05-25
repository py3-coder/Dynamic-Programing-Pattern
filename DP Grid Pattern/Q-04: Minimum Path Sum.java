/*
64. Minimum Path Sum
Medium

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.

Example 1:
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Example 2:
Input: grid = [[1,2,3],[4,5,6]]
Output: 12
 
Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200

*/

class Solution {
    static int[][] memo;
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        memo =new int[n+1][m+1];
        Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        // return solveRec(n-1,m-1,grid);
        //return solveTab(grid); 
        return solveOpt(grid);
    }
    public int solveRec(int n,int m,int[][] grid){
        //base case
        if(n==0 && m==0){
            return grid[0][0];
        }
        
        if(memo[n][m]!=-1){
            return memo[n][m];
        }
        
        // 2 choice either  :
        //up  and  left :
        int up=Integer.MAX_VALUE,left=Integer.MAX_VALUE;
        if(n>0){
            up = solveRec(n-1,m,grid);
        }
        if(m>0){
            left = solveRec(n,m-1,grid);
        }
        return memo[n][m]=grid[n][m] + Math.min(left,up);
    }
    
    public int solveTab(int[][] grid){
        int n = grid.length;
        int m = grid[0].length;
        int[][] tab = new int[n+1][m+1];
     
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){ 
                if(i==0 && j==0){
                    tab[i][j] = grid[i][j];
                }else{
                    int up = (int)1e9,left =(int)1e9;
                    if(i>0){
                        up = tab[i-1][j]; 
                    }
                    if(j>0){
                        left = tab[i][j-1];
                    }
                    tab[i][j] = grid[i][j]+Math.min(left,up);
                }
            }
        }
        return tab[n-1][m-1];
    }
    public int solveOpt(int[][] grid){
        int n = grid.length;
        int m = grid[0].length;
        int[] prev= new int[m+1];
        
     
        for(int i=0;i<n;i++){
            int[] temp = new int[m];
            for(int j=0;j<m;j++){ 
                if(i==0 && j==0){
                    temp[j] = grid[i][j];
                }else{
                    int up = (int)1e9,left =(int)1e9;
                    if(i>0){
                        up = prev[j]; 
                    }
                    if(j>0){
                        left = temp[j-1];
                    }
                    temp[j] = grid[i][j]+Math.min(left,up);
                }
            }
            prev = temp;
        }
        return prev[m-1];
    }
}
