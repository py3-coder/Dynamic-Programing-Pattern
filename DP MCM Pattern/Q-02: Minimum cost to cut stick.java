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
class Solution {
    static int memo[][] = new int[102][102];
    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        int arr[]= new int[cuts.length+2];
        arr[0]=0; arr[arr.length-1]=n;
        for(int i=0;i<cuts.length;i++){
            arr[i+1] =cuts[i];
        }
        //return solveRec(arr,1,cuts.length);
        
        // Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        // return solveMemo(arr,1,cuts.length);

        return solveTab(arr,cuts.length);
        
    }
    public static int solveRec(int arr[],int i,int j){
        //Base Case::
        if(i>j) return 0; 
        int ans =Integer.MAX_VALUE;
        for(int k=i;k<=j;k++){
                int temp = solveRec(arr,i,k-1)+ solveRec(arr,k+1,j)+(arr[j+1]-arr[i-1]);
                ans =Math.min(temp,ans);
        }
        return  ans;
    }
    public static int solveMemo(int arr[],int i,int j){
        //Base Case::
        if(i>j) return 0; 
        if(memo[i][j]!=-1){
            return memo[i][j];
        }
        int ans =Integer.MAX_VALUE;
        for(int k=i;k<=j;k++){
            int left,right;
            if(memo[i][k-1]!=-1){
                left=memo[i][k-1];
            }else{
                left =solveMemo(arr,i,k-1);
                memo[i][k-1]=left;
            }
            if(memo[k+1][j]!=-1){
                right=memo[k+1][j];
            }else{
                right=solveMemo(arr,k+1,j);
                memo[k+1][j] =right;
            }
            int temp = left+right+(arr[j+1]-arr[i-1]);
            ans =Math.min(temp,ans);
            memo[i][j]=ans;
        }
        return  memo[i][j]=ans;
    }
    public static int solveTab(int arr[],int len){
        //Tabulation:: Bottom up:
        int dp[][] = new int[len+2][len+2];
        // 1.base case--> 0 whne i>j : fill array with 0
        //2. Reverse the loop and take care of i>j case carefully:
        for(int i=len;i>0;i--){
            for(int j=1;j<=len;j++){
                if(i>j) continue;
                int ans =Integer.MAX_VALUE;
                for(int k=i;k<=j;k++){
                    int temp = dp[i][k-1]+dp[k+1][j]+ (arr[j+1]-arr[i-1]);
                    ans =Math.min(temp,ans);
                }
                dp[i][j]=ans;
            }
        }
        return dp[1][len];
    }
}
