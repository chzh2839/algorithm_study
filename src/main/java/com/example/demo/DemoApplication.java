package com.example.demo;

import com.example.demo.algo1.DataStructure;
import com.example.demo.algo1.Sorting;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@SpringBootApplication
public class DemoApplication {

	public static void maniddo() {
		String[] array = {"퍼플", "엑셀", "시오", "항정", "마빡", "벙글", "르네", "요비", "코카", "애쉬", "라준", "제제", "호피"};

		List<Integer> selectedNumbers = new ArrayList<>();

		for (int index = 0; index < array.length; index++) {
			int randomIndex = new Random().nextInt(array.length);

			while (selectedNumbers.contains(randomIndex) || randomIndex == index) {
				randomIndex = new Random().nextInt(array.length);
			}

			selectedNumbers.add(randomIndex);
			String target = array[randomIndex];

			System.out.println(index + ". \'" + array[index] + "\'의 마니또는 \'" + target + "\' 입니다.");
		}
	}

	public static void main(String[] args) throws IOException {
		Instant start = Instant.now();  // <= 시작점을 지정해준다
//		maniddo();
		DataStructure dataStructure = new DataStructure();
//		dataStructure.makeScoreArg();
//		dataStructure.makeBetweenSum();
//		dataStructure.getCaseCount2();
//		dataStructure.slidingWindow();
//		dataStructure.makeStack();
//		dataStructure.makeQueue();
//		dataStructure.makePriorityQueue();
		Sorting sorting = new Sorting();
//		sorting.useBubbleSorting();
//		sorting.useSelectionSorting();
//		sorting.useQuickSorting();
		sorting.useMergeSort();
		Instant finish = Instant.now(); // <= 끝나는 지점을 지정해준다
		long elapsedTime = Duration.between(start, finish).toMillis(); // <=시작와 끝 사이의 경과시간을 계산해준다. ms단위로 끊었다.
		System.out.println("elapsedTime(ms) : " + elapsedTime); // <= 값을 실행창에 출력
	}
}
