package com.example;

import java.util.Random;

public class Create {
     /**
     * 式子生成器
     * totalOperator 为 当前式子 的 运算符 数组
     * formula 为 当前式子 的 字符串形式
     * totalFraction 为 当前式子 的 操作数 数组
     * @param r 为 操作数 的 范围
     * @return ansFormula 为 当前式子 的 逆波兰表达式 && 结果 && 字符串形式 的 数组
     */
    public String[] createFormula(int r){
        Random random = new Random();
        String[] operator = {"＋","－","×","÷","＝"};
        //totalOperator为一个算式中的所有符号
        String[] totalOperator = new String[1 + random.nextInt(3)];
        //totalFraction为一个算式中的所有操作数
        String[] totalFraction = new String[totalOperator.length+1];
        //式子的字符串形式
        String formula = new String();
        //算式是否存在分数
        Boolean hasFraction = false;

        //操作数
        for (int i=0;i<totalFraction.length;i++) {
            //随机生成的操作数为整数还是分数
            int fractionOrNot = random.nextInt(2);
            //若fractionOrNot为0，则随机生成整数
            if (fractionOrNot == 0) {
                int integralPart = random.nextInt(r+1);
                totalFraction[i] = String.valueOf(integralPart);
            } 
            //若fractionOrNot为0，则随机生成分数
            else {
                //denominator为分母
                int denominator = 1+random.nextInt(r);
                //molecule为分子
                int molecule = random.nextInt(denominator);
                //带分数的整数部分
                int integralPart = random.nextInt(r+1);
                //若分子不为0，则对真分数部分进行化简
                if (molecule!=0) {
                    //求出分子分母最大公因数
                    int commonFactor = commonFactor(denominator, molecule);
                    denominator /= commonFactor;
                    molecule /= commonFactor;
                }
                //整数部分为0且分子不为0，则存入totalFraction中
                if (integralPart == 0 && molecule > 0) {
                    totalFraction[i] = molecule + "/" + denominator;
                    hasFraction = true;
                }
                //若分子为0，则将整数部分存入totalFraction中
                else if (molecule == 0)
                    totalFraction[i] = String.valueOf(integralPart);
                else {
                    totalFraction[i] = integralPart + "'" + molecule + "/" + denominator;
                    hasFraction = true;
                }
            }
        }

        //运算符
        for (int i=0;i < totalOperator.length;i++) {
            //若存在分数，则运算符只取加减
            if (hasFraction)
                totalOperator[i] = operator[random.nextInt(2)];
            else
                totalOperator[i] = operator[random.nextInt(4)];
        }

        int choose = totalFraction.length;
        if (totalFraction.length != 2 )
            choose = random.nextInt(totalFraction.length);

        //将操作数和运算符进行拼接得到算式的字符串形式
        for (int i=0;i<totalFraction.length;i++) {
            if (i == choose && choose<totalOperator.length) {
                formula = formula + "(" + totalFraction[i] + totalOperator[i] ;
            } else if (i == totalFraction.length - 1 && i == choose+1 && choose<totalOperator.length) {
                formula = formula + totalFraction[i] + ")" + "=";
            } else if (i == choose+1 && choose<totalOperator.length) {
                formula = formula + totalFraction[i] + ")" + totalOperator[i];
            } else if (i == totalFraction.length - 1) {
                formula = formula + totalFraction[i] + "=";
            } else {
                formula = formula + totalFraction[i] + totalOperator[i];
            }
        }

        //检查运算结果
        checkAns checkAns = new checkAns();
        //ansFormula为由原算式得到的逆波兰表达式
        String[] ansFormula = checkAns.checkout(formula,3*totalOperator.length+3);

        if (ansFormula!=null)
            return ansFormula;
        return null;
    }

    /**
     * 求最大公因数，以化简分数
     * @param x 为 操作数 的 分母
     * @param y 为 操作数 的 分子
     * @return y 为 最大公因数
     */
    public int commonFactor(int x,int y) {
        while(true)
        {
            if(x%y==0)return y;
            int temp=y;
            y=x%y;
            x=temp;
        }
    }

    
}
