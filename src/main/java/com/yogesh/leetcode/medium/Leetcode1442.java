package com.yogesh.leetcode.medium;

import java.util.Arrays;

public class Leetcode1442 {
    public static void main(String[] args) {
        int[] arr = {1,1,1,1,1};
        System.out.println(countTriplets(arr));
    }
    private static int countTriplets(int[] arr) {
        int result = 0;

        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        for( int i =1; i < arr.length; i++) {
            dp[i] = dp[i-1] ^ arr[i];
        }
        System.out.println(Arrays.toString(dp));
        for( int i =0; i -1 < arr.length; i++) {
            for( int j = i+1; j < arr.length; j++) {
                if( i ==0  && dp[j] == 0)
                    result +=(j-i);
                else if( i > 0 && (dp[j] ^ dp[i-1]) == 0)
                    result +=(j-i);
            }
        }
        return result;
    }
}
