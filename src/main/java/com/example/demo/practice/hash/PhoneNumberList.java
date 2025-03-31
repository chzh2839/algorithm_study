package com.example.demo.practice.hash;

import java.util.HashMap;

/**
 * [ HashMap ] LV2
 * 전화번호 목록
 * 전화번호부(phone_book)에 어떤 번호가 다른 번호의 접두어면 false, 아니면 true
 * 1 <= phone_book <= 1,000,000
 * 1 <= 각 번호의 길이 <= 20
 * 중복된 전화번호는 없음.
 * ex1) ["119", "97674223", "1195524421"]	false
 * ex2) ["123","456","789"]	true
 * ex3) ["12","123","1235","567","88"]	false
 * */
public class PhoneNumberList {
    public void doProcess() {
//        solution3(new String[]{"119", "97674223", "1195524421"});
//        solution1_not_correct(new String[]{"123","456","789"});

        // 대량 데이터 테스트
        String[] array = {"12","123","1235","567","88"};
        int max = 1000;
        String[] array2 = new String[array.length * max];
        for(int i = 0; i < array2.length; i++) {
            int index = i;
            int multi = i / 5;
            if (i >= array.length) {
                index = i - (array.length * multi);
            }
            array2[i] = array[index];
        }
        solution1_not_correct(array2);
    }

    // 시간복잡도 계산 오답임.
    private void solution1_not_correct(String[] phone_book) {
        boolean answer = true;
        for (int i = 0; i < phone_book.length; i++){
            String target = phone_book[i];
            for (int j = 0; j < phone_book.length; j++){
                if (i != j && target.length() <= phone_book[j].length()){
                    if (target.equals(phone_book[j].substring(0, target.length()))) {
                        answer = false;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private void solution2(String[] phone_book) {
        boolean answer = true;
        for (int i = 0; i < phone_book.length-1; i++){
            String target = phone_book[i];
            for (int j = i+1; j < phone_book.length; j++){
                if (target.startsWith(phone_book[j]) || phone_book[j].startsWith(target)) {
                    answer = false;
                    break;
                }
            }
        }

        System.out.println(answer);
    }

    // hashMap 사용
    // hashMap은 값을 넣거나 변경할때 시간복잡도가 O(1)이다.
    // hashMap은 순서 보장 안됨. (순서보장은 LinkedHashMap)
    private void solution3(String[] phone_book) {
        boolean answer = true;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < phone_book.length; i++){
            map.put(phone_book[i], i);
        }
        for (int i = 0; i < phone_book.length; i++){
            for (int j = 0; j < phone_book[i].length(); j++){
                if (map.containsKey(phone_book[i].substring(0, j))) {
                    answer = false;
                }
            }
        }
        System.out.println(answer);
    }
}
