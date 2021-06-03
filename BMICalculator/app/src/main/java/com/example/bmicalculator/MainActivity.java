package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

/**
 * This class implements the MainActivity.
 * EVELYN GIORDANO & CHRISTOPHER DEFRANZA
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        RadioGroup mGroup = (RadioGroup) findViewById(R.id.measurement);
        //RadioButton measurement = (RadioButton) mGroup.findViewById(mGroup.getCheckedRadioButtonId());
        final EditText w = findViewById((R.id.weight));
        final EditText h = findViewById((R.id.height));
        final TextView out = findViewById((R.id.bmi));
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.english:
                        w.setHint("Enter weight in pounds");
                        h.setHint("Enter height in inches");
                        w.setText("");
                        h.setText("");
                        out.setText("");
                        break;
                    case R.id.metric:
                        w.setHint("Enter weight in kilograms");
                        h.setHint("Enter height in meters");
                        w.setText("");
                        h.setText("");
                        out.setText("");
                        break;
                }
            }
        });
    }


    //change the hint when  the selection is changed

    /**
     * Calculates the bmi for the entered height and weight.
     * Does not perform if inputs are missing or invalid.
     * @param view  button click
     */
    public void calculateBMI(View view){
        //dont do if either height or weight is empty
        //get intake info
        RadioGroup mGroup = (RadioGroup)findViewById(R.id.measurement);
        EditText etW = findViewById(R.id.weight);
        EditText etH = findViewById(R.id.height);
        TextView output = findViewById(R.id.bmi);
        String w = etW.getText().toString();
        String h = etH.getText().toString();
        double weight = 0.0;
        double height = 0.0;

        if(!w.isEmpty() && !h.isEmpty()) {
            weight = Double.parseDouble(w);
            height = Double.parseDouble(h);
        } else {
            Toast.makeText(getApplicationContext(), "You must enter both your height and your weight!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if((weight <= 0) || (height <= 0)) {
            Toast.makeText(getApplicationContext(), "Invalid weight or height!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        double bmi = 0;
        DecimalFormat df = new DecimalFormat("#.##");


        if (mGroup.getCheckedRadioButtonId() == R.id.english) {
            bmi = (weight * 703.00) / (height * height);
            output.setText(df.format(bmi));
        }
        else if (mGroup.getCheckedRadioButtonId() == R.id.metric) {
            bmi = weight / (height * height);
            output.setText(df.format(bmi));
        }





        //get radiobutton selection
        //do calculations
        //output to the BMI area

    }

    /**
     * Opens the a new activity to display advice based on the calculated BMI.
     * Implements error checking.
     * @param view  button click
     */
    public void getAdvice(View view) {
        EditText etW = findViewById(R.id.weight);
        EditText etH = findViewById(R.id.height);
        TextView output = findViewById(R.id.bmi);
        String w = etW.getText().toString();
        String h = etH.getText().toString();
        String b = output.getText().toString();
        if((!w.isEmpty() && !h.isEmpty()) && !b.isEmpty()) {
            Intent i = new Intent(this, getAdvice.class);
            i.putExtra("bmi", output.getText().toString());
            output.setText("");
            //        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
            //        getSupportActionBar().hide();
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "You must calculate your BMI first!",
                    Toast.LENGTH_SHORT).show();
            //do toast
        }
    }
}
