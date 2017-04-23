package com.blethelv.android.calculator;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class CalculatorActivity extends AppCompatActivity {
    private EditText mNumberTextView;
    private Button[] mButtonNumber;
    private Button mButtonPlus,mButtonRadix_point,mButtonMinus,mButtonTimes,
            mButtonInto,mButtonPercent,mButtonBackSpace,mButtonC,mButtonEqual;
    private int mMaxDecimal;
    private Calculate mCalculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        mNumberTextView=(EditText)findViewById(R.id.number_edit);
        mMaxDecimal=20;
        mCalculate=new Calculate(mMaxDecimal);
        mButtonNumber=new Button[10];
        mButtonNumber[0]=(Button)findViewById(R.id.zero);
        mButtonNumber[1]=(Button)findViewById(R.id.one);
        mButtonNumber[2]=(Button)findViewById(R.id.two);
        mButtonNumber[3]=(Button)findViewById(R.id.three);
        mButtonNumber[4]=(Button)findViewById(R.id.four);
        mButtonNumber[5]=(Button)findViewById(R.id.five);
        mButtonNumber[6]=(Button)findViewById(R.id.six);
        mButtonNumber[7]=(Button)findViewById(R.id.seven);
        mButtonNumber[8]=(Button)findViewById(R.id.eight);
        mButtonNumber[9]=(Button)findViewById(R.id.nine);

        mButtonRadix_point=(Button)findViewById(R.id.radix_point);//.
        mButtonPlus=(Button)findViewById(R.id.plus);//＋
        mButtonMinus=(Button)findViewById(R.id.minus);//－
        mButtonTimes=(Button)findViewById(R.id.times);//×
        mButtonInto=(Button)findViewById(R.id.into);//÷
        mButtonPercent=(Button)findViewById(R.id.percent);//%
        mButtonC=(Button)findViewById(R.id.clean_up);//清除
        mButtonEqual=(Button)findViewById(R.id.equal);//等于
        mButtonBackSpace=(Button)findViewById(R.id.backSpace);//退格
        //----------------------------------------------------------
        mButtonNumber[1].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('1');
                updateText();
            }
        });
        mButtonNumber[2].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('2');
                updateText();
            }
        });
        mButtonNumber[3].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('3');
                updateText();
            }
        });
        mButtonNumber[4].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('4');
                updateText();
            }
        });
        mButtonNumber[5].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('5');
                updateText();
            }
        });
        mButtonNumber[6].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('6');
                updateText();
            }
        });
        mButtonNumber[7].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('7');
                updateText();
            }
        });
        mButtonNumber[8].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('8');
                updateText();
            }
        });
        mButtonNumber[9].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('9');
                updateText();
            }
        });
        mButtonNumber[0].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('0');
                updateText();
            }
        });

        mButtonRadix_point.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('.');
                updateText();
            }
        });

        mButtonPlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('+');
                updateText();
            }
        });
        mButtonMinus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('-');
                updateText();
            }
        });
        mButtonTimes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('×');
                updateText();
            }
        });
        mButtonInto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('÷');
                updateText();
            }
        });
        mButtonPercent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('%');
                updateText();
            }
        });
        mButtonBackSpace.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.backSpace();
                updateText();
            }
        });

        mButtonC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.clean();
                updateText();
            }
        });
        mButtonEqual.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.getTheResult();
                updateText();
            }
        });
    }

    private void updateText(){
        String number;
        StringBuffer text=new StringBuffer(mCalculate.getFormula().toString());
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

        mNumberTextView.setText(text);
        mNumberTextView.setSelection(mNumberTextView.getText().length());
    }

    private String setNumberFormat(String number,int radixPoint){
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
}

