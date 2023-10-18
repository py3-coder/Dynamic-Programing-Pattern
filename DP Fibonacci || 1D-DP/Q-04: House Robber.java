/*

198. House Robber

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically 
contact the police if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 2:
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
 
Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400
*/

class Solution {
    static int[] memo = new int[101];
    public int rob(int[] nums) {
        int n=nums.length;
        //Recursion::
        //return solveRec(nums,n-1);
        Arrays.fill(memo,-1);
        return solveMemo(nums,n-1);
        
    }
    public static int solveRec(int[] arr,int n){
        //Base Case ::
        if(n<0) return 0;
        //Choices we have ::
        int rob = arr[n] +solveRec(arr,n-2);
        int notrob =solveRec(arr,n-1);

        return memo[n]=Math.max(rob,notrob);
    }
    public static int solveMemo(int[] arr,int n){
        //Base Case ::
        if(n<0) return 0;
        // precamputed value::
        if(memo[n]!=-1){
            return  memo[n];
        }
        //Choices we have ::
        int rob = arr[n] +solveMemo(arr,n-2);
        int notrob =solveMemo(arr,n-1);

        return memo[n]=Math.max(rob,notrob);
    }
   
}
