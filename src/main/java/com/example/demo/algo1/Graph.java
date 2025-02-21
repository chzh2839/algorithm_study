package com.example.demo.algo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Do it! 알고리즘 코딩테스트 with JAVA
 * (Graph)
 * */
public class Graph {
    public void doProcess() throws IOException {
//        checkBipartiteGraph();
//        unionFind();
//        topologicalSort();
        getShortestDistance();
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

    /** 위상 정렬 - 줄 세우기
     * */
    private void topologicalSort() {
        Scanner sc = new Scanner(System.in);
        int nodeCnt = sc.nextInt();
        int queryCnt = sc.nextInt();
        ArrayList<ArrayList<Integer>> array = new ArrayList<>(); // 인접리스트
        for (int i = 0; i <= nodeCnt; i++) { // 1부터 nodeCnt까지 정렬해야 하니까
            array.add(new ArrayList<>());
        }

        int[] inDegree = new int[nodeCnt+1]; // 진입차수 배열
        for (int i = 0; i < queryCnt; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            array.get(start).add(end);
            inDegree[end]++; // *** 진입차수 배열에 데이터 저장하는 부분!
        }

        // 위상정렬
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= nodeCnt; i++) {
            if (inDegree[i] == 0) queue.offer(i); // 차수배열이 0이면 큐에 담기
        }
        while(!queue.isEmpty()) {
            // 정렬된 값 출력 부분
            int now = queue.poll();
            System.out.print(now + " ");

            for (int next: array.get(now)) {
                inDegree[next]--; // 타깃 노드 차수배열 1씩 차감
                if (inDegree[next] == 0) {
                    queue.offer(next); // 차수배열이 0이면 큐에 담기
                }
            }
        }
    }

    /** 다익스트라 - 최단거리 구하기 */
    class Node {
        int index, cost;
        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }
    ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    boolean[] visitedArray;
    int[] distanceArray;
    private void getShortestDistance() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeCnt = Integer.parseInt(st.nextToken());
        int edgeCnt = Integer.parseInt(st.nextToken());
        int startIndex = Integer.parseInt(st.nextToken());

        visitedArray = new boolean[nodeCnt+1];
        distanceArray = new int[nodeCnt+1];
        Arrays.fill(distanceArray, Integer.MAX_VALUE); // 거리 배열을 최댓값으로 세팅
        for (int i = 0; i <= nodeCnt; i++) {
            graph.add(new ArrayList<>());
        }

        // 가중치가 포함된 인접 그래프 세팅
        for (int i = 0; i < edgeCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, cost));
        }

        // 다익스트라 알고리즘
        dijkstra(startIndex);

        System.out.println(startIndex + "부터 각 노드 간 걸리는 최단거리 확인 결과 : ");
        for (int i = 1; i <= nodeCnt; i++) {
            System.out.print(distanceArray[i] == Integer.MAX_VALUE ? "INF" : distanceArray[i]);
            System.out.print(" ");
        }
        System.out.println();
    }
    private void dijkstra(int startIndex) {
        // 가중치 기준으로 오름차순. 비용이 낮은 것이 우선순위가 높음.
        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        // 출발노드의 가중치는 자기자신이니까 무조건 0
        distanceArray[startIndex] = 0;
        q.add(new Node(startIndex, 0));

        while (!q.isEmpty()) {
            Node current = q.poll(); // 오름차순했으니까, 제알 앞이 우선순위가 가장 높은 것
            if (!visitedArray[current.index]) {
                visitedArray[current.index] = true; // 방문처리
            }

            for (Node next: graph.get(current.index)) {
                // 1. 방문하지 않았고
                // 2. 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧을 경우
                /* ex. (distanceArray[1](=0) + cost(1->2로가는 비용))랑
                * distanceArray[2](=MAX_VALUE) 비교해서 더 낮은 것으로 distanceArray[2]의 cost 변경 */
                if (!visitedArray[next.index] && (distanceArray[next.index] > current.cost + next.cost)) {
                    distanceArray[next.index] = current.cost + next.cost;
                    q.add(new Node(next.index, distanceArray[next.index])); // next.index로 가는 최단거리 세팅
                }
            }
        }
    }
}
