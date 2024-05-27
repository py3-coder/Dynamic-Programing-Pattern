/*
Chocolates Pickup
Hard  Accuracy: 54.34% Submissions: 9K+Points: 8

You are given an r rows and c cols matrix grid representing a field of chocolates where grid[i][j] represents the number of chocolates that you can collect from the (i, j) cell.
You have two robots that can collect chocolates for you:

Robot #1 is located at the top-left corner (0, 0), and
Robot #2 is located at the top-right corner (0, cols - 1).
Return the maximum number of chocolates collection using both robots by following the rules below:

From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
When any robot passes through a cell, It picks up all chocolates, and the cell becomes an empty cell.
When both robots stay in the same cell, only one takes the chocolates.
Both robots cannot move outside of the grid at any moment.
Both robots should reach the bottom row in grid.
Example:

Input:
r = 3, c = 4
grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
Output:
24
Explanation:
Path of robot #1 and #2 are described in color green and blue respectively. Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12. Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12. Total of cherries: 12 + 12 = 24.
Your Task:
You don't need to read input or print anything. Your task is to complete the function Solve() which takes r rows, c columns, and a matrix grid and returns the maximum number of chocolates that can be collected by two robots.

Expected Time Complexity: O(r * c * c)
Expected Space Complexity: O(c * c * c)

Constraint:
2 <= r < = 70
0 <= grid[i][j] <= 100
*/

class Solution{
    static int[][][] memo;
    public int solve(int n, int m, int grid[][]){
        // Code here
        memo = new int[n][m][m];
        Arrays.stream(memo).forEach(plane -> Arrays.stream(plane).forEach(row -> Arrays.fill(row,-1)));
        //return solveRec(0,0,m-1,n,m,grid);
        
        //return solveTab(n,m,grid);
        return solveOpt(n,m,grid);
    }
    
    public int solveRec(int i,int j1,int j2,int n,int m,int[][] grid){
        //Base Case ::
        if(j1<0 || j2<0 || j1>=m || j2>=m ){
            return (int)-1e8;
        }
        
        if(memo[i][j1][j2]!=-1){
            return memo[i][j1][j2];
        }
        if(i == n-1 ){
            if(j1==j2){
                return grid[i][j1];
            }else{
                return grid[i][j1]+ grid[i][j2];
            }
        }
        
        
        // 3 - state - > 3 state  --> 9 state 
        int maxi = (int)-1e8;
        for(int dj1=-1;dj1<2;dj1++){
            for(int dj2=-1;dj2<2;dj2++){
                int val =0;
                //
                if(j1 == j2 ){
                    val = grid[i][j1];
                }else{
                    val = grid[i][j1] + grid[i][j2];
                }
                
                val += solveRec(i+1,j1+dj1,j2+dj2,n,m,grid);
                
                maxi = Math.max(maxi,val);
                
            }
        }
        return memo[i][j1][j2] =maxi ;
    }
    public int solveTab(int n,int m,int[][] grid){
        int[][][] tab = new int[n][m][m] ;
        
        //Base Case ::-
        for(int j1 =0;j1<m;j1++){
            for(int j2=0;j2<m;j2++){
                if(j1 == j2 ) {
                    tab[n-1][j1][j2] = grid[n-1][j1];
                }else{
                    tab[n-1][j1][j2] = grid[n-1][j1] + grid[n-1][j2];
                }
            }
        }
        
        
        //
        
        for(int i=n-2;i>=0 ;i--){
            for(int j1 =0;j1<m;j1++){
                for(int j2=0;j2<m;j2++){
                    int maxi = (int)-1e8;
                    for(int dj1=-1;dj1<2;dj1++){
                        for(int dj2=-1;dj2<2;dj2++){
                            int val =0;
                            //
                            if(j1 == j2 ){
                                val = grid[i][j1];
                            }else{
                                val = grid[i][j1] + grid[i][j2];
                            }
                            
                            if(j1+dj1>=0 && j1+dj1<m && j2+dj2>=0 && j2+dj2<m){
                                val += tab[i+1][j1+dj1][j2+dj2];
                            }else{
                                val += (int)-1e8;
                            }
                            
                            maxi = Math.max(maxi,val);
                        }
                    }
                    tab[i][j1][j2] = maxi ;
                }
            }
        }
        return tab[0][0][m-1];
    }
    public int solveOpt(int n,int m,int[][] grid){
        int[][] next = new int[m][m];
        int[][] curr  = new int[m][m];
        
        //Base Case ::-
        for(int j1 =0;j1<m;j1++){
            for(int j2=0;j2<m;j2++){
                if(j1 == j2 ) {
                    next[j1][j2] = grid[n-1][j1];
                }else{
                    next[j1][j2] = grid[n-1][j1] + grid[n-1][j2];
                }
            }
        }
        
        
        //
        
        for(int i=n-2;i>=0 ;i--){
            for(int j1 =0;j1<m;j1++){
                for(int j2=0;j2<m;j2++){
                    int maxi = (int)-1e8;
                    for(int dj1=-1;dj1<2;dj1++){
                        for(int dj2=-1;dj2<2;dj2++){
                            int val =0;
                            //
                            if(j1 == j2 ){
                                val = grid[i][j1];
                            }else{
                                val = grid[i][j1] + grid[i][j2];
                            }
                            
                            if(j1+dj1>=0 && j1+dj1<m && j2+dj2>=0 && j2+dj2<m){
                                val += next[j1+dj1][j2+dj2];
                            }else{
                                val += (int)-1e8;
                            }
                            
                            maxi = Math.max(maxi,val);
                        }
                    }
                    curr[j1][j2] = maxi ;
                }
            }
            
            for(int j1 =0;j1<m;j1++){
                for(int j2=0;j2<m;j2++){
                    next[j1][j2] = curr[j1][j2];
                }
            }

        }
        return next[0][m-1];
    }
}
