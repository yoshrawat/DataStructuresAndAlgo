package com.yogesh.leetcode.hard;

import java.util.*;

public class Leetcode301 {
    Map<String, Boolean> map = new HashMap<>();

    public static void main(String[] args) {
        Leetcode301 leetcode301 = new Leetcode301();
        System.out.println(leetcode301.removeInvalidParentheses(")(f"));
    }
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();

        StringBuilder validString = findValidPa(s.toCharArray(), '(', ')');
        validString = findValidPa(validString.reverse().toString().toCharArray(), ')', '(');
        final String finalStr = validString.reverse().toString();

        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add(s);
        visited.add(s);

        boolean found = false;

        while( !q.isEmpty()) {
            int size = q.size();
            while( size > 0) {
                String temp = q.poll();
                System.out.println(temp);
                if(isValid(temp.toCharArray()) && temp.length() == finalStr.length()) {
                    result.add(temp);
                    size--;
                    found = true;
                }
                if( found) {
                    size--;
                    continue;
                }
                for( int i =0; i <temp.length(); i++) {
                    if( !(temp.charAt(i) == ')' || temp.charAt(i) == '('))
                        continue;
                    String str = temp.substring(0,i) + temp.substring(i+1);
                    System.out.println("str" + str);
                    if( !visited.contains(str)) {
                        visited.add(str);
                        q.add(str);
                    }
                }
                size--;
            }
        }
        return result;
    }
    /*public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();

        StringBuilder validString = findValidPa(s.toCharArray(), '(', ')');
        validString = findValidPa(validString.reverse().toString().toCharArray(), ')', '(');

        final String finalStr = validString.reverse().toString();

        // System.out.println("validString" + validString);
        helper(s.toCharArray(), 0, "", result, finalStr.length());
        if(result.size() == 0){
            result.add("");
        }
        return result;
    }*/
    private void helper( char[] ch, int index, String str, List<String> result, int len ) {
        if( index == ch.length && str.length() == len && !result.contains(str)) {
            if( map.containsKey(str) && map.get(str)) {
                result.add(str);
                return;
            }
            if( isValid(str.toCharArray())) {
                result.add(str);
                map.put(str, true);
            }else {
                map.put(str, false);
            }
            return;
        }
        if( index >= ch.length)
            return;
        if( Character.isLetter(ch[index]))
            helper(ch, index+1, str, result, len);
        else {
            helper(ch, index+1, str + ch[index], result, len);
            helper(ch, index+1, str, result, len);
        }
    }
    private StringBuilder findValidPa( char[] ch, char open , char close) {
        StringBuilder str = new StringBuilder();
        int balance = 0;
        for( char a : ch) {
            if( Character.isLetter(a)) {
                str.append(a);
                continue;
            }
            if( a == open) {
                balance++;
            } else if( balance == 0 && a == close ) {
                continue;
            }else {
                balance--;
            }
            str.append(a);
        }
        return str;
    }
    private boolean isValid( char[] ch ){
        int balance = 0;
        for( char c : ch ) {
            if( Character.isLetter(c))
                continue;
            if( c == '(') {
                balance++;
            }else if( balance > 0 ) {
                balance--;
            }else{
                return false;
            }
        }
        return balance == 0 ? true : false;
    }

}
