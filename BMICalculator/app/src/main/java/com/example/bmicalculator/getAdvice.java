package com.example.bmicalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Implements the advice activity when the "Get advice" button is clicked in the Main Activity.
 * Closes the new activity when the back button is pressed.
 * EVELYN GIORDANO & CHRISTOPHER DEFRANZA
 */
public class getAdvice extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_advice);
        setAdvice();

        Toolbar t = (Toolbar)findViewById(R.id.toolbar);
        t.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    /**
     * Sets the correct advice and image based on the current BMI.
     */
    public void setAdvice() {
        final double minimum = 18.5;
        final double normalMax = 24.9;
        final double overMax = 29.9;

        Intent i = getIntent();
        String value = i.getStringExtra("bmi");
        TextView output = (TextView)findViewById(R.id.advice);
        ImageView image = (ImageView)findViewById(R.id.weightPic);
        double bmi = Double.parseDouble(value);


        if(bmi < minimum) {
            image.setImageResource(R.drawable.underweight);
            output.setText("UNDERWEIGHT");
        }
        else if ((bmi >= minimum) && (bmi <= normalMax)) {
            image.setImageResource(R.drawable.normal);
            output.setText("NORMAL");
        }
        else if ((bmi > normalMax) && (bmi  <= overMax)) {
            image.setImageResource((R.drawable.overweight));
            output.setText("OVERWEIGHT");

        } else {
            image.setImageResource((R.drawable.obesity));
            output.setText("OBESE");
        }
    }


}
