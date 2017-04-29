package com.blethelv.android.calculator;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CalculatorActivity extends AppCompatActivity {
    private EditText mNumberTextView;
    private Button[] mButtonNumber;
    private Button mButtonPlus,mButtonRadix_point,mButtonMinus,mButtonTimes,
            mButtonInto,mButtonPercent,mButtonBackSpace,mButtonC,mButtonEqual,
            mButtonLeft_bracket,mButtonRight_bracket,mButtonSin,mButtonCos,
            mButtonTan,mButtonLog,mButtonLn,mButtonRadical_sign,mButtonPower,
            mButtonPi,mButtonNatural_constant;
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
        mButtonLeft_bracket=(Button)findViewById(R.id.left_bracket);// (
        mButtonRight_bracket=(Button)findViewById(R.id.right_bracket);// )
        mButtonSin=(Button)findViewById(R.id.sin);//sin
        mButtonCos=(Button)findViewById(R.id.cos);//cos
        mButtonTan=(Button)findViewById(R.id.tan);//tan
        mButtonLog=(Button)findViewById(R.id.log);//log
        mButtonLn=(Button)findViewById(R.id.ln);//ln
        mButtonRadical_sign=(Button)findViewById(R.id.radical_sign);//√
        mButtonPower=(Button)findViewById(R.id.power);//^
        mButtonPi=(Button)findViewById(R.id.pi);//π
        mButtonNatural_constant=(Button)findViewById(R.id.natural_constant);//e
        //----------------------------------------------------------
        mButtonNumber[1].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(1);
                updateText();
            }
        });
        mButtonNumber[2].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(2);
                updateText();
            }
        });
        mButtonNumber[3].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(3);
                updateText();
            }
        });
        mButtonNumber[4].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(4);
                updateText();
            }
        });
        mButtonNumber[5].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(5);
                updateText();
            }
        });
        mButtonNumber[6].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(6);
                updateText();
            }
        });
        mButtonNumber[7].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(7);
                updateText();
            }
        });
        mButtonNumber[8].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(8);
                updateText();
            }
        });
        mButtonNumber[9].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(9);
                updateText();
            }
        });
        mButtonNumber[0].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(0);
                updateText();
            }
        });

        mButtonRadix_point.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(getString(R.string.radix_point));
                updateText();
            }
        });

        mButtonPlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(getString(R.string.plus));
                updateText();
            }
        });
        mButtonMinus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(getString(R.string.minus));
                updateText();
            }
        });
        mButtonTimes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(getString(R.string.times));
                updateText();
            }
        });
        mButtonInto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(getString(R.string.into));
                updateText();
            }
        });
        mButtonLeft_bracket.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(getString(R.string.left_bracket));
                updateText();
            }
        });
        mButtonRight_bracket.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(getString(R.string.right_bracket));
                updateText();
            }
        });
        mButtonRadical_sign.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(getString(R.string.radical_sign));
                updateText();
            }
        });
        mButtonPi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(getString(R.string.pi));
                updateText();
            }
        });
        mButtonNatural_constant.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(getString(R.string.natural_constant));
                updateText();
            }
        });
        mButtonPercent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(getString(R.string.percent));
                updateText();
            }
        });
        mButtonPower.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(getString(R.string.power));
                updateText();
            }
        });
        mButtonSin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(getString(R.string.sin));
                updateText();
            }
        });
        mButtonCos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(getString(R.string.cos));
                updateText();
            }
        });
        mButtonTan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(getString(R.string.tan));
                updateText();
            }
        });
        mButtonLog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(getString(R.string.log));
                updateText();
            }
        });
        mButtonLn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner(getString(R.string.ln));
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
                mCalculate.scanner(" ");
                mCalculate.getTheResult();
                updateText();
            }
        });
    }
    private void updateText(){
        mNumberTextView.setText(mCalculate.getFormula());
        mNumberTextView.setSelection(mNumberTextView.getText().length());
    }

}

