/*
LINK : https://www.codingninjas.com/studio/problems/frog-jump_3621012?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTab=0
*/


import java.util.* ;
import java.io.*; 
public class Solution {
    static int memo[] =new int[100001];
    public static int frogJump(int n, int heights[]) {
        
        // Lets Play with Recursion:)
        //1. Recursion :: TC :O(2^n)
        // SC :O(1) +O(n)--Aux space::
        //return solveRec(n-1, heights);
        // Arrays.fill(memo,-1);
        // return solveMemo(n-1,heights);
        
        //return solveTab(n,heights);
        return solveOptimised(n,heights);
        // TC : O(n)
        // SC : O(1)
    }
    public static int solveRec(int n,int arr[]){
        //Base Case ::
        if(n==0) return 0;
        //Case  1::
        int left =solveRec(n-1,arr)+Math.abs(arr[n]-arr[n-1]);
        int right=Integer.MAX_VALUE;
        if(n>1){
            right=solveRec(n-2,arr)+Math.abs(arr[n]-arr[n-2]);
        }
        return Math.min(left,right);
    }
    public static int solveMemo(int n,int arr[]){
        //Base Case ::
        if(n==0) return 0;
        if(memo[n]!=-1){
            return memo[n];
        }
        //Case  1::
        int left =solveMemo(n-1,arr)+Math.abs(arr[n]-arr[n-1]);
        int right=Integer.MAX_VALUE;
        if(n>1){
            right=solveMemo(n-2,arr)+Math.abs(arr[n]-arr[n-2]);
        }
        return memo[n]=Math.min(left,right);
    }
    public static int solveTab(int n,int arr[]){
        //Tabulation --

        int tab[] =new int[n+1];
        for(int i=1;i<n;i++){
            int left = tab[i-1] +Math.abs(arr[i]-arr[i-1]);
            int right=Integer.MAX_VALUE;
            if(i>1) right= tab[i-2] + Math.abs(arr[i]-arr[i-2]);
            tab[i] =Math.min(left,right);
        }
        return tab[n-1];
    }
    public static int solveOptimised(int n,int arr[]){
        //Tabulation --

        int prev1=0;
        int prev2=0;
        int curr=0;
        for(int i=1;i<n;i++){
            int left = prev1 +Math.abs(arr[i]-arr[i-1]);
            int right=Integer.MAX_VALUE;
            if(i>1) right= prev2 + Math.abs(arr[i]-arr[i-2]);
            curr =Math.min(left,right);
            prev2=prev1;
            prev1=curr;
        }
        return prev1;
    }
    
}
