/*
Author : Saurabh Kumar
Problem Link :https://cses.fi/problemset/task/1633
*/

import java.util.Arrays;
import java.util.Scanner;

public class DiceCombinations {
  static long MOD = 1000000007 ;
  static long[] dp = new long[10000007];
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    Arrays.fill(dp, -1);
    System.out.println(solveTab(n));
  }
   // TLE ::)
  public static long solveRec(int n) {
    //Base Case ::
    if (n == 0)
      return 1;
    if (dp[n] != -1) {
      return dp[n];
    }
    long ans = 0;
    for (int i = 1; i <= Math.min(n, 6); i++) {
      ans = (ans + solveRec(n - i)) % MOD;
    }
    return dp[n] = ans;
  }

  public static long solveTab(int n) {
    long[] dp = new long[n + 1];

    dp[0] = 1;
    for (int i = 1; i <=n; i++) {
      for (int j = 0; j <= Math.min(i,6); j++) {
        dp[i] += dp[i - j];
        dp[i] %= MOD;
      }
    }
    return dp[n];
  }
}
