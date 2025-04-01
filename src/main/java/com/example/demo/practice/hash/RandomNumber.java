package com.example.demo.practice.hash;

import java.util.*;

/**
 * LV0
 * 무작위로 K개의 수 뽑기
 * 정수 배열 arr가 주어집니다. 문제에서의 무작위의 수는 arr에 저장된 순서대로 주어질 예정이라고 했을 때, 완성될 배열을 return 하는 solution 함수를 완성해 주세요.
 * 단, 완성될 배열의 길이가 k보다 작으면 나머지 값을 전부 -1로 채워서 return 합니다.
 *
 * 1 ≤ arr의 길이 ≤ 100,000
 * 0 ≤ arr의 원소 ≤ 100,000
 * 1 ≤ k ≤ 1,000
 *
 * ex1)
 * arr : [0, 1, 1, 2, 2, 3]
 * k : 3
 * return [0, 1, 2]
 *
 * ex2)
 * arr : [0, 1, 1, 1, 1]
 * k : 4
 * return [0, 1, -1, -1]
 * */
public class RandomNumber {
    public void doProcess() {
        solution1(new int[]{0, 1, 1, 2, 2, 3}, 3);
        solution1(new int[]{0, 1, 1, 1, 1}, 4);
    }

    private void solution1(int[] arr, int k) {
        int[] answer = new int[k];
        Arrays.fill(answer, -1);

        // 반복을 없애면서 순서를 보장하는 LinkedHashSet 사용
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for (int ele: arr){
            set.add(ele);
        }
        ArrayList<Integer> list = new ArrayList<>(set);
        int index = Math.min(list.size(), k);
        for (int i = 0 ; i < index; i++){
            answer[i] = list.get(i);
        }
        System.out.println(Arrays.toString(answer));
    }
}
