/*
Parent : Problem : 0-1 KnapSack
Partition Equal Subset Sum
MediumAccuracy: 30.24%
Given an array arr[] of size N, check if it can be partitioned into two parts such that the sum of elements in both parts is the same.

Example 1:
Input: N = 4
arr = {1, 5, 11, 5}
Output: YES
Explanation: 
The two parts are {1, 5, 5} and {11}.

Example 2:
Input: N = 3
arr = {1, 3, 5}
Output: NO
Explanation: This array can never be 
partitioned into two such parts.
Your Task:
You do not need to read input or print anything. Your task is to complete the function equalPartition() which takes the value N and the array as input parameters and returns 1 if the partition is possible. Otherwise, returns 0.

Expected Time Complexity: O(N*sum of elements)
Expected Auxiliary Space: O(N*sum of elements)

Constraints:
1 ≤ N ≤ 100
1 ≤ arr[i] ≤ 1000
N*sum of elements ≤ 5*106
*/


class Solution{
    static int[][] memo = new int[101][100001];
    static int equalPartition(int N, int arr[])
    {
        // code here
        //Approch :: Idea-->
        // Since We Know Subset Sum::) So sum of all ele of array if
        // Odd -> Not possible to  Partition Equal subset
        //Even  --> so if we find single subset available the other must::
        // sum/2 = apply subset sum ::
        // Lets start ::
        //TC : O(n*sum/2)
        //SC : O(n*sum/2)
        
        int sum =0;
        for(int ele:arr){
            sum+=ele;
        }
        if(sum%2==1) return 0;
        else{
            sum =sum/2;
            return solveTab(N,arr,sum);
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
    public static int solveTab(int n,int  arr[] ,int target){
        //Base --> initlization
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
                    tab[i][j] =Math.max(tab[i-1][j-arr[i-1]],tab[i-1][j]);
                }
            }
        }
        return tab[n][target];
    }
}
