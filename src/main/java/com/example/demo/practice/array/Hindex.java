package com.example.demo.practice.array;

import java.util.Arrays;

/**
 * H-index 구하기
 * 어떤 과학자가 발표한 논문 n편 중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 이 과학자의 H-Index입니다.
 * 1 <= n <= 1,000
 * 0 <= h <= 10,000
 * ex) [3, 0, 6, 1, 5] -> 3
 * 이 과학자가 발표한 논문의 수는 5편이고, 그중 3편의 논문은 3회 이상 인용되었습니다. 그리고 나머지 2편의 논문은 3회 이하 인용되었기 때문에 이 과학자의 H-Index는 3입니다.
 * */
/**
 * 추가ex1) [10, 8, 5, 4, 3] 의 인용횟수를 가진 교수가 있다면
 * 10번 이상 인용 횟수를 가진 논문은 1편입니다. 이때 H-Index는 1입니다.
 * 8번 이상 인용 횟수를 가진 논문은 2편입니다. 이때 H-Index는 2입니다.
 * 5번 이상 인용 횟수를 가진 논문은 3편입니다. 이때 H-Index는 3입니다.
 * 4번 이상 인용 횟수를 가진 논문은 4편입니다. 이때 H-Index는 4입니다.
 * 3번 이상 인용 횟수를 가진 논문은 5편입니다. 이때 H-Index는 3입니다.
 * => 여기서 H-Index는 4입니다
 * */
/**
 * 추가ex2) [9, 7, 6, 2, 1] 의 인용횟수를 가진 교수가 있다면
 * 9번 이상 인용 횟수를 가진 논문은 1편입니다. 이때 H-Index는 1입니다.
 * 7번 이상 인용 횟수를 가진 논문은 2편입니다. 이때 H-Index는 2입니다.
 * 6번 이상 인용 횟수를 가진 논문은 3편입니다. 이때 H-Index는 3입니다.
 * 2번 이상 인용 횟수를 가진 논문은 4편입니다. 이때 H-Index는 2입니다.
 * 1번 이상 인용 횟수를 가진 논문은 5편입니다. 이때 H-Index는 1입니다.
 * => 여기서 H-Index는 3입니다
 * */
public class Hindex {
    public void doProcess() {
        solution2(new int[]{3, 0, 6, 1, 5});
        solution2(new int[]{10000, 9999, 9998, 9997, 9996});
        solution2(new int[]{0,1,1,1,3,5,6});
    }

    private void solution1(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);

        for (int i = 0; i < citations.length; i++) {
            int h = citations.length - i;
            if (h <= citations[i]) {
                answer = h;
                break;
            }
        }
        System.out.println(answer);
    }

    private void solution2(int[] citations) {
        Arrays.sort(citations);
        int max = 0;
        for (int i = (citations.length-1); i >= 0; i--) {
            int min = Math.min(citations[i], citations.length - i);
            if (max < min) max = min;
        }
        System.out.println(max);
    }
}
