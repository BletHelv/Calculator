package com.blethelv.android.calculator;

import java.math.BigDecimal;

public class Operator implements MathSymbol {
    private String mSymbol;//运算符
    private int mVHL;//优先级
    private Boolean mIsOperator=true;

    public Operator(String Symbol, int VHL){
        mSymbol=Symbol;
        mVHL=VHL;
    }

    public MathNumber doCalculate(MathNumber[] numbers,int maxDecimal){
        BigDecimal answer;
        if (numbers[0]==null&&numbers[1]==null){
            answer=calculate();
        }else if (numbers[1]==null){
            answer=calculate(numbers[0].getValue());
        }else {
            answer=calculate(numbers[1].getValue(),numbers[0].getValue());
        }
        answer=answer.setScale(maxDecimal,BigDecimal.ROUND_DOWN);
        return new MathNumber(solveErrorValue(answer,maxDecimal));
    }
    private BigDecimal calculate(){//常数
        BigDecimal result=new BigDecimal(0);
        switch (mSymbol){
            case "π":
                result=new BigDecimal(Math.PI);
                break;
            case "e":
                result=new BigDecimal(Math.E);
        }
        return result;
    }
    private BigDecimal calculate(BigDecimal number){//单目运算
        BigDecimal result=BigDecimal.ZERO;
        switch (mSymbol) {
            case "%":
                result=number.divide(BigDecimal.valueOf(10));
                break;
            case "-":
                result=BigDecimal.ZERO.subtract(number);
                break;
            case "+":
                result=BigDecimal.ZERO.add(number);
                break;
            case "sin":
                result=new BigDecimal(Math.sin(Math.toRadians(number.doubleValue())));//转化成角度
                break;
            case "cos":
                result=new BigDecimal(Math.cos(Math.toRadians(number.doubleValue())));
                break;
            case "tan":
                result=new BigDecimal(Math.tan(Math.toRadians(number.doubleValue())));
                break;
            case "log":
                result=new BigDecimal(Math.log10(number.doubleValue()));
                break;
            case "ln":
                result=new BigDecimal(Math.log(number.doubleValue()));
                break;
            case "π":
                result=new BigDecimal(Math.PI).multiply(number);
                break;
            case "e":
                result=new BigDecimal(Math.E).multiply(number);
        }
        return result;
    }

    private BigDecimal calculate(BigDecimal number1,BigDecimal number2){//双目运算
        BigDecimal result=BigDecimal.ZERO;
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
                result=number1.divide(number2,maxDecimal ,BigDecimal.ROUND_HALF_UP);
                break;
            case "√":
                result=new BigDecimal(Math.pow(number2.doubleValue(),
                        new BigDecimal(1).divide(number1).doubleValue()));
                break;
            case "^":
                result=new BigDecimal(Math.pow(number1.doubleValue(),number2.doubleValue()));
                break;
        }
        return result;
    }

    public int getVHL() {
        return mVHL;
    }

    @Override
    public String getName() {
        return mSymbol;
    }

    @Override
    public boolean getIsOperator(){
        return mIsOperator;
    }

    private BigDecimal solveErrorValue(BigDecimal value,int maxDecimal){//解决 误差值
        String number=value.subtract(new BigDecimal(value.toBigInteger())).toPlainString();//不以指数形式输出
        BigDecimal result=value;
        if (number.length()==maxDecimal+2){
            String end2_3=number.substring(number.length()-3,number.length()-1);//倒数第二第三位
            int end1=number.charAt(number.length()-1)-'0';//倒数第一位的数
            if (end2_3.equals("99")) {
                result=value.add(makeLeastValue(10-end1,maxDecimal));
            }else if (end2_3.equals("00")){
                result=value.subtract(makeLeastValue(end1,maxDecimal));
            }
        }
        return result;
    }

    private BigDecimal makeLeastValue(int value,int maxDecimal){//生成最小的数 （小数点精确位）
        return new BigDecimal(value).divide(BigDecimal.TEN.pow(maxDecimal));
    }

}
