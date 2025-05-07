package com.example.demo;

import com.example.demo.algo1.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import com.example.demo.practice.Command;
//import com.example.demo.algo1.Command;

@SpringBootApplication
public class DemoApplication {
	public static void maniddo() {
		String[] array = new String[10];
		for (int i = 0; i < 10; i++) {
			array[i] = "nickname" + (i+1);
		}

		List<Integer> selectedNumbers = new ArrayList<>();

		for (int index = 0; index < array.length; index++) {
			int randomIndex = new Random().nextInt(array.length);

			while (selectedNumbers.contains(randomIndex) || randomIndex == index) {
				randomIndex = new Random().nextInt(array.length);
			}

			selectedNumbers.add(randomIndex);
			String target = array[randomIndex];

			System.out.println((index+1) + ". \'" + array[index] + "\'의 마니또는 \'" + target + "\' 입니다.");
		}
	}

	public static void main(String[] args) throws IOException {
		Instant start = Instant.now();  // <= 시작점을 지정해준다
		maniddo();

		Command cmd = new Command();
//		cmd.command();

		Instant finish = Instant.now(); // <= 끝나는 지점을 지정해준다
		long elapsedTime = Duration.between(start, finish).toMillis(); // <=시작와 끝 사이의 경과시간을 계산해준다. ms단위로 끊었다.
		System.out.println("elapsedTime(ms) : " + elapsedTime); // <= 값을 실행창에 출력
	}
}
