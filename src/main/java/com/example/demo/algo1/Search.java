package com.example.demo.algo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Do it! 알고리즘 코딩테스트 with JAVA
 * (탐색 Search)
 * */
public class Search {
    public void doProcess() throws IOException {
        getConnectedComponent();
    }

    /** DFS */
    boolean[] visited; // 방문배열
    ArrayList<Integer>[] array; // 인접리스트
    private void getConnectedComponent() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeCnt = Integer.parseInt(st.nextToken());
        int edgeCnt = Integer.parseInt(st.nextToken());
        visited = new boolean[nodeCnt+1];
        // 인접리스트 구현
        array = new ArrayList[nodeCnt+1];
        for (int i = 1; i < nodeCnt+1; i++) {
            array[i] = new ArrayList<>();
        }

        for (int i = 0; i < edgeCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            // 각 node는 양쪽으로 연결
            array[start].add(end);
            array[end].add(start);
        }

        int count = 0;
        for (int i = 1; i < nodeCnt+1; i++) {
            if (!visited[i]) { // 방문하지 않은 값이면
                count++;
                dfs(i);
            }
        }
        System.out.println("연결요소 개수 : " + count);
    }
    private void dfs(int nodeIndex) {
        if (visited[nodeIndex]) return;

        visited[nodeIndex] = true;
        for (int i : array[nodeIndex]) {
            if (!visited[i]) { // 방문하지 않은 값이면
                dfs(i);
            }
        }
    }
}
