package com.example.demo.algo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Do it! 알고리즘 코딩테스트 with JAVA
 * (Tree)
 * */
public class Tree {
    public void doProcess() throws IOException {
//        findParent();
//        indexTree();
//        indexTree2();
        lca();
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

    /** 세그먼트 트리 (인덱스 트리)
     * input : 5 8 4 3 7 2 1 6
     * 1. 구간합 구하기
     * */
    private void indexTree() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeCnt = Integer.parseInt(st.nextToken()); // = 리프노드 개수

        // 1. 트리 배열 초기화
        int k = 0;
        while ((int) Math.pow(2, k) < nodeCnt) {
            k++;
        }
        int startIndex = (int) Math.pow(2, k);
        int arraySize = startIndex * 2; // 부모노드를 포함한 트리의 전체 노드 개수 (2의 k제곱 값 * 2)
        long[] tree = new long[arraySize];
        System.out.println("k: " + k + ", arraySize : " + arraySize);
        // 리프노드 트리에 채우기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nodeCnt; i ++) {
            tree[startIndex + i] = Long.parseLong(st.nextToken()); // 리프노드만 입력받기
        }
        // 부모노드 트리에 채우기
        for (int i = startIndex-1; i >= 1; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
        System.out.println("중간 체크 1 : " + Arrays.toString(tree));

        // 2. 질의 값 구하기 (구간 합)
        st = new StringTokenizer(br.readLine());
        int op = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        if (op == 0) {
            // 2-1) x번째 수를 y로 변경
            int targetIndex = startIndex + (x - 1);
            long diff = y - tree[targetIndex];
            while (targetIndex > 0) {
                // x번째 리프노드의 부모노드들 전부 변경.
                // 변경된 값 diff만큼 모두 더하기
                tree[targetIndex] += diff;
                targetIndex /= 2; // 변경 후, 부모노드 찾기
            }
            System.out.println("변경 후 : " + Arrays.toString(tree));
        } else {
            // 2-2) x ~ y까지의 구간합
            int start = x + (startIndex - 1);
            int end = y + (startIndex - 1);
            long result = 0;
            while (start <= end) {
                // x~y범위의 노드가 start/end와 동일한 부모를 가진 자식 노드가 전부 포함되어 있는지
                // start가 홀수면 동일한 부모를 가진 자식 노드를 포함하면 안 되므로, 해당 start의 값만 독립적으로 계산.
                // end도 동일. end는 짝수일 경우 그러함.
                if (start % 2 == 1) result += tree[start];
                if (end % 2 == 0) result += tree[end];
                // 부모노드를 구하는 index/2 공식에서, 만약 start/end가 위에서 독립적으로 계산된 case를 걸러내기 위해 +1/-1을 함.
                start = (start + 1) / 2;
                end = (end - 1) / 2;
            }
            System.out.printf("리프노드 %d에서 %d까지 구간합 : %d", x, y, result);
            System.out.println();
        }
        br.close();
    }

    /** 세그먼트 트리 (인덱스 트리)
     * input : 5 8 4 3 7 2 1 6
     * 2. 최대값
     * 3. 최소값
     * */
    private void indexTree2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 정수 개수
        int m = Integer.parseInt(st.nextToken()); // // 최솟,최댓값 찾는 횟수
        long[] array = new long[n+1];
        // 리프노드 트리에 채우기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i ++) {
            array[i] = Long.parseLong(st.nextToken()); // 리프노드만 입력받기
        }

        // 트리 사이즈 구하기
        int size = getTreeSize(n);
        long[] minSegmentTree = new long[size]; // 최솟값 세그먼트 트리
        long[] maxSegmentTree = new long[size]; // 최대값 세그먼트 트리

        // 트리 각각 초기화
        initTree(array, minSegmentTree, 1, 0, n-1, "min");
        initTree(array, maxSegmentTree, 1, 0, n-1, "max");
        System.out.println("중간 체크 min : " + Arrays.toString(minSegmentTree));
        System.out.println("중간 체크 max : " + Arrays.toString(maxSegmentTree));

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            long minValue = getValue(minSegmentTree, 1, 0, n-1, a, b, "min");
            long maxValue = getValue(maxSegmentTree, 1, 0, n-1, a, b, "max");

            System.out.printf("최소값: %d, 최대값: %d", minValue, maxValue);
            System.out.println();
        }
        br.close();
    }
    private int getTreeSize(int n) {
        // 세그먼트 트리 전체 노드 수 계산
        // 세그먼트 트리의 높이 = logN + 1 (밑이 2인 로그)
        int height = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        return (int) Math.pow(2, height); // 전체 노드 수 = 2^트리의 높이
    }
    private long initTree(long[] array, long[] tree, int currentNode, int start, int end, String minOrMax) {
        if (start == end) {
            // 현재 노드가 리프노드인 경우, 현재 노드에 배열 값 저장
            return tree[currentNode] = array[start];
        } else {
            int mid = (start + end) / 2;
            long leftChildNodeValue = initTree(array, tree, currentNode * 2, start, mid, minOrMax); // 왼쪽 자식 노드 탐색
            long rightChildNodeValue = initTree(array, tree, (currentNode * 2) + 1, mid + 1, end, minOrMax); // 오른쪽 자식 노드 탐색
            if (minOrMax.equals("min")) {
                // 최솟 값 세그먼트 트리인 경우 자식노드 중 작은 값을 가짐
                return tree[currentNode] = Math.min(leftChildNodeValue, rightChildNodeValue);
            } else {
                // 최댓 값 세그먼트 트리인 경우 자식노드 중 큰 값을 가짐
                return tree[currentNode] = Math.max(leftChildNodeValue, rightChildNodeValue);
            }
        }
    }
    private long getValue(long[] tree, int currentNode, int start, int end, int a, int b, String minOrMax) {
        if (end < a || start > b) {
            // 최솟, 최댓 값을 탐색할 범위가 노드가 가지는 범위를 벗어난 경우
            return minOrMax.equals("min") ? Long.MAX_VALUE : Long.MIN_VALUE;
        }

        if (start >= a && end <= b) {
            // 최솟, 최댓 값을 탐색할 범위가 노드가 가지는 범위보다 큰 경우 노드 값 리턴
            return tree[currentNode];
        }

        // 그 외의 경우는 2가지가 더 존재
        // 최솟, 최댓 값을 탐색할 범위보다 노드가 가지는 범위가 큰 경우
        // 최솟, 최댓 값을 탐색할 범위에 노드가 가지는 범위가 일부 포함 되는 경우
        // 위의 2가지 경우에 해당할 때는 자식노드를 탐색 후 자식노드의 값을 가져옴
        int mid = (start + end) / 2;
        long leftChildNodeValue = getValue(tree, currentNode * 2, start, mid, a, b, minOrMax); // 왼쪽 자식 노드 탐색
        long rightChildNodeValue = getValue(tree, (currentNode * 2) + 1, mid + 1, end, a, b, minOrMax); // 오른쪽 자식 노드 탐색
        if (minOrMax.equals("min")) {
            return Math.min(leftChildNodeValue, rightChildNodeValue);
        } else {
            return Math.max(leftChildNodeValue, rightChildNodeValue);
        }
    }

    /** LCA(최소 공통 조상)
     * input :
15
1 2
1 3
2 4
3 7
6 2
3 8
4 9
2 5
5 11
7 13
10 4
11 15
12 5
14 7
6
6 11
10 9
2 6
7 6
8 13
8 15
     * 1. 일반적인 LCA 풀이 */
    ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    int[] nodeDepth;
    int[][] parent;
    private void lca() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 정수 개수
        for (int i = 0; i < n+1; i ++) {
            tree.add(new ArrayList<>());
        }

        //트리 정보 저장하기
        for (int i = 1; i < n; i ++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }
//        for (int i = 0; i < n+1; i++) {
//            System.out.println("tree " + i + " : " + Arrays.toString(tree.get(i).toArray()));
//        }

        int height = getTreeHeight(n);
        nodeDepth = new int[n+1];
        parent = new int[n+1][height];
        setTree(1, 1, 0); //트리 형태 구성 및 높이 설정
//        System.out.println("node depth : " + Arrays.toString(nodeDepth));
//        for (int i = 0; i < n+1; i++) {
//            System.out.println("-parent " + i + " : " + Arrays.toString(parent[i]));
//        }
        parentInit(height, n); // 점화식을 통해 부모 노드 DP 구성
//        for (int i = 0; i < n+1; i++) {
//            System.out.println("parent " + i + " : " + Arrays.toString(parent[i]));
//        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.printf("lca(%d, %d) : %d", a, b, doLca(a, b, height));
            System.out.println();
        }
        br.close();
    }
    private int getTreeHeight(int n) {
        return (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
    }
    //트리의 형태를 만드는 재귀 함수
    private void setTree(int currentNode, int depth, int parentNode) {
        nodeDepth[currentNode] = depth; // 현재 노드 깊이 세팅
        parent[currentNode][0] = parentNode; // 부모 노드 세팅
        // 자식 노드 세팅
        for (int next: tree.get(currentNode)) {
            if (next == parentNode) continue;
            setTree(next, depth + 1, currentNode);
        }
    }
    //부모 노드에 대한 DP 점화식을 이용하여 구성하는 함수
    private void parentInit(int height, int n) {
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < n+1; j++) {
                parent[j][i] = parent[parent[j][i-1]][i-1];
            }
        }
    }
    private int doLca(int a, int b, int height) {
        int aDepth = nodeDepth[a];
        int bDepth = nodeDepth[b];
        if (aDepth < bDepth) { // aDepth > bDepth로 세팅
            int temp = a;
            a = b;
            b = temp;
        }

        // 높이 동일하게 맞추기
        for (int i = height-1; i >= 0; i--) {
            // depth[a] - depth[b]는 깊이의 차
            // 깊이의 차만큼 이동
            if (Math.pow(2, i) <= (nodeDepth[a] - nodeDepth[b])) {
                a = parent[a][i];
            }
        }

        if (a == b) return a; // a와 b가 같다면 이미 공통 조상 노드에 도착했다고 판단

        // 동시에 거슬러 올라가면서, 두 노드가 같은 노드가 될때까지 반복
        // 처음 만나는 노드가 최소 공통 조상임.
        for (int i = height-1; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        return parent[a][0];
    }

    /** LCA(최소 공통 조상)
     * 2. 빠르게 풀기 - 세그먼트 트리 활용 */
    // 참고: https://loosie.tistory.com/364
    private void lca2() throws IOException {

    }
}
