package com.yogesh.interviewbit;

import java.util.Arrays;
import java.util.*;
import java.util.stream.Collectors;

public class LargestDistanceTree {
    private static Map<Integer, Integer> height = new HashMap<>();
    public static void main(String[] args) {
        int[] arr = { -1, 0, 1, 1, 2, 0, 5, 0, 3, 0, 0, 2, 3, 1, 12, 14, 0, 5, 9, 6, 16, 0, 13, 4, 17, 2, 1, 22, 14, 20, 10, 17, 0, 32, 15, 34, 10, 19, 3, 22, 29, 2, 36, 16, 15, 37, 38, 27, 31, 12, 24, 29, 17, 29, 32, 45, 40, 15, 35, 13, 25, 57, 20, 4, 44, 41, 52, 9, 53, 57, 18, 5, 44, 29, 30, 9, 29, 30, 8, 57, 8, 59, 59, 64, 37, 6, 54, 32, 40, 26, 15, 87, 49, 90, 6, 81, 73, 10, 8, 16 };
//        int[] arr = {-1};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        System.out.println(longestDistance(list));
    }
    private static int longestDistance(List<Integer> A) {
        int result = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        processArrayList(A, map);
//        return helper(map, map.get(-1).get(0));
        int farthestNode = bfs(map, 0);
        System.out.println(farthestNode);
        Set<Integer> visited = new HashSet<>();
        return bfs(map, farthestNode, visited,0);
    }
    private static int bfs(Map<Integer, List<Integer>> map, int farthestNode, Set<Integer> visited, int i) {
        int result   =0;
        Queue<int[]> q = new LinkedList<>();
        visited.add(farthestNode);
        q.add( new int[]{farthestNode,0});

        while(!q.isEmpty()) {
            int[] temp = q.poll();
            result = Math.max(result, temp[1]);
            List<Integer> child = map.get(temp[0]);
            if( child == null )
                continue;
            for( int a  : child) {
                if( visited.contains(a))
                    continue;
                q.add( new int[]{a, temp[1]+1});
            }
        }
        return result;
    }
    private static int dfs(Map<Integer, List<Integer>> map, int farthestNode, Set<Integer> visited, int i) {
        int result = 0;
        visited.add(farthestNode);
        List<Integer> child = map.get(farthestNode);
        if( child == null)
            return i;
        for( int a : child) {
            if( visited.contains(a))
                continue;
            result = Math.max(result, dfs(map, a , visited, i+1));
        }
        return result;
    }
    private static int bfs(Map<Integer, List<Integer>> map, int root) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(root);
        q.add(root);

        while(!q.isEmpty()) {
            int temp = q.poll();
            root = temp;
            List<Integer> child = map.get(temp);
            if( child == null )
                continue;
            for( int a : child) {
                if( visited.contains(a))
                    continue;
                visited.add(a);
                q.add(a);
            }
        }
        return root;
    }
    private static int helper( Map<Integer, List<Integer>> map, int root) {
        List<Integer> list = map.get(root);
        if(list == null || list.size() == 0 )
            return 0;
        int result = 0;
        int max1 = 0;
        int max2 = 0;
        for( int a : list) {
            int h = 0;
            if( height.containsKey(a))
                h = height.get(a);
            else {
                h = getHeight(a, map);
            }
            if( h > max1) {
                max2 = max1;
                max1 = h;
            }else if( h > max2) {
                max2 = h;
            }
        }
        for( int a : list) {
            result = Math.max( result, helper(map, a));
        }
        return Math.max( result, max1+ max2);
    }

    private static int getHeight(int a, Map<Integer, List<Integer>> map) {
        if( height.containsKey(a))
            return height.get(a);

        List<Integer> child = map.get(a);
        if( child == null || child.size() == 0)
            return 1;
        int result =0;
        for( int b : child) {
            result = Math.max( result, getHeight(b, map));
        }
        height.put(a, result +1);
        return 1 + result;
    }

    private static void processArrayList( List<Integer> list, Map<Integer, List<Integer>> map ) {
        int index = 0;
        for( int a : list) {
            if( a == -1) {
                map.put(index, new ArrayList<>());
                index++;
                continue;
            }
            if( !map.containsKey(a)) {
                map.put(a, new ArrayList<>());
            }
            if( !map.containsKey(index)) {
                map.put(index, new ArrayList<>());
            }
            map.get(a).add(index);
            map.get(index).add(a);
            index++;
        }
    }
}
