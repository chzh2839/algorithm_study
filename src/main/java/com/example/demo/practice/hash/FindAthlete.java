package com.example.demo.practice.hash;

import java.util.Arrays;
import java.util.HashMap;

/**
 * [ HashMap ] LV1
 * 완주하지 못한 선수 찾기
 * 마라톤 참가 선수 명단(participant)에서 완주하지 못한 단 1명의 선수 찾기
 * 1 <= participant <= 100,000
 * 완주선수명단(completion)은 participant의 길이보다 1 작다.
 * 선수명은 1개 이상 20개 이하의 알파벳 소문자. 동명이인 있음.
 * ex1) ["leo", "kiki", "eden"]	["eden", "kiki"]	-> "leo"
 * ex2) ["marina", "josipa", "nikola", "vinko", "filipa"]	["josipa", "filipa", "marina", "nikola"]	-> "vinko"
 * ex3) ["mislav", "stanko", "mislav", "ana"]	["stanko", "ana", "mislav"]	-> "mislav"
 * */
public class FindAthlete {
    public void doProcess() {
        solution2(new String[]{"leo", "kiki", "eden"}, new String[]{"kiki", "eden"});
        solution2(new String[]{"marina", "josipa", "nikola", "vinko", "filipa"}, new String[]{"josipa", "filipa", "marina", "nikola"});
        solution2(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"});
        solution2(new String[]{"kiki", "eden", "leo"}, new String[]{"kiki", "eden"});
    }

    private void solution1(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        // 선수당 참가명단에 몇번들어갔는지 value값으로 설정 (동명이인이 존재하기 때문)
        // ex. {ana=1, mislav=2, stanko=1}
        for (int i = 0; i < participant.length; i++){
            map.put(participant[i], map.getOrDefault(participant[i], 0) + 1);
        }
        System.out.println(map);

        // 완주한 선수들 value값 1씩 감소하기
        // value가 최종적으로 1로 남아있는 선수 하나가 완주하지 않은 선수임!
        for (int i = 0; i < completion.length; i++){
            map.replace(completion[i], map.get(completion[i]) - 1);
        }

        for (String key: map.keySet()){
            if (map.get(key) != 0) answer = key;
        }

        System.out.println(answer);
    }

    private void solution2(String[] participant, String[] completion) {
        String answer = "";
        Arrays.sort(participant);
        Arrays.sort(completion);
        int i;
        for (i = 0; i < completion.length; i++){
            if (!completion[i].equals(participant[i])) {
                answer = participant[i];
                break;
            }
        }
        answer = participant[i];
        System.out.println(answer);
    }
}
