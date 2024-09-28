package com.example.Main;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.example.checkRepeat;

public class checkRepeatTest {
    @Test
    public void test1() { //测试生成暂存题集、答案集
        checkRepeat c = new checkRepeat();
        ArrayList List = new ArrayList<>();
        List = c.generate(10, 10);
    }
}
