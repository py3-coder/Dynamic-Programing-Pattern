//Promnle LINK :https://www.codingninjas.com/studio/problems/longest-bitonic-sequence_1062688?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTab=0

import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    static HashMap<String,Integer> map;
    public static int longestBitonicSequence(int[] arr, int n) {
        // Write your code here.
        //return solveRec(arr, 0, -1,false);

        //
        // map =new HashMap<>();
        // return solveMemo(arr,0,-1,false);
        return solveLIS(arr);
    }
    public static int solveRec(int[] arr,int indx ,int prev,boolean flag){
        //Base :::
        if(indx>=arr.length) return 0;
        
        //
        
        if(flag!=true){
            int pick=Integer.MIN_VALUE;
            if(prev==-1 || arr[prev]<arr[indx]){
                pick = 1+solveRec(arr, indx+1, indx, flag);
            }else if(arr[prev]>arr[indx]){
                pick =1+solveRec(arr, indx+1,indx, true);
            }
            int notpick = solveRec(arr, indx+1, prev, flag);
            return Math.max(pick,notpick);
        }else{
            int pick=Integer.MIN_VALUE;
            if(arr[prev]>arr[indx]){
                pick = 1+solveRec(arr, indx+1, indx, flag);
            }
            int notpick= solveRec(arr, indx+1, prev, flag);

            return Math.max(pick,notpick);
        }
    }
    public static int solveMemo(int[] arr,int indx ,int prev,boolean flag){
        //Base :::
        if(indx>=arr.length) return 0;
        
        String key =indx+"-"+prev+"-"+flag;
        if(map.containsKey(key)){
            return map.get(key);
        }
        
        if(flag!=true){
            int pick=Integer.MIN_VALUE;
            if(prev==-1 || arr[prev]<arr[indx]){
                pick = 1+solveMemo(arr, indx+1, indx, flag);
            }else if(arr[prev]>arr[indx]){
                pick =1+solveMemo(arr, indx+1,indx, true);
            }
            int notpick = solveMemo(arr, indx+1, prev, flag);
            map.put(key,Math.max(pick,notpick));
            return Math.max(pick,notpick);
        }else{
            int pick=Integer.MIN_VALUE;
            if(arr[prev]>arr[indx]){
                pick = 1+solveMemo(arr, indx+1, indx, flag);
            }
            int notpick= solveMemo(arr, indx+1, prev, flag);
            map.put(key,Math.max(pick,notpick));
            return Math.max(pick,notpick);
        }
    }

    // Best Soln :: Apprcoch : Could be LIS (arr)  LIS (rev(arr)) ::
    //Lets SEE ::
    public static int solveLIS(int arr[]){
        int n=arr.length;
        int frontLIS[] =new int[n];
        int revLIS[] =new int[n];
        Arrays.fill(frontLIS,1);
        Arrays.fill(revLIS,1);
        int maxi=0;
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j] && 1+frontLIS[j]>frontLIS[i]){
                    frontLIS[i] =1+frontLIS[j];
                }
            }
        }

       for(int i=n-1;i>=0;i--){
           for(int j=n-1;j>i;j--){
               if(arr[i]>arr[j] && 1+revLIS[j]>revLIS[i]){
                   revLIS[i] =1+revLIS[j];
               }
           }
           maxi =Math.max(maxi,frontLIS[i]+revLIS[i]-1);
       }
       return maxi;
    }

}
