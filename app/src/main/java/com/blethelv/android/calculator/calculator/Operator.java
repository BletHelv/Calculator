package com.blethelv.android.calculator.calculator;

import com.blethelv.android.calculator.MathNumber;
import com.blethelv.android.calculator.MathSymbol;

import java.math.BigDecimal;
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

}
