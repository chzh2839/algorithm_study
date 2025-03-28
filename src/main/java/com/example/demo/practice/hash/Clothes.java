package com.example.demo.practice.hash;

import java.util.HashMap;

/**
 * [ HashMap ]
 * 의상 - 의상종류별로 하나씩의 옷을 조합해서 코디하기. 최소 하루에 하나는 무조건 입기.
 *
 * clothes의 각 행은 [의상의 이름, 의상의 종류]로 이루어져 있습니다.
 * 코니가 가진 의상의 수는 1개 이상 30개 이하입니다.
 * 같은 이름을 가진 의상은 존재하지 않습니다.
 * clothes의 모든 원소는 문자열로 이루어져 있습니다.
 * 모든 문자열의 길이는 1 이상 20 이하인 자연수이고 알파벳 소문자 또는 '_' 로만 이루어져 있습니다.
 *
 * ex1) [["yellow_hat", "headgear"], ["blue_sunglasses", "eyewear"], ["green_turban", "headgear"]]	-> 5
 * 1. yellow_hat
 * 2. blue_sunglasses
 * 3. green_turban
 * 4. yellow_hat + blue_sunglasses
 * 5. green_turban + blue_sunglasses
 *
 * ex2) [["crow_mask", "face"], ["blue_sunglasses", "face"], ["smoky_makeup", "face"]]	-> 3
 * 1. crow_mask
 * 2. blue_sunglasses
 * 3. smoky_makeup
 * */
public class Clothes {
    public void doProcess() {
//        solution1(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}});
        solution1(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"},
                {"red_hat", "headgear"}, {"blue_blouse", "upper"}, {"black_shirt", "upper"}});
    }

    private void solution1(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        // {headgear=2, eyewear=1} 이런식으로 매핑하기
        for (int i = 0; i < clothes.length; i++){
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);
        }
        System.out.println(map);

        for (Integer integer : map.values()) {
            answer = (integer + 1) * answer; // 하나만 입는 경우 +1해서 계산
        }
        answer = answer - 1; // 하나도 안 입는 경우 빼기

        System.out.println(answer);
    }
}
