package com.example.demo.practice;

import com.example.demo.practice.hash.BestAlbum;
import com.example.demo.practice.hash.Clothes;
import com.example.demo.practice.hash.PhoneNumberList;
import com.example.demo.practice.hash.RandomNumber;
import com.example.demo.practice.search.FindPrimeNumber;
import com.example.demo.practice.search.MinQuadrangle;

public class Command {
    public void command() {
//        CalNumberDenom  cmd = new CalNumberDenom();
//        BigNumber  cmd = new BigNumber();
//        Hindex cmd = new Hindex();
//        FindAthlete cmd = new FindAthlete();
//        PhoneNumberList cmd = new PhoneNumberList();
//        Clothes cmd = new Clothes();
//        BestAlbum cmd = new BestAlbum();
//        MinQuadrangle cmd = new MinQuadrangle();
//        RandomNumber cmd = new RandomNumber();
        FindPrimeNumber cmd = new FindPrimeNumber();
        cmd.doProcess();
    }
}