package com.example.demo.algo1;

import java.util.Scanner;

/**
 * Do it! 알고리즘 코딩테스트 with JAVA
 * (정수론 - 소수 prime number 구하기)
 * */
public class PrimeNumber {
    public void doProcess() {
        getPrimeNumbers();
    }

    /** 소수 구하기
     * n ~ m까지의 숫자 중에서 소수인 것만 구하기 (에라토스테네스 방법으로 해결) */
    private void getPrimeNumbers() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] array = new int[m+1];
        for (int i = 1; i <= m; i++) {
            array[i] = i; // 0은 제외하고 1~m까지 숫자를 배열에 담기
        }

        // 1은 소수가 아니니까 2부터 시작.
        // m의 제곱근까지 반복. => m이 a*b라고 가정했을 때, a와 b 모두 m의 제곱근보다 클 수 없음!!
        for (int i = 2; i <= Math.sqrt(m); i++) {
            if (array[i] == 0) continue; // 소수가 아니면 skip
            for (int j = i + i; j <= m; j += i) { // i의 배수 찾기. 배수라는 건 소수가 아니라는 의미임.
                array[j] = 0; // 소수가 아니면 0처리
            }
        }

        System.out.println("--- 소수 찾기 완료 ---");
        for (int i = n; i < m; i++) {
            if (array[i] != 0) System.out.println(array[i]);
        }
    }
}
