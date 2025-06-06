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
//        getShortestDistance();
//        getFastestTime();
//        getPath();
        mst();
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
//            if (!visitedArray[current.index]) {
//                visitedArray[current.index] = true; // 방문처리
//            }

            /* 방문 배열 없이 처리하는 방법
            * - 해당 노드에 대한 distance 값은 여러번 갱신될 수 있어서 큐에 자주 들어갈 수있지만,
            *  한번 방문됬다면 distance의 값은 최소값이다. */
            if (distanceArray[current.index] < current.cost) {
                continue;
            }

            for (Node next: graph.get(current.index)) {
                // 1. 방문하지 않았고
                // 2. 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧을 경우
                /* ex. (distanceArray[1](=0) + cost(1->2로가는 비용))랑
                * distanceArray[2](=MAX_VALUE) 비교해서 더 낮은 것으로 distanceArray[2]의 cost 변경 */
                if (/*!visitedArray[next.index] &&*/ (distanceArray[next.index] > current.cost + next.cost)) {
                    distanceArray[next.index] = current.cost + next.cost;
                    q.add(new Node(next.index, distanceArray[next.index])); // next.index로 가는 최단거리 세팅
                }
            }
        }
    }

    /** 벨만-포드 - A->B까지 가는 가장 빠른 시간 구하기
     * N개의 도시, M개의 버스노선. A(시작도시) -> B(도착도시) 걸리는 시간(C)
     * C == 0이면 순간이동, C < 0 이면 타임머신으로 시간을 되돌아가는 경우.
     * 출력 : 무한히 오래 전으로 되돌릴 수 있다면 첫째 줄에 -1을 출력.
     *      1번 도시에서 출발해 2번 도시, 3번 도시, ..., N번 도시로 가는 가장 빠른 시간을 순서대로.
     *      만약 해당 도시로 가는 경로가 없다면 대신 -1을 출력. */
    BufferedReader bufferedReader;
    StringTokenizer stringTokenizer;
    long INF = Long.MAX_VALUE;
    int cityCnt, edgeCnt;
    private void getFastestTime() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        cityCnt = Integer.parseInt(stringTokenizer.nextToken());
        edgeCnt = Integer.parseInt(stringTokenizer.nextToken());

        long[] times = new long[cityCnt + 1]; // 최단시간배열
        Arrays.fill(times, INF);
        times[1] = 0; // 1부터 시작. 시작점은 0

        boolean isMinusCycle = bellmanFord(times);
        if (isMinusCycle) System.out.println("-1");
        else {
            for (int i = 2; i < times.length; i++) {
                if (times[i] == INF) {
                    System.out.println("-1");
                } else {
                    System.out.println(times[i]);
                }
            }
        }

        bufferedReader.close();
    }
    private boolean bellmanFord(long[] times) throws IOException {
        boolean isMinusCycle = false;

        Edge[] eg = new Edge[edgeCnt];
        for (int i = 0; i < edgeCnt; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            long time = Long.parseLong(stringTokenizer.nextToken());
            eg[i] = new Edge(start, end, time);
        }

        int cityCnt = times.length - 1;
        for (int i = 0; i < cityCnt + 1; i++) {
            for (int j = 0; j < edgeCnt; j++) {
                Edge current = eg[j];
                if (times[current.start] == INF) continue;

                if (times[current.end] > times[current.start] + current.time) {
                    times[current.end] = times[current.start] + current.time;
                    if (i == cityCnt) {
                        isMinusCycle = true;
                        break;
                    }
                }
            }
        }
        return isMinusCycle;
    }

    /** 플로이드-워셜 - 경로찾기 */
    long[][] graph2;
    private void getPath() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int nodeCnt = Integer.parseInt(stringTokenizer.nextToken());
        int queryCnt = Integer.parseInt(stringTokenizer.nextToken());

        graph2 = new long[nodeCnt][nodeCnt];
        for (int i = 0; i < nodeCnt; i++) {
            Arrays.fill(graph2[i], INF);
            graph2[i][i] = 0; // 본인한테 가는 건 0
        }
        for (int i = 0; i < queryCnt; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());

            // 가는 경로가 하나가 아닐 수 있다. 따라서 그 중 최소 비용을 저장해두면 된다.
            graph2[start][end] = Math.min(graph2[start][end], cost);
            graph2[end][start] = Math.min(graph2[end][start], cost);
        }
        bufferedReader.close();

        floydWarshall(nodeCnt);

        for (int i = 0; i < nodeCnt; i++) {
            for (int j = 0; j < nodeCnt; j++) {
                if(graph2[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(graph2[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    private void floydWarshall(int nodeCnt) {
        for (int k = 0; k < nodeCnt; k++) {
            for (int s = 0; s < nodeCnt; s++) {
                for (int e = 0; e < nodeCnt; e++) {
                    graph2[s][e] = Math.min(graph2[s][e], graph2[s][k] + graph2[k][e]);
                }
            }
        }
    }

    /** 최소 신장 트리 - 칼루스칼 알고리즘 */
    private void mst() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int nodeCnt = Integer.parseInt(stringTokenizer.nextToken());
        int edgeCnt = Integer.parseInt(stringTokenizer.nextToken());
        int[][] graph = new int[edgeCnt][3];
        for (int i = 0; i < edgeCnt; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());
            graph[i][0] = start;
            graph[i][1] = end;
            graph[i][2] = cost;
        }

        // 가중치 기준으로 오름차순 정렬
        Arrays.sort(graph, (o1, o2) -> o1[2] - o2[2]);

        parent = new int[nodeCnt + 1];
        for (int i = 0; i < nodeCnt; i++) {
            parent[i] = i;
        }

        kruskal(graph);
    }
    private void kruskal(int[][] graph) {
        int minimumCost = 0;
        for (int i = 0; i < graph.length; i++) {
            if (find(graph[i][0]) != find(graph[i][1])) {
                // start와 end의 대표노드가 같지 않으면 union으로 연결해주기
                // start와 end의 대표노드가 같다는 건, 사이클이 있다는 의미임.
                minimumCost += graph[i][2];
                union(graph[i][0], graph[i][1]);
            }
        }
        System.out.println("최소 가중치: " + minimumCost);
    }
}

class Node {
    int index, cost;
    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
}

class Edge {
    int start, end;
    long time;
    public Edge(int start, int end, long time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }
}