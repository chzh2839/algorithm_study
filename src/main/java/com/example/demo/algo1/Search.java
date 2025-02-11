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
//        getConnectedComponent();
        searchMaze();
    }

    /** DFS - 연결요소 구하기 */
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
    /** BFS - 미로 탐색
     * [
     *  [0, 1, 0, 1, 1, 0],
     *  [0, 0, 1, 0, 1, 1],
     *  [0, 1, 0, 0, 0, 0],
     *  [0, 1, 1, 1, 0, 0],
     * ]
     * 위 이차원 배열에서 (0,0)에서 마지막 (5,3)위치로 가는 횟수 구하기
     * 단, 상하좌우로 1일 경우에만 이동 가능
     * */
    // 상하좌우를 탐색하기 위한 define값 정의 변수
    // ex. index=0 => x축 0만큼 이동, y축 1만큼 이동은 배열에서 아래로 이동
    // ex. index=1 => x축 1만큼 이동, y축 0만큼 이동은 배열에서 오른쪽으로 이동
    int[] xMove = {0,1,0,-1};
    int[] yMove = {1,0,-1,0};
    boolean[][] visited2; // 방문배열
    int[][] array2;
    int widthCnt;
    int heightCnt;
    private void searchMaze() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        widthCnt = Integer.parseInt(st.nextToken());
        heightCnt = Integer.parseInt(st.nextToken());
        visited2 = new boolean[widthCnt][heightCnt];
        // 인접리스트 구현
        array2 = new int[widthCnt][heightCnt];
        for (int w = 0; w < widthCnt; w++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken(); // ex. 110010
            for (int h = 0; h < heightCnt; h++) {
                array2[w][h] = Integer.parseInt(line.substring(h, h+1));
            }
        }

        bfs(0,0); // 이차원배열 (0,0) 위치에서 시작
        System.out.println("마지막 위치까지 간 횟수 : " + array2[widthCnt-1][heightCnt-1]);
    }
    private void bfs(int start, int end) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, end}); // offer()와 add()의 차이 참고 https://atomicliquors.tistory.com/26

        System.out.println("queue 초기값 : " + queue.stream().map(Arrays::toString).toList());

        // 큐가 빌때까지 반복
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            visited2[start][end] = true;
            for (int i = 0; i < 4; i++) { // 상하좌우 탐색
                int x = now[0] + xMove[i];
                int y = now[1] + yMove[i];
                if (x >= 0 && x < widthCnt && y >= 0 && y < heightCnt) {
                    if (array2[x][y] != 0 && !visited2[x][y]) {
                        System.out.printf("x로 %d만큼 y로 %d만큼 이동합니다.\n", xMove[i], yMove[i]);
                        // 이동하려는 위치의 값이 0이 아니고, 방문하지 않았어야 함
                        visited2[x][y] = true;
                        array2[x][y] = array2[now[0]][now[1]] + 1; // 배열 값이 1뿐이니, 이동한 횟수를 여기에 1씩 더하면서 이동
                        queue.add(new int[]{x, y});
                    }
                }
            }
        }
    }
}
