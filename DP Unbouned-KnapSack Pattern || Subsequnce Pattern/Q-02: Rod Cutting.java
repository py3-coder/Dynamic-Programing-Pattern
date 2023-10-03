/*
Rod Cutting
Medium

Given a rod of length N inches and an array of prices, price[]. price[i] denotes the value of a piece of length i. 
Determine the maximum value obtainable by cutting up the rod and selling the pieces.

Note: Consider 1-based indexing.
Example 1:
Input:
N = 8
Price[] = {1, 5, 8, 9, 10, 17, 17, 20}
Output:
22
Explanation:
The maximum obtainable value is 22 by 
cutting in two pieces of lengths 2 and 
6, i.e., 5+17=22.
Example 2:

Input:
N=8
Price[] = {3, 5, 8, 9, 10, 17, 17, 20}
Output: 
24
Explanation: 
The maximum obtainable value is 
24 by cutting the rod into 8 pieces 
of length 1, i.e, 8*price[1]= 8*3 = 24. 
Your Task:  
You don't need to read input or print anything. Your task is to complete the function cutRod() which takes the array A[] and its size N as inputs and returns the maximum price obtainable.

Expected Time Complexity: O(N2)
Expected Auxiliary Space: O(N)

Constraints:
1 ≤ N ≤ 1000
1 ≤ Ai ≤ 105
*/


class Solution{
    public int cutRod(int price[], int n) {
        //code here
        // Lets Play with Recursion --> DP ::
        //1. Recursion :: 
        // TLE ::
        //TC : O(2^n)
        //SC : O(1)
        int size[] = new int[n];
        for(int i=0;i<n;i++){
            size[i] =i+1;
        }
        // return solveRec(price,size,n,n);
        //2.Memo
        // int [][] memo =new int[n+1][n+1];
        // Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        // return solveMemo(price,size,n,n,memo);
        //3. Tab:
        return solveTab(price,size,n,n);
        
    }
    public static int solveRec(int price[],int size[],int n,int s){
        //Base Case ::
        if(n==0 || s==0) return 0;
        
        if(size[n-1]>s){
            return solveRec(price,size,n-1,s);
        }else{
            return Math.max(price[n-1]+solveRec(price,size,n,s-size[n-1]),solveRec(price,size,n-1,s));
        }
    }
    public static int solveMemo(int price[],int size[],int n,int s,int[][] memo){
        if(n==0 || s==0) return 0;
        if(memo[n][s]!=-1){
            return memo[n][s];
        }
        if(size[n-1]>s){
            return memo[n][s]=solveMemo(price,size,n-1,s,memo);
        }else{
            return memo[n][s]=Math.max(price[n-1]+solveMemo(price,size,n,s-size[n-1],memo),solveMemo(price,size,n-1,s,memo));
        }
    }
    public static int solveTab(int price[],int size[],int n,int s){
        //Table ::
        int tab[][] = new int[n+1][n+1];
        //base - initlisation::automatic since default value is 0
        for(int i=1;i<n+1;i++){
            for(int j=1;j<s+1;j++){
                if(size[i-1]>j){
                    tab[i][j] =tab[i-1][j];
                }else{
                    tab[i][j] =Math.max(price[i-1]+tab[i][j-size[i-1]],tab[i-1][j]);
                }
            }
        }
        return tab[n][s];
    }
}
