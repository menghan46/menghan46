package com.example;

import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.json.*;
import java.util.*;
import com.alibaba.fastjson.*;


public class PlagiarismChecking {
    //记录重复率
    static double repetition=0;
    public static void GetCheckingResult(String str1,String str2){
        ApartWold(str1, str2);
    }
    //对传入的字符串进行分析并统计词频
    public static void ApartWold(String str1,String str2){
        Map<String, Integer> map1 = new HashMap<>();
        ToAnalysis.parse(str1).forEach(item -> {
            //没有则赋初始值，有则+1
            if (map1.get(item.getName()) == null){
                map1.put(item.getName(),1);
            }else {
                map1.put(item.getName(),map1.get(item.getName())+1);
            }
        });
        Map<String,Integer> map2 = new HashMap<>();
        ToAnalysis.parse(str2).forEach(item -> {
            //没有则赋初始值，有则+1
            if (map2.get(item.getName()) == null){
                map2.put(item.getName(),1);
            }else {
                map2.put(item.getName(),map2.get(item.getName())+1);
            }
        });
        CountWord(map1, map2);
    }
    //合并两个进行词频统计后的哈希表，并再次统计两个版本文件的词频
    public static void CountWord(Map<String,Integer> map1,Map<String,Integer> map2){
        Set<String> set1 = map1.keySet();
        Set<String> set2 = map2.keySet();
        Set<String> setAll = new HashSet<>();
        setAll.addAll(set1);
        setAll.addAll(set2);
        List<Integer> list1 = new ArrayList<>(setAll.size());
        List<Integer> list2 = new ArrayList<>(setAll.size());
        //若存在该词，则记录其词频，否则将该词词频记为0
        setAll.forEach(item ->{
            if (set1.contains(item)){
                list1.add(map1.get(item));
            }else {
                list1.add(0);
            }

            if (set2.contains(item)){
                list2.add(map2.get(item));
            }else {
                list2.add(0);
            }
        });
        Computesimilarity(setAll,list1,list2);
    }
    //运用余弦相似法进行重复率计算，并将最终结果输出到repetition变量中
    public static void Computesimilarity(Set<String> setAll,List<Integer> list1,List<Integer> list2){
        int sum =0;
        long sq1 = 0;
        long sq2 = 0;
        double result = 0;
        for (int i =0;i<setAll.size();i++){
            sum +=list1.get(i)*list2.get(i);
            sq1 += list1.get(i)*list1.get(i);
            sq2 += list2.get(i)*list2.get(i);
        }
        result = sum/(Math.sqrt(sq1)*Math.sqrt(sq2));
        System.out.println("重复率="+result);
        repetition=result;
        
    }
}
