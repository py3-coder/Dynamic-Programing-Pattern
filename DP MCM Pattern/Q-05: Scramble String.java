/*
Scramble String
Dynamic Programming
Hard  39.1% Success

Given a string A, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of A = “great”:
    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
 
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node “gr” and swap its two children, it produces a scrambled string “rgeat”.

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that “rgeat” is a scrambled string of “great”.

Similarly, if we continue to swap the children of nodes “eat” and “at”, it produces a scrambled string “rgtae”.

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that “rgtae” is a scrambled string of “great”.

Given two strings A and B of the same length, determine if B is a scrambled string of S.
Input Format:
The first argument of input contains a string A.
The second argument of input contains a string B.
Output Format:

Return an integer, 0 or 1:
    => 0 : False
    => 1 : True
Constraints:

1 <= len(A), len(B) <= 50
Examples:

Input 1:
    A = "we"
    B = "we"

Output 1:
    1

Input 2:
    A = "phqtrnilf"
    B = "ilthnqrpf"
    
Output 2:
    0
*/
public class Solution {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    static HashMap<String,Boolean> map =new HashMap<>();
    public int isScramble(final String A, final String B) {
        if(A.length()!=B.length()){
            return 0;
        }
        if(A=="" && B==""){
            return 1;
        }
        //return solveRec(A,B)==true?1:0;
        return solveMemo(A,B)==true?1:0;
    }
    public static boolean solveRec(String a, String b){
        //Base Case ::
        int n=a.length();
        if(a.equals(b)) return true;
        if(a.length()<=1) return false;
        
        //k loop
        boolean flag=false;
        for(int k=1;k<=n-1;k++){
            boolean test1=false,test2=false;
            if((solveRec(a.substring(0,k),b.substring(n-k,n))) && (solveRec(a.substring(k,n),b.substring(0,n-k)))){
                test1=true;
            }
            if((solveRec(a.substring(0,k),b.substring(0,k))) && (solveRec(a.substring(k+1,n),b.substring(k+1)))){
                test2=true;
            }
            if(test1 || test2){
                flag=true;
                break;
            }
            
        }
        return flag;
    }
    public static boolean solveMemo(String a, String b){
        //Base Case ::
        int n=a.length();
        if(a.equals(b)) return true;
        if(a.length()<=1) return false;
        String key =a+" "+b;
        if(map.containsKey(key)){
            return (boolean)map.get(key);
        }
        //k loop
        boolean flag=false;
        for(int k=1;k<=n-1;k++){
            boolean test1=false,test2=false;
            test1 =((solveMemo(a.substring(0,k),b.substring(n-k,n)))&& (solveMemo(a.substring(k,n),b.substring(0,n-k))));
            test2 =((solveMemo(a.substring(0,k),b.substring(0,k))) && (solveMemo(a.substring(k,n),b.substring(k,n))));
            if(test1 || test2){
                flag=true;
                break;
            }
            
        }
        map.put(key,flag);
        return flag;
    }
}
