/*
Boolean Parenthesization
Hard 
Accuracy: 20.15%

Given a boolean expression S of length N with following symbols.
Symbols
    'T' ---> true
    'F' ---> false
and following operators filled between symbols
Operators
    &   ---> boolean AND
    |   ---> boolean OR
    ^   ---> boolean XOR
Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.

Note: The answer can be large, so return it with modulo 1003


Example 1:

Input: N = 7
S = T|T&F^T
Output: 4
Explaination: The expression evaluates 
to true in 4 ways ((T|T)&(F^T)), 
(T|(T&(F^T))), (((T|T)&F)^T) and (T|((T&F)^T)).
Example 2:

Input: N = 5
S = T^F|F
Output: 2
Explaination: ((T^F)|F) and (T^(F|F)) are the 
only ways.
 
Your Task:
You do not need to read input or print anything. Your task is to complete the function countWays() which takes N and S as input parameters and returns number of possible ways modulo 1003.

Expected Time Complexity: O(N3)
Expected Auxiliary Space: O(N2)


Constraints:
1 ≤ N ≤ 200 
*/


class Solution{
    static int memo[][][] = new int[201][201][2];
    static int mod=1003;
    static int countWays(int N, String S){
        // 1. Recursion :: IMP Question & Tough too
        //return solveRec(S,0,N-1,true);
        for(int f[][]:memo){
            for(int[] i:f){
                Arrays.fill(i,-1);
            }
        }
        return solveMemo(S,0,N-1,true);
        
        
    }
    public static int solveRec(String str,int i,int j,boolean istrue){
        //Base Case::
        if(i>j) return 0;
        if(i==j)
        { 
            if(istrue) 
            return str.charAt(i)=='T'?1:0; 
            else 
            return str.charAt(i)=='F'?1:0;  
        }
        int ans=0;
        for(int k=i+1;k<j;k+=2){
            int lt=solveRec(str,i,k-1,true);
            int lf=solveRec(str,i,k-1,false);
            int rt=solveRec(str,k+1,j,true);
            int rf=solveRec(str,k+1,j,false);
            
            if(str.charAt(k)=='|'){
                if(istrue==true){
                    ans+=(lt*rt)+(lt*rf)+(rt*lf);
                }else{
                    ans+= lf*rf;
                }
            }else if(str.charAt(k)=='&'){
                if(istrue==true){
                    ans+=(lt*rt);
                }else{
                    ans+=(lt*rf)+(rt*lf)+(lf*rf);
                }
            }else if(str.charAt(k)=='^'){
                if(istrue==true){
                    ans+=(lt*rf)+(rt*lf);
                }else{
                    ans+=(lf*rf)+(lt*rt);
                }
            }
        }
        return ans%mod;
    }
    public static int solveMemo(String str,int i,int j,boolean istrue){
        //Base Case::
        if(i>j) return 0;
        if(i==j)
        { 
            if(istrue) 
            return str.charAt(i)=='T'?1:0; 
            else 
            return str.charAt(i)=='F'?1:0;  
        }
        if(memo[i][j][istrue==true?1:0]!=-1){
            return memo[i][j][istrue==true?1:0]%mod;
        }
        int ans=0;
        for(int k=i+1;k<j;k+=2){
            int lt=solveMemo(str,i,k-1,true);
            int lf=solveMemo(str,i,k-1,false);
            int rt=solveMemo(str,k+1,j,true);
            int rf=solveMemo(str,k+1,j,false);
            
            if(str.charAt(k)=='|'){
                if(istrue==true){
                    ans+=(lt*rt)+(lt*rf)+(rt*lf);
                }else{
                    ans+= lf*rf;
                }
            }else if(str.charAt(k)=='&'){
                if(istrue==true){
                    ans+=(lt*rt);
                }else{
                    ans+=(lt*rf)+(rt*lf)+(lf*rf);
                }
            }else if(str.charAt(k)=='^'){
                if(istrue==true){
                    ans+=(lt*rf)+(rt*lf);
                }else{
                    ans+=(lf*rf)+(lt*rt);
                }
            }
        }
        ans%=mod;
        memo[i][j][istrue==true?1:0]=ans;
        return ans%mod;
    }
}
