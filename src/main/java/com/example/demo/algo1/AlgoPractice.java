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
}
