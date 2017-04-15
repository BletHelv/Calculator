package com.blethelv.android.calculator;

import java.math.BigDecimal;

public class MathNumber implements MathSymbol {
    private String mNumber;
    private BigDecimal mValue;//数值
    private Boolean mIsOperator=false;

    public MathNumber(BigDecimal value){
        mValue=value;
        mNumber=mValue.toString();
    }

    public MathNumber(int value){
        mValue=new BigDecimal(value);
        mNumber=mValue.toString();
    }

    public MathNumber(String number){
        mNumber=number;
        mValue=new BigDecimal(mNumber);
    }

    public MathNumber(StringBuffer number){
        mNumber=number.toString();
        mValue=new BigDecimal(mNumber);
    }

    @Override
    public boolean getIsOperator() {
        return mIsOperator;
    }

    @Override
    public String getName() {
        return mNumber;
    }

    public BigDecimal getValue() {
        return mValue;
    }
}
