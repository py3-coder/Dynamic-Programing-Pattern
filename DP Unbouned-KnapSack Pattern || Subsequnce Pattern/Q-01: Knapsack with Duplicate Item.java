/*
Knapsack with Duplicate Items
Medium Accuracy: 52.13%

Given a set of N items, each with a weight and a value, represented by the array w and val respectively. Also, a knapsack with weight limit W.
The task is to fill the knapsack in such a way that we can get the maximum profit. Return the maximum profit.
Note: Each item can be taken any number of times.

Example 1:

Input: 
N = 2
W = 3
val = {1, 1}
wt = {2, 1}
Output: 
3
Explanation: 
1.Pick the 2nd element thrice.
2.Total profit = 1 + 1 + 1 = 3. Also the total weight = 1 + 1 + 1  = 3 which is <= 3.
Example 2:

Input: 
N = 4
W = 8
val[] = {6, 1, 7, 7}
wt[] = {1, 3, 4, 5}
Output: 
13
Explanation: 
The optimal choice is to pick the 1st and 4th element or 1st and 3rd element.
Your Task:
You do not need to read input or print anything. Your task is to complete the function knapSack() which takes the values N, W and the arrays val and wt as input parameters and returns the maximum possible value.

Expected Time Complexity: O(N*W)
Expected Auxiliary Space: O(W)

Constraints:
1 ≤ N, W ≤ 1000
1 ≤ val[i], wt[i] ≤ 100
*/

class Solution{
    static int[][] memo = new int[1001][1001];
    static int knapSack(int N, int W, int val[], int wt[])
    {
        // code here
        //Lets play with recursion :-
        //1.Recursion ::
        //TC : O(2^n)
        // TLE : 3/205
        // return solveRec(N,W,val,wt);
        //2. Memo Call
        // TC : O(n*w)
        // SC: O(n*w)
        // Arrays.stream(memo).forEach(a -> Arrays.fill(a,-1));
        // return solveMemo(N,W,val,wt);
        //3. tab
        return solveTab(N,W,val,wt);
        
    }
    public static int solveRec(int n,int w,int val[],int wt[]){
        //Base Case ::
        if(n==0 || w==0) return 0;
        
        if(wt[n-1]>w){
            return solveRec(n-1,w,val,wt);
        }else{
            return Math.max(val[n-1]+solveRec(n,w-wt[n-1],val,wt),solveRec(n-1,w,val,wt));
        }
    }
    public static int solveMemo(int n,int w,int val[],int wt[]){
       //Base Case ::
        if(n==0 || w==0) return 0;
        
        if(memo[n][w]!=-1){
            return memo[n][w];
        }
        if(wt[n-1]>w){
            return memo[n][w]=solveMemo(n-1,w,val,wt);
        }else{
            return memo[n][w]=Math.max(val[n-1]+solveMemo(n,w-wt[n-1],val,wt),solveMemo(n-1,w,val,wt));
        } 
    }
    public static int solveTab(int n,int w,int val[],int wt[]){
        //
        int tab[][] = new int[n+1][w+1];
        // Base Case - Initlization::
        // o is default value of array so no need to fill it manually::
        for(int i=1;i<n+1;i++){
            for(int j=1;j<w+1;j++){
                if(wt[i-1]>j){
                    tab[i][j] = tab[i-1][j];
                }else{
                    tab[i][j] = Math.max(val[i-1]+tab[i][j-wt[i-1]],tab[i-1][j]);
                }
            }
        }
        return tab[n][w];
    }
}
