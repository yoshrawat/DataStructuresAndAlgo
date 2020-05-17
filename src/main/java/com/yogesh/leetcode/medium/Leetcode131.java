package com.yogesh.leetcode.medium;

import java.util.*;
public class Leetcode131 {
    public static void main(String[] args) {
        String str = "cbbbcc";
        List<List<String>> result = new ArrayList<>();
        helper(str, 0, result, new ArrayList<>());
        for( List<String> list : result)
            System.out.println(list.toString());
        System.out.println("list.toString()");
    }
    private static void helper(String str, int index, List<List<String>> result, List<String> list){
        if( index == str.length()) {
            result.add(new ArrayList<>(list));
            return;
        }
        if( index > str.length())
            return;
        for( int i = index+1; i <= str.length(); i++) {
            if( isPallindrome(str.substring(index, i))) {
                list.add(str.substring(index, i));
                helper(str, i, result, list);
                list.remove(list.size()-1);
            }
        }
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
