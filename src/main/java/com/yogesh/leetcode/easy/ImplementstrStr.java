package com.yogesh.leetcode.easy;

public class ImplementstrStr {
    public static void main(String[] args) {
        System.out.println(strStr("mississippi", "issi"));
    }
    public static int strStr(String haystack, String needle) {
        
        if( needle.length() > haystack.length())
            return -1;
        if( needle == null || needle.length() == 0)
            return 0;
        
        for( int i =0; i < haystack.length(); i++ ) {
            int j = i;
            int k = 0;
            
            while( k < needle.length() && haystack.charAt(j) == needle.charAt(k)) {
                j++;
                k++;
            }
            if( k == needle.length())
                return i;
        }
        return -1;
    }
}