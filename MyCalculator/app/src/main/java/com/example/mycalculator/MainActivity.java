package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    TextView result , expression;

    Button ac , clear , negate , divide , multiply , subtract , add , equals , point , zero , one , two , three , four , five , six , seven , eight , nine;



    boolean isAdd = true , isSub = true , isMul = true , isDiv = true , isDot = false , dec = false , zro = false , displayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        result = (TextView) findViewById(R.id.answer);
        expression = (TextView) findViewById(R.id.exp);

        ac = (Button) findViewById(R.id.AC);
        divide = (Button) findViewById(R.id.divide);
        multiply = (Button) findViewById(R.id.multiply);
        subtract = (Button) findViewById(R.id.subtract);
        add = (Button) findViewById(R.id.add);
        equals = (Button) findViewById(R.id.equal);
        point = (Button) findViewById(R.id.point);
        zero = (Button) findViewById(R.id.zero);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);

        //reset
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result.setText("");
                expression.setText("");
                isAdd = isSub = isMul = isDiv = isDot = dec = false;
            }
        });

        //divide
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(result.getText() != "")
                {
                    if(isAdd == true || isSub == true || isMul == true || isDiv == true)
                    {
                        Toast toast = Toast.makeText(getApplicationContext() , "Cannot add another operator next" , Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        if(isDot == false) {
                            isDiv = true;
                            result.setText(result.getText() + "/");
                            if(dec == true)
                                dec = false;
                        }
                    }
                }

            }
        });

        //multiply
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(result.getText() != "")
                {
                    if(isAdd == true || isSub == true || isMul == true || isDiv == true)
                    {
                        Toast toast = Toast.makeText(getApplicationContext() , "Cannot add another operator next" , Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        if(isDot == false) {
                            isMul = true;
                            result.setText(result.getText() + "*");
                            if(dec == true)
                                dec = false;
                        }
                    }
                }

            }
        });

        //subtract
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(result.getText() != "")
                {
                    if(isAdd == true || isSub == true || isMul == true || isDiv == true)
                    {
                        Toast toast = Toast.makeText(getApplicationContext() , "Cannot add another operator next" , Toast.LENGTH_SHORT);
                        toast.show();

                    }
                    else
                    {
                        if(isDot == false) {
                            isSub = true;
                            result.setText(result.getText() + "-");
                            if(dec == true)
                                dec = false;
                        }
                    }
                }

            }
        });

        //add
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(result.getText() != "")
                {
                    if(isAdd == true || isSub == true || isMul == true || isDiv == true)
                    {
                        Toast toast = Toast.makeText(getApplicationContext() , "Cannot add another operator next" , Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        if(isDot == false) {
                            isAdd = true;
                            result.setText(result.getText() + "+");
                            zro = dec = false;
                        }
                    }
                }

            }
        });

        //equals
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(result.getText() != "")
                {
                    String exp = result.getText().toString();

                    expression.setText(exp.toString());

                    Stack<Double> operands = new Stack<Double>();

                    Stack<Character> operators = new Stack<Character>();

                    String temp = "";

                    Double op1 , op2 , value;

                    if(isDot == true)
                        exp += "0";

                    int size = exp.length();

                    if (isAdd == true || isSub == true || isDiv == true || isMul == true)
                     {
                         size--;
                         Toast toast = Toast.makeText(getApplicationContext() , "No operand after the last operator, the operator is removed" , Toast.LENGTH_SHORT);
                         toast.show();
                     }

                    for (int i = 0 ; i < size ; i++)
                    {
                        if( exp.charAt(i) == '+' || exp.charAt(i) == '-' || exp.charAt(i) == '/' || exp.charAt(i) == '*')
                        {
                            if(temp != "")
                            {
                                operands.push(Double.valueOf(temp));
                                System.out.println(temp + " pushed in operands in for if operator ");
                                temp = "";

                            }

                            if(exp.charAt(i) == '+' || exp.charAt(i) == '-')
                            {
                                if(!operators.empty())
                                {
                                    op2 = operands.pop();
                                    op1 = operands.pop();

                                    value = calculate(op1 , op2 , operators.pop());

                                    operands.push( value );

                                    System.out.println(value + " pushed in operands in for after calculation");

                                    i--;
                                }
                                else
                                {
                                    operators.push(exp.charAt(i));
                                    System.out.println(exp.charAt(i) + " pushed in operators in for lower coz none");
                                }
                            }
                            else
                            {
                                if(!operators.empty())
                                    if(operators.peek() == '*' || operators.peek() == '/')
                                    {
                                        op2 = operands.pop();
                                        op1 = operands.pop();
                                        value = calculate(op1 , op2 , operators.pop());

                                        operands.push( value );

                                        System.out.println(value + " pushed in operands in for after calculation");
                                        i--;
                                    }
                                    else
                                    {
                                        operators.push(exp.charAt(i));
                                        System.out.println(exp.charAt(i) + " pushed in operators in for higher");
                                    }
                                else
                                {
                                    operators.push(exp.charAt(i));
                                    System.out.println(exp.charAt(i) + " pushed in operators in for coz none");
                                }


                            }

                        }
                        else
                            temp = temp + exp.charAt(i);

                   }

                    if(temp != "")
                    {
                        operands.push(Double.valueOf(temp));
                        System.out.println(temp + " pushed in operands outside for");
                    }

                    while(!operators.empty())
                    {
                        op2 = operands.pop();
                        op1 = operands.pop();
                        value = calculate(op1 , op2 , operators.pop());

                        operands.push( value );

                        System.out.println(value + " pushed in operands final");
                    }

                    expression.setText(result.getText() + "  =  " + operands.peek().toString());

                    result.setText("");

                    isAdd = isSub = isMul = isDiv = isDot = dec = zro = displayed = false;
                }

            }
        });

        //point
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (result.getText() == "")
                {
                    result.setText("0.");
                    isDot = true;
                    dec = true;
                }
                else
                {
                    if (isDot == false && dec == false) {
                        if(isAdd == true || isSub == true || isMul == true || isDiv == true)
                            result.setText(result.getText() + "0.");
                        else
                            result.setText(result.getText() +".");
                        isAdd = isDiv = isMul = isSub = false;
                        isDot = true;
                        dec = true;
                    }
                }
            }
        });

        //zero
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dec == true )
                    result.setText(result.getText() + "0");
                else if(zro == false)
                {
                    if(isDiv == true)
                    {
                        Toast toast = Toast.makeText(getApplicationContext() , "Can't divide by 0" , Toast.LENGTH_SHORT);
                        toast.show();
                        toast = Toast.makeText(getApplicationContext() , "Enter '.' to enter a decimal number like '0.x'", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        result.setText(result.getText() + "0");
                        if(isAdd == true || isSub == true || isMul == true || result.getText().length() == 1)
                            zro = true;
                    }
                }
                else
                    return;

                isAdd = isDiv = isMul = isSub = isDot = false;

            }
        });

        //one
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result.setText(result.getText() + "1");
                isAdd = isDiv = isMul = isSub = isDot = zro = false;

            }
        });

        //two
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result.setText(result.getText() + "2");
                isAdd = isDiv = isMul = isSub = isDot = zro = false;

            }
        });

        //three
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result.setText(result.getText() + "3");
                isAdd = isDiv = isMul = isSub = isDot = zro = false;

            }
        });

        //four
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result.setText(result.getText() + "4");
                isAdd = isDiv = isMul = isSub = isDot = zro = false;

            }
        });

        //five
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result.setText(result.getText() + "5");
                isAdd = isDiv = isMul = isSub = isDot = zro = false;

            }
        });

        //six
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result.setText(result.getText() + "6");
                isAdd = isDiv = isMul = isSub = isDot = zro = false;

            }
        });

        //seven
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result.setText(result.getText() + "7");
                isAdd = isDiv = isMul = isSub = isDot = zro = false;

            }
        });

        //eight
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result.setText(result.getText() + "8");
                isAdd = isDiv = isMul = isSub = isDot = zro = false;

            }
        });

        //nine
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result.setText(result.getText() + "9");
                isAdd = isDiv = isMul = isSub = isDot = zro = false;

            }
        });

    }

    private Double calculate(Double op1, Double op2, char pop) {

        Double ans = null;

        switch (pop)
        {
            case '+':
                ans = op1 + op2;
                System.out.println(op1 + " + " + op2 + " = " + ans);
                break;
            case '-':
                ans = op1 - op2;
                System.out.println(op1 + " - " + op2 + " = " + ans);
                break;
            case '/':
                ans = op1 / op2;
                System.out.println(op1 + " / " + op2 + " = " + ans);
                break;
            case '*':
                ans = op1 * op2;
                System.out.println(op1 + " * " + op2 + " = " + ans);
                break;
        }

        return ans;
    }

}