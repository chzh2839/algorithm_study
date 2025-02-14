package com.example.demo.algo1;

import java.io.IOException;
import java.util.*;

/**
 * Do it! 알고리즘 코딩테스트 with JAVA
 * (그리디 알고리즘 Greedy Algorithm)
 * */
public class Greedy {
    public void doProcess() {
//        getMinimumChangeCoins();
        getMinimumResult();
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

    /** +/-로 된 수식에 괄호를 이용해서 최소의 결과값 만들기
     * 가장 큰 수에서 빼면 되기 때문에, 덧셈 먼저 다 하고, 빼기 */
    private void getMinimumResult() {
        Scanner sc = new Scanner(System.in);
        String example = sc.nextLine(); // ex. 100-40+50+74-30+29-45+43+11
        String[] splitByMinus = example.split("-");

        int result = 0;
        for (int i = 0; i < splitByMinus.length; i++) {
            int temp = getSum(splitByMinus[i]);
            if (i == 0) result += temp;
            else result -= temp;
        }

        System.out.println("최소 결과값 : " + result);
    }
    private int getSum(String text) {
        String[] splitByPlus = text.split("\\+");
        int sumValue = 0;
        for (int i = 0; i < splitByPlus.length; i++) {
            sumValue += Integer.parseInt(splitByPlus[i]);
        }
        return sumValue;
    }
}
