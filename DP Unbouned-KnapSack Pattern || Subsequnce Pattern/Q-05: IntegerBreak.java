/*
343. Integer Break
Medium

Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.

Return the maximum product you can get.
Example 1:

Input: n = 2
Output: 1
Explanation: 2 = 1 + 1, 1 × 1 = 1.
Example 2:

Input: n = 10
Output: 36
Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 

Constraints:
2 <= n <= 58
*/

class Solution {
    static int memo[][] =new int[59][59];
    public int integerBreak(int n) {
        //1. Recursion :
        // TC : O(2^n)
        if(n==2) return 1;
        if(n==3) return 2;
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=i+1;
        }
        // return solveRec(n,arr,n);
        //2. Memoization::
        // TC : O(n*n) SC : O(n*n)
        //Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        //return solveMemo(n,arr,n);

        //3. Tabulation:
        // TC : O(n*n)
        // SC : O(n*n)
        return solveTab(n,arr,n);

    }
    public static int solveRec(int n,int[] arr,int target){
        if(n==0 || target==0 ) return 1;
        if(arr[n-1]>target){
            return solveRec(n-1,arr,target);
        }else{
            return Math.max(arr[n-1]*solveRec(n,arr,target-arr[n-1]),solveRec(n-1,arr,target));
        }
    }
    public static int solveMemo(int n,int[] arr,int target){
        if(n==0 || target==0 ) return 1;
        if(memo[n][target]!=-1){
            return memo[n][target];
        }
        if(arr[n-1]>target){
            return memo[n][target]=solveRec(n-1,arr,target);
        }else{
        return memo[n][target]=Math.max(arr[n-1]*solveRec(n,arr,target-arr[n-1]),solveRec(n-1,arr,target));
        }
    }
    public static int solveTab(int n,int[] arr,int target){
            // Tabulation ::
        int tab[][] = new int[n+1][n+1];
        for(int i=0;i<n+1;i++){
            for(int j=0;j<n+1;j++){
                if(i==0) tab[i][j]=1;
                if(j==0) tab[i][j]=1;
            }
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<target+1;j++){
                if(arr[i-1]>j){
                    tab[i][j] = tab[i-1][j];
                }else{
                    tab[i][j] =Math.max(arr[i-1]*tab[i][j-arr[i-1]],tab[i-1][j]);
                }
            }
        }
        return tab[n][n];
    }

}
