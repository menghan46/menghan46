package com.example.Main;

import org.junit.Test;

import com.example.Create;

public class CreateTest {
    @Test
    public void test1() { //测试式子生成
        Create c = new Create();
        c.createFormula(10);
    }
    @Test
    public void test2() { //测试化简分数
        Create c = new Create();
        c.commonFactor(9, 3);
    }
}
