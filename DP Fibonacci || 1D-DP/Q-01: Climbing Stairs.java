/*
Climbing Stairs
Easy

You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

Constraints:

1 <= n <= 45
*/
class Solution {
    static int memo[]= new int[46];
    public int climbStairs(int n) {
        //Lets Play With Recursion::
        //1. Recursion::
        //return solveRec(n);
        //TC : O(2^n)
        
        //2. Memo:
        // Arrays.fill(memo,-1);
        // return solveMemo(n);
        // TC : O(n)
        // SC : O(n)  + Aux space -O(n)

        //3. Bottom UP : 
        // TC : O(n)
        // SC: O(n)
        //return solveTab(n);

        //4. Space Optimised::
        return solveOptmised(n);

    }
    public static int solveRec(int n){
        //Base Case ::
        if(n<0) return 0;
        if(n==0) return 1;
        //choice diagram ::
        // either 1 steps or 2 steps :: since we are counting the ways:: just add it:
        if(n<2){
            return solveRec(n-1);
        }
        return solveRec(n-1)+solveRec(n-2);
    }
    public static int solveMemo(int n){
        //Base Case ::
        if(n<0) return 0;
        if(n==0) return 1;
        if(memo[n]!=-1){
            return memo[n];
        }
        //choice diagram ::
        // either 1 steps or 2 steps :: since we are counting the ways:: just add it:
        if(n<2){
            return memo[n]=solveMemo(n-1);
        }
        return memo[n]=solveMemo(n-1)+solveMemo(n-2);
    }
    public static int solveTab(int n){
        //Base Case ::
		int tab[] = new int[n+2];
		tab[0]=1;
		tab[1]=1;
        //choice diagram ::
        // either 1 steps or 2 steps :: since we are counting the ways:: just add it:
        for(int i=2;i<=n;i++){
			tab[i] = (tab[i-1]+tab[i-2]);
		}
		return tab[n];
    }
    public static int solveOptmised(int n){
        //Base Case ::
		int prev2=1;
        int prev1=1;
        int curr=0;
        //choice diagram ::
        // either 1 steps or 2 steps :: since we are counting the ways:: just add it:
        for(int i=2;i<=n;i++){
			curr = prev1+prev2;
            prev2=prev1;
            prev1=curr;
		}
		return prev1;
    }
}
