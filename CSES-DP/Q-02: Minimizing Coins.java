/*
Author : Saurabh Kumar
Link Problem : https://cses.fi/problemset/task/1634/
*/

import java.util.Arrays;
import java.util.Scanner;

public class MinimizingCoins {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int amt = sc.nextInt();
    int deno[] = new int[n];
    for (int i = 0; i < n; i++) {
      deno[i] = sc.nextInt();
    }
    int[][] memo = new int[n + 1][amt + 1];
    Arrays.stream(memo).forEach(a -> Arrays.fill(a, -1));
    // int ans = solveRec(n, amt, deno, memo);
    // if (ans == Integer.MAX_VALUE - 1)
    //   System.out.print(-1);
    // else {
    //   System.out.print(ans);
    // }
    System.out.print(solveOpt(n,amt,deno));
  }
  // TLE::
  public static int solveRec(int n, int target, int[] deno, int[][] memo) {
    //base case:
    if (target == 0)
      return 0;
    if (n == 0)
      return Integer.MAX_VALUE - 1;
    if (memo[n][target] != -1) {
      return memo[n][target];
    }
    if (target < deno[n - 1]) {
      return memo[n][target] = solveRec(n - 1, target, deno, memo);
    } else {
      return memo[n][target] = Math.min(1 + solveRec(n, target - deno[n - 1], deno, memo),
          solveRec(n - 1, target, deno, memo));
    }
  }
  // TLE::
  public static int solveTab(int n, int target, int[] deno) {
    //Tabulation::
    int[][] dp = new int[n + 1][target + 1];
    //Base::
    for (int i = 0; i < n + 1; i++) {
      for (int j = 0; j < target + 1; j++) {
        if (i == 0)
          dp[i][j] = Integer.MAX_VALUE - 1;
        if (j == 0)
          dp[i][j] = 0;
      }
    }
    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < target + 1; j++) {
        if (j < deno[i - 1]) {
          dp[i][j] = dp[i - 1][j];
        } else {
          dp[i][j] = Math.min(1 + dp[i][j - deno[i - 1]], dp[i - 1][j]);
        }
      }
    }
    return dp[n][target] == Integer.MAX_VALUE - 1 ? -1 : dp[n][target];
  }
  // Sucessfully Passed:)
  public static int solveOpt(int n, int target, int[] deno) {
    int[] dp = new int[target + 1];
    Arrays.fill(dp,  Integer.MAX_VALUE-1);
    dp[0]=0;
    for (int i = 1; i <= target; i++) {
      for (int j = 0; j < n; j++) {
        if (deno[j] <= i) {
          dp[i] = Math.min(dp[i], 1 + dp[i - deno[j]]);
        }
      }
    }
    return dp[target]== Integer.MAX_VALUE - 1 ? -1 : dp[target];
    }
}
