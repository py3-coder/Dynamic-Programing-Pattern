/*
Number of Coins
Medium

Given a value V and array coins[] of size M, the task is to make the change for V cents,
given that you have an infinite supply of each of coins{coins1, coins2, ..., coinsm} valued coins.
Find the minimum number of coins to make the change. If not possible to make change then return -1.

Example 1:

Input: V = 30, M = 3, coins[] = {25, 10, 5}
Output: 2
Explanation: Use one 25 cent coin
and one 5 cent coin
Example 2:
Input: V = 11, M = 4,coins[] = {9, 6, 5, 1} 
Output: 2 
Explanation: Use one 6 cent coin
and one 5 cent coin

Your Task:  
You don't need to read input or print anything. Complete the function minCoins() which takes V, M and array coins as input parameters and returns the answer.

Expected Time Complexity: O(V*M)
Expected Auxiliary Space: O(V)

Constraints:
1 ≤ V*M ≤ 106
All array elements are distinct
*/
class Solution{
	public int minCoins(int coins[], int M, int V) 
	{ 
	    // Your code goes here
	    //1. Recursion::
	    // TC : O(m^v)
	    //SC : O(1) --> Aux O(n):
	    //return solveRec(coins.length,coins,V);
	    
	    //2. Memo
	    //TC : O(n*v)
	   //int n=coins.length;
	   // int memo[][] = new int[n+1][V+1];
	   // Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
	   // return solveMemo(n,coins,V,memo)==Integer.MAX_VALUE-1?-1:solveMemo(n,coins,V,memo);
	   
	   //3.Tabulation::
	   return solveTab(M,coins,V);
	}
	public static int solveRec(int n,int coins[],int sum){
	    //Base ::
	    if(sum==0) return 0;
	    if(n==0) return Integer.MAX_VALUE-1;
	    if(coins[n-1]>sum){
	        return solveRec(n-1,coins,sum);
	    }else{
	        return Math.min(1+solveRec(n,coins,sum-coins[n-1]),solveRec(n-1,coins,sum));
	    }
	}
	public static int solveMemo(int n,int coins[],int sum,int [][]memo){
	    if(sum==0) return 0;
	    if(n==0) return Integer.MAX_VALUE-1;
	    
	    if(memo[n][sum]!=-1){
	        return memo[n][sum];
	    }
	    if(coins[n-1]>sum){
	        return memo[n][sum] =solveMemo(n-1,coins,sum,memo);
	    }else{
	        return memo[n][sum] =Math.min(1+solveMemo(n,coins,sum-coins[n-1],memo),solveMemo(n-1,coins,sum,memo));
	    }
	}
	public static int solveTab(int n,int coins[],int sum){
	    //Tabulation:)
	    
	    int tab[][] = new int[n+1][sum+1];
	    //base->Initlization::
	    for(int i=0;i<n+1;i++){
	        for(int j=0;j<sum+1;j++){
	            if(i==0) tab[i][j] =Integer.MAX_VALUE-1;
	            if(j==0) tab[i][j] =0;
	        }
	    }
	    for(int i=1;i<n+1;i++){
	        for(int j=1;j<sum+1;j++){
	            if(coins[i-1]>j){
	                tab[i][j] =tab[i-1][j];
	            }else{
	                tab[i][j] =Math.min(1+tab[i][j-coins[i-1]],tab[i-1][j]);
	            }
	        }
	    }
	    return tab[n][sum]==Integer.MAX_VALUE-1?-1:tab[n][sum];
	}
}
