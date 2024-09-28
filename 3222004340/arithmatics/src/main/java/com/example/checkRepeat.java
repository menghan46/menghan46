package com.example;

import java.util.ArrayList;
import java.util.Arrays;

public class checkRepeat {
    /**
     * 生成暂存题集、答案集
     * @param n 为 需要的式子总数
     * @param r 为 式子中操作数的范围
     * @return returnList 为 题集&答案集
     */
    //
    ArrayList returnList = new ArrayList<>();
    ArrayList<String> txtList = new ArrayList<>();
    ArrayList<String> ansList = new ArrayList<>();
    ArrayList<String[]> ansFoList = new ArrayList<>();
    public ArrayList generate(int n,int r) {
        Create create = new Create();
        //生成n条不重复的式子
        for(int i=0;i<n;){
            String[] ansFormula = create.createFormula(r);
            if (ansFormula!=null)
                if (!ifRepeat(ansFormula)) i++;
        }

        //把式子及运算结果添加到returnList
        for (int i =0; i<2*n;i++) {
            if(i<n) {
                returnList.add(txtList.get(i));
            } else {
                returnList.add(ansList.get(i - n));
            }
        }
        return returnList;
    }
     /**
     * 判断式子是否重复
     * @param ansFormula 为 后缀表达式、运算结果、式子 的 数组
     * @return ifRepeat 表示当前式子是否重复
     */
    public boolean ifRepeat(String[] ansFormula) {
        String formula = ansFormula[ansFormula.length-1];
        String[] rPNotation = new String[ansFormula.length-1];
        //将ansFormula从ansFormula[0]开始，拷贝到rPNotation[0]位置开始，拷贝ansFormula.length-1个数，
        System.arraycopy(ansFormula, 0, rPNotation, 0, ansFormula.length-1);
        boolean ifRepeat = false;

        for (String[] ansFo: ansFoList) {
            if (Arrays.equals(ansFo,rPNotation)) { //直接一一对应比较
                ifRepeat = true;
            } else if (ansFo.length == rPNotation.length && ansFo[ansFo.length-1].equals(rPNotation[rPNotation.length-1])){//若运算结果及长度一致，则式子可能重复，进一步比较
                int j=0;
                for (j=0;j<rPNotation.length-2;) {
                    boolean opRight = ansFo[j+2].equals("＋")||ansFo[j+2].equals("×");
                    boolean exRight = ansFo[j].equals(rPNotation[j + 1]) && ansFo[j + 1].equals(rPNotation[j]) && ansFo[j + 2].equals(rPNotation[j + 2]);
                    boolean copRight = ansFo[j].equals(rPNotation[j]) && ansFo[j + 1].equals(rPNotation[j + 1]) && ansFo[j + 2].equals(rPNotation[j + 2]);
                    //运算符前后两个操作数交换比较
                    if (exRight&&opRight) {
                        j = j + 3;
                    } else if (copRight) {
                        j = j + 3;
                    } else {
                        break;
                    }
                }
                if (j == rPNotation.length-2) {
                    ifRepeat = true;
                    break;
                }
            }
        }

        if (!ifRepeat) {
            this.txtList.add(formula);
            this.ansList.add(rPNotation[rPNotation.length-1]);
            this.ansFoList.add(rPNotation);
        }
        return ifRepeat;
    }
}
