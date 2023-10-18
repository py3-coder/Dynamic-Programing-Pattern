
/*
673. Number of Longest Increasing Subsequence
Medium

Given an integer array nums, return the number of longest increasing subsequences.
Notice that the sequence has to be strictly increasing.

Example 1:
Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].

Example 2:
Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.

Constraints:
1 <= nums.length <= 2000
-106 <= nums[i] <= 106
*/

class Solution {
    public int findNumberOfLIS(int[] nums) {
        // LIS Pure::
        //edge case :: 
        if(nums.length==1){
            return 1;
        }
        return solveLIS(nums);
        
    }
    public static int solveLIS(int[] arr){
        int n=arr.length;
        HashMap<Integer,Integer> map =new HashMap<>();
        int len[] = new int[n];
        int cnt[] = new int[n];
        Arrays.fill(len,1);
        Arrays.fill(cnt,1);

        int maxi =0;
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j]){
                    if(1+len[j]>len[i]){
                        len[i] =1+len[j];
                        cnt[i]=cnt[j];
                    }else if(1+len[j]==len[i]){
                        cnt[i]+=cnt[j];
                    }
                }
            }
            maxi =Math.max(maxi,len[i]);
        }
        int num=0;
        for(int i=0;i<n;i++){
            if(maxi==len[i]){
                num +=cnt[i];
            }
        }
        return num;
    }
}
