package com.example.demo.practice.hash;

import java.util.*;

/**
 * [ HashMap ] LV3
 * 베스트 앨범 - 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시
 * 1. 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
 * 2. 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
 * 3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다
 *
 * genres[i]는 고유번호가 i인 노래의 장르입니다.
 * plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
 * genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
 * 장르 종류는 100개 미만입니다.
 * 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
 * 모든 장르는 재생된 횟수가 다릅니다.
 *
 * ex1)
 * genres : ["classic", "pop", "classic", "classic", "pop"]
 * plays : [500, 600, 150, 800, 2500]
 * return [4, 1, 3, 0]
 * => 따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록
 * */
public class BestAlbum {
    public void doProcess() {
        solution1(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500});
    }

    private void solution1(String[] genres, int[] plays) {
        int[] answer = {};

        HashMap<String, HashMap<Integer, Integer>> genresMap = new HashMap<>(); // 각 장르에 속하는 각 노래의 고유 번호와 재생 횟수를 저장할 해시맵의 해시맵
        HashMap<String, Integer> playMap = new HashMap<>(); // 각 장르별로 노래의 총 재생 횟수를 저장할 해시맵
        ArrayList<Integer> resultList = new ArrayList<>();

        for (int i = 0; i < genres.length; i++){
            HashMap<Integer, Integer> infoMap = new HashMap<>();
            if (genresMap.containsKey(genres[i])) {
                infoMap = genresMap.get(genres[i]);
            }
            infoMap.put(i, plays[i]);
            genresMap.put(genres[i], infoMap);

            // 재생수
            playMap.put(genres[i], playMap.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<String> keyset = new ArrayList(playMap.keySet()); // 장르명을 리스트로 변환 (정렬하기 위해)
        Collections.sort(keyset, (s1, s2) -> playMap.get(s2) -  playMap.get(s1)); // 리스트를 재생 횟수에 따라 내림차순으로 정렬

        for (String key: keyset) {
            // 각 장르별로 노래를 정렬

            HashMap<Integer, Integer> map = genresMap.get(key);
            List<Integer> genreKey = new ArrayList<>(map.keySet());

            // 각 장르의 HashMap에서 키셋(노래 고유 번호)을 가져와 재생 횟수에 따라 내림차순으로 정렬
            Collections.sort(genreKey, (s1, s2) -> map.get(s2) - map.get(s1));

            // 각 장르에서 재생 횟수가 가장 높은 노래 두 개를 answer 리스트에 추가
            // 한 장르에 속한 노래가 1개면 1개만 추가
            resultList.add(genreKey.get(0));
            if (genreKey.size() > 1) {
                resultList.add(genreKey.get(1));
            }
        }

        answer = resultList.stream().mapToInt(i -> i).toArray();
        System.out.println(Arrays.toString(answer));
    }
}
