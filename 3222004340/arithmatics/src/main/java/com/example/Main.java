package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while(true) {
            int n = 10;
            int r = 10;
            //题目解答文件路径
            String submitPath = null;
            //正确答案文件路径
            String answersPath = null;
            //命令行输入
            try {
                System.out.println("请输入参数：");
                Scanner command = new Scanner(System.in);
                String arr[] = command.nextLine().split("\\s");
                

                //获取命令行的相应参数
                if (arr.length > 1) {
                    for (int i = 0; i < arr.length; i = i + 2) {
                        switch (arr[i]) {
                            //算式个数
                            case "-n":
                                n = Integer.parseInt(arr[i + 1]);
                                if (n > 10000 || n < 1) {
                                    System.out.println("请输入1-10000的数字！");
                                    return;
                                }
                                break;
                            //算式的数字范围
                            case "-r":
                                r = Integer.parseInt(arr[i + 1]);
                                if (r < 1) {
                                    System.out.println("请输入大于等于1的自然数！");
                                    return;
                                }
                                break;
                            case "-e":
                                submitPath = arr[i + 1];
                                if (submitPath == null) {
                                    System.out.println("请输入正确的文件路径");
                                    return;
                                }
                                break;
                            case "-a":
                                answersPath = arr[i + 1];
                                if (answersPath == null) {
                                    System.out.println("请输入正确的文件路径");
                                    return;
                                }
                                break;
                            default:
                                System.out.println("命令输入错误!");
                                break;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("您输入的命令有误，请重新输入");
            }

            /* **** 执行函数 **** */
            System.out.println("题目个数： " + n + ", 数值范围： " + r);
            getProblem problems = new getProblem();
            if (submitPath != null && answersPath != null)
                //获取解题文件以及答案文件
                problems.createGradeFile(submitPath,answersPath);
            else
                problems.createProblems(n,r);
        }
    }

}
