/*
983. Minimum Cost For Tickets
Medium

You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.
Train tickets are sold in three different ways:

a 1-day pass is sold for costs[0] dollars,
a 7-day pass is sold for costs[1] dollars, and
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.

For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
Return the minimum number of dollars you need to travel every day in the given list of days.

Example 1:

Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total, you spent $11 and covered all the days of your travel.

Example 2:
Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
Output: 17
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
In total, you spent $17 and covered all the days of your travel.
 
Constraints:

1 <= days.length <= 365
1 <= days[i] <= 365
days is in strictly increasing order.
costs.length == 3
1 <= costs[i] <= 1000
*/


class Solution {
    static int[][] memo= new int[366][500];
    static int[]memoB= new int[366];
    public int mincostTickets(int[] days, int[] costs) {
        //Recursion :: TC =O(3^n)
        // SC : O(1)+O(n)
        //return solveRec(0,-1,days,costs);

        // Memoisation ::
        // TC : O(n*n)
        // SC: O(n*n) +O(n)
        //  Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        // return solveMemo(0,0,days,costs);

        //Optimised:: Using Binary Search 
        // TC : O(nlogn)
        // SC : O(n)
        Arrays.fill(memoB,-1);
        return solveMemoBS(0,days,costs);
    }
    public static int solveRec(int indx,int validity,int[] days,int[] cost){
        //Base Case::
        if(indx>=days.length) return 0;

        //choices based on validity::
        if(validity<days[indx]){
            int oneday = cost[0]+solveRec(indx+1,days[indx]+1-1,days,cost);
            int sevenday =cost[1] +solveRec(indx+1,days[indx]+7-1,days,cost);
            int thirtyday =cost[2]+solveRec(indx+1,days[indx]+30-1,days,cost);

            return Math.min(oneday,Math.min(sevenday,thirtyday));
        }else{
            return solveRec(indx+1,validity,days,cost);
        }
    }
    public static int solveMemo(int indx,int validity,int[] days,int[] cost){
        //Base Case::
        if(indx>=days.length) return 0;
        if(memo[indx][validity]!=-1){
            return memo[indx][validity];
        }
        //choices based on validity::
        if(validity<days[indx]){
            int oneday = cost[0]+solveMemo(indx+1,days[indx]+1-1,days,cost);
            int sevenday =cost[1] +solveMemo(indx+1,days[indx]+7-1,days,cost);
            int thirtyday =cost[2]+solveMemo(indx+1,days[indx]+30-1,days,cost);

            return memo[indx][validity]=Math.min(oneday,Math.min(sevenday,thirtyday));
        }else{
            return memo[indx][validity] =solveMemo(indx+1,validity,days,cost);
        }
    }
    public static int solveMemoBS(int indx,int[] days,int[] cost){
        //Base Case::
        if(indx>=days.length) return 0;
        if(memoB[indx]!=-1){
            return memoB[indx];
        }
        int day = binarySearch(indx,days[indx]+1,days);
        int oneday = cost[0]+solveMemoBS(day,days,cost);
        
        day =binarySearch(indx,days[indx]+7,days);
        int sevenday =cost[1] +solveMemoBS(day,days,cost);

        day =binarySearch(indx,days[indx]+30,days);
        int thirtyday =cost[2]+solveMemoBS(day,days,cost);

        return memoB[indx]=Math.min(oneday,Math.min(sevenday,thirtyday));
        
    }
    //trying to find the first target index if not present first greatest value index to it::
    public static int binarySearch(int low,int target,int days[]){
        int high=days.length-1;
        while(low<=high){
            int mi =low+(high-low)/2;
            if(days[mi]==target){
                return mi;
            }else if(days[mi]>target){
                high=mi-1;
            }else{
                low=mi+1;
            }
        }
        return low;
    }

}
