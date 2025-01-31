package com.example.demo.algo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    /** quick sorting */
    public void useQuickSorting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int arraySize = Integer.parseInt(br.readLine());
        int[] numArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            numArray[i] = Integer.parseInt(br.readLine());
        }
        System.out.println("배열 값 : " + Arrays.toString(numArray));

        quickSort(numArray, 0, numArray.length - 1);
        System.out.println("----------- 정렬완료 - 배열 값 : " + Arrays.toString(numArray));
    }

    private void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            // 정렬할 원소가 1개 이하라는 의미니까, 바로 return
             return;
        }
//        int pivotPoint = partition(array, start, end);
//        quickSort(array, start, pivotPoint - 1); // pivot보다 작은 값으로 이루어진 부분집합 다시 반복
//        quickSort(array, pivotPoint, end); // pivot보다 큰 값으로 이루어진 부분집합 다시 반복

        int pivotPoint = partitionByMiddlePivot(array, start, end);
        quickSort(array, start, pivotPoint);
        quickSort(array, pivotPoint+1, end);
    }
    private int partition(int[] array, int start, int end) {
        System.out.println("start:: " + start + ", end:: " + end);
        int lowPoint = start;
        int highPoint = end;
//        int pivot = array[end]; // 부분집합/배열의 제일 오른쪽값을 피벗으로 선정
        int pivot = array[start]; // 부분집합/배열의 제일 왼쪽값을 피벗으로 선정

        // lowPoint == highPoint 질 때까지 반복
        while (lowPoint < highPoint) {
//            while (array[lowPoint] < pivot && lowPoint < highPoint) {
//                lowPoint++; // lowPoint의 값이 pivot보다 작으면 point 오른쪽으로 한 칸씩 이동
//            }
//            while (array[highPoint] >= pivot && lowPoint < highPoint) {
//                highPoint--; // highPoint의 값이 pivot보다 크면 point 왼쪽으로 한 칸씩 이동
//            }

            while (array[lowPoint] <= pivot && lowPoint < highPoint) {
                lowPoint++; // lowPoint의 값이 pivot보다 작으면 point 오른쪽으로 한 칸씩 이동
            }
            while (array[highPoint] > pivot && lowPoint < highPoint) {
                highPoint--; // highPoint의 값이 pivot보다 크면 point 왼쪽으로 한 칸씩 이동
            }

            if (lowPoint != highPoint) { // 값으면 swap할 필요없음
                swap(array, lowPoint, highPoint); // lowPoint의 값과 highPoint값의 이동이 멈추면 swap하기
            }
        }

        // lowPoint == highPoint 지점의 값보다 pivot(start/end가 가리키는 값)이 작으면 왼쪽으로, 크면 오른쪽으로 이동
        // && 피벗이 이동할 지점 기준으로, 피벗이 있던 위치까지의 데이터 일괄 이동
        System.out.println("high:: " + highPoint + ", low:: " + lowPoint);
        System.out.println("만난 지점의 값:: " + array[highPoint] + ", pivot:: " + pivot);
        if (array[highPoint] > pivot) {
            /** 피벗을 부분집합의 오른쪽 값으로 지정하는 경우 */
//            if (highPoint - 1 >= 0) {
//                for (int i = end; i > highPoint; i--) {
//                    array[i] = array[i - 1];
//                }
//                // 피벗을 만난 지점의 왼쪽으로 이동
//                array[highPoint] = pivot;
//            }

            /** 피벗을 부분집합의 왼쪽 값으로 지정하는 경우 */
            for (int i = start; i < highPoint; i++) {
                array[i] = array[i + 1];
            }
            // 피벗을 만난 지점의 왼쪽으로 이동
            array[highPoint - 1] = pivot;

        } else if (array[highPoint] < pivot) {
            /** 피벗을 부분집합의 오른쪽 값으로 지정하는 경우 */
//            if (highPoint + 1 < array.length) {
//                for (int i = end; i > highPoint; i--) {
//                    array[i] = array[i - 1];
//                }
//                // 피벗을 만난 지점의 오른쪽으로 이동
//                array[highPoint + 1] = pivot;
//            }

            /** 피벗을 부분집합의 왼쪽 값으로 지정하는 경우 */
            for (int i = start; i < highPoint; i++) {
                array[i] = array[i + 1];
            }
            // 피벗을 만난 지점의 오른쪽으로 이동
            array[highPoint] = pivot;
        }

        System.out.println("중간 체크 array:: " + Arrays.toString(array));
//        return highPoint; // 두 요소가 swap되면, 피벗이었던 요소는 highPoint에 위치하므로 highPoint를 반환한다
        return lowPoint; // 왼쪽 값으로 피벗 선정했다면 반대로 lowPoint를 반환
    }
    private int partitionByMiddlePivot(int[] array, int start, int end) {
        System.out.println("start:: " + start + ", end:: " + end);
        int lowPoint = start-1;
        int highPoint = end+1;
        int pivotIndex = (start + end)/2;
        int pivot = array[pivotIndex]; // 부분집합/배열의 가운데 값을 피벗으로 선정

        while(true) {
            do {
                lowPoint++;
            } while(array[lowPoint] < pivot);
            do {
                highPoint--;
            } while(array[highPoint] > pivot && lowPoint <= highPoint);
            if (lowPoint >= highPoint) {
                return highPoint;
            }
            swap(array, lowPoint, highPoint);
        }
    }
    private void swap(int[] array, int meetPoint, int pivotPoint) {
        int temp = array[meetPoint];
        array[meetPoint] = array[pivotPoint];
        array[pivotPoint] = temp;
    }
}