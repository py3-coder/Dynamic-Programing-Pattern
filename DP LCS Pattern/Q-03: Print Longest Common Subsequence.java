/*
IMP Problem :

Printing Longest Common Subsequence:
Given two sequences, print the longest subsequence present in both of them.
Example:
LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3. 
*/

public class Solution {
    public static String findLCS(int n, int m, String s1, String s2){
        // Write your code here.
        return solveTab(n, m, s1, s2);
    }
    public static String  solveTab(int n,int m,String s1,String s2){
        //Use LCS to print LCS
        int tab[][] =new int[n+1][m+1];
        //base - handle by default value::
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    tab[i][j] =1+tab[i-1][j-1];
                }else{
                    tab[i][j] =Math.max(tab[i-1][j],tab[i][j-1]);
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        int i=n,j=m;
        while(i>0 && j>0){
            if(s1.charAt(i-1)==s2.charAt(j-1)){
                ans.append(s1.charAt(i-1));
                i--;
                j--;
            }else{
                if(tab[i-1][j]>=tab[i][j-1]){
                    i--;
                }else{
                    j--;
                }
            }
        }
        return ans.reverse().toString();
        
    }

}
