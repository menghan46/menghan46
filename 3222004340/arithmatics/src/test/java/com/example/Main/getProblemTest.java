package com.example.Main;

import java.util.ArrayList;

import org.junit.Test;

import com.example.getProblem;

public class getProblemTest {
    @Test
    public void test1() { //生成算式与答案的文件
        getProblem a = new getProblem();
        a.createProblems(10,10);
    }

    @Test
    public void test2() {
        ArrayList List = new ArrayList<>();
        List.add("1+1=");
        getProblem a = new getProblem();
        a.createEXEFile(List);
    }

    @Test
    public void test3() {
        ArrayList List = new ArrayList<>();
        List.add("3");
        getProblem a = new getProblem();
        a.createAnsFile(List);
    }
}