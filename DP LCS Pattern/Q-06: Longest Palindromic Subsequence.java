/*
Longest Palindromic Subsequence
Medium

NOTE: Subsequence of a given sequence is a sequence that can be derived from the given sequence by deleting some or no elements without changing the order of the remaining elements

Example 1:

Input:
S = "bbabcbcab"
Output: 7
Explanation: Subsequence "babcbab" is the
longest subsequence which is also a palindrome.


Example 2:
Input: 
S = "abcd"
Output: 1
Explanation: "a", "b", "c" and "d" are
palindromic and all have a length 1.

Your Task:
You don't need to read input or print anything. Your task is to complete the function longestPalinSubseq() which takes the string S as input and returns an integer denoting the length of the longest palindromic subsequence of S.

Expected Time Complexity: O(|S|*|S|).
Expected Auxiliary Space: O(|S|*|S|).

Constraints:
1 ≤ |S| ≤ 1000
*/
class Solution
{
    public int longestPalinSubseq(String S)
    {
        //Lets Play With DP:)
        String rev="";
        for(char ch: S.toCharArray()){
            rev =ch+rev;
        }
        int n=S.length();
        return solveTabLCS(S,rev,n,n);
        
        
    }
    public static int solveTabLCS(String X,String Y,int n,int m){
        // Tabulation::
        
        int tab[][] = new int[n+1][m+1];
        //Base case by default handle::
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(X.charAt(i-1)==Y.charAt(j-1)){
                    tab[i][j]=1+tab[i-1][j-1];
                }else{
                    tab[i][j]= Math.max(tab[i-1][j],tab[i][j-1]);
                }
            }
        }
        return tab[n][m];
    }
}
