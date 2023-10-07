/*
Shortest Common Supersequence
Medium
Given two strings X and Y of lengths m and n respectively, find the length of the smallest string which has both, X and Y as its sub-sequences.
Note: X and Y can have both uppercase and lowercase letters.

Example 1
Input:
X = abcd, Y = xycd
Output: 6
Explanation: Shortest Common Supersequence
would be abxycd which is of length 6 and
has both the strings as its subsequences.

Example 2
Input:
X = efgh, Y = jghi
Output: 6
Explanation: Shortest Common Supersequence
would be ejfghi which is of length 6 and
has both the strings as its subsequences.
Your Task:
Complete shortestCommonSupersequence() function that takes X, Y, m, and n as arguments and returns the length of the required string.

Expected Time Complexity: O(Length(X) * Length(Y)).
Expected Auxiliary Space: O(Length(X) * Length(Y)).

Constraints:
1<= |X|, |Y| <= 100
*/
class Solution
{
    //Function to find length of shortest common supersequence of two strings.
    public static int shortestCommonSupersequence(String X,String Y,int m,int n)
    {
        // LCS - Modified Liitle Bit :
        // TC : O(n*m) SC: O(n*m)
        return solveTabLCS(X,Y,m,n);
    }
    public static int solveTabLCS(String X,String Y,int m,int n){
        //Tabulation::
        int tab[][] = new int[m+1][n+1];
        //base by default handle by 0:
        
        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(X.charAt(i-1)==Y.charAt(j-1)){
                    tab[i][j] =1+tab[i-1][j-1];
                }else{
                    tab[i][j] =Math.max(tab[i-1][j],tab[i][j-1]);
                }
            }
        }
        return m+n-tab[m][n];
    }
}
