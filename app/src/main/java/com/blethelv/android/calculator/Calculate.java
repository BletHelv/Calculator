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
    private int mMaxDecimal;//保留最大小数位数
    public Calculate(int maxDecimal){
        operatorData();//运算符数据
        errorsData();//错误信息数据
        mMaxDecimal=maxDecimal;
    }

    public void scanner(int i){//输入数字
        cleanErrorMessage();
        if (i>=0&&i<=9) {
            if (mFormula.length() == 1 && mFormula.charAt(0) == '0') {
                mFormula.deleteCharAt(0);
            }
        }
        mFormula.append(i);
    }
    public void scanner(String s){//输入运算符
        cleanErrorMessage();
        if (s.equals(" ")){
            mFormula.append(" ");
        }else {
            mFormula.append(" ");
            mFormula.append(s);
            mFormula.append(" ");
        }
    }

    public void backSpace(){
        if (mFormula.length()>0) {
            int i = mFormula.length() - 1;
            if (mFormula.charAt(i) == ' ') {
                do {
                    i--;
                } while (mFormula.charAt(i) != ' ');
            }
            mFormula.delete(i,mFormula.length());
        }
    }

    public void clean(){
        mFormula.delete(0,mFormula.length());
    }

    public StringBuffer getFormula(){
        //处理数学表达式格式
        String number;
        StringBuffer text=new StringBuffer(mFormula.toString().replaceAll(" ",""));//去空格
        int start=0;
        int radixPoint=-1;
        int i;
        for (i=start;i<text.length();i++){
            char character=text.charAt(i);
            if (character == '.'){
                radixPoint=i;
            }else if (!( character>= '0' && character <= '9')){
                number=setNumberFormat((text.substring(start,i)),radixPoint);
                text.replace(start,i,number);
                start=i+1;
            }
        }
        number=setNumberFormat((text.substring(start,i)),radixPoint);
        text.replace(start,i,number);
        return text;
    }

    public void getTheResult(){
        try {
            doRPN();//转化成逆波兰表达式
            mFormula = new StringBuffer(calculateRPN().getName());//对逆波兰表达式进行运算
        }catch (RuntimeException e) {
            disposeErrors(e);//报错
        }
        removeUselessZero();//去无用之零
    }


    private void doRPN() {//转化成逆波兰表达式
        StringBuffer numberString = new StringBuffer();
        StringBuffer operatorString = new StringBuffer();
        for (int i = 0; i < mFormula.length(); i++) {
            char formulaChar = mFormula.charAt(i);//字母和符号
            if (formulaChar >= '0' && formulaChar <= '9' || formulaChar == '.') {
                numberString.append(formulaChar);
            } else if (formulaChar!=' ') {
                operatorString.append(formulaChar);
            } else{
                if (numberString.length() > 0) {
                    mFormulaList.add(new MathNumber(numberString));
                    numberString.setLength(0);
                }else if(operatorString.length() > 0){
                    OperatorInto(operatorString);
                    operatorString.setLength(0);
                }
            }
        }
        while (mOperators.size() != 0) {
            mFormulaList.add(mOperators.pop());
        }
    }

    private MathNumber calculateRPN(){//运算逆波兰表达式
        // TODO: 2017/4/29 debug 
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
                mFormulaList.set(i,operator.doCalculate(numbers,mMaxDecimal));
            }
        }
        MathNumber result=(MathNumber)mFormulaList.get(0);
        mFormulaList.clear();
        return result;
    }

    private void OperatorInto(StringBuffer operatorString){//把运算符放入式子里
        Operator operator=mOperatorMap.get(operatorString.toString());
        int lastVHL=0;
        if (mOperators.size() > 0) {
            lastVHL=mOperators.peek().getVHL();
        }
        if (operator.getName() .equals(")") ) {
            while (!mOperators.peek().getName().equals("(")) {
                mFormulaList.add(mOperators.pop());
            }
            mOperators.pop();
        } else {
            if (operator.getVHL() <= lastVHL&&!operator.getName().equals("(")) {
                mFormulaList.add(mOperators.pop());
            }
            mOperators.push(operator);
        }
    }

    private String setNumberFormat(String number,int radixPoint){//给数字添加千位分隔符 逗号
        String formatNumber;
        int cnt=0;
        if (number.length()>3){
            StringBuffer buffer=new StringBuffer(number);
            if (radixPoint==-1){
                radixPoint=buffer.length();
            }
            for (int i=radixPoint-1;i>0;i--){
                cnt++;
                if (cnt==3){
                    buffer.insert(i,',');
                    cnt=0;
                }
            }
            formatNumber=buffer.toString();
        }
        else {
            formatNumber=number;
        }
        return formatNumber;
    }

    private void removeUselessZero(){//去无用之零
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

    private void operatorData(){
        mOperatorMap.put("+",new Operator("+",3));
        mOperatorMap.put("-",new Operator("-",3));
        mOperatorMap.put("×",new Operator("×",5));
        mOperatorMap.put("÷",new Operator("÷",5));
        mOperatorMap.put("%",new Operator("%",5));
        mOperatorMap.put("(",new Operator("(",1));
        mOperatorMap.put(")",new Operator(")",8));
        mOperatorMap.put("^",new Operator("^",7));
        mOperatorMap.put("√",new Operator("√",5));
        mOperatorMap.put("π",new Operator("π",5));
        mOperatorMap.put("e",new Operator("e",5));
        mOperatorMap.put("sin",new Operator("sin",5));
        mOperatorMap.put("cos",new Operator("cos",5));
        mOperatorMap.put("tan",new Operator("tan",5));
        mOperatorMap.put("log",new Operator("log",5));
        mOperatorMap.put("ln",new Operator("ln",5));
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
    private void cleanErrorMessage(){//清除错误信息
        for (String error:mErrors) {
            if (mFormula.toString().equals(error)){
                clean();
            }
        }
    }

}
