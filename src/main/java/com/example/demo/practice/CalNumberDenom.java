package com.example.demo.practice;

import java.util.Arrays;

/**
 * 분수 계산하기
 * */
public class CalNumberDenom {
    public void doProcess() {
        solution2(1,2,3,4);
        solution2(9,2,1,3);
    }

    /** 분수의 덧셈
     * ex1) 1 / 2 + 3 / 4 = 5 / 4입니다. 따라서 [5, 4]를 return 합니다.
     * ex2) 9 / 2 + 1 / 3 = 29 / 6입니다. 따라서 [29, 6]을 return 합니다. */
    int rs = 0;
    private void solution1(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = new int[2];
        int numer = numer1*denom2 + numer2*denom1;
        int denom = denom1 * denom2;
        gcd1(numer, denom);
        if (rs != 0) {
            answer[0] = numer/rs;
            answer[1] = denom/rs;
        } else {
            answer[0] = numer;
            answer[1] = denom;
        }

        System.out.println(Arrays.toString(answer));
    }
    private void gcd1(int n, int m){
        int bigNum = n;
        int smallNum = m;
        if (n < m) {
            bigNum = m;
            smallNum = n;
        }
        int mod = bigNum % smallNum;
        if (mod == 0) rs = smallNum;
        else gcd1(smallNum, mod);
    }

    /** 다른 풀이식 */
    private void solution2(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = new int[2];
        int numer = numer1*denom2 + numer2*denom1;
        int denom = denom1 * denom2;
        int rs2 = gcd2(numer, denom);
        if (rs2 != 0) {
            answer[0] = numer/rs2;
            answer[1] = denom/rs2;
        } else {
            answer[0] = numer;
            answer[1] = denom;
        }

        System.out.println(Arrays.toString(answer));
    }
    private int gcd2(int n, int m){
        if (n < m) {
            int temp = n;
            n = m;
            m = temp;
        }
        int mod = n % m;
        if (mod == 0) return m;
        else return gcd2(m, mod);
    }
}
