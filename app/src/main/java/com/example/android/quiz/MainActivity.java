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

    public boolean checkBox_spices, checkBox_whiteSauce;
    public boolean dessert, coffee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void submitOrder(View view) {
        CheckBox check_spices = (CheckBox) findViewById(R.id.checkbox_spices);
        checkBox_spices = check_spices.isChecked();

        CheckBox check_whiteSauce = (CheckBox) findViewById(R.id.checkbox_white_sauce);
        checkBox_whiteSauce = check_whiteSauce.isChecked();

        RadioButton radioDessert = (RadioButton) findViewById(R.id.radio_yes);
        dessert = radioDessert.isChecked();

        RadioButton coffeeDessert = (RadioButton) findViewById(R.id.radio_yes_coffee);
        coffee = radioDessert.isChecked();

        TextView mealField = (EditText) findViewById(R.id.meal_field);
        String nameOfmeal = mealField.getText().toString();

        String result = createOrderSummary(nameOfmeal, checkBox_spices, checkBox_whiteSauce, dessert, coffee);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Meal Order of: " + nameOfmeal);
        intent.putExtra(Intent.EXTRA_TEXT, result);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public String createOrderSummary(String meal, boolean checkSpices, boolean checkWhiteSauce, boolean dessert, boolean coffee){
        String textMessage = "Name of meal: " + meal ;

        if (checkBox_spices){
            textMessage += "\n- with spices.";
        }
        else {
            textMessage += "\n- no spices.";
        }

        if (checkBox_whiteSauce){
            textMessage += "\n- with white sauce.";
        }
        else {
            textMessage += "\n- no white sauce.";
        }

        if (dessert){
            textMessage += "\n- with our dessert.";
        }
        else {
            textMessage += "\n- no dessert.";
        }

        if (coffee){
            textMessage += "\n- with coffee.";
        }
        else {
            textMessage += "\n- no coffee.";
        }

        textMessage += "\n\tThank you :)";
        return textMessage;
    }


}
