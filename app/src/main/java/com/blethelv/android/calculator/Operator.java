package com.blethelv.android.calculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class Operator implements MathSymbol {
    private MathContext mDigits = new MathContext(DIGITS);
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
        BigDecimal minApproximation = new BigDecimal(answer.toBigInteger());//最小近似值
        BigDecimal MaxApproximation = minApproximation.add(new BigDecimal("1"));//最大近似值
        if (MaxApproximation.subtract(answer).compareTo(minErorValue())==-1){//误差小于精确值
            answer=MaxApproximation;
        }else if (answer.subtract(minApproximation).compareTo(minErorValue())==-1){
            answer=minApproximation;
        }
        return new MathNumber(answer);
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
                result=number1.divide(number2,mDigits);
                break;
        }
        return result;
    }

    public int getVHL() {
        return mVHL;
    }

    public MathContext getDigits() {
        return mDigits;
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

    private BigDecimal minErorValue(){//最小误差值
       return new BigDecimal(1).divide(new BigDecimal(10).pow(DIGITS-1));
    }
}
