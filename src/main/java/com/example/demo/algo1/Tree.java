package com.example.demo.algo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Do it! 알고리즘 코딩테스트 with JAVA
 * (Tree)
 * */
public class Tree {
    public void doProcess() throws IOException {
        findParent();
    }

    /** find parent node by children
     * 부모노드 찾기. root는 1로 고정. */
    int[] parents;
    boolean[] visits;
    ArrayList<Integer>[] linkList;
    private void findParent() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeCnt = Integer.parseInt(st.nextToken());
        parents = new int[nodeCnt + 1]; // 각 노드의 부모 노드를 저장하는 배열
        linkList = new ArrayList[nodeCnt + 1]; // 연결리스트 배열
        for (int i = 1; i <= nodeCnt; i++) {
            linkList[i] = new ArrayList<>();
        }
        visits = new boolean[nodeCnt + 1];
        for (int i = 1; i < nodeCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            linkList[start].add(end);
            linkList[end].add(start);
        }

        dfs(1); // 트리의 루트는 1

        System.out.println("부모노드 결과: ");
        for (int i = 2; i < parents.length; i++) { // root 1 제외하고 출력
            System.out.print(parents[i] + " ");
        }
        System.out.println();
    }
    private void dfs(int node) {
        if (visits[node]) return;
        visits[node] = true;
        for (Integer edge: linkList[node]) {
            if (!visits[edge]) {
                parents[edge] = node;
                dfs(edge);
            }
        }
    }
}
