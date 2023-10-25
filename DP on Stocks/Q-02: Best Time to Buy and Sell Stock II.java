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
    static int memo[][] =new int[30001][2];
    public int maxProfit(int[] prices) {
        //Recursion::)
        //return solveRec(prices,0,1);

        // Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        // return solveMemo(prices,0,1);

        return solveTab(prices);
        
    }
    public static int solveRec(int[] prices ,int indx,int buy){
        //Base Case ::
        if(indx==prices.length) return 0;
        int profit=0;
        if(buy==1){
            profit = -prices[indx]+solveRec(prices,indx+1,0);
        }else{
            profit =prices[indx]+solveRec(prices,indx+1,1);
        }
        int skip =solveRec(prices,indx+1,buy);
        return Math.max(profit,skip);
    }
    public static int solveMemo(int[] prices,int indx,int buy){
        //Base Case ::
        if(indx==prices.length) return 0;
        if(memo[indx][buy]!=-1){
            return memo[indx][buy];
        }
        int profit=0;
        if(buy==1){
            profit = -prices[indx]+solveMemo(prices,indx+1,0);
        }else{
            profit =prices[indx]+solveMemo(prices,indx+1,1);
        }
        int skip =solveMemo(prices,indx+1,buy);
        return memo[indx][buy]=Math.max(profit,skip);
    }
    public static int solveTab(int[] prices){
        int n=prices.length;
        int tab[][] =new int[n+1][2];
        Arrays.stream(tab).forEach(a->Arrays.fill(a,-1));
        tab[n][0]=0;
        tab[n][1]=0;

        int profit=0;
        for(int i=n-1;i>=0;i--){
            for(int j=0;j<=1;j++){
                if(j==1){
                    profit = Math.max(prices[i]+tab[i+1][0],tab[i+1][1]);
                }
                if(j==0){
                    profit = Math.max(-prices[i]+tab[i+1][1],tab[i+1][0]);
                }
                tab[i][j]=profit;
            }
        }  
        return tab[0][0];      
    }
}
