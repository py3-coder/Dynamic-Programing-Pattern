/*
Longest Common Subsequence: Parent Problem:)
Medium
Given two strings, find the length of longest subsequence present in both of them. Both the strings are in uppercase latin alphabets.

Example 1:
Input:
A = 6, B = 6
str1 = ABCDGH
str2 = AEDFHR
Output: 3
Explanation: LCS for input strings “ABCDGH” and “AEDFHR” is “ADH” of length 3.

Example 2:
Input:
A = 3, B = 2
str1 = ABC
str2 = AC
Output: 2
Explanation: LCS of "ABC" and "AC" is "AC" of length 2.
Your Task:

Complete the function lcs() which takes the length of two strings respectively and two strings as input parameters and returns the length of the longest subsequence present in both of them.

Expected Time Complexity : O(|str1|*|str2|)
Expected Auxiliary Space: O(|str1|*|str2|)

Constraints:
1<=size(str1),size(str2)<=103
*/

class Solution
{
    //Function to find the length of longest common subsequence in two strings.
    static int[][] memo = new int[1001][1001];
    static int lcs(int x, int y, String s1, String s2)
    {
        // Lets Play With Recursion to DP:)
        // 1.Recursion::
        // TC : O(2^(n+m)
        // TLE:
        //return solveRec(x,y,s1,s2);
        
        //2. Memoization Call:
        // Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        // return solveMemo(x,y,s1,s2);
        // TC : O(n*m)
        
        //3.Tabulation Call::
        return solveTab(x,y,s1,s2);
    }
    static int solveRec(int n,int m,String s1,String s2){
        //Base Case::
        if(n==0 || m==0){
            return 0;
        }
         // If characters are the same, we can extend the subsequence length by 1
        if(s1.charAt(n-1)==s2.charAt(m-1)){
            return 1+solveRec(n-1,m-1,s1,s2);
        }else{
            // If characters are different, we have two choices:
            // 1. Exclude the last character of s1 and compare s1[0..n-2] with s2[0..m-1]
            // 2. Exclude the last character of s2 and compare s1[0..n-1] with s2[0..m-2]
            return Math.max(solveRec(n-1,m,s1,s2),solveRec(n,m-1,s1,s2));
        }
    }
    public static int solveMemo(int n,int m,String s1,String s2){
        //Bottom UP :DP-- Memoization:
        //Base Case::
        if(n==0 || m==0){
            return 0;
        }
        //check pre computed::
        if(memo[n][m]!=-1){
            return memo[n][m];
        }
        //if char are same :: means we can +1 the subsequence length;
        if(s1.charAt(n-1)==s2.charAt(m-1)){
            return memo[n][m]=1+solveMemo(n-1,m-1,s1,s2);
        }else{
            return memo[n][m]=Math.max(solveMemo(n-1,m,s1,s2),solveMemo(n,m-1,s1,s2));
        }
    }
    public static int solveTab(int n,int m,String s1,String s2){
        //Top-Down: DP--Tabulation
        //Base --> Initlisation
        int tab[][] = new int[n+1][m+1];
        for(int i=0;i<n+1;i++){
            for(int j=0;j<m+1;j++){
                if(i==0) tab[i][j]=0;
                if(j==0) tab[i][j]=0;
            }
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    tab[i][j] =1+tab[i-1][j-1];
                }else{
                    tab[i][j] =Math.max(tab[i-1][j],tab[i][j-1]);
                }
            }
        }
        return tab[n][m];
    }
}
