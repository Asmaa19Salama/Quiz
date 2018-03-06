package com.example.android.quiz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public boolean checkBox_presentPerfect, checkBox_for, checkBox_passive;
    public boolean radio_pastVerb, radio_always;
    public String readText;

    public int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void submitTest(View view) {

        CheckBox check_1 = (CheckBox) findViewById(R.id.checkbox_presentperfect);
        checkBox_presentPerfect = check_1.isChecked();

        CheckBox check_2 = (CheckBox) findViewById(R.id.checkbox_for);
        checkBox_for = check_2.isChecked();

        CheckBox check_3 = (CheckBox) findViewById(R.id.checkbox_passive);
        checkBox_passive = check_3.isChecked();

        RadioButton radio_past = (RadioButton) findViewById(R.id.radio_no);
        radio_pastVerb = radio_past.isChecked();

        ///
        RadioButton radio_last = (RadioButton) findViewById(R.id.radio_yes_always);
        radio_always = radio_last.isChecked();

        TextView readField = (EditText) findViewById(R.id.read_field);
        readText = readField.getText().toString();

        score = calculateScore(readText, checkBox_presentPerfect, checkBox_for, checkBox_passive, radio_pastVerb, radio_always);

        String result = "You scored " + score + "%.\nThanks for taking the test :D";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Score of the test :) ");
        intent.putExtra(Intent.EXTRA_TEXT, result);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public int calculateScore(String read, boolean checkPresentPerfect, boolean checkFor, boolean checkPassive, boolean radio_pastVerb, boolean radio_always){

        if (checkBox_presentPerfect && checkBox_passive && !checkBox_for){
            score += 25;
        }
        else if (checkBox_passive && !checkBox_presentPerfect) {
            score += 10;
        }
        else if (!checkBox_passive && checkBox_presentPerfect) {
            score += 10;
        }

        if (radio_pastVerb){
            score += 25;
        }


        if (read.equalsIgnoreCase("read")){
            score += 25;
        }

        if (radio_always){
            score += 25;
        }

        return score;
    }


}
