package com.example.demo.practice.stackqueue;

import java.util.Stack;

/**
 * [ 올바른 괄호 ] LV2
 * 괄호가 바르게 짝지어졌다는 것은 '(' 문자로 열렸으면 반드시 짝지어서 ')' 문자로 닫혀야 한다는 뜻입니다.
 * "()()" 또는 "(())()" 는 올바른 괄호입니다.
 * ")()(" 또는 "(()(" 는 올바르지 않은 괄호입니다.
 *
 * ** 제한사항
 * 문자열 s의 길이 : 100,000 이하의 자연수
 * 문자열 s는 '(' 또는 ')' 로만 이루어져 있습니다.
 *
 * ex1) "()()"	    return true
 * ex2) "(())()"	return true
 * ex3) ")()("	    return false
 * ex4) "(()("	    return false
 * */
public class Parentheses {
    public void doProcess() {
        solution2("()()");
        solution2("(())()");
        solution2(")()(");
        solution2("(()(");
    }

    // 스택 미사용 풀이
    private void solution1(String s) {
        boolean answer = true;

        char[] array = s.toCharArray();
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if ('(' == array[i]){
                count++;
            }
            if (')' == array[i]) {
                if (count == 0) answer = false;
                else count--;
            }
        }
        if (count != 0) answer = false;
        System.out.println(answer);
    }

    // 스택 사용 풀이
    private void solution2(String s) {
        boolean answer = true;
        Stack<Character> stack = new Stack<>(); // 괄호를 저장하기 위한 문자열 스택 생성
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(c); // 스택에 문자 c 추가

            if (c == ')') {
                if (stack.isEmpty()) {
                    answer = false;
                } else {
                    stack.pop(); // 마지막 값 삭제
                }
            }
        }

        if (!stack.isEmpty()) { // 만약 스택이 비어있지 않은 경우
            answer = false;
        }

        System.out.println(answer);
    }
}
