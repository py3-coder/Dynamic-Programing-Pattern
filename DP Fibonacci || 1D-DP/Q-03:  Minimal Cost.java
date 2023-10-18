/*
link : https://www.codingninjas.com/studio/problems/minimal-cost_8180930?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
*/


import java.util.Arrays;
public class Solution {
    static int memo[] = new int[10001];
    public static int minimizeCost(int n, int k, int []height){
        // Lets Play with Recurion::
       // return solveRec(height, 0, k, n);
        
        //Memoization:)
        // Arrays.fill(memo,-1);
        // return solveMemo(height,0,k,n);

        return solveTab(height,k,n);
    }
    public static int solveRec(int arr[],int i,int k,int n){
        //Base Case::
        if(i>=n-1) return 0; 

        // loop
        int ans =Integer.MAX_VALUE;
        for(int m=i+1;m<=k+i && m<n;m++){
            int temp = Math.abs(arr[i]-arr[m]) +solveRec(arr, m, k, n);
            ans = Math.min(ans,temp);
        }
        return ans;
    }
    public static int solveMemo(int arr[],int i,int k,int n){
        //Base Case::
        if(i>=n-1) return 0; 

        if(memo[i]!=-1){
            return memo[i];
        }
        int ans =Integer.MAX_VALUE;
        for(int m=i+1;m<=k+i && m<n;m++){
            int temp = Math.abs(arr[i]-arr[m]) +solveMemo(arr, m, k, n);
            ans = Math.min(ans,temp);
        }
        return memo[i]=ans;
    }
    public static int solveTab(int arr[],int k,int n){
        int tab[] = new int[n+1];
        tab[0] =0;

        for(int i=0;i<n;i++){
            int ans =Integer.MAX_VALUE;
            for(int m=i+1;m<=k+i && m<n;m++){
                int temp = Math.abs(arr[i]-arr[m]) +tab[m];
                ans = Math.min(ans,temp);
            }
            tab[i] =ans;
        }
        return tab[n];   
    }
}
