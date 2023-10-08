/*
Form a palindrome
Medium

Given a string, find the minimum number of characters to be inserted to convert it to palindrome.

For Example:
ab: Number of insertions required is 1. bab or aba
aa: Number of insertions required is 0. aa
abcd: Number of insertions required is 3. dcbabcd

Example 1:
Input:
abcd
Output:
3
Explanation:
Here we can append 3 characters in the 
beginning,and the resultant string will 
be a palindrome ("dcbabcd").

Example 2:
Input:
aba
Output:
0
Explanation:
Given string is already a pallindrome hence no insertions are required.

Your Task:  
You don't need to read input or print anything. Your task is to complete the function findMinInsertions() which takes string S as input parameters and returns minimimum numser of insertions required.

Expected Time Complexity: O(|S|2)
Expected Auxiliary Space: O(|S|2)

Constraints:
1 ≤ |S| ≤ 500
*/

class Solution{
    int findMinInsertions(String S){
        // Lets Play with DP:)
        String rev="";
        for(char ch: S.toCharArray()){
            rev =ch+rev;
        }
        return solveTabLCS(S,rev);
    }
    // LPS --> LCS(a,rev(a))::
    // S.length-LPS = (No. of insertion/deletion)
    public static int solveTabLCS(String S1,String S2){
        // Tabulation::
        int n=S1.length();
        int tab[][] = new int[n+1][n+1];
        //Initlisation:: Default Value ->0:
        for(int i=1;i<n+1;i++){
            for(int j=1;j<n+1;j++){
                if(S1.charAt(i-1)==S2.charAt(j-1)){
                    tab[i][j] = 1+tab[i-1][j-1];
                }else{
                    tab[i][j] =Math.max(tab[i][j-1],tab[i-1][j]);
                }
            }
        }
        return S1.length()-tab[n][n];
    }
}

