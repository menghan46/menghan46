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
            String filepath1="";
            String filepath2="";
            String filepath3="";
            if(args.length>=3){
                filepath1=args[0];
                filepath2=args[1];
                filepath3=args[2];
            }
            else{
                    System.out.println("请指定文件路径");
            }
            InputStreamReader reader1=new InputStreamReader(new FileInputStream(filepath1),"UTF-8");
            BufferedReader br1=new BufferedReader(reader1);
            InputStreamReader reader2=new InputStreamReader(new FileInputStream(filepath2),"UTF-8");
            BufferedReader br2=new BufferedReader(reader2);
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
            PlagiarismChecking check=new PlagiarismChecking();
            check.GetCheckingResult(str1,str2);
            FileWriter writer=new FileWriter(filepath3,true);
            String strfw="重复率："+Double.toString(check.repetition);
            writer.write(strfw+"\r\n");
            writer.close();  
        }catch(IOException e){   
            e.printStackTrace();   
        }  
        
       
    }
}

