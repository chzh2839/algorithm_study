package com.example.demo.practice.search;

/**
 * [ 완전탐색 ] LV1
 * 최소 직사각형
 * [가로,세로] 크기의 명함들을 전부 담을 수 있는 최소 지갑의 크기 구하기.
 * 가로/세로를 회전해서 담을 수 있으면 된다.
 *
 * ** 제한사항
 * sizes의 길이는 1 이상 10,000 이하입니다.
 * sizes의 원소는 [w, h] 형식입니다.
 * w는 명함의 가로 길이를 나타냅니다.
 * h는 명함의 세로 길이를 나타냅니다.
 * w와 h는 1 이상 1,000 이하인 자연수입니다.
 *
 * ex1) [[60, 50], [30, 70], [60, 30], [80, 40]]	return 4000 (=80 x 50)
 * ex2) [[10, 7], [12, 3], [8, 15], [14, 7], [5, 15]]	return 120 (=8 x 15)
 * ex3) [[14, 4], [19, 6], [6, 16], [18, 7], [7, 11]]	return 133 (=19 x 7)
 * */
public class MinQuadrangle {
    public void doProcess() {
        solution1(new int[][]{{60, 50}, {30, 70}, {60, 30}, {80, 40}});
    }

    private void solution1(int[][] sizes) {
        int answer = 0;
        int wMax = 0;
        int hMax = 0;
        for (int i = 0; i < sizes.length; i++) {
            int w = Math.max(sizes[i][0], sizes[i][1]);
            int h = Math.min(sizes[i][0], sizes[i][1]);
            wMax = Math.max(wMax, w);
            hMax = Math.max(hMax, h);
        }
        answer = wMax * hMax;
        System.out.println(answer);
    }
}
