/*
Classical DP -- Parent Problem (This Pattern)
0 - 1 Knapsack Problem
Accuracy: 31.76%

Statement ::
You are given weights and values of N items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack. Note that we have only one quantity of each item.
In other words, given two integer arrays val[0..N-1] and wt[0..N-1] which represent values and weights associated with N items respectively. Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W. You cannot break an item, either pick the complete item or dont pick it (0-1 property).

Example 1:

Input:
N = 3
W = 4
values[] = {1,2,3}
weight[] = {4,5,1}
Output: 3
Explanation: Choose the last item that weighs 1 unit and holds a value of 3. 
Example 2:

Input:
N = 3
W = 3
values[] = {1,2,3}
weight[] = {4,5,6}
Output: 0
Explanation: Every item has a weight exceeding the knapsack's capacity (3).
Your Task:
Complete the function knapSack() which takes maximum capacity W, weight array wt[], value array val[], and the number of items n as a parameter and returns the maximum possible value you can get.

Expected Time Complexity: O(N*W).
Expected Auxiliary Space: O(N*W)

Constraints:
1 ≤ N ≤ 1000
1 ≤ W ≤ 1000
1 ≤ wt[i] ≤ 1000
1 ≤ v[i] ≤ 1000


*/

class Solution 
{ 
    static int[][] memo = new int[1001][1001];
    static int knapSack(int W, int wt[], int val[], int n) 
    { 
         //Lets Play with Recursion --> DP ::
         //1. Call Recursion::
         //return solveRec(W,wt,val,n);
         // TLE :: 1156/1210
         // TC : O(2^n)
         // SC :O(1) -- Aux. Space : O(n)
         //2. Memo::
         //best ways to fill 2d array with single value::
        //  Arrays.stream(memo).forEach(a -> Arrays.fill(a, -1));
        //  return solveMemo(W,wt,val,n);
        // Test Case : 1210/1210
        // TC : O(n*w)
        // SC : O(n*w)
        return solvetab(W,wt,val,n);
         
    } 
    public static int solveRec(int w ,int wt[] ,int val[],int n){
        //Base Case ::
        if(w==0 || n==0){
            return 0;
        }
        //
        if(wt[n-1]>w){
            //not pick case since wt is greater::
            return solveRec(w,wt,val,n-1);
        }else{
            //pick and not pick :: max 
            return Math.max(val[n-1]+solveRec(w-wt[n-1],wt,val,n-1),solveRec(w,wt,val,n-1));
        }
    }
    public static int solveMemo(int w ,int wt[] ,int val[],int n){
        //Base Case ::
        if(w==0 || n==0){
            return 0;
        }
        //check pre-calculated ::
        if(memo[n][w]!=-1){
            return memo[n][w];
        }
        if(wt[n-1]>w){
            //not pick case since wt is greater::
            return memo[n][w]=solveMemo(w,wt,val,n-1);
        }else{
            //pick and not pick :: max 
            return memo[n][w]=Math.max(val[n-1]+solveMemo(w-wt[n-1],wt,val,n-1),solveMemo(w,wt,val,n-1));
        }
    }
    public static int solvetab(int w,int wt[],int val[],int n){
        // Base ---> Insilization::
        int[][] tab = new int[n+1][w+1];
        for(int i=0;i<n+1;i++){
            for(int j=0;j<w+1;j++){
                if(i==0 || j==0){
                    tab[i][j] =0;
                }
            }
        }
        //Memo --> Tab Code 
        for(int i=1;i<n+1;i++){
            for(int j=1;j<w+1;j++){
                if(wt[i-1]>j){
                    tab[i][j] =tab[i-1][j];
                }else{
                    tab[i][j] =Math.max(val[i-1]+tab[i-1][j-wt[i-1]],tab[i-1][j]);
                }
            }
        }
        return tab[n][w];
    }
    //Space Optimize.
    // TC : O(n*w)
    // SC : 2*O(w)
    public static int solveOpt(int W,int[] wt, int[] val,int n){
        
        int[] curr = new int[W+1];
        int[] prev = new int[W+1];
        
        for(int i=1;i<n+1;i++){
            for(int j=1;j<W+1;j++){
                if(wt[i-1]>j){
                    curr[j] =prev[j];
                }else{
                    curr[j] = Math.max(prev[j-wt[i-1]]+val[i-1] , prev[j]);
                }
            }
            for(int k=0;k<W+1;k++){
                prev[k] = curr[k];
            }
        }
        return curr[W];
    }
    
}

//Author : Saurabh Kumar
