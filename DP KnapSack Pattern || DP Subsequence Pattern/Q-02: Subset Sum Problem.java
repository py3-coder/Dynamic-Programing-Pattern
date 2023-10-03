/*
Parent Problem : 0/1 KnapSack Pattern 
Subset Sum Problem
MediumAccuracy: 32.0%
Given an array of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum. 

Example 1:
Input:
N = 6
arr[] = {3, 34, 4, 12, 5, 2}
sum = 9
Output: 1 
Explanation: Here there exists a subset with
sum = 9, 4+3+2 = 9.

Example 2:
Input:
N = 6
arr[] = {3, 34, 4, 12, 5, 2}
sum = 30
Output: 0 
Explanation: There is no subset with sum 30.

Your Task:  
You don't need to read input or print anything. Your task is to complete the function isSubsetSum() which takes the array arr[], its size N and an integer sum as input parameters and returns boolean value true if there exists a subset with given sum and false otherwise.
The driver code itself prints 1, if returned value is true and prints 0 if returned value is false.
 

Expected Time Complexity: O(sum*N)
Expected Auxiliary Space: O(sum*N)
 

Constraints:
1 <= N <= 100
1<= arr[i] <= 100
1<= sum <= 105


*/




class Solution{
    static int[][] memo = new int[101][100001];
    static Boolean isSubsetSum(int N, int arr[], int sum){
        // Lets Play With Recursion :::
        //1. Recursion Call ::
        //return solveRec(N,arr,sum);
        // TEL ::) !!
        // TC : O(2^n)
        // SC : O(1)
        //2.Memo Call ::
        // Arrays.stream(memo).forEach(a -> Arrays.fill(a, -1));
        // return solvememo(N,arr,sum)==1?true:false;
        //TC : O(n*sum)
        //SC :O(n*sum)
        //3. Tab call
        //TC : O(n*sum)
        //SC : O(n*sum)
        return solveTab(N,arr,sum)==1?true:false;
        
        
    }
    public static boolean solveRec(int n,int arr[],int target){
        //Base ::
        if(target==0) return true;
        if(n==0) return false;
        
        if(arr[n-1]>target){
            //not possible to pick --> Not pick Case ::
            return solveRec(n-1,arr,target);
        }else{
            //pick and not pick case::
            return solveRec(n-1,arr,target-arr[n-1]) || solveRec(n-1,arr,target);
        }
    }
    public static int solvememo(int n,int arr[],int target){
        //Base ::
        if(target==0) return 1;
        if(n==0) return 0;
        
        //check pre-camputed ?
        if(memo[n][target]!=-1){
            return memo[n][target];
        }
        if(arr[n-1]>target){
            //not possible to pick --> Not pick Case ::
            return memo[n][target]=solvememo(n-1,arr,target);
        }else{
            //pick and not pick case::
            return memo[n][target]=Math.max(solvememo(n-1,arr,target-arr[n-1]),solvememo(n-1,arr,target));
        }
    }
    public static int solveTab(int n ,int arr[],int target){
        //Base -->initilisation
        int tab[][] = new int[n+1][target+1];
        for(int i=0;i<n+1;i++){
            for(int j=0;j<target+1;j++){
                if(i==0){
                    tab[i][j]=0;
                }
                if(j==0){
                    tab[i][j]=1;
                }
            }
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<target+1;j++){
                if(arr[i-1]>j){
                    tab[i][j]=tab[i-1][j];
                }else{
                    tab[i][j]=Math.max(tab[i-1][j-arr[i-1]] ,tab[i-1][j]);
                }
            }
        }
        return tab[n][target];
    }
}
