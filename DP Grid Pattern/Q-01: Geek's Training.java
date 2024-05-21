/*
Geek's Training
MediumAccuracy: 49.98

Geek is going for n days training program, he can perform any one of these three activities Running, Fighting, and Learning Practice. 
Each activity has some point on each day. as Geek wants to improve all his skills, he can't do the same activity on two consecutive days,
help Geek to maximize his merit points as we were given a 2D array of n*3 points corresponding to each day and activity.

Example:
Input:
n = 3
point= [[1,2,5],[3,1,1],[3,3,3]]
Output:
11
Explanation:
Geek will learn a new move and earn 5 point then on second
day he will do running and earn 3 point and on third day
he will do fighting and earn 3 points so, maximum point is 11.
Your Task:
You don't have to read input or print anything. Your task is to complete the function maximumPoints() which takes the integer n and 2 D array points and returns the maximum point he can earn.

Expected Time Complexity: O(n)
Expected Space Complexity: O(n2)

Constraint:
1 <=  n <= 100000
1 <=  point[i] <= 100
*/
class Solution{
    static int[][] memo;
    public int maximumPoints(int points[][],int N){
        // Recursion - TLE
        
        // Memoization - 
        // TC : O(n*4*3)
        //SC : O(n+4)
        memo = new int[N+1][4];
        Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        //return solveRec(N,-1,points);
        
        //// TC : O(n*4*3)
        //SC : O(n+4)
        //return solveTab(N,points);
        
        //TC : O(n*4*3)
        // SC : O(4)
        return solveOpt(N,points);
    }
    public int solveRec(int n,int opt,int[][] points){
        //Base Case:
        
        if(n<=0) return 0;
        if(memo[n-1][opt+1]!=-1){
            return memo[n-1][opt+1];
        }
        int maxscore=0;
        for(int i=0;i<3;i++){
            if(i!=opt){
                int currsum = points[n-1][i] + solveRec(n-1,i,points);
                maxscore = Math.max(currsum,maxscore);
            }
        }
        return memo[n-1][opt+1]=maxscore;
    }
    public int solveTab(int n,int[][] points){
        int[][] tab = new int[n][4];
        tab[0][0] = Math.max(points[0][1], points[0][2]);
        tab[0][1] = Math.max(points[0][0], points[0][2]);
        tab[0][2] = Math.max(points[0][0], points[0][1]);
        tab[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));
        
        
        
        for(int i=1;i<n;i++){
            for(int j=0;j<4;j++){
                tab[i][j]=0;
                for(int k=0;k<3;k++){
                    if(k!=j){
                        int currsum = points[i][k] + tab[i-1][k];
                        tab[i][j] = Math.max(currsum,tab[i][j]);
                    }
                }
            }
            
        }
        return tab[n-1][3];
    }
    // Space Optimised : 
    public int solveOpt(int n,int[][] points){
        int[] prev = new int[4];
        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));
        
        for(int i=1;i<n;i++){
            int[] curr = new int[4];
            for(int j=0;j<4;j++){
                curr[j]=0;
                for(int k=0;k<3;k++){
                    if(k!=j){
                        curr[j] = Math.max(points[i][k] + prev[k],curr[j]);
                    }
                }
            }
            prev = curr;
            
        }
        return prev[3];
    }
    
}
