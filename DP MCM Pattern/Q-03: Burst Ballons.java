/*
312. Burst Balloons
Hard

You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums.
You are asked to burst all the balloons.
If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. 
If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.

Return the maximum coins you can collect by bursting the balloons wisely.

Example 1:
Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167

Example 2:
Input: nums = [1,5]
Output: 10
 
Constraints:

n == nums.length
1 <= n <= 300
0 <= nums[i] <= 100

*/


import java.util.*;
public class Solution {
    static int[][] memo = new int[102][102];
    public static int burstBalloons(int []nums){
        // Lets Play with DP:)
        // little bit diff problem :: approch from bottom that suppose at last this ballon brust and 
        // so on the next last ballon::
        int arr[] = new int[nums.length+2];
        arr[0]=1;
        arr[arr.length-1]=1;
        for(int i=0;i<nums.length;i++){
            arr[i+1] =nums[i];
        }
        //return solveRec(arr,1,nums.length);
        // Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        // return solveMemo(arr,1,nums.length);
        //TC: O(n^3)

        return solveTab(arr,nums.length);
        // TC: O(n^3) 
        //SC: O(n^2)
    }
     public static int solveRec(int[] arr,int i,int j){
        //Base Case ::
        if(i>j)  return 0;
        int ans =Integer.MIN_VALUE;
        for(int k=i;k<=j;k++){
            int temp = solveRec(arr,i,k-1) + solveRec(arr,k+1,j) + (arr[i-1]*arr[k]*arr[j+1]);
            ans =Math.max(temp,ans);
        }
        return ans;
    }
    public static int solveMemo(int[] arr,int i,int j){
        //Base Case ::
        if(i>j)  return 0;
        if(memo[i][j]!=-1){
            return memo[i][j];
        }
        int ans =Integer.MIN_VALUE;
        for(int k=i;k<=j;k++){
            int left,right;
            if(memo[i][k-1]!=-1){
                left = memo[i][k-1];
            }else{
                left =solveMemo(arr,i,k-1);
                memo[i][j]=left;
            }

            if(memo[k+1][j]!=-1){
                right=memo[k+1][j];
            }else{
                right=solveMemo(arr,k+1,j);
                memo[k+1][j]=right;
            }

            int temp = left + right + (arr[i-1]*arr[k]*arr[j+1]);
            ans =Math.max(temp,ans);
            memo[i][j]=ans;
        }
        return memo[i][j]=ans;
    }
    public static int solveTab(int arr[],int n){
        //Bottom UP DP:)
        int tab[][] = new int[n+2][n+2];
        //Base Case --> Initlisation: fill array woth 0: 
        // by default its zero::

        for(int i=n;i>=1;i--){
            for(int j=1;j<=n;j++){
                if (i > j) continue;
                int ans =Integer.MIN_VALUE;
                for(int k=i;k<=j;k++){
                    int temp = tab[i][k-1]+tab[k+1][j] + (arr[i-1]*arr[k]*arr[j+1]);
                    ans =Math.max(temp,ans);
                }
                tab[i][j]=ans;
            }
        }
        return tab[1][n];
    }
}
