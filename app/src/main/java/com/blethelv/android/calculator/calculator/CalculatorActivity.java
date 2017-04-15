package com.blethelv.android.calculator.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blethelv.android.calculator.Calculate;
import com.blethelv.android.calculator.R;

public class CalculatorActivity extends AppCompatActivity {
    private EditText mNumberTextView;
    private Button mButton1,mButton2,mButton3,mButton4,mButton5,
            mButton6,mButton7,mButton8,mButton9,mButton0,
            mButtonPlus,mButtonRadix_point,mButtonMinus,mButtonTimes,
            mButtonInto,mButtonPercent,mButtonBackSpace,mButtonC,mButtonEqual;
    private Calculate mCalculate=new Calculate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        mNumberTextView=(EditText)findViewById(R.id.number_edit);
        mButton0=(Button)findViewById(R.id.zero);
        mButton1=(Button)findViewById(R.id.one);
        mButton2=(Button)findViewById(R.id.two);
        mButton3=(Button)findViewById(R.id.three);
        mButton4=(Button)findViewById(R.id.four);
        mButton5=(Button)findViewById(R.id.five);
        mButton6=(Button)findViewById(R.id.six);
        mButton7=(Button)findViewById(R.id.seven);
        mButton8=(Button)findViewById(R.id.eight);
        mButton9=(Button)findViewById(R.id.nine);

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
        mButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('1');
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('2');
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });
        mButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('3');
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });
        mButton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('4');
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });
        mButton5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('5');
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });
        mButton6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('6');
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });
        mButton7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('7');
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });
        mButton8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('8');
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });
        mButton9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('9');
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });
        mButton0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('0');
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });

        mButtonRadix_point.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('.');
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });

        mButtonPlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('+');
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });
        mButtonMinus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('-');
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });
        mButtonTimes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('×');
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });
        mButtonInto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('÷');
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });
        mButtonPercent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.scanner('%');
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });
        mButtonBackSpace.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.backSpace();
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });

        mButtonC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCalculate.clean();
                mNumberTextView.setText(mCalculate.getFormula());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });
        mButtonEqual.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mNumberTextView.setText(mCalculate.getTheResult());
                mNumberTextView.setSelection(mNumberTextView.getText().length());
            }
        });
    }
}
