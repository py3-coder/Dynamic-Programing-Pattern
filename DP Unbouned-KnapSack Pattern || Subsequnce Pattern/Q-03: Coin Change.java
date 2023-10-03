
/*
Coin Change
Medium:

Given an integer array coins[ ] of size N representing different denominations of currency and an integer sum, find the number of ways you can make sum by using different combinations from coins[ ].  
Note: Assume that you have an infinite supply of each type of coin. And you can use any coin as many times as you want.

Example 1:
Input:
N = 3, sum = 4
coins = {1,2,3}
Output: 4
Explanation: Four Possible ways are: {1,1,1,1},{1,1,2},{2,2},{1,3}.

Example 2:
Input:
N = 4, Sum = 10
coins = {2,5,3,6}
Output: 5
Explanation: Five Possible ways are: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}.

Your Task:
You don't need to read input or print anything. Your task is to complete the function count() which accepts an array coins its size N and sum as input parameters and returns the number of ways to make change for given sum of money. 

Expected Time Complexity: O(sum*N)
Expected Auxiliary Space: O(sum)

Constraints:
1 <= sum, N, coins[i] <= 103
*/

class Solution {
    static long[][] memo =new long[1001][1001];
    public long count(int coins[], int N, int sum) {
        // code here.
        //1. Recursion::
        // TC : O(2^n) SC : O(1)
        //return solveRec(coins,N,sum);
        
        //2.Memo ::
        // TLE : 235/1120
        //TC : O(n*sum)
        //SC :O(n*sum)
        // Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        // return solveMem(coins,N,sum);
        
        //3. Tabulation::
        // TC :O(n*sum)
        //SC : O(n*sum)
        return solveTab(coins,N,sum);
    }
    public long solveRec(int coins[],int n,int sum){
        if(sum==0) return 1;
        if(n==0) return 0;
        
        if(coins[n-1]>sum){
            return solveRec(coins,n-1,sum);
        }else{
            return solveRec(coins,n,sum-coins[n-1])+solveRec(coins,n-1,sum);
        }
    }
    public long solveMem(int coins[],int n,int sum){
        if(sum==0) return 1;
        if(n==0) return 0;
        
        if(memo[n][sum]!=-1){
            return memo[n][sum];
        }
        if(coins[n-1]>sum){
            return memo[n][sum]=solveMem(coins,n-1,sum);
        }else{
            return memo[n][sum]=solveMem(coins,n,sum-coins[n-1])+solveMem(coins,n-1,sum);
        }
    }
    public long solveTab(int coins[],int n,int sum){
        //Tabulation::
        long tab[][] = new long[n+1][sum+1];
        for(int i=0;i<n+1;i++){
            for(int j=0;j<sum+1;j++){
                if(i==0) tab[i][j]=0;
                if(j==0) tab[i][j]=1;
            }
        }
        //
        for(int i=1;i<n+1;i++){
            for(int j=0;j<sum+1;j++){
                if(coins[i-1]>j){
                    tab[i][j] = tab[i-1][j];
                }else{
                    tab[i][j] =tab[i][j-coins[i-1]]+tab[i-1][j];
                }
            }
        }
        return tab[n][sum];
    }
}
