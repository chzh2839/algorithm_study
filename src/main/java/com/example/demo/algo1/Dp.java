package com.example.demo.algo1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Do it! 알고리즘 코딩테스트 with JAVA
 * */
public class Dp {
    public void doProcess() {
//        getBinomialCoefficient();
        fibinacci();
    }

    /** 조합 (combination)
     * 이항계수 구하기
     * 1 <= N <= 10
     * 0 <= K <= N
    */
    private void getBinomialCoefficient() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[][] dpArray = new int[n+1][n+1];

        // dp 초기화
        for (int i = 1; i <= n; i++) {
            dpArray[i][i] = 1; // i개에서 모두를 선택하는 경우의 수는 무조건 1개
            dpArray[i][0] = 1; // i개에서 1개도 선택하지 않은 경우의 수는 무조건 1개
            dpArray[i][1] = i; // i개에서 1개 선택하는 경우의 수는 무조건 i개
        }

        // 점화식으로 배열 완성하기
        for (int i = 2; i <= n; i++) { // 1까지는 초기화했으니 2부터
            for (int j = 1; j < n; j++) { // 마지막 i번째 값을 초기화했으니 n은 포함하지 않음
                dpArray[i][j] = dpArray[i-1][j-1] + dpArray[i-1][j];
            }
        }

        for (int i = 0; i < dpArray.length; i++) { // 1까지는 초기화했으니 2부터
            System.out.println("dpArray " + i + " : " + Arrays.toString(dpArray[i]));
        }
        System.out.println("--- dpArray[n][k] 값 출력 -- " + dpArray[n][k]);
    }

    /**
     * 동적 계획법
     * - 피보나치수
     * */
    int[] array;
    private void fibinacci() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        array = new int[n+1];
        for (int i = 0; i <= n; i++) {
            array[i] = -1;
        }
        array[0] = 0; // 초기화
        array[1] = 1; // 초기화
//        topdown(n);
        bottomup();
        System.out.println(array[n]);
    }
    // top-down 방식 - 재귀함수 형태
    private int topdown(int n) {
        if (array[n] != -1) return array[n]; // 기존에 계산한 적이 있으면, 재계산하지 않고 바로 리턴
        return array[n] = topdown(n-1) + topdown(n-2); // 메모이제이션
    }
    // bottom-up 방식
    private void bottomup() {
        for (int i = 2; i < array.length; i++) {
            array[i] = array[i-1] + array[i-2];
        }
    }
}
