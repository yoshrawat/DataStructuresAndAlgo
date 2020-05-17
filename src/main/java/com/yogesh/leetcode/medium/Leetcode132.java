package com.yogesh.leetcode.medium;

import java.util.ArrayList;
import java.util.*;

public class Leetcode132 {
    private static Map<String, Integer> map = new HashMap<>();
    public static void main(String[] args) {
        String str = "cabababcbc";
//        System.out.println(helper(str, 0, new ArrayList<>()));
        dp(str.toCharArray());
    }
    private static int dp( char[] ch){
        int len = ch.length;


        int[][] dp = new int[len][len];

        // for length 1
        for( int i =0; i < len; i++) {
            dp[i][i] = 1;
        }

        // for length 2
        for( int i =0; i < len-1; i++) {
            if( ch[i] == ch[i+1] )
                dp[i][i+1] = 2;
        }

        // for length > 2
        for( int size = 2; size < len; size++) {
            for( int i =0, j = size; j < len; i++, j++) {
                if( ch[i] == ch[j] && dp[i+1][j-1] > 0){
                    dp[i][j] = Math.max(2 + dp[i+1][j-1], dp[i][j]);
                }
            }
        }
        for( int[] rows: dp ){
            System.out.println( Arrays.toString(rows));
        }
        int result =Integer.MAX_VALUE;

        for( int i =0; i < dp.length; i++) {
            int j =dp[0].length-1;
            int max = 0;
            while( j >= 0) {
                if( dp[i][j] == 0 ) {
                    max++;
                    j--;
                }else {
                    max++;
                    j -= dp[i][j];
                }
            }
            result = Math.min( result, max);
        }
        System.out.println(result-1);
        return result;
    }
    private static int helper(String str, int index,  List<String> list){
        if( index == str.length()) {
            return list.size()-1;
        }
        if( index > str.length())
            return Integer.MAX_VALUE;

        String key = str.substring(index);

        int result = Integer.MAX_VALUE;
        for( int i = str.length(); i > index ; i--) {
            if( isPallindrome(str.substring(index, i))) {
                list.add(str.substring(index, i));
                int temp = helper(str, i, list);
                result = Math.min( result, temp);
                list.remove(list.size()-1);
            }
        }
        map.put(key, result);
        return result;
    }
    private static boolean isPallindrome(String str ) {
        if( str == null || str.length() == 1)
            return true;
        int left = 0;
        int right = str.length()-1;
        char[] ch = str.toCharArray();

        while( left < right ) {
            if( ch[left] != ch[right])
                return false;
            left++;
            right--;
        }
        return true;
    }
}
