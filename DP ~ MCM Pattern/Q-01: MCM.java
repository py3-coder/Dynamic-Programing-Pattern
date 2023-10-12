/*
Matrix Chain Multiplication
Hard:
Given a sequence of matrices, find the most efficient way to multiply these matrices together. The efficient way is the one that involves the least number of multiplications.
The dimensions of the matrices are given in an array arr[] of size N (such that N = number of matrices + 1) where the ith matrix has the dimensions (arr[i-1] x arr[i]).

Example 1:

Input: N = 5
arr = {40, 20, 30, 10, 30}
Output: 26000
Explanation: There are 4 matrices of dimension 
40x20, 20x30, 30x10, 10x30. Say the matrices are 
named as A, B, C, D. Out of all possible combinations,
the most efficient way is (A*(B*C))*D. 
The number of operations are -
20*30*10 + 40*20*10 + 40*10*30 = 26000.

Example 2:
Input: N = 4
arr = {10, 30, 5, 60}
Output: 4500
Explanation: The matrices have dimensions 
10*30, 30*5, 5*60. Say the matrices are A, B 
and C. Out of all possible combinations,the
most efficient way is (A*B)*C. The 
number of multiplications are -
10*30*5 + 10*5*60 = 4500.

Your Task:
You do not need to take input or print anything. Your task is to complete the function matrixMultiplication() which takes the value N and the array arr[] as input parameters and returns the minimum number of multiplication operations needed to be performed.


Expected Time Complexity: O(N3)
Expected Auxiliary Space: O(N2)


Constraints: 
2 ≤ N ≤ 100
1 ≤ arr[i] ≤ 500
*/
class Solution{
    static int memo[][] = new int[501][501];
    static int matrixMultiplication(int N, int arr[])
    {
        // Lets Play with DP:)
        //return solveRec(arr,1,N-1);
        Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        return solveMemo(arr,1,N-1);
    }
    public static int solveRec(int arr[],int i,int j){
        //Base Case::
        if(i>=j){
            return 0;
        }
        int ans=Integer.MAX_VALUE;
        for(int k=i;k<=j-1;k++){
            int temp=solveRec(arr,i,k)+solveRec(arr,k+1,j)+(arr[i-1]*arr[k]*arr[j]);
            if(temp<ans){
                ans=temp;
            }
        }
        return ans;
    }
   public static int solveMCMmemo(int[] arr,int i, int j){
		// Base Case :::
        if(i>=j) return 0;
		if(memo[i][j]!=-1){
			return memo[i][j];
		}
		int ans=Integer.MAX_VALUE;
		for(int k=i;k<j;k++){
			int left,right;
			if(memo[i][k]!=-1){
				left =memo[i][k];
			}else{
				left =solveMCMmemo(arr,i,k);
				memo[i][k]=left;
			}

			if(memo[k+1][j]!=-1){
				right =memo[k+1][j];
			}else{
				right =solveMCMmemo(arr,k+1,j);
				memo[k+1][j]=right;
			}

			int temp =left +right +(arr[i-1]*arr[k]*arr[j]);
			ans =Math.min(ans,temp);
			memo[i][j] =ans;
		}
		return memo[i][j]=ans;
	}

    // Tabulation Rules: Bottom up dp -- MCM pattern always bottom up dp ends.
    // 1. Base Case - Initilisation -- fill dp with -1/0 as per req.
    // 2. Loop for i and j -- imp: reverse it almost case works
    // 3. Edge case for i cross j -- vimp
    //4. copy the recursion and change the variable
    //5. Return tab[1][n-1] always
	public static int SolveTabulation(int arr[]){
		//Tabulation::
		int n=arr.length;
		int dp[][] = new int[n][n];
		// 
		Arrays.stream(dp).forEach(a->Arrays.fill(a,-1));
		//Base Case --> Initlisation::
		for(int i=1;i<n;i++){
			dp[i][i]=0;
		}

		for(int i=n-1;i>=1;i--){
			for(int j=i+1;j<n;j++){
				int ans=Integer.MAX_VALUE;
				for(int k=i;k<j;k++){
					int ops =dp[i][k] + dp[k+1][j] +(arr[i-1]*arr[k]*arr[j]);
					ans =Math.min(ops,ans);
				}
				dp[i][j]=ans;
			}
		}
		return dp[1][n-1];

	}
}
