/*
Perfect Sum Problem
MediumAccuracy: 20.58%

Given an array arr of non-negative integers and an integer sum, the task is to count all subsets of the given array with a sum equal to a given sum.
Note: Answer can be very large, so, output answer modulo 109+7.

Example 1:
Input: 
N = 6
arr = [5, 2, 3, 10, 6, 8]
sum = 10
Output: 
3
Explanation: 
{5, 2, 3}, {2, 8}, {10} are possible subsets.

Example 2:
Input: 
N = 5
arr = [2, 5, 1, 4, 3]
sum = 10
Output: 
3
Explanation: 
{2, 1, 4, 3}, {5, 1, 4}, {2, 5, 3} are possible subsets.
Your Task:  
You don't need to read input or print anything. Complete the function perfectSum() which takes N, array arr and sum as input parameters and returns an integer value.

Expected Time Complexity: O(N*sum)
Expected Auxiliary Space: O(N*sum)

Constraints:
1 ≤ N*sum ≤ 106
0 ≤ arr[i] ≤ 106
*/

class Solution{
    static int mod=1000000007;
	public int perfectSum(int arr[],int n, int sum) 
	{ 
	    // Your code goes here
	    //Lets Play with Recursion::
	    //1. Recursion Call ::
	    //TC : O(2^n)
	    //SC :O(1) + Aux =O(n)
	    //return solveRec(arr,n,sum);
	    //2. Memo Call
	   // int memo[][]=new int[n+1][sum+1];
	   // Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
	   // return solveMemo(arr,n,sum,memo);
	   return solveTab(arr,n,sum);
	    
	    
	} 
	public static int solveRec(int arr[] ,int n, int sum){
	    //Base Case :: Why am I adding ?? Since When we got req sum just return 1 which iclude:
	    if(n==0){
	        if(sum==0) return 1;
	        return 0;
	    }
	    if(arr[n-1]>sum){
	        return solveRec(arr,n-1,sum);
	    }else{
	        return solveRec(arr,n-1,sum-arr[n-1])+solveRec(arr,n-1,sum)%mod;
	    }
	}
	public static int solveMemo(int arr[] ,int n, int sum,int[][] memo){
	    //Base Case :: Why am I adding ?? Since When we got req sum just return 1 which iclude:
	    if(n==0){
	        if(sum==0) return 1;
	        return 0;
	    }
	    if(memo[n][sum]!=-1){
	        return memo[n][sum];
	    }
	    if(arr[n-1]>sum){
	        return memo[n][sum]=solveMemo(arr,n-1,sum,memo);
	    }else{
	        memo[n][sum]=(solveMemo(arr,n-1,sum-arr[n-1],memo)%mod)+(solveMemo(arr,n-1,sum,memo)%mod);
	        return memo[n][sum]=memo[n][sum]%mod;
	    }
	}
	public static int solveTab(int arr[],int n,int sum){
	    //
	    int tab[][] = new int[n+1][sum+1];
	    for(int i=0;i<n+1;i++){
	        for(int j=0;j<sum+1;j++){
	            if(i==0) tab[i][j]=0;
	            if(j==0) tab[i][j]=1;
	        }
	    }
	    for(int i=1;i<n+1;i++){
	        for(int j=0;j<sum+1;j++){
	            if(arr[i-1]>j){
	                tab[i][j] =tab[i-1][j]%mod;
	            }else{
	                tab[i][j]=tab[i-1][j-arr[i-1]]%mod+tab[i-1][j]%mod;
	                tab[i][j]%=mod;
	            }
	        }
	    }
	    return tab[n][sum];
	}
}
