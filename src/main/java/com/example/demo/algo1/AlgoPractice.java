package com.example.demo.algo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Do it! 알고리즘 코딩테스트 with JAVA
 * */
public class AlgoPractice {
    /** 배열 평균값 */
    public void makeScoreArg() {
        Scanner sc = new Scanner(System.in);
        int subjectCnt = sc.nextInt();
        int[] socres = new int[subjectCnt];
        double[] socres2 = new double[subjectCnt];
        for (int i = 0; i < subjectCnt; i++) {
            socres[i] = sc.nextInt();
        }
        int max = 0;
        for (int i = 0; i < subjectCnt; i++) {
            if (socres[i] > max) { max = socres[i]; }
        }
        System.out.println("최대값 : " + max);
        for (int i = 0; i < subjectCnt; i++) {
            socres2[i] = (double)socres[i]/max*100;
            System.out.println(socres2[i]);
        }

        double sum = 0.0;
        for (int i = 0; i < subjectCnt; i++) {
            sum += socres2[i];
        }
        System.out.println("평균 값 : "+ (sum/subjectCnt));
    }

    /** 구간 합 */
    public void makeBetweenSum() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numberCount = Integer.parseInt(st.nextToken());
        int questionCount = Integer.parseInt(st.nextToken());
        System.out.println("numberCount : " + numberCount + ", questionCount : " + questionCount);

        st = new StringTokenizer(br.readLine());
        long[] sumArray = new long[numberCount+1];
        for (int i = 1; i <= numberCount; i++) {
            sumArray[i] = sumArray[i-1] + Long.parseLong(st.nextToken());
        }
        System.out.println("sumArray : " + Arrays.toString(sumArray));

        for (int i = 0; i < questionCount; i++) {
            st = new StringTokenizer(br.readLine());
            int startIndex = Integer.parseInt(st.nextToken());
            int endIndex = Integer.parseInt(st.nextToken());
            System.out.println(sumArray[endIndex] - sumArray[startIndex - 1]);
        }
    }

    /** 투 포인터 - 연속된 자연수의 합으로 나타내는 가짓수 */
    public void getCaseCount() {
        Scanner sc = new Scanner(System.in);
        int inputNumber = sc.nextInt();
        int sum = 1;
        int start = 1;
        int end = 1;
        int count = 1; // 자기자신 하나로 이루어진 경우의수
        while(end < inputNumber) {
            if (sum == inputNumber) {
                count++;
                end++;
                sum = sum + end; // *** end++ 후, 이거 해야 함
            } else if (sum < inputNumber) {
                end++;
                sum = sum + end;
            } else {
                sum = sum - start; // 이거 선행 후, start++ 해야 함
                start++;
            }
        }

        System.out.println(count);
    }

    public void getCaseCount2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int arrayCount = Integer.parseInt(br.readLine());
        int sum = Integer.parseInt(br.readLine());
        Integer[] array = new Integer[arrayCount];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arrayCount; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array); // Primitive Type 원시타입 오른차순만 가능
//        Arrays.sort(array, Collections.reverseOrder()); // 내림차순 정렬 (Integer여야 가능)
        System.out.println("array : " + Arrays.toString(array));

        int startIndex = 0;
        int endIndex = arrayCount - 1;
        int count = 0;
        while(startIndex < endIndex) {
            int tempSum = array[startIndex] + array[endIndex];
            if (sum == tempSum) {
                count++;
                endIndex--; startIndex++; // 한번씩만 재료사용 가능
            } else if (sum < tempSum) {
                endIndex--;
            } else {
                startIndex++;
            }
        }

        System.out.println(count);
    }

    /** DNA 비밀번호 A C G T */
    int[] conditionCnts; // A C G T 각각 몇개여야 하는지
    int[] checkConditionCnts; // A C G T 각각 실제로 몇개인지 체크한 값
    int passConditionCnt; // 각 문자가 조건에 맞았는지 확인. 4가 되면 최종 조건 일치! (result++)
    public void slidingWindow() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int wholeTextCnt = Integer.parseInt(st.nextToken());
        int checkTextCnt = Integer.parseInt(st.nextToken());

        char[] textArray = new char[wholeTextCnt];
        textArray = br.readLine().toCharArray();
        System.out.println("입력받은 문자열: " + Arrays.toString(textArray));

        st = new StringTokenizer(br.readLine());
        conditionCnts = new int[4]; // A C G T 각각 몇개여야 하는지
        checkConditionCnts = new int[4]; // A C G T 각각 실제로 몇개인지 체크한 값
        passConditionCnt = 0; // 각 문자가 조건에 맞았는지 확인. 4가 되면 최종 조건 일치! (result++)
        for (int i = 0; i < 4; i++) {
            conditionCnts[i] = Integer.parseInt(st.nextToken());
            // 0회 이상이면 볼 필요없음
            if (conditionCnts[i] == 0) {
                passConditionCnt++;
            }
        }

        int result = 0; // 조건에 일치하는 문자열 경우의 수

        // 최초 1번 바로 넣기
        for (int i = 0; i < checkTextCnt; i++) {
            add(textArray[i]);
        }
        if (passConditionCnt == 4) result++;

        // 슬라이딩 윈도우
        for (int endIndex = checkTextCnt; endIndex < wholeTextCnt; endIndex++) {
            int startIndex = endIndex - checkTextCnt;
            add(textArray[endIndex]);
            remove(textArray[startIndex]);
            if (passConditionCnt == 4) result++;
        }
        System.out.println("최종 경우의 수는 " + result);
        br.close();
    }

    private void add(char c) {
        switch (c) {
            case 'A':
                checkConditionCnts[0]++;
                if (checkConditionCnts[0] == conditionCnts[0]) passConditionCnt++;
                break;
            case 'C':
                checkConditionCnts[1]++;
                if (checkConditionCnts[1] == conditionCnts[1]) passConditionCnt++;
                break;
            case 'G':
                checkConditionCnts[2]++;
                if (checkConditionCnts[2] == conditionCnts[2]) passConditionCnt++;
                break;
            case 'T':
                checkConditionCnts[3]++;
                if (checkConditionCnts[3] == conditionCnts[3]) passConditionCnt++;
                break;
        }
    }
    private void remove(char c) {
        switch (c) {
            case 'A':
                if (checkConditionCnts[0] == conditionCnts[0]) passConditionCnt--;
                checkConditionCnts[0]--;
                break;
            case 'C':
                if (checkConditionCnts[1] == conditionCnts[1]) passConditionCnt--;
                checkConditionCnts[1]--;
                break;
            case 'G':
                if (checkConditionCnts[2] == conditionCnts[2]) passConditionCnt--;
                checkConditionCnts[2]--;
                break;
            case 'T':
                if (checkConditionCnts[3] == conditionCnts[3]) passConditionCnt--;
                checkConditionCnts[3]--;
                break;
        }
    }
}
