package com.blethelv.android.calculator;

import java.math.BigDecimal;

public interface MathSymbol {
    BigDecimal V0=new BigDecimal(0);
    BigDecimal V10=new BigDecimal(10);
    BigDecimal V100=new BigDecimal(100);
    BigDecimal V180=new BigDecimal(180);
    int maxDecimal=20;
    boolean getIsOperator();
    String getName();
}
