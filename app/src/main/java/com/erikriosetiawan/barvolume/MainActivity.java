package com.erikriosetiawan.barvolume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editLength, editWidth, editHeight;
    Button buttonCalculate;
    TextView textResult;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editLength = findViewById(R.id.edit_length);
        editWidth = findViewById(R.id.edit_width);
        editHeight = findViewById(R.id.edit_height);

        buttonCalculate = findViewById(R.id.btn_calculate);
        buttonCalculate.setOnClickListener(this);

        textResult = findViewById(R.id.tv_result);

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            textResult.setText(result);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_calculate) {

            String inputLength = editLength.getText().toString().trim();
            String inputWidth = editWidth.getText().toString().trim();
            String inputHeight = editHeight.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true;
                editLength.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true;
                editWidth.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true;
                editHeight.setError("Field ini tidak boleh kosong");
            }

            Double length = toDouble(inputLength);
            Double width = toDouble(inputWidth);
            Double height = toDouble(inputHeight);

            if (length == null) {
                isInvalidDouble = true;
                editLength.setError("Field ini harus berupa nomor yang valid");
            }

            if (width == null) {
                isInvalidDouble = true;
                editWidth.setError("Field ini harus berupa nomor yang valid");
            }

            if (height == null) {
                isInvalidDouble = true;
                editHeight.setError("Field ini harus berupa nomor  yang valid");
            }

            if ((!isEmptyFields) && (!isInvalidDouble)) {
                double volume = length * width * height;
                textResult.setText(String.valueOf(volume));
            }

        }
    }

    Double toDouble(String str) {
        try{
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, textResult.getText().toString());
    }
}
