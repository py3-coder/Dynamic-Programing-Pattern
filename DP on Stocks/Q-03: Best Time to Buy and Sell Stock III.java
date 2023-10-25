/*
123. Best Time to Buy and Sell Stock III
Hard

You are given an array prices where prices[i] is the price of a given stock on the ith day.
Find the maximum profit you can achieve. You may complete at most two transactions.
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:
Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

Example 2:
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.

Example 3:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 105
*/

class Solution {
    public int maxProfit(int[] prices) {
        //1. Recursion::
        //return solveRec(prices,0,0,0);

        //2. Memoization::
        // 3 chnaging parameter:) Use either 3dp or hashmap:)
        // HashMap<String,Integer> map =new HashMap<>();
        // return solveMemo(prices,0,0,0,map);

        //return solveTab(prices);

        return solveTabSpaceOptmised(prices);
        
    }
    public static int solveRec(int[] prices,int indx,int buy,int cnt){
        if(indx==prices.length) return 0;
        if(cnt>2) return 0;

        int profit=0;
        if(buy==0){
            // buy::
            profit =-prices[indx]+solveRec(prices,indx+1,1,cnt+1);
        }else{
            //sell::
            profit =prices[indx]+solveRec(prices,indx+1,0,cnt);
        }
        int skip =solveRec(prices,indx+1,buy,cnt);
        return Math.max(skip,profit);
    }
    public static int solveMemo(int[] prices,int indx,int buy,int cnt,HashMap<String,Integer> map){
        if(indx==prices.length) return 0;
        if(cnt>2) return 0;

        String key = indx+"-"+buy+"-"+cnt;
        if(map.containsKey(key)){
            return map.get(key);
        }
        int profit=0;
        if(buy==0){
            // buy::
            profit =-prices[indx]+solveMemo(prices,indx+1,1,cnt+1,map);
        }else{
            //sell::
            profit =prices[indx]+solveMemo(prices,indx+1,0,cnt,map);
        }
        int skip =solveMemo(prices,indx+1,buy,cnt,map);
        map.put(key,Math.max(skip,profit));
        return Math.max(skip,profit);
    }
    public static int solveTab(int[] prices){
        //Tabulation::)
        int n=prices.length;
        int tab[][][] = new int[n+1][2][3];

        for(int ind=n-1;ind>=0;ind--){
            for(int buy=0;buy<=1;buy++){
                for(int cap=1;cap<=2;cap++){
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
        return  tab[0][0][2];
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
