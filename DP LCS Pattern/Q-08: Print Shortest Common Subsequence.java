/*
1092. Shortest Common Supersequence
Hard

Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.
If there are multiple valid strings, return any of them.

A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.

Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
Example 2:

Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"
 

Constraints:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.
*/

class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        return solveTabLCS(str1,str2);
    }
    public static String solveTabLCS(String str1,String str2){
        //Tabulation -- LCS 
        int n=str1.length();
        int m=str2.length();

        int tab[][] = new int[n+1][m+1];
        //Initlisation:: Base case handle by default value 0 
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    tab[i][j] =1+tab[i-1][j-1];
                }else{
                    tab[i][j] =Math.max(tab[i-1][j],tab[i][j-1]);
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        int i=n,j=m;
        while(i>0 && j>0){
            if(str1.charAt(i-1)==str2.charAt(j-1)){
                ans.append(str1.charAt(i-1));
                i--;
                j--;
            }else{
                if(tab[i][j-1]>tab[i-1][j]){
                    ans.append(str2.charAt(j-1));
                    j--;
                }else{
                    ans.append(str1.charAt(i-1));
                    i--;
                }
            }
        }
        while(i>0){
            ans.append(str1.charAt(i-1));
            i--;
        }
        while(j>0){
            ans.append(str2.charAt(j-1));
            j--;
        }

        return ans.reverse().toString();
    }
}
