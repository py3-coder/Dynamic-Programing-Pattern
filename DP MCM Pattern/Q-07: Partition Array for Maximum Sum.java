/*
1043. Partition Array for Maximum Sum

Given an integer array arr, partition the array into (contiguous) subarrays of length at most k.
After partitioning, each subarray has their values changed to become the maximum value of that subarray.
Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.

Example 1:

Input: arr = [1,15,7,9,2,5,10], k = 3
Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]
Example 2:

Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
Output: 83
Example 3:

Input: arr = [1], k = 1
Output: 1
 
Constraints:

1 <= arr.length <= 500
0 <= arr[i] <= 109
1 <= k <= arr.length
*/
class Solution {
    static int memo[][] = new int[501][501];
    public int maxSumAfterPartitioning(int[] arr, int k) {
        //Lets Play with DP:)
        //1. Recursion :)
        //return solveRec(arr,k,0,arr.length-1);

        //2. Memoisation:)
        Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        return solveMemo(arr,k,0,arr.length-1);
        // TC : O()
        
    }
    public static int solveRec(int arr[],int k,int i,int j){
        //Base Case ::
        if(i==j) return arr[i];
        if(j-i+1<=k){
            int maxi=0;
            for(int e=i;e<=j;e++){
                maxi =Math.max(arr[e],maxi);
            }
            return  maxi*(j-i+1);
        }
        //K loop:
        int ans =0;
        for(int p=i;p<i+k && p<j;p++){
            int temp = solveRec(arr,k,i,p)+solveRec(arr,k,p+1,j);
            ans =Math.max(temp,ans);
        }
        return ans;
    }
    public static int solveMemo(int arr[],int k,int i,int j){
        //Base Case ::
        if(i==j) return arr[i];
        if(memo[i][j]!=-1){
            return memo[i][j];
        }
        if(j-i+1<=k){
            int maxi=0;
            for(int e=i;e<=j;e++){
                maxi =Math.max(arr[e],maxi);
            }
            return  maxi*(j-i+1);
        }
        //K loop:
        int ans =Integer.MIN_VALUE;
        for(int p=i;p<i+k && p<j;p++){
            int leftpart,rightpart;
            if(memo[i][p]!=-1){
                leftpart=memo[i][p];
            }else{
                leftpart=solveMemo(arr,k,i,p);
            }

            if(memo[p+1][j]!=-1){
                rightpart =memo[p+1][j];
            }else{
                rightpart=solveMemo(arr,k,p+1,j);
            }
            int temp = leftpart+rightpart;
            ans =Math.max(temp,ans);
            memo[i][j]=ans;
        }
        memo[i][j]=ans;
        return ans;
    }
}
