package com.blethelv.android.calculator;

import java.math.BigDecimal;
import java.math.BigInteger;

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
        if (numbers[0]==null&&numbers[1]==null){//无值运算
            answer=calculate();
        }else if (numbers[1]==null){//单目运算
            answer=calculate(numbers[0].getValue());
        }else {//双目运算
            answer=calculate(numbers[1].getValue(),numbers[0].getValue());
        }
        if (!answer.equals(BigDecimal.ZERO)) {
            answer = answer.setScale(maxDecimal, BigDecimal.ROUND_HALF_UP);
        }
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
            case "√":
                result=new BigDecimal(Math.sqrt(number.doubleValue()));
                break;
            case "sin":
                result=triangle(mSymbol,number);
                break;
            case "cos":
                result=triangle(mSymbol,number);
                break;
            case "tan":
                result=triangle(mSymbol,number);
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
                result= BigDecimal.ONE.divide(number1,maxDecimal ,BigDecimal.ROUND_HALF_UP);
                result=new BigDecimal(Math.pow(number2.doubleValue(),
                        result.doubleValue()));
                break;
            case "^":
                result=new BigDecimal(Math.pow(number1.doubleValue(),number2.doubleValue()));
                break;
            case "sin":
                result=triangle(mSymbol,number2);
                result=number1.multiply(result);
                break;
            case "cos":
                result=triangle(mSymbol,number2);
                result=number1.multiply(result);
                break;
            case "tan":
                result=triangle(mSymbol,number2);
                result=number1.multiply(result);
                break;
            case "log":
                result=new BigDecimal(Math.log10(number2.doubleValue()));
                result=number1.multiply(result);
                break;
            case "ln":
                result=new BigDecimal(Math.log(number2.doubleValue()));
                result=number1.multiply(result);
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


    private BigDecimal triangle(String triangle,BigDecimal number){//三角函数
        BigDecimal result=BigDecimal.ONE;// TODO: 2017/5/1
        switch (triangle) {
            case "sin":
                BigInteger multiple90Sin = getMultiple90(number);
                if (multiple90Sin == null) {
                    result = new BigDecimal(Math.sin(Math.toRadians(number.doubleValue())));//转化成角度
                } else {
                    result = TriangleSpecial("sin", multiple90Sin);
                }
                break;
            case "cos":
                BigInteger multiple90Cos = getMultiple90(number);
                if (multiple90Cos == null) {
                    result = new BigDecimal(Math.sin(Math.toRadians(number.doubleValue())));
                } else {
                    result = TriangleSpecial("cos", multiple90Cos);
                }
                break;
            case "tan":
                BigInteger multiple90Tan = getMultiple90(number);
                if (multiple90Tan == null) {
                    result = new BigDecimal(Math.sin(Math.toRadians(number.doubleValue())));
                } else {
                    result = TriangleSpecial("tan", multiple90Tan);
                }
                break;
        }
        return result;
    }

    private BigInteger getMultiple90(BigDecimal value){//是特殊三角函数
        BigInteger angle=value.toBigInteger();
        BigInteger squareness=new BigInteger("90");
        BigInteger multiple90=null;//90的倍数
        if (new BigDecimal(angle).equals(value)){
            if (angle.mod(squareness).equals(BigInteger.ZERO)){
                multiple90=angle.divide(squareness);
            }
        }
        return multiple90;
    }

    private BigDecimal TriangleSpecial(String triangle,BigInteger multiple90){
        BigDecimal result=BigDecimal.ZERO;
        BigInteger two=new BigInteger("2");
        switch (triangle){
            case "sin":
                if (multiple90.mod(two).equals(BigInteger.ONE)){
                    if (multiple90.divide(two).add(BigInteger.ONE).mod(two).equals(BigInteger.ONE)){
                        result=new BigDecimal(1);
                    }else {
                        result=new BigDecimal(-1);
                    }
                }else {
                    result=new BigDecimal(0);
                }
                break;
            case "cos":
                if (multiple90.mod(two).equals(BigInteger.ZERO)){
                    if (multiple90.divide(two).mod(two).equals(BigInteger.ZERO)){
                        result=new BigDecimal(1);
                    }else {
                        result=new BigDecimal(-1);
                    }
                }else {
                    result=new BigDecimal(0);
                }
                break;
            case "tan":
                if (multiple90.mod(two).equals(BigInteger.ZERO)){
                    result=new BigDecimal(0);
                }else {
                    throw new InfinityException();
                }
        }
        return result;
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
