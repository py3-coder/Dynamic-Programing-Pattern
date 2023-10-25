/*
714. Best Time to Buy and Sell Stock with Transaction Fee

You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
Note:
You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
The transaction fee is only charged once for each stock purchase and sale.
 
Example 1:
Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

Example 2:
Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6
 
Constraints:

1 <= prices.length <= 5 * 104
1 <= prices[i] < 5 * 104
0 <= fee < 5 * 104
*/
class Solution {
    static int memo[][] =new int[50001][2];
    public int maxProfit(int[] prices, int fee) {
        //Recursion::)
        //return solveRec(prices,0,1,fee);
        // TLE

        // Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        // return solveMemo(prices,0,1,fee);
        // TLE

        //TC : O(n*2)  // SC: O
        return solveTab(prices,fee);
        
    } 
    public static int solveRec(int[] prices ,int indx,int buy,int fee){
        //Base Case ::
        if(indx==prices.length) return 0;
        int profit=0;
        if(buy==1){
            profit = -prices[indx]-fee+solveRec(prices,indx+1,0,fee);
        }else{
            profit =prices[indx]+solveRec(prices,indx+1,1,fee);
        }
        int skip =solveRec(prices,indx+1,buy,fee);
        return Math.max(profit,skip);
    }
    public static int solveMemo(int[] prices,int indx,int buy,int fee){
        //Base Case ::
        if(indx==prices.length) return 0;
        if(memo[indx][buy]!=-1){
            return memo[indx][buy];
        }
        int profit=0;
        if(buy==1){
            profit = -prices[indx]-fee+solveMemo(prices,indx+1,0,fee);
        }else{
            profit =prices[indx]+solveMemo(prices,indx+1,1,fee);
        }
        int skip =solveMemo(prices,indx+1,buy,fee);
        return Math.max(profit,skip);
    }
    public static int solveTab(int[] prices,int fee){
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
                    profit = Math.max(-prices[i]-fee+tab[i+1][1],tab[i+1][0]);
                }
                tab[i][j]=profit;
            }
        }  
        return tab[0][0];      
    }
}
