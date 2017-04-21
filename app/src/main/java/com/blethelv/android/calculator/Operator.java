package com.blethelv.android.calculator;

import java.math.BigDecimal;

public class Operator implements MathSymbol {
    private String mSymbol;//运算符
    private int mVHL;//优先级
    private int mMany;
    private Boolean mIsOperator=true;

    public Operator(String Symbol, int VHL,int many){
        mSymbol=Symbol;
        mVHL=VHL;
        mMany=many;
    }

    public MathNumber doCalculate(MathNumber[] numbers){
        BigDecimal answer=new BigDecimal(0);
        if (numbers[1]==null){
            answer=calculate(numbers[0].getValue());
        }
        if (numbers[1]!=null){
            answer=calculate(numbers[1].getValue(),numbers[0].getValue());
        }
        return new MathNumber(solveErrorValue(answer));
    }

    private BigDecimal calculate(BigDecimal number){//单目运算
        BigDecimal result=new BigDecimal(0);
        switch (mSymbol) {
            case "%":
                result=number.divide(new BigDecimal(100));
                break;
            case "-":
                result=new BigDecimal(0).subtract(number);
                break;
            case "+":
                result=new BigDecimal(0).add(number);
            default:
                break;
        }
        return result;
    }

    private BigDecimal calculate(BigDecimal number1,BigDecimal number2){//双目运算
        BigDecimal result=new BigDecimal(0);
        switch (mSymbol){
            case "+":
                result=number1.add(number2);
                break;
            case "-":
                result=number1.subtract(number2);
                break;
            case "×":
                result=number1.multiply(number2);
                break;
            case "÷":
                result=number1.divide(number2,DIGITS ,BigDecimal.ROUND_HALF_UP);
                break;
        }
        return result;
    }

    public int getVHL() {
        return mVHL;
    }

    public int getDigit() {
        return DIGITS;
    }

    @Override
    public String getName() {
        return mSymbol;
    }

    @Override
    public boolean getIsOperator(){
        return mIsOperator;
    }

    public int getMany() {
        return mMany;
    }

    private BigDecimal solveErrorValue(BigDecimal value){//解决 误差值
        String number=value.subtract(new BigDecimal(value.toBigInteger())).toPlainString();//不以指数形式输出
        BigDecimal result=value;
        if (number.length()==DIGITS+2){
            String end2_3=number.substring(number.length()-3,number.length()-1);//倒数第二第三位
            int end1=number.charAt(number.length()-1)-'0';//倒数第一位的数
            if (end2_3.equals("99")) {
                result=value.add(makeLeastValue(10-end1));
            }else if (end2_3.equals("00")){
                result=value.subtract(makeLeastValue(end1));
            }
        }
        return result;
    }
    private BigDecimal makeLeastValue(int value){//生成最小的数 （小数点精确位）
        return new BigDecimal(value).divide(new BigDecimal(10).pow(DIGITS));
    }
}
