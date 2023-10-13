/*
887. Super Egg Drop
Hard

You are given k identical eggs and you have access to a building with n floors labeled from 1 to n.
You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break, and any egg dropped at or below floor f will not break.
Each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, you can no longer use it. However, if the egg does not break, you may reuse it in future moves.
Return the minimum number of moves that you need to determine with certainty what the value of f is.

Example 1:
Input: k = 1, n = 2
Output: 2
Explanation: 
Drop the egg from floor 1. If it breaks, we know that f = 0.
Otherwise, drop the egg from floor 2. If it breaks, we know that f = 1.
If it does not break, then we know f = 2.
Hence, we need at minimum 2 moves to determine with certainty what the value of f is.
Example 2:

Input: k = 2, n = 6
Output: 3
Example 3:

Input: k = 3, n = 14
Output: 4

Constraints:
1 <= k <= 100
1 <= n <= 104
*/
class Solution {
    static int[][] memo = new int[101][10001];
    public int superEggDrop(int k, int n) {
        // MCM Pattern DP:)
        // 1. Recursion 
        // TC : O(2^(min(k,n)))
        // SC: O(1)
        //return solveRec(k,n);

        // fill with -1:
        Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        ///Time Complexity: O((n^2) * k)
        //Space Complexity: O(k * n)
        //return solveMemo(k,n);
        return solveMemoBS(k,n);
    }
    public static int solveRec(int e , int f){
        //Base Case ::

        if(e==1) return f;
        if(f==1 || f==0) return f;

        //k loop -i,j
        //Here worst case means we have to take both the conditions at every floor that
        //egg is broken and egg is not broken and try to achieve highest threshold floor,
        // You need to find min no. of moves in worst case:
        int ans =Integer.MAX_VALUE;
        for(int k=1;k<=f;k++){
            int temp = 1+Math.max(solveRec(e-1,k-1),solveRec(e,f-k));
            ans=Math.min(ans,temp);
        }
        return ans;
    }
    public static int solveMemo(int e,int f){
        //Base Case ::
        if(e==1) return f;
        if(f==1 || f==0) return f;

        if(memo[e][f]!=-1){
            return memo[e][f];
        }
        
        int ans =Integer.MAX_VALUE;
        for(int k=1;k<=f;k++){
            int down,up;
            if(memo[e-1][k-1]!=-1){
                down =memo[e-1][k-1];
            }else{
                down=solveMemo(e-1,k-1);
                memo[e-1][k-1]=down;
            }

            if(memo[e][f-k]!=-1){
                up =memo[e][f-k];
            }else{
                up=solveMemo(e,f-k);
                memo[e][f-k]=up;
            }
            int temp = 1+Math.max(down,up);
            ans=Math.min(ans,temp);
            memo[e][f]=ans;
        }
        return memo[e][f]=ans;
    }
    //Optimised::) By using Binary Search::)
    public static int solveMemoBS(int e,int f){
        //Base Case ::
        if(e==1) return f;
        if(f==1 || f==0) return f;

        if(memo[e][f]!=-1){
            return memo[e][f];
        }
        
        int ans =Integer.MAX_VALUE;
        int low=1,high=f;
        // Just Applied BS over linear search::) O(N) -- O(logn)::
        while(low<=high){
            int mid =low+(high-low)/2;
            
            int down,up;
            if(memo[e-1][mid-1]!=-1){
                down =memo[e-1][mid-1];
            }else{
                down=solveMemoBS(e-1,mid-1);
                memo[e-1][mid-1]=down;
            }

            if(memo[e][f-mid]!=-1){
                up =memo[e][f-mid];
            }else{
                up=solveMemoBS(e,f-mid);
                memo[e][f-mid]=up;
            }
            int temp =1+Math.max(down,up);
            //since up is more than down and we need more in worst case  
            if(down<up){
                low=mid+1;
            }else{
                high=mid-1;
            }
            ans=Math.min(ans,temp);
        }
    
        return memo[e][f]=ans;
    }
}
