package com.example;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileWriter;



public class Main {
    public static void main(String[] args) {
        try{  
            //分别记录原版文件路劲、抄袭版文件路径、以及文件答案输出路径
            String filepath1="";
            String filepath2="";
            String filepath3="";
            //判断命令行是否已输入三个路径
            if(args.length>=3){
                filepath1=args[0];
                filepath2=args[1];
                filepath3=args[2];
            }
            else{
                    System.out.println("请指定文件路径");
            }
            //读取原版文件以及抄袭版文件信息
            InputStreamReader reader1=new InputStreamReader(new FileInputStream(filepath1),"UTF-8");
            BufferedReader br1=new BufferedReader(reader1);
            InputStreamReader reader2=new InputStreamReader(new FileInputStream(filepath2),"UTF-8");
            BufferedReader br2=new BufferedReader(reader2);
            //将文件内的文字进行拼接并去除标点符号
            String line;
            String str1="";
            String str2="";
            while((line=br1.readLine())!=null) {
                str1+=line;
            }
            while((line=br2.readLine())!=null) {
                str2+=line;
            }
            str1 = str1.replaceAll("[\\pP‘’“”]", ""); 
            str2 = str2.replaceAll("[\\pP‘’“”]", ""); 
            //创建一个论文查重类，并进行重复率计算
            PlagiarismChecking check=new PlagiarismChecking();
            check.GetCheckingResult(str1,str2);
            FileWriter writer=new FileWriter(filepath3,true);
            //将重复率输出到答案输出文件中
            String strfw="重复率："+String.format("%.2f",check.repetition);
            writer.write(strfw+"\r\n");
            writer.close();  
        }catch(IOException e){   
            e.printStackTrace();   
        }  
        
       
    }
}

