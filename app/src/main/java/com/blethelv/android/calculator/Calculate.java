package com.blethelv.android.calculator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Calculate {//计算
    private StringBuffer mFormula=new StringBuffer();
    private List<MathSymbol> mFormulaList=new LinkedList<>();
    private Stack<Operator> mOperators=new Stack<>();
    private HashMap<String,Operator> mOperatorMap = new HashMap<>();
    private String[] mErrors=new String[]{"不能÷0","格式错误","超过计算范围"};
    private HashMap<String,String> mErrorsCue=new HashMap<>();
    public Calculate(){
        operatorData();
        errorsData();
    }

    public void scanner(char c){
        for (String error:mErrors) {
            if (mFormula.toString().equals(error)){
                clean();
            }
        }
        if (c>='0'&&c<='9') {
            if (mFormula.length() == 1 && mFormula.charAt(0) == '0') {
                mFormula.deleteCharAt(0);
            }
        }
        mFormula.append(c);
    }

    public void backSpace(){
        if (mFormula.length()>0) {
            mFormula.deleteCharAt(mFormula.length() - 1);
        }
    }

    public void clean(){
        mFormula.delete(0,mFormula.length());
    }

    public StringBuffer getFormula(){
        return mFormula;
    }

    public void getTheResult(){
        try {
            doRPN();
            mFormula = new StringBuffer(calculateRPN().getName());
        }catch (ArithmeticException e) {
            disposeErrors(e);
        }catch (NumberFormatException e){
            disposeErrors(e);
        }
        int deleteCnt=mFormula.length();
        boolean isDecimal=false;
        boolean isWithout=false;//没有可以去除的零
        for (int i=mFormula.length()-1;i>0;i--){
            char c=mFormula.charAt(i);
            if (c=='0'&&!isWithout){
                deleteCnt=i;
            }
            else {
                if (c == '.') {
                    isDecimal = true;
                    if (!isWithout) {
                        deleteCnt=i;
                        break;
                    }
                }
                else {
                    isWithout = true;
                }
            }
        }
        if (isDecimal) {
            for (int i=mFormula.length()-1;i>=deleteCnt;i--) {
                mFormula.deleteCharAt(i);
            }
        }
    }


    private void doRPN() {
        StringBuffer numberString = new StringBuffer();
        StringBuffer operatorString = new StringBuffer();
        for (int i = 0; i < mFormula.length(); i++) {
            char formulaChar = mFormula.charAt(i);//字母和符号
            if (formulaChar >= '0' && formulaChar <= '9' || formulaChar == '.') {
                numberString.append(formulaChar);
                if (operatorString.length() > 0) {
                    OperatorInto(operatorString);
                    operatorString.setLength(0);
                }
            } else {
                operatorString.append(formulaChar);
                if (numberString.length() > 0) {
                    mFormulaList.add(new MathNumber(numberString));
                    numberString.setLength(0);
                }
            }
        }
        if (numberString.length() > 0) {
            mFormulaList.add(new MathNumber(numberString));
        } else {
            mOperators.push(mOperatorMap.get(operatorString.toString()));
        }
        while (mOperators.size() != 0) {
            mFormulaList.add(mOperators.pop());
        }
    }

    private MathNumber calculateRPN(){
        MathNumber[] numbers=new MathNumber[2];
        MathSymbol symbol;
        for (int i=0;i<mFormulaList.size();i++){
            symbol=mFormulaList.get(i);
            if (symbol.getIsOperator()){
                Operator operator=(Operator)symbol;
                int place=i-1;
                for (int j=place;j>place-2;j--){
                    if (!mFormulaList.get(j).getIsOperator()) {
                        MathNumber number = (MathNumber) mFormulaList.get(j);
                        mFormulaList.remove(j);
                        i--;
                        numbers[place-j] = number;
                    }
                    if (j==0){
                        break;
                    }
                }
                mFormulaList.set(i,operator.doCalculate(numbers));
            }
        }
        MathNumber result=(MathNumber)mFormulaList.get(0);
        mFormulaList.clear();
        return result;
    }

    private void OperatorInto(StringBuffer operatorString){
        Operator operator=mOperatorMap.get(operatorString.toString());
        int lastVHL=0;
        if (mOperators.size() > 0) {
            lastVHL=mOperators.peek().getVHL();
        }
        if (operator.getName() .equals(")") ) {
            while (mOperators.peek().getName().equals("(") ) {
                mFormulaList.add(mOperators.pop());
            }
        } else if (operator.getVHL() <= lastVHL) {
            mFormulaList.add(mOperators.pop());
        }
        mOperators.push(operator);
    }

    private void operatorData(){
        mOperatorMap.put("+",new Operator("+",3,2));
        mOperatorMap.put("-",new Operator("-",3,2));
        mOperatorMap.put("×",new Operator("×",5,2));
        mOperatorMap.put("÷",new Operator("÷",5,2));
        mOperatorMap.put("%",new Operator("%",5,1));
        mOperatorMap.put("(",new Operator("(",1,0));
        mOperatorMap.put(")",new Operator(")",8,0));
    }
    private void errorsData(){//报错数据
        mErrorsCue.put(new ArithmeticException().toString(),mErrors[0]);
        mErrorsCue.put(new NumberFormatException().toString(),mErrors[1] );
    }
    private void disposeErrors(RuntimeException e){//错误处理
        mFormulaList.clear();
        mOperators.clear();
        String exception=e.toString().replace(": "+e.getMessage(),new String());
        mFormula=new StringBuffer(mErrorsCue.get(exception));
    }
}
