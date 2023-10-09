/*
72. Edit Distance
Medium

Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character
 

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 

Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
*/

class Solution {
    static int[][]  memo = new int[501][501];
    public int minDistance(String word1, String word2) {
        // Recursion:: TLE ::
        //return solveRec(word1.length(),word2.length(),word1,word2);
        // Arrays.stream(memo).forEach(a->Arrays.fill(a,-1));
        // return solveMemo(word1.length(),word2.length(),word1,word2);

        return solveTab(word1.length(),word2.length(),word1,word2);

        
    }
    public static int solveRec(int n,int m,String word1,String word2){
        //Base::
        if(n==0 && m==0) return 0;
        // when word1 finishes and due in word2 means that we need to insert in word1 
        if(n==0) return m;
        // when word2 finishes and due in word1 means we got the match part 
        //to make equal need to delete the left one::
        if(m==0) return n;
        // if both char are equal we don't need do any just decrease i and j::
        if(word1.charAt(n-1)==word2.charAt(m-1)){
            return solveRec(n-1,m-1,word1,word2);
        }else{
            // 3 choices: insert / delete / replace 
            // try all and take min of all:
            int insert = 1+solveRec(n,m-1,word1,word2);
            int delete =1+solveRec(n-1,m,word1,word2);
            int replace =1+solveRec(n-1,m-1,word1,word2);

            return Math.min(insert,Math.min(delete,replace));
        }

    }
    public static int solveMemo(int n,int m,String word1,String word2){
        //Base::
        if(n==0 && m==0) return 0;
        // when word1 finishes and due in word2 means that we need to insert in word1 
        if(n==0) return m;
        // when word2 finishes and due in word1 means we got the match part 
        //to make equal need to delete the left one::
        if(m==0) return n;

        if(memo[n][m]!=-1){
            return memo[n][m];
        }
        // if both char are equal we don't need do any just decrease i and j::
        if(word1.charAt(n-1)==word2.charAt(m-1)){
            return memo[n][m]=solveMemo(n-1,m-1,word1,word2);
        }else{
            // 3 choices: insert / delete / replace 
            // try all and take min of all:
            int insert = 1+solveMemo(n,m-1,word1,word2);
            int delete =1+solveMemo(n-1,m,word1,word2);
            int replace =1+solveMemo(n-1,m-1,word1,word2);

            return memo[n][m]=Math.min(insert,Math.min(delete,replace));
        }
    }
    public static int solveTab(int n,int m,String str1,String str2){
        //Tabulation::

        int tab[][] = new int[n+1][m+1];
        //Initlisation:)
        for(int i=0;i<n+1;i++){
            for(int j=0;j<m+1;j++){
                if(i==0 && j==0) tab[i][j]=0;
                if(i==0) tab[i][j] =j;
                if(j==0) tab[i][j] =i;
            }
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    tab[i][j] =tab[i-1][j-1];
                }else{
                    int ins =1+tab[i][j-1];
                    int del =1+tab[i-1][j];
                    int rep =1+tab[i-1][j-1];

                    tab[i][j] =Math.min(ins,Math.min(del,rep));
                }
            }
        }
        return tab[n][m];
    }
}
