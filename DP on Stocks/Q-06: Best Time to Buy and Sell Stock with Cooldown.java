/*
309. Best Time to Buy and Sell Stock with Cooldown
Medium

You are given an array prices where prices[i] is the price of a given stock on the ith day.
Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:
Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]

Example 2:
Input: prices = [1]
Output: 0
 
Constraints:
1 <= prices.length <= 5000
0 <= prices[i] <= 1000
*/
static int memo[][] = new int[5001][2];
    public int maxProfit(int[] prices) {
        //Recursion:)
        // TC : O(2^n)  SC: O(1)+O(n) --aux space::
        //return solveRec(prices,0,0);

        //Memoisation:)
        // TC : O(n*2) SC: O(1)+O(n)--aux space::
        // Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        // return solveMemo(prices,0,0);

        // TC : O(n*2) SC : O(n*2)
        //Bottom Up approch ::
        //return solveTab(prices);

        // TC : O(n) SC: O(n)
        //return solveTabOpt(prices);
 
        // TC : O(n)  SC:O(1)
        return solveTabSpaceOpt(prices);
    }
    private int solveRec(int[] prices ,int indx,int buy){
        //Base Case ::
        if(indx>=prices.length) return 0;
        int profit=0;
        if(buy==0 ){
            profit =Math.max(-prices[indx]+solveRec(prices,indx+1,1),solveRec(prices,indx+1,0));
        }else{
            profit =Math.max(prices[indx]+solveRec(prices,indx+2,0),solveRec(prices,indx+1,1));
        }
        return profit;
    }
    private int solveMemo(int[] prices ,int indx,int buy){
        //Base Case ::
        if(indx>=prices.length) return 0;

        if(memo[indx][buy]!=-1){
            return memo[indx][buy];
        }
        int profit=0;
        if(buy==0 ){
            profit =Math.max(-prices[indx]+solveMemo(prices,indx+1,1),solveMemo(prices,indx+1,0));
        }else{
            profit =Math.max(prices[indx]+solveMemo(prices,indx+2,0),solveMemo(prices,indx+1,1));
        }
        return memo[indx][buy]=profit;
    } 
    private int solveTab(int[] prices){
        //Tabulation::)
        int n =prices.length;
        int tab[][] = new int[n+2][2];

        for(int indx=n-1;indx>=0;indx--){
            for(int j=0;j<=1;j++){
                if(j==0 ){
                    tab[indx][j]=Math.max(-prices[indx]+tab[indx+1][1],tab[indx+1][0]);
                }
                if(j==1){
                    tab[indx][j]=Math.max(prices[indx]+tab[indx+2][0],tab[indx+1][1]);
                }
            }
        }
        return tab[0][0];
    } 
    private int solveTabOpt(int[] prices){
        //Tabulation::)
        int n =prices.length;
        int tab[][] = new int[n+2][2];

        for(int indx=n-1;indx>=0;indx--){
            tab[indx][0]=Math.max(-prices[indx]+tab[indx+1][1],tab[indx+1][0]);
            tab[indx][1]=Math.max(prices[indx]+tab[indx+2][0],tab[indx+1][1]);
        }
        return tab[0][0];
    }
    private int solveTabSpaceOpt(int[] prices){
        //Tabulation::) Striver APPROCH::
        int n =prices.length;
        int ahead2[]=new int[2];
        int ahead[] =new int[2];
        int curr[]  =new int[2];

        for(int indx=n-1;indx>=0;indx--){
            curr[0]=Math.max(-prices[indx]+ahead[1],ahead[0]);
            curr[1]=Math.max(prices[indx]+ahead2[0],ahead[1]);

            //Now updated :: ahead2 to ahead2,ahead1 to curr
            System.arraycopy(ahead,0,ahead2,0,2);
            System.arraycopy(curr,0,ahead,0,2);
        }
        return curr[0];
    }
// Same way diff code :-
//--------------------------------------------------------------------------------------
class Solution {
    static int[][] memo;
    public int maxProfit(int[] prices) {
        int n =prices.length;
        // memo = new int[n+1][2];
        // Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        //return solveRec(prices,0,0);
        
        //return solveTab(prices);
        
        return solveOpt(prices);
    }
    
    public int solveRec(int[] prices, int indx, int buy){
        //Base Case ::
        if(indx>=prices.length){
            return 0;
        }
        if(memo[indx][buy]!=-1){
            return memo[indx][buy];
        }
        int profit =0;
        if(buy==0){
           profit =-prices[indx] +solveRec(prices,indx+1,1);    
        }else{
            profit = prices[indx] + solveRec(prices,indx+2,0) ; 
        }
        int skip = solveRec(prices,indx+1,buy);
        
        return memo[indx][buy] = Math.max(profit,skip);
    }
    public int solveTab(int[] prices){
        int n =prices.length;
        int[][] tab =  new int[n+2][2];
        
        for(int i=n-1;i>=0;i--){
            for(int j=0;j<2;j++){
                int profit =0;
                if(j==0){
                   profit =-prices[i] +tab[i+1][1];    
                }else{
                    profit = prices[i] +tab[i+2][0] ; 
                }
                int skip = tab[i+1][j];
                tab[i][j] = Math.max(profit,skip);     
            }
        }
        return tab[0][0];
    }
    public int solveOpt(int[] prices){
        int n =prices.length;
        int[] curr =  new int[2];
        int[] ahead =  new int[2];
        int[] ahead2 = new int[2];
        
        for(int i=n-1;i>=0;i--){
            for(int j=0;j<2;j++){
                int profit =0;
                if(j==0){
                   profit =-prices[i] +ahead[1];    
                }else{
                    profit = prices[i] +ahead2[0]; 
                }
                int skip = ahead[j];
                curr[j] = Math.max(profit,skip);     
            }
            System.arraycopy(ahead, 0, ahead2, 0, 2);
            System.arraycopy(curr, 0, ahead, 0, 2);
        }
        return curr[0];
    }
}
