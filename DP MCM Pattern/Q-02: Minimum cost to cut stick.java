/*
Minimum Cost to Cut a Stick
Hard
Given a wooden stick of length n units. The stick is labelled from 0 to n. For example, a stick of length 6 is labelled as follows:
Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.
You should perform the cuts in order, you can change the order of the cuts as you wish.
The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts.
When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the length of the stick before the cut).
Please refer to the first example for a better explanation.
Return the minimum total cost of the cuts.

Example 1:
Input: n = 7, cuts = [1,3,4,5]
Output: 16
Explanation: Using cuts order = [1, 3, 4, 5] as in the input leads to the following scenario:

The first cut is done to a rod of length 7 so the cost is 7. The second cut is done to a rod of length 6 (i.e. the second part of the first cut), the third is done to a rod of length 4 and the last cut is to a rod of length 3. The total cost is 7 + 6 + 4 + 3 = 20.
Rearranging the cuts to be [3, 5, 1, 4] for example will lead to a scenario with total cost = 16 (as shown in the example photo 7 + 4 + 3 + 2 = 16).
Example 2:

Input: n = 9, cuts = [5,6,1,4,2]
Output: 22
Explanation: If you try the given cuts ordering the cost will be 25.
There are much ordering with total cost <= 25, for example, the order [4, 6, 5, 2, 1] has total cost = 22 which is the minimum possible.
 

Constraints:

2 <= n <= 106
1 <= cuts.length <= min(n - 1, 100)
1 <= cuts[i] <= n - 1
All the integers in cuts array are distinct.
*/
class Solution {
    static int memo[][] = new int[102][102];
    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        int arr[]= new int[cuts.length+2];
        arr[0]=0; arr[arr.length-1]=n;
        for(int i=0;i<cuts.length;i++){
            arr[i+1] =cuts[i];
        }
        //return solveRec(arr,1,cuts.length);
        
        // Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        // return solveMemo(arr,1,cuts.length);

        return solveTab(arr,cuts.length);
        
    }
    public static int solveRec(int arr[],int i,int j){
        //Base Case::
        if(i>j) return 0; 
        int ans =Integer.MAX_VALUE;
        for(int k=i;k<=j;k++){
                int temp = solveRec(arr,i,k-1)+ solveRec(arr,k+1,j)+(arr[j+1]-arr[i-1]);
                ans =Math.min(temp,ans);
        }
        return  ans;
    }
    public static int solveMemo(int arr[],int i,int j){
        //Base Case::
        if(i>j) return 0; 
        if(memo[i][j]!=-1){
            return memo[i][j];
        }
        int ans =Integer.MAX_VALUE;
        for(int k=i;k<=j;k++){
            int left,right;
            if(memo[i][k-1]!=-1){
                left=memo[i][k-1];
            }else{
                left =solveMemo(arr,i,k-1);
                memo[i][k-1]=left;
            }
            if(memo[k+1][j]!=-1){
                right=memo[k+1][j];
            }else{
                right=solveMemo(arr,k+1,j);
                memo[k+1][j] =right;
            }
            int temp = left+right+(arr[j+1]-arr[i-1]);
            ans =Math.min(temp,ans);
            memo[i][j]=ans;
        }
        return  memo[i][j]=ans;
    }
    public static int solveTab(int arr[],int len){
        //Tabulation:: Bottom up:
        int dp[][] = new int[len+2][len+2];
        // 1.base case--> 0 whne i>j : fill array with 0
        //2. Reverse the loop and take care of i>j case carefully:
        for(int i=len;i>0;i--){
            for(int j=1;j<=len;j++){
                if(i>j) continue;
                int ans =Integer.MAX_VALUE;
                for(int k=i;k<=j;k++){
                    int temp = dp[i][k-1]+dp[k+1][j]+ (arr[j+1]-arr[i-1]);
                    ans =Math.min(temp,ans);
                }
                dp[i][j]=ans;
            }
        }
        return dp[1][len];
    }
}
