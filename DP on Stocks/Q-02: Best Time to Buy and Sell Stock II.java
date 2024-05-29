/*
122. Best Time to Buy and Sell Stock II

You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.
Find and return the maximum profit you can achieve.

Example 1:
Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.

Example 2:
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.

Example 3:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.

Constraints:
1 <= prices.length <= 3 * 104
0 <= prices[i] <= 104
*/
class Solution {
    static int[][] memo ;
    public int maxProfit(int[] prices) {
        int n = prices.length;
        memo = new int[n][2];
        Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        //return solveRec(prices,0,1);
        //return solveTab(prices);
        
        return solveOpt(prices);
    }
    public int solveRec(int[] prices , int indx , int buy){
        //Base Case :-
        if(indx >= prices.length){
            return  0;
        }
        if(memo[indx][buy]!=-1){
            return memo[indx][buy];
        }
        int profit =0;
        if(buy==1){
            profit = -prices[indx]+ solveRec(prices,indx+1,0);
        }else{
            profit = prices[indx] + solveRec(prices,indx+1,1);
        }
        int skip = solveRec(prices,indx+1,buy);
        return memo[indx][buy] = Math.max(profit,skip);
    }
    public int solveTab(int[] prices){
        int n = prices.length;
        int[][] tab = new int[n+1][2];
        
        for(int indx = n-1 ;indx>=0 ;indx--){
            for(int buy =0;buy<2;buy++){
                int profit =0;
                if(buy==1){
                    profit = -prices[indx]+ tab[indx+1][0];
                }else{
                    profit = prices[indx] + tab[indx+1][1];
                }
                int skip =tab[indx+1][buy];
                tab[indx][buy] = Math.max(profit,skip);
            }
        }
        return tab[0][1];
    }
    public int solveOpt(int[] prices){
        int n = prices.length;
        
        int[] curr = new int[2];
        int[] next =new int[2];
        for(int indx = n-1 ;indx>=0 ;indx--){
            for(int buy =0;buy<2;buy++){
                int profit =0;
                if(buy==1){
                    profit = -prices[indx]+ next[0];
                }else{
                    profit = prices[indx] + next[1];
                }
                int skip =next[buy];
                curr[buy] = Math.max(profit,skip);
            }
            next = curr ;
        }
        return curr[1];
    }
}
