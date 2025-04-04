package com.example.demo.practice.search;

import java.util.ArrayList;
import java.util.Collections;

/**
 * [ 완전탐색 ] LV2
 * 모음사전
 * 사전에 알파벳 모음 'A', 'E', 'I', 'O', 'U'만을 사용하여 만들 수 있는, 길이 5 이하의 모든 단어가 수록되어 있습니다.
 * 사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA"이며, 마지막 단어는 "UUUUU"입니다.
 *
 * ** 제한사항
 * word의 길이는 1 이상 5 이하입니다.
 * word는 알파벳 대문자 'A', 'E', 'I', 'O', 'U'로만 이루어져 있습니다.
 *
 * ex1) word: "AAAAE"  return 6
 * ex2) word: "AAAE"  return 10
 * ex3) word: "I"  return 1563
 * ex4) word: "EIO"  return 1189
 * */
public class WordDictionary {
    public void doProcess() {
        solution1("AAAAE");
        solution1("AAAE");
        solution1("I");
        solution1("EIO");
    }

    ArrayList<String> list;
    char[] array = new char[]{'A', 'E', 'I', 'O', 'U'};
    private void solution1(String word) {
        int answer = 0;
        list = new ArrayList<>();
        dfs("", word);
        Collections.sort(list);
        answer = list.indexOf(word) + 1;
        System.out.println(answer);
    }
    private void dfs(String cur, String word) {
        if (cur.length() == array.length || cur.equals(word)) return;
        for (int i  = 0; i < array.length; i++) {
            list.add(cur + array[i]);
            dfs(cur + array[i], word);
        }
    }

    /** 정답인데, 인텔리제이에서 실행하면 결과값이 다르게 나옴...
     * 코딩테스트 환경과 인텔레제이 환경에서 sb에 들어가는 값 순서가 다른 것 같음. */
    StringBuilder sb = new StringBuilder();
    boolean found = false;
    int answer2 = 0;
    private void solution2(String word) {
        dictionary(word);
        System.out.println(answer2);
    }
    private void dictionary(String word) {
        if (word.equals(sb.toString())) {
            found = true;
            return;
        }
        if (sb.length() == array.length) return;

        for (int i = 0; i < array.length; i++) {
            answer2++;
            sb.append(array[i]);
            dictionary(word);
            sb.deleteCharAt(sb.length() - 1);

            if (found) return;
        }
    }
}
