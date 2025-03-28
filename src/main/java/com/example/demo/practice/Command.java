package com.example.demo.practice;

import com.example.demo.practice.hash.Clothes;
import com.example.demo.practice.hash.PhoneNumberList;

public class Command {
    public void command() {
//        CalNumberDenom  cmd = new CalNumberDenom();
//        BigNumber  cmd = new BigNumber();
//        Hindex cmd = new Hindex();
//        FindAthlete cmd = new FindAthlete();
//        PhoneNumberList cmd = new PhoneNumberList();
        Clothes cmd = new Clothes();
        cmd.doProcess();
    }
}