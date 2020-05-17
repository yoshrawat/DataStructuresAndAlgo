package com.yogesh.leetcode.medium;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        int[] coins = new int[]{186,419,83,408};
        int amount = 6249;
        System.out.println(coinChange(coins, amount));
    }
    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[][] dp = new int[coins.length+1][amount+1];
        for( int i =0; i <= amount; i++ ) {
            dp[0][i] = 0;
        }
        
        for( int i =0; i <= coins.length; i++ ) {
            dp[i][0] = 0;
        }
        
        for( int i =1; i <= coins.length; i++ ) {
            for( int j =1; j <= amount; j++ ) {
                int remainAmount = j % coins[i-1];
                if( remainAmount == 0 ) {
                    dp[i][j] = j / coins[i-1];
                }else if( remainAmount >= 0 && dp[i-1][remainAmount] != 0 ) {
                    dp[i][j] = j / coins[i-1] + dp[i-1][remainAmount];
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return  dp[coins.length][amount] == 0 ? -1 : dp[coins.length][amount] ;
    }
}