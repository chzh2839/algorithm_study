package com.example.demo.algo1;

import java.util.*;

/**
 * Do it! 알고리즘 코딩테스트 with JAVA
 * (Sorting)
 * */
public class Sorting {
    /** bubble sorting */
    public void useBubbleSorting() {
        Scanner sc = new Scanner(System.in);
        int arraySize = sc.nextInt();
        int[] numbers = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            numbers[i] = sc.nextInt();
        }
        System.out.println("배열 값 : " + Arrays.toString(numbers));
        // start Bubble sorting
        for (int i = 0; i < arraySize - 1; i++) {
            for (int j = 0; j < arraySize -1 -i; j++) {
                if (numbers[j] > numbers[j+1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
        }
        System.out.println("버븥 정렬한 배열 값 : " + Arrays.toString(numbers));
    }

    /** selection sorting */
    public void useSelectionSorting() {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int[] numbers = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            numbers[i] = Integer.parseInt(str.substring(i, i+1));
        }
        System.out.println("배열 값 : " + Arrays.toString(numbers));
        // start Selection sorting
        for (int i = 0; i < str.length(); i++) {
            int maxIndex = i;
            for (int j = i+1; j < str.length(); j++) {
                if (numbers[j] > numbers[maxIndex]) {
                    maxIndex = j;
                }
            }
            if (numbers[maxIndex] > numbers[i]) {
                int temp = numbers[i];
                numbers[i] = numbers[maxIndex];
                numbers[maxIndex] = temp;
            }
        }
        System.out.print("선택 정렬한 배열 값 : ");
        for (int i = 0; i < str.length(); i++) {
            System.out.print(numbers[i]);
        }
        System.out.println();
    }
}