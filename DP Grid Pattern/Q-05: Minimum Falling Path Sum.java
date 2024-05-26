/*
931. Minimum Falling Path Sum
Medium

Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
A falling path starts at any element in the first row and chooses the element in the next row that is 
either directly below or diagonally left/right. Specifically, 
the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).

 

Example 1:
Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
Output: 13
Explanation: There are two falling paths with a minimum sum as shown.

Example 2:
Input: matrix = [[-19,57],[-40,-5]]
Output: -59
Explanation: The falling path with a minimum sum is shown.
 

Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 100
-100 <= matrix[i][j] <= 100
*/
class Solution {
    static int[][] memo;
    public int minFallingPathSum(int[][] matrix) {
        
        int n  = matrix.length;
        if (n == 1) return matrix[0][0];
        //-100 <= matrix[i][j] <= 100
        memo = new int[n+1][n+1];
        Arrays.stream(memo).forEach(a->Arrays.fill(a,Integer.MAX_VALUE));
        // int mini  = Integer.MAX_VALUE;
        // for(int i=0;i<n;i++){
        //     mini = Math.min(mini , solveRec(0,i,n,matrix));
        // }
        //return mini;
        
        return solveTab(matrix);
        
        //return solveOpt(matrix);
    }
    public int solveRec(int i,int j,int n,int[][] matrix){
        //base case ::
        if(i==n){
            return 0;
        }
        if(memo[i][j]!=Integer.MAX_VALUE){
            return memo[i][j];
        }
        
        int left =Integer.MAX_VALUE,right=Integer.MAX_VALUE;
        if(j>0){
            left = solveRec(i+1,j-1,n,matrix);
        }
        int center = solveRec(i+1,j,n,matrix);
        if(j<n-1){
            right = solveRec(i+1,j+1,n,matrix);
        }
        return memo[i][j] = matrix[i][j] + Math.min(left,Math.min(center,right));
    }
    public int solveTab(int[][] matrix){
        int n  = matrix.length;
        int[][] tab = new int[n][n];
        
        for(int i=0;i<n;i++){
            tab[0][i] = matrix[0][i];
        }
        
        for(int i=1;i<n;i++){
            for(int j=0;j<n;j++){
                int left =Integer.MAX_VALUE,right=Integer.MAX_VALUE;
                if(j>0){
                    left = tab[i-1][j-1];
                }
                int center = tab[i-1][j];
                if(j<n-1){
                    right = tab[i-1][j+1];
                }
                tab[i][j] =  matrix[i][j] + Math.min(left,Math.min(center,right));
            }
        }
        
        int maxi = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            maxi = Math.min(maxi, tab[n - 1][j]);
        }

        return maxi;
    }
    public int solveOpt(int[][] matrix){
        int n  = matrix.length;
        int[] prev = new int[n];
        
        for(int i=0;i<n;i++){
            prev[i] = matrix[0][i];
        }
        
        for(int i=1;i<n;i++){
            int[] temp = new int[n];
            for(int j=0;j<n;j++){
                int left =Integer.MAX_VALUE,right=Integer.MAX_VALUE;
                if(j>0){
                    left = prev[j-1];
                }
                int center = prev[j];
                if(j<n-1){
                    right = prev[j+1];
                }
                temp[j] =  matrix[i][j] + Math.min(left,Math.min(center,right));
            }
            prev = temp;
        }
        
        int maxi = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            maxi = Math.min(maxi, prev[j]);
        }

        return maxi;
    }
}
