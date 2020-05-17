package com.yogesh.leetcode.medium;

import java.util.Stack;

public class Leetcode1249 {
    public static void main(String[] args) {
        String str = "lee(t(c)o)de)";
        System.out.println(minRemoveToMakeValid1(str));
    }
    public static String minRemoveToMakeValid1(String s) {
        StringBuilder builder = removeExtraBracket(s, '(', ')');
        return removeExtraBracket(builder.reverse().toString(), ')', '(').reverse().toString();
    }

    private static StringBuilder removeExtraBracket( String s, char open, char close) {
        StringBuilder builder = new StringBuilder();
        int balance = 0;
        for( char ch : s.toCharArray()) {
            if( ch == open) {
                balance++;
            }else if( ch == close && balance == 0)
                continue;
            else if( balance > 0 && ch == close)
                balance--;
            builder.append(ch);
        }
        return builder;
    }
    public String minRemoveToMakeValid(String s) {
        char[] ch = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        for( int i =0; i < ch.length; i++) {
            if( ch[i] == '(' || ch[i] == ')') {
                if( ch[i] == '(') {
                    stack.push(i);
                }else if( !stack.isEmpty() && ch[stack.peek()] == '(') {
                    stack.pop();
                }else {
                    stack.push(i);
                }
            }
        }
        // System.out.println("index" + stack.isEmpty());
        if( stack.isEmpty())
            return s;
        StringBuilder builder = new StringBuilder(s);
        while( !stack.isEmpty()) {
            int index = stack.pop();
            // System.out.println(index);
            builder.deleteCharAt(index);
        }
        return builder.toString();
    }
}
