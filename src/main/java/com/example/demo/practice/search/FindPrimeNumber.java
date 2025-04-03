package com.example.demo.practice.search;

import java.util.HashSet;

/**
 * [ 완전탐색 ] LV2
 * 소수 찾기
 *
 * ** 제한사항
 * numbers는 길이 1 이상 7 이하인 문자열입니다.
 * numbers는 0~9까지 숫자만으로 이루어져 있습니다.
 * "013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.
 *
 * ex1) numbers: "17"  return 3
 * => [1, 7]으로는 소수 [7, 17, 71]를 만들 수 있습니다.
 * ex2) numbers: "011"  return 2
 * => [0, 1, 1]으로는 소수 [11, 101]를 만들 수 있습니다.
 * */
public class FindPrimeNumber {
    public void doProcess() {
        solution1("17");
        solution1("011");
    }

    HashSet<Integer> set;
    char[] arr;
    boolean[] visited;
    private void solution1(String numbers) {
        int answer = 0;
        set = new HashSet<>(); // 조합된 숫자 중 중복숫자X
        arr = new char[numbers.length()];
        visited = new boolean[numbers.length()];

        for (int i = 0; i < numbers.length(); i++) {
            arr[i] = numbers.charAt(i);
        }

        dfs("", 0);

        // 소수 찾기
        for (Integer num : set) {
            if (isPrime(num)) answer++;
        }
        System.out.println(answer);
    }
    private void dfs(String str, int index) {
        if (!"".equals(str)) {
            int num = Integer.parseInt(str);
            if (isPrime(num)) set.add(num);
        }
        if (index == arr.length) return; // 끝까지 다 탐색했다면 종료

        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue; // 방문한 노드면 넘어감

            visited[i] = true; // 방문
            dfs(str + arr[i], index+1); // 방문했을 시 재귀
            visited[i] = false; // 백트레킹
        }
    }
    private boolean isPrime(int num) {
        if (num < 2) return false;
//        for(int i = 2; i <= (int) Math.sqrt(num); i++){
        for(int i = 2; i*i <= num; i++){
            if (num % i == 0) return false;
        }
        return true;
    }

    /** 정상 결과가 나오는데, 특정 case에서 실패남.
     * 실패나는 case는 찾지 못함. */
    private void solution2_not_correct(String numbers) {
        int answer = 0;

        // 숫자 조합하기
        set = new HashSet<>();
        visited = new boolean[numbers.length()];
        dfs(numbers, "", 0);

        // 소수 찾기
        for (Integer num:set){
            if (isPrime(num)) answer++;
        }
        System.out.println(answer);
    }
    private void dfs(String numbers, String s, int depth) {
        if (depth >= numbers.length()) return;
        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]){
                visited[i] = true;
                set.add(Integer.parseInt(s+numbers.charAt(i)));
                dfs(numbers, s+numbers.charAt(i), depth+1);
                visited[i] = false;
            }
        }
    }
}
