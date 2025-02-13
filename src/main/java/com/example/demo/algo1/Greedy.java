package com.example.demo.algo1;

import java.io.IOException;
import java.util.*;

/**
 * Do it! 알고리즘 코딩테스트 with JAVA
 * (그리디 알고리즘 Greedy Algorithm)
 * */
public class Greedy {
    public void doProcess() throws IOException {
        getMinimumChangeCoins();
    }

    /** 동전 개수 최소값 구하기 / 거스름돈
     * *** array[i]는 array[i-1]의 배수여야 한다. - 이 조건으로 반례를 없앰으로 그리디 알고리즘 사용가능. */
    private void getMinimumChangeCoins() {
        Scanner sc = new Scanner(System.in);
        int coinCnt = sc.nextInt();
        int goalAmount = sc.nextInt();
        int[] coinArray = new int[coinCnt];
        for (int i = 0; i < coinCnt; i++) {
            coinArray[i] = sc.nextInt();
        }

        int coinUseCnt = 0;
        for (int i = coinCnt-1; i >= 0; i--) { // 최대한 큰 동전 먼저 사용하기
            if (coinArray[i] <= goalAmount) {
                int use = goalAmount / coinArray[i];
                coinUseCnt += use;
                goalAmount = goalAmount % coinArray[i];

                System.out.printf("%d원 동전을 %d개 사용했습니다.\n", coinArray[i], use);
            }
        }

        System.out.println("사용한 동전 개수 : " + coinUseCnt);
    }
}
