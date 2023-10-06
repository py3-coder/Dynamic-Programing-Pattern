// Very Very Important Problem::
/*
Longest Common Substring
Medium
Given two strings. The task is to find the length of the longest common substring.

Example 1:
Input: S1 = "ABCDGH", S2 = "ACDGHR", n = 6, m = 6
Output: 4
Explanation: The longest common substring
is "CDGH" which has length 4.

Example 2:
Input: S1 = "ABC", S2 "ACB", n = 3, m = 3
Output: 1
Explanation: The longest common substrings
are "A", "B", "C" all having length 1.

Your Task:
You don't need to read input or print anything. Your task is to complete the function longestCommonSubstr() which takes the string S1, string S2 and their length n and m as inputs and returns the length of the longest common substring in S1 and S2.


Expected Time Complexity: O(n*m).
Expected Auxiliary Space: O(n*m).


Constraints:
1<=n, m<=1000
*/

class Solution{
    int longestCommonSubstr(String S1, String S2, int n, int m){
        // Recusrision is bit logical :: Handle With Care:
        //1. Recursion:: TLE :
        //return solveRec(S1,S2,n,m,0);
        //2 Memoisation:
        // Since We have 3 varible n,m ,length so we can't store in 2d matrix;
        // We need 3d dp::
        //better use hashmap::
        // HashMap<String,Integer> map = new HashMap<>();
        // return solveMemo(S1,S2,n,m,0,map);
        
        //3. Tabulation TOP-DOWN:
        return solveTab(S1,S2,n,m);
    }
    public static int solveRec(String S1,String S2,int n ,int m,int len){
        // Base::
        if(n==0 || m==0) return len;
        
        // last char are same we include it else:
        int ans2=0; int ans1=len;
        if(S1.charAt(n-1)== S2.charAt(m-1)){
            ans1 =solveRec(S1,S2,n-1,m-1,len+1);
        }
        ans2 =Math.max(solveRec(S1,S2,n-1,m,0),solveRec(S1,S2,n,m-1,0));
        return Math.max(ans1,ans2);
    }
    public static int solveMemo(String S1,String S2,int n ,int m,int len,HashMap<String,Integer> map){
        // Base::
        if(n==0 || m==0) return len;
        //check precamputed::
        String key=n+"f"+m+"s"+len;
        if(map.containsKey(key)){
            return map.get(key);
        }
        // last char are same we include it else:
        int ans2=0; int ans1=len;
        if(S1.charAt(n-1)== S2.charAt(m-1)){
            ans1 =solveMemo(S1,S2,n-1,m-1,len+1,map);
        }
        ans2 =Math.max(solveMemo(S1,S2,n-1,m,0,map),solveMemo(S1,S2,n,m-1,0,map));
        map.put(key,Math.max(ans1,ans2));
        return Math.max(ans1,ans2);
    }
    public static int solveTab(String S1,String S2,int n, int m){
        //Tabulation:: Similar to LCS-{LOngest Common Subsquence}:
        int tab[][] = new int[n+1][m+1];
        
        //base - intilization::
        //by defalut value are 0:
        int sol=0;
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(S1.charAt(i-1)==S2.charAt(j-1)){
                    tab[i][j] = 1+tab[i-1][j-1];
                    sol=Math.max(sol,tab[i][j]);
                }else{
                    tab[i][j] =0;
                }
            }
        }
        return sol;
        
    }
}
