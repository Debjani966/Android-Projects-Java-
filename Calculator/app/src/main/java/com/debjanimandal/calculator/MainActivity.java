package com.debjanimandal.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9
            ,btnAC,btnDel,btnPlus,btnMinus,btnDivide,btnMulti,btnEquals,btnDot;

    private TextView textViewResult,textViewHistory;

    private String number = null;

    double firstNumber = 0;
    double lastNumber = 0;

    String status = null;
    boolean operator = false;
    boolean operatorcheck=false;

    DecimalFormat myFormatter = new DecimalFormat("######.######");

    String history, currentResult;

    boolean dot = true;

    boolean btnACcontrol = true;

    boolean btnEqualsControl = false;
    boolean equalsoperater=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnPlus = findViewById(R.id.btnadd);
        btnMinus = findViewById(R.id.btnsub);
        btnDivide = findViewById(R.id.btnDiv);
        btnMulti = findViewById(R.id.btnMul);

        btnAC = findViewById(R.id.btnAC);
        btnDel = findViewById(R.id.btnDelete);
        btnDot = findViewById(R.id.btndot);
        btnEquals = findViewById(R.id.btnequals);

        textViewResult = findViewById(R.id.textViewResult);
        textViewHistory = findViewById(R.id.textViewhistory);


        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorcheck=false;
                numberClick("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorcheck=false;
                numberClick("1");

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorcheck=false;
                numberClick("2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorcheck=false;
                numberClick("3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorcheck=false;
                numberClick("4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorcheck=false;
                numberClick("5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorcheck=false;
                numberClick("6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorcheck=false;
                numberClick("7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorcheck=false;
                numberClick("8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorcheck=false;
                numberClick("9");
            }
        });

        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number = null;
                status = null;
                textViewResult.setText("0");
                textViewHistory.setText("");
                firstNumber = 0;
                lastNumber = 0;
                dot = true;
                btnACcontrol = true;

            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btnACcontrol)
                {
                    textViewResult.setText("0");
                }
                else
                {
                    number = number.substring(0,number.length()-1);

                    if (number.length() == 0)
                    {
                        btnDel.setClickable(false);
                    }
                    else if (number.contains("."))
                    {
                        dot = false;
                    }
                    else
                    {
                        dot = true;
                    }

                    textViewResult.setText(number);
                }



            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                if(!operatorcheck) {
                    if (equalsoperater) {
                        textViewHistory.setText(currentResult + "+");
                    } else {
                        textViewHistory.setText(history + currentResult + "+");
                    }

                    if (operator) {
                        if (status == "multiplacition") {
                            multiply();
                        } else if (status == "division") {
                            divide();
                        } else if (status == "subtraction") {
                            minus();
                        } else {
                            plus();
                        }
                    }
                }else {
                    String ss=textViewHistory.getText().toString();
                    String s=ss.substring(0,ss.length()-1);
                    textViewHistory.setText(s+"+");
                }

                status = "sum";
                operator = false;
                number = null;
                equalsoperater=false;
                operatorcheck=true;

            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                if(!operatorcheck) {
                    if (equalsoperater) {
                        textViewHistory.setText(currentResult + "-");
                    } else {
                        textViewHistory.setText(history + currentResult + "-");
                    }

                    if (operator) {
                        if (status == "multiplacition") {
                            multiply();
                        } else if (status == "division") {
                            divide();
                        } else if (status == "sum") {
                            plus();
                        } else {
                            minus();
                        }
                    }
                }
                else {
                    String ss=textViewHistory.getText().toString();
                    String s=ss.substring(0,ss.length()-1);
                    textViewHistory.setText(s+"-");
                }

                status = "subtraction";
                operator = false;
                number = null;
                equalsoperater=false;
                operatorcheck=true;
            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                if(!operatorcheck)
                {
                if(equalsoperater)
                {
                    textViewHistory.setText(currentResult+"*");
                }
                else {
                    textViewHistory.setText(history + currentResult + "*");
                }

                if (operator) {
                    if (status == "sum") {
                        plus();
                    } else if (status == "division") {
                        divide();
                    } else if (status == "subtraction") {
                        minus();
                    } else {
                        multiply();
                    }
                }
                }else {
                    String ss=textViewHistory.getText().toString();
                    String s=ss.substring(0,ss.length()-1);
                    textViewHistory.setText(s+"*");
                }

                status = "multiplacition";
                operator = false;
                number = null;
                equalsoperater=false;
                operatorcheck=true;

            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                if(!operatorcheck) {
                    if (equalsoperater) {
                        textViewHistory.setText(currentResult + "/");
                    } else {
                        textViewHistory.setText(history + currentResult + "/");
                    }

                    if (operator) {
                        if (status == "multiplacition") {
                            multiply();
                        } else if (status == "sum") {
                            plus();
                        } else if (status == "subtraction") {
                            minus();
                        } else {
                            divide();
                        }
                    }
                }else {
                    String ss=textViewHistory.getText().toString();
                    String s=ss.substring(0,ss.length()-1);
                    textViewHistory.setText(s+"/");
                }

                status = "division";
                operator = false;
                number = null;
                equalsoperater=false;
                operatorcheck=true;

            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalsoperater=true;

                if (operator)
                {
                    if (status == "sum")
                    {
                        plus();
                    }
                    else if (status == "subtraction")
                    {
                        minus();
                    }
                    else if (status == "multiplacition")
                    {
                        multiply();
                    }
                    else if (status == "division")
                    {
                        divide();
                    }
                    else
                    {
                        firstNumber = Double.parseDouble(textViewResult.getText().toString());
                    }
                }

                operator = false;
                btnEqualsControl = true;

            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dot)
                {

                    if (number == null)
                    {
                        number = "0.";
                    }
                    else
                    {
                        number = number + ".";
                    }

                }

                textViewResult.setText(number);
                dot = false;
            }
        });
    }

    public void numberClick(String view)
    {
        if (number == null)
        {
            number = view;
        }
        else if (btnEqualsControl)
        {
            firstNumber = 0;
            lastNumber = 0;
            number = view;
        }
        else
        {
            number = number + view;
        }

        textViewResult.setText(number);
        operator = true;
        btnACcontrol = false;
        btnDel.setClickable(true);
        btnEqualsControl = false;
    }

    public void plus()
    {
        lastNumber = Double.parseDouble(textViewResult.getText().toString());
        textViewHistory.setText(myFormatter.format(firstNumber)+"+"+myFormatter.format(lastNumber));
        firstNumber = firstNumber + lastNumber;

        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }

    public void minus()
    {
        if (firstNumber == 0)
        {
            firstNumber = Double.parseDouble(textViewResult.getText().toString());
        }
        else
        {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            textViewHistory.setText(myFormatter.format(firstNumber)+"-"+myFormatter.format(lastNumber));
            firstNumber = firstNumber - lastNumber;
        }

        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }

    public void multiply()
    {
        if (firstNumber == 0)
        {
            firstNumber = 1;
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            textViewHistory.setText(myFormatter.format(firstNumber)+"*"+myFormatter.format(lastNumber));
            firstNumber = firstNumber * lastNumber;
        }
        else
        {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            textViewHistory.setText(myFormatter.format(firstNumber)+"*"+myFormatter.format(lastNumber));
            firstNumber = firstNumber * lastNumber;
        }

        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }

    public void divide()
    {
        if (firstNumber == 0)
        {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            textViewHistory.setText(myFormatter.format(firstNumber)+"/"+myFormatter.format(lastNumber));
            firstNumber = lastNumber / 1;
        }
        else
        {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            textViewHistory.setText(myFormatter.format(firstNumber)+"/"+myFormatter.format(lastNumber));
            firstNumber = firstNumber / lastNumber;
        }

        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }
}