package com.example.demo.practice.search;

import java.util.ArrayList;

/**
 * [ 완전탐색 ] LV2
 * 전력망을 둘로 나누기
 * n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다.
 * 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다.
 * 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.
 *
 * ** 제한사항
 * n은 2 이상 100 이하인 자연수입니다.
 * wires는 길이가 n-1인 정수형 2차원 배열입니다.
 * wires의 각 원소는 [v1, v2] 2개의 자연수로 이루어져 있으며, 이는 전력망의 v1번 송전탑과 v2번 송전탑이 전선으로 연결되어 있다는 것을 의미합니다.
 * 1 ≤ v1 < v2 ≤ n 입니다.
 * 전력망 네트워크가 하나의 트리 형태가 아닌 경우는 입력으로 주어지지 않습니다.
 *
 * ex1) n: 9, wires: [[1,3],[2,3],[3,4],[4,5],[4,6],[4,7],[7,8],[7,9]]  return 3
 * ex1) n: 4, wires: [[1,2],[2,3],[3,4]]  return 0
 * ex1) n: 7, wires: [[1,2],[2,7],[3,7],[3,4],[4,5],[6,7]]  return 1
 * */
public class DivideWires {
    public void doProcess() {
        solution1(9, new int[][]{{1, 3},{2, 3},{3, 4},{4, 5},{4, 6},{4, 7},{7, 8},{7, 9}});
        solution1(4, new int[][]{{1, 2},{2, 3},{3, 4}});
        solution1(7, new int[][]{{1, 2},{2, 7},{3, 7},{3, 4},{4, 5},{6, 7}});
    }

    ArrayList<Integer>[] graph;
    boolean[] visited;
    private void solution1(int n, int[][] wires) {
        graph = new ArrayList[n + 1];
        int answer = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            // 양반향 간선 구조이므로 두번 add
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        // 하나씩 제거해서 return 값 찾기
        for (int i = 0 ; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];

            visited = new boolean[n + 1];

            // 양방향이므로 두번 remove
            graph[v1].remove(Integer.valueOf(v2));
            graph[v2].remove(Integer.valueOf(v1));

            int nodeCntOfOnePart = dfs(1); // 임의의 시작점에서 탐색 (첫번째 노드와 붙어있는 전력의 노드 수 구하기)
            int nodeCntOfOtherPart = n - nodeCntOfOnePart;
            int diff = Math.abs(nodeCntOfOnePart - nodeCntOfOtherPart);
            answer = Math.min(answer, diff); // 양 쪽 전력 노드 수 차이가 가장 적은 거 출력

            // 위에서 remove한거 다시 add
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        System.out.println(answer);
    }
    private int dfs(int v) {
        visited[v] = true;
        int cnt = 1;

        for (int next: graph[v]) {
            if (!visited[next]) cnt += dfs(next);
        }
        return cnt;
    }
}
