package com.yogesh.spoj;

import java.util.HashMap;
import java.util.Map;

class Main {
    public static void main(String args[]) {
        int target = 6;

        int[] dp = new int[target+1];
        int[] speed = new int[target+1];
        dp[0] = 0;
        dp[1] = 1;
        speed[0] = 1;
        speed[1] = 2;

        for( int i =2; i <= target; ++i) {
            dp[i] = Integer.MAX_VALUE;
            for( int j =0; j < i; j++) {
                if( j + speed[j] == i) {
                    dp[i] = Math.min( dp[i], dp[j] + 1);
                }
            }
        }
        System.out.println(dp[target]);
    }
    private static Map<Integer, Integer> map = new HashMap<>();
    private static int helper( int target, int p, int s, int len) {
        System.out.println(p);
        if( p == target)
            return len;
        if( p > target)
            return Integer.MAX_VALUE;
        if( target > 0 && p < 0 )
            return Integer.MAX_VALUE;
        if( map.containsKey( p))
            return  map.get(p);
        int result = Math.min( helper(target, p +s, s*2, len+1),
                helper(target, p, s >= 0 ? -1 : 1 , len+1));
        map.put(p, result);
        return result;
    }
}