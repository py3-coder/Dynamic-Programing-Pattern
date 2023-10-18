/*
Practise Link :https://www.codingninjas.com/studio/problems/printing-longest-increasing-subsequence_8360670?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
*/

import java.util.*;
public class Solution {
    public static List< Integer > printingLongestIncreasingSubsequence(int []arr, int x) {
        // Write Your Code Here
        return printLIS(arr);
    }
    public static List<Integer> printLIS(int []arr){
        List<Integer> res = new ArrayList<>();
        int n =arr.length;
        int temp[] = new int[n];
        int index[]= new int[n];
        
        Arrays.fill(temp,1);
        for(int i=1;i<n;i++){
            index[i] =i;
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j] && temp[i]<temp[j]+1){
                    temp[i] =1+temp[j];
                    index[i] =j;
                    
                }
            }
        }
        int ans=-1;
        int lastindx=-1;
        for(int i=0;i<temp.length;i++){
            if(temp[i]>ans){
                ans =temp[i];
                lastindx=i;
            }
        }
        //System.out.print(lastindx);
        res.add(arr[lastindx]);
        while(index[lastindx]!=lastindx){
            lastindx= index[lastindx];
            res.add(arr[lastindx]);
           
        }
        Collections.reverse(res);
        return res;
    }
}
