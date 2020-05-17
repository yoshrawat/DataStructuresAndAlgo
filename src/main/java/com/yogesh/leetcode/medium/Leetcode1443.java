package com.yogesh.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

public class Leetcode1443 {
    public static void main(String[] args) {

    }
    public static int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for( int[] row : edges) {
            map.putIfAbsent( row[0], new ArrayList<>());
            map.get(row[0]).add(row[1]);
        }

        Set<Integer> visited = new HashSet<>();
        return helper(0, map, hasApple, visited);
    }
    private static int helper( int root, Map<Integer, List<Integer>> map, List<Boolean> apple, Set<Integer> visited) {
        int result = 0;
        List<Integer> child = map.get(root);
        if( child == null) {
            if( apple.get(root))
                return 2;
            else
                return 0;
        }

        for( int a : child) {
            result += helper( a, map, apple, visited);
        }
        if( result > 0 )
            return root != 0 ? result+2 : result;
        else if( apple.get(root))
            return 2;
        return 0;
    }
}
