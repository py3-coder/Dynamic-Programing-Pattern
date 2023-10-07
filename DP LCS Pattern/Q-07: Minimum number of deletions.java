/*
Minimum number of deletions.

Given a string 'str' of size ‘n’. The task is to remove or delete the minimum number of characters from the string so that the resultant string is a palindrome.
Find the minimum numbers of characters we need to remove.

Note: The order of characters should be maintained.

Example 1:
Input: n = 7, str = "aebcbda"
Output: 2
Explanation: We'll remove 'e' and
'd' and the string become "abcba".

Example 2:
Input: n = 3, str = "aba"
Output: 0
Explanation: We don't remove any
character.
Your Task:  
You don't need to read input or print anything. Your task is to complete the function minDeletions() which takes the string s and length of s as inputs and returns the answer.

Expected Time Complexity: O(|str|2)
Expected Auxiliary Space: O(|str|2)

Constraints:
1 ≤ |str| ≤ 103
*/
// LCS --> LPS --> Modified LPS Applied:)
class Solution 
{
    int minDeletions(String str, int n)
    {
        //Lets Play With DP:)
        String rev="";
        for(char ch: str.toCharArray()){
            rev =ch+rev;
        }
        return solveTabLCS(str,rev,n,n);
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
        return X.length()-tab[n][m];
    }
} 
