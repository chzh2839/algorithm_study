package com.example.demo.algo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Do it! 알고리즘 코딩테스트 with JAVA
 * (Graph)
 * */
public class Graph {
    public void doProcess() throws IOException {
//        checkBipartiteGraph();
        unionFind();
    }

    /** 이분 그래프 (bipartite graph) 판별하기
     * 노드끼리 서로 인접하지 않은 두 집합으로 그래프 노드를 나눌 수 있는 그래프.
     * 사이클이 존재하면 이분 그래프가 아니란 의미.
     *  */
    boolean[] visited; // 방문배열
    ArrayList<Integer>[] array; // 인접리스트
    int[] checkArr; // 체크 배열
    boolean isBipartiteGraph = true;
    private void checkBipartiteGraph() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < testCaseCnt; i++) {
            String[] str = br.readLine().split(" "); // 인접한 두개의 노드는 공백을 사이에 두고 주어진다.
            int nodeCnt = Integer.parseInt(str[0]);
            int edgeCnt = Integer.parseInt(str[1]);
            visited = new boolean[nodeCnt+1];
            array = new ArrayList[nodeCnt+1];
            checkArr = new int[nodeCnt+1];
            for (int j = 1; j < nodeCnt+1; j++) {
                array[j] = new ArrayList<>();
            }

            // 엣지 데이터 저장하기
            for (int j = 0; j < edgeCnt; j++) {
                str = br.readLine().split(" ");
                int startNode = Integer.parseInt(str[0]);
                int endNode = Integer.parseInt(str[1]);
                // 방향성이 없으면 양방향으로 다 넣어주기
                array[startNode].add(endNode);
                array[endNode].add(startNode);
            }

            // 모든 노드에서 dfs 실행하기
            for (int j = 1; j <= nodeCnt; j++) { // 1부터 시작
                if (isBipartiteGraph) dfs(j);
                else break; // 이분 그래프가 아니면 더 이상 탐색할 필요없음.
            }

            System.out.println("노드개수: " + nodeCnt + ", 엣지개수: " + edgeCnt);
            System.out.println("이분그래프 여부: " + (isBipartiteGraph ? "YES" : "NO"));
        }
    }
    private void dfs(int start) {
        visited[start] = true; // 방문했다
        // ***인접리스트에서 받아서 start에서 연결된 모든 노드를 탐색하게 된다.
        for (int i : array[start]) {
            if (!visited[i]) {
                // 바로 직전에 있는 노드와 다른 집합으로 분류해주기!
                checkArr[i] = (checkArr[start] + 1) % 2;
                dfs(i);
            } else if (checkArr[start] == checkArr[i]) { // 인접한 두 개의 노드가 같은 집합이면, 사이클 존재, 이분그래프가 아니란 의미.
                isBipartiteGraph = false;
            }
        }
    }

    /** 유니온파인드 - 집합 표현하기
     * 0 a b이면 a가 포함된 집합과 b가 포함된 집합 합치기 (union),
     * 1 a b이면 두 원소가 같은 집합에 포함되어 있는지 확인 (find)
     * */
    int[] parent;
    private void unionFind() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeCnt = Integer.parseInt(st.nextToken());
        int queryCnt = Integer.parseInt(st.nextToken());
        parent = new int[nodeCnt + 1];
        for (int i = 0; i < nodeCnt; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < queryCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int question = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (question == 0) {
                union(a, b);
            } else {
                // 같은 집합 원소인지 확인
                if (find(a) == find(b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }
    // 각각의 대표노드를 찾아서, 다르면 두 원소의 대표노드끼리 연결하기.
    private void union(int a, int b) {
        int first = find(a);
        int second = find(b);
        if (first != second) {
            parent[second] = first; // 두 개 연결
        }
    }
    // 대표노드 찾기
    private int find(int a) {
        /* a가 대표노드면 리턴.
        아니면 a의 대표노드값을 find(parent[a]) 값으로 저장 -> 재귀 함수 형태
        * */
        if (a == parent[a]) return a;
        else {
            return parent[a] = find(parent[a]); // **** value를 index로 바꿔서 또 찾기
        }
    }
}
