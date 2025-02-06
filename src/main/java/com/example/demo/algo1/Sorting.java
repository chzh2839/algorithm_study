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

    /** merge sort */
    // 참고 자료: https://st-lab.tistory.com/233
    // 참고 자료: https://velog.io/@hansung1459/%EC%A0%95%EB%A0%AC-Java-%EB%B3%91%ED%95%A9-%ED%95%A9%EB%B3%91-%EC%A0%95%EB%A0%AC-Merge-Sort
    int[] sorted;
    public void useMergeSort() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int arraySize = Integer.parseInt(br.readLine());
        int[] numArray = new int[arraySize];
        sorted = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            numArray[i] = Integer.parseInt(br.readLine());
        }
        System.out.println("배열 값 : " + Arrays.toString(numArray));

//        mergeSortTopDown(numArray, 0, numArray.length - 1);
        mergeSortBottomUp(numArray, 0, numArray.length - 1);
        System.out.println("----------- 정렬완료 - 배열 값 : " + Arrays.toString(numArray));
    }
    // Top-Down 형식 - 재귀 형태로 stackOverFlow가 발생할 수 있음
    private void mergeSortTopDown(int[] array, int left, int right) {
        if (left == right) return; // 부분집합이 1개일때
        int mid = (left + right) / 2; // 절반위치
        mergeSortTopDown(array, left, mid);		// 절반 중 왼쪽 부분리스트(left ~ mid)
        mergeSortTopDown(array, mid + 1, right);	// 절반 중 오른쪽 부분리스트(mid+1 ~ right)

        merge(array, left, mid, right);		// 병합작업
    }
    // Bottom-Up 형식
    private void mergeSortBottomUp(int[] array, int left, int right) {
        /*
         * 배열의 개수가 1, 2, 4, 8.. 이렇게 아래서부터 위로 증가한다고 보면 된다.
         * top down 방식은
         * 예로 배열 크기가 8일때, 8 -> 4, 4 -> 2,2 / 2,2 -> 1,1 / 1,1   // 1,1 / 1,1
         * 했던 것을 반대로 한다고 보면 된다.
         * size의 반복횟수가 길이와 같은 이유는,
         * 배열의 개수가 홀수일 경우 길이 -1만큼 넣기 때문에 수가 짝수이다.
         * 근데 만약, 길이가 <=가 아닌 <만 해버리면, 마지막 남은 한 수는
         * 확인하지 못하는 경우가 발생하기 때문에 반드시 <=를 해줘야 하는 것이다.
         */
        for (int size = 1; size <= array.length; size = size * 2) {
            for (int i = 0; i <= array.length - size; i += (size * 2)) {
                int leftStartIndex = i;
                /*
                 * 우리가 흔히 미드를 구할 떄 (0 + arr.length-1) / 2 로 구했던 것을 알 것이다.
                 * 이를, length가 곧 size가 되었다고 생각하면 된다.
                 * 그럼 /2를 안하는 이유는??..
                 * 필자 생각으로는, 이미 배열을 두 개로 나눈 상태이기 때문에 /2를 해주지 않는 것이라 이해했다.
                 */
                int mid = leftStartIndex + size - 1;
                /*
                 * r 역시 마찬가지다. 하지만, 여기서 l과 r은 비교 대상이 되는 배열로,
                 * 결국 2 * size가 곧 비교 대상 배열의 마지막 인덱스가 될 것이다.
                 * 근데 왜 right와 비교해서 최솟값을??
                 * size가 커지면, 또 l값이 커지면 right보다 커지는 순간이 반드시 오기 때문이다.
                 */
                int rightStartIndex = Math.min((leftStartIndex + (size * 2) - 1), right);

                // 분할 한 두 배열을 합치기
                merge(array, leftStartIndex, mid, rightStartIndex);
            }
        }
    }
    private void merge(int[] array, int left, int mid, int right) {
        int leftStartIndex = left; // 왼쪽 부분집합 시작
        int rightStartIndex = mid + 1; // 오른쪽 부분집합 시작
        int index = left; // 채워넣읗 sorted의 인덱스

        while (leftStartIndex <= mid && rightStartIndex <= right) {
            if (array[leftStartIndex] <= array[rightStartIndex]) {
                sorted[index] = array[leftStartIndex];
                index++;
                leftStartIndex++;
            } else {
                sorted[index] = array[rightStartIndex];
                index++;
                rightStartIndex++;
            }
        }

        // 왼쪽 부분집합이 먼저 모두 sorted에 채워졌고, 오른쪽 부분집합엔 원소가 남아있을 경우
        if (leftStartIndex > mid) {
            while (rightStartIndex <= right) {
                // 오른쪽 부분집합의 나머지 원소 sorted에 넣기
                // 각 부분집합의 원소들은 정렬이 된 상태이므로.
                sorted[index] = array[rightStartIndex];
                index++;
                rightStartIndex++;
            }
        } else { // 오른쪽 부분집합이 먼져 모두 sorted에 채워졌고, 왼쪽 부분집합엔 원소가 남아있을 경우
            while (leftStartIndex <= left) {
                sorted[index] = array[leftStartIndex];
                index++;
                leftStartIndex++;
            }
        }

        // 정렬된 새 배열을 기존의 배열에 복사
        for (int i = left; i <= right; i++) {
            System.out.print(sorted[i] + " ");
            array[i] = sorted[i];
        }
        System.out.println();
    }
}