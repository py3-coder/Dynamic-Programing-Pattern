/*
188. Best Time to Buy and Sell Stock IV
Hard

You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:
Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.

Example 2:
Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

Constraints:

1 <= k <= 100
1 <= prices.length <= 1000
0 <= prices[i] <= 1000
*/
class Solution {
    //static int[][][] memo = new int[1001][2][101];
    static HashMap<String,Integer> map = new HashMap<>();
    public int maxProfit(int k, int[] prices) {
        //1. Recursion::
        //return solveRec(prices,0,0,k);

        // 2. Memoization::
        // 3 chnaging parameter:) Use either 3dp or hashmap:)
        //return solveMemo(prices,0,0,k);

        //3d -dp way:)
        // for(int[][] arr:memo){
        //     for(int[] si:arr){
        //         Arrays.fill(si,-1);
        //     }
        // }
        // return solveMemo(prices,0,0, k);

        return solveTab(prices,k);

        //return solveTabSpaceOptmised(prices);
    }
    public static int solveRec(int[] prices,int indx,int buy,int cnt){
        if(indx==prices.length) return 0;
        if(cnt<0) return 0;
        
        int profit=0;
        if(buy==0){
            // buy::
            profit =-prices[indx]+solveRec(prices,indx+1,1,cnt-1);
        }else{
            //sell::
            profit =prices[indx]+solveRec(prices,indx+1,0,cnt);
        }
        int skip =solveRec(prices,indx+1,buy,cnt);
        
        return Math.max(skip,profit);
    }
    public static int solveMemo(int[] prices,int indx,int buy,int cnt){
        if(indx==prices.length) return 0;
        if(cnt<0) return 0;

        String str =indx+"-"+buy+"-"+cnt;
        if(map.containsKey(str)){
            return map.get(str);
        }
        int profit=0;
        if(buy==0){
            // buy::
            profit =-prices[indx]+solveMemo(prices,indx+1,1,cnt-1);
        }else{
            //sell::
            profit =prices[indx]+solveMemo(prices,indx+1,0,cnt);
        }
        int skip =solveMemo(prices,indx+1,buy,cnt);
        map.put(str,Math.max(skip,profit));
        return Math.max(skip,profit);
    }
  public static int solveMemo(int[] prices,int indx,int buy,int k){
        if(k<0) return 0;
        if(indx>=prices.length) return 0;

        if(memo[indx][buy][k]!=-1){
            return memo[indx][buy][k];
        }
        int profit =0;
        if(buy==0){
            profit = Math.max(-prices[indx]+solveMemo(prices,indx+1,1,k),solveMemo(prices,indx+1,buy,k));
        }else{
            profit = Math.max(prices[indx]+solveMemo(prices,indx+1,0,k-1),solveMemo(prices,indx+1,buy,k));
        }
        return memo[indx][buy][k]=profit;
    }
    public static int solveTab(int[] prices,int k){
        //Tabulation::)
        int n=prices.length;
        int tab[][][] = new int[n+1][2][k+1];

        for(int ind=n-1;ind>=0;ind--){
            for(int buy=0;buy<=1;buy++){
                for(int cap=1;cap<=k;cap++){
                     if (buy == 0) {
                        tab[ind][buy][cap] = Math.max(0 + tab[ind + 1][0][cap],
                                -prices[ind] + tab[ind + 1][1][cap]);
                    }
                    if (buy == 1) { 
                        tab[ind][buy][cap] = Math.max(0 + tab[ind + 1][1][cap],
                                prices[ind] + tab[ind + 1][0][cap - 1]);
                    }
                }
            }
        }
        return  tab[0][0][k];
    }
    public static int solveTabSpaceOptmised(int[] prices){
        //Sapce Optimied:: Tabulation::)
        int n=prices.length;
        
        int ahead[][] = new int[2][3];
        int curr[][] = new int[2][3];

        for(int ind=n-1;ind>=0;ind--){
            for(int buy=0;buy<=1;buy++){
                for(int cap=1;cap<=2;cap++){
                     if (buy == 0) {
                        curr[buy][cap] = Math.max(0 + ahead[0][cap],
                                -prices[ind] + ahead[1][cap]);
                    }
                    if (buy == 1) { 
                        curr[buy][cap] = Math.max(0 + ahead[1][cap],
                                prices[ind] + ahead[0][cap - 1]);
                    }
                }
            }
            for (int i = 0; i < 2; i++) {
                for (int j = 1; j < 3; j++) {
                    ahead[i][j] = curr[i][j];
                }
            }
        }
        
        return  ahead[0][2];
    }
}
