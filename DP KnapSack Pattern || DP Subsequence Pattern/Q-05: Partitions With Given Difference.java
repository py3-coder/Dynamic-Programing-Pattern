//Problem Link :: https://www.codingninjas.com/studio/problems/minimum-elements_3843091?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf

import java.util.* ;
import java.io.*; 
public class Solution {
	static int mod=1000000007;
	public static int countPartitions(int n, int d, int[] arr) {
		// Write your code here.
		//Count of Subset sum problem ::)
		int sum=0;
		for(int ele:arr){
			sum+=ele;
		}
		if(d>=sum){
			return 0;
		}
		int req =(sum+d);
		if(req%2!=0){
			return 0;
		}else{
			req =req/2;
		}
		return solveTab(arr, n, req);


	}
	public static int solveTab(int[] arr,int n,int target){
		//
		int[][] tab = new int[n+1][target+1];
		//initlization::
		for(int i=0;i<n+1;i++){
			for(int j=0;j<target+1;j++){
				if(i==0) tab[i][j] =0;
				if(j==0) tab[i][j] =1;
			}
		}
		for(int i=1;i<n+1;i++){
			for(int j=0;j<target+1;j++){
				if(arr[i-1]>j){
					tab[i][j] =tab[i-1][j]%mod;
				}else{
					tab[i][j] =(tab[i-1][j-arr[i-1]])%mod +(tab[i-1][j])%mod;
					tab[i][j]%=mod;
				}
			}
		}
		return tab[n][target];
	}
	
}
