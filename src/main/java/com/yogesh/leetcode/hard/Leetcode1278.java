package com.yogesh.leetcode.hard;

import java.util.*;

public class Leetcode1278 {
    public static void main(String[] args) {
        String str = "uyskbebqrhfoythvwazswib";
//        String str = "abc";
        System.out.println(palindromePartition_1(str,2));
    }
    // bottom up approach
    static Map<String, Integer> map = new HashMap<>();
    public static int palindromePartition_1(String s, int k) {
        int len = s.length();
        char[] ch = s.toCharArray();

        int[][] dp = new int[k][len];

        for( int i =0; i < len; i++) {
            dp[0][i] = helper( ch, 0, i);
        }

        //dp[i][j] min cost to split string [0,j] into i part

        for (int i = 1; i < k; ++i){
            for (int j = i; j < len; ++j){
                int cur = Integer.MAX_VALUE;
                for (int p = j; p >= i; p--){
                    cur = Math.min(cur, dp[i - 1][p - 1] + helper(ch, p-1, j-1));
                }
                dp[i][j] = cur;
            }
        }
        return dp[k-1][len-1];
    }
    private static int helper(String str){
        if (str == null || str.length() == 0) return 0;
        if (map.containsKey(str)) return map.get(str);
        int res = 0;
        for (int i = 0; i < str.length(); ++i){
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) res++;
        }
        res /= 2;
        map.put(str, res);
        return res;
    }

    private static int helper(char[] ch, int left, int right) {
        String key = left+ "_" + right;
        if( map.containsKey(key))
            return map.get(key);
        int ans=0;
        while( left < right) {
            if(ch[left] != ch[right]) {
                ans++;
            }
            left++;
            right--;

        }
        map.put(key, ans);
        return ans;
    }

    public static int palindromePartition(String s, int k) {
        char[] ch = s.toCharArray();
        int len = ch.length;
        int[][] dp = new int[len][len];

        // for len 1
        for( int i =0; i < len; i++) {
            dp[i][i] = 0;
        }

        // for lem 2
        for( int i =0; i < len-1; i++) {
            if( ch[i] == ch[i+1])
                dp[i][i+1] = 0;
            else
                dp[i][i+1] = 1;
        }
        // for len > 2
        for( int window =2; window < len; window++) {
            for( int i =0, j = window; j < len; ++j, ++i) {
                if( ch[i] == ch[j])
                    dp[i][j] = dp[i+1][j-1];
                else
                    dp[i][j] = dp[i+1][j-1] + 1;
            }
        }

        for( int[] rows : dp) {
            System.out.println( Arrays.toString(rows));
        }
        int result = dfs(dp, ch,0, k, 0);
        System.out.println( result);
        return  result == Integer.MAX_VALUE ? 0 : result;
    }
    private static int dfs( int[][] dp , char[] ch, int index , int k , int ans ) {
        // base condition
        if( (index == ch.length) && k == 0 )
            return ans;
        if( k != 0 && index >= ch.length )
            return Integer.MAX_VALUE;
        if( k < 0 )
            return Integer.MAX_VALUE;
        String key = index + "_" + k;
        /*if( map.containsKey(key))
            return map.get(key);*/
        int result = Integer.MAX_VALUE;

        for( int i = index; i < ch.length; i++) {
            result = Math.min( result, dfs( dp, ch, i+1, k -1, ans + dp[index][i]));
        }
        map.put(key, result);
        return result;
    }
}
