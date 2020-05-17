package com.yogesh.leetcode.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MergeKStones {
    static Map<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) {
        System.out.println(findMinFibonacciNumbers(513314));
    }
    public static int findMinFibonacciNumbers(int k) {
        if( k <=2 )
            return k;
        int[] dp = new int[k+1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        dp[1] = 1;
        for( int i =2; i <=k; i++ ) {
            dp[i] = dp[i-1] + dp[i-2];
            if( dp[i] > k )
                break;
        }
        // System.out.println( Arrays.toString(dp));
        return helper( dp, k , 0);
    }
    private static int helper( int[] dp, int sum, int index) {
        if(sum == 0 )
            return 0;
        if( index ==  dp.length)
            return -1;
        String str = String.valueOf(sum);
        if( str.length() > 10 )
            System.out.println(str);
        if( map.containsKey(sum))
            return map.get(sum);
        if( dp[index] > sum ) {
            return helper(dp, sum, index+1);
        }
        int a = helper( dp, sum - dp[index], index+1);
        int b = helper( dp, sum, index+1);
        int result = 0;
        if( a >= 0 && b >= 0 ) {
            result = Math.min(a+1, b);
        }else if( a >= 0 ) {
            result = a+1;
        }else if( b >= 0 ){
            result = b;
        }else {
            result = -1;
        }
        map.put(sum, result);
        return result;
    }
}