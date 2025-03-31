package com.example.demo.practice.array;

import java.util.Arrays;

/**
 * 가장 큰 수 구하기 LV2
 * 정수 배열 원소 값들을 조합해서 가장 큰 수가 되게 하기
 * 1 <= numbers <= 100,000
 * 0 <= numbers의 각 원소 <= 1,000
 * ex) [3, 30, 34, 5, 9] -> 9534330
 * ex) [6, 10, 2] -> 6210
 * */
public class BigNumber {
    public void doProcess() {
        solution1(new int[]{6, 10, 2});
        solution1(new int[]{3, 30, 34, 5, 9});
    }

    private void solution1(int[] numbers) {
        String answer = "";

        String[] strArray = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strArray[i] = String.valueOf(numbers[i]);
        }
        // string으로 변환한 숫자 내림차순 정렬
        // o1 : 10, o2 : 2 이면 102과 210 중 뭐가 더 큰지 비교
        Arrays.sort(strArray, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        // 첫 번째 수가 0이면 0으로만 이뤄진 배열이므로 0을 리턴
        if (strArray[0].equals("0")) answer = "0";
        else answer = String.join("", strArray);
        System.out.println(answer);
    }
}
