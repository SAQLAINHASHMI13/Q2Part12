package com.example.currencyconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etAmount;
    private Spinner spinnerFromCurrency, spinnerToCurrency;
    private Button btnConvert;
    private TextView tvResult;

    // Sample currency rates (assuming conversion to USD for simplicity)
    private final double USD_TO_EUR = 0.85;
    private final double USD_TO_GBP = 0.75;
    private final double USD_TO_INR = 74.0;
    private final double USD_TO_JPY = 110.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        etAmount = findViewById(R.id.et_amount);
        spinnerFromCurrency = findViewById(R.id.spinner_from_currency);
        spinnerToCurrency = findViewById(R.id.spinner_to_currency);
        btnConvert = findViewById(R.id.btn_convert);
        tvResult = findViewById(R.id.tv_result);

        // Set up spinners with currency options
        String[] currencies = {"USD", "EUR", "GBP", "INR", "JPY"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromCurrency.setAdapter(adapter);
        spinnerToCurrency.setAdapter(adapter);

        // Set onClickListener for the Convert button
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        // Get the input amount
        String amountStr = etAmount.getText().toString();
        if (amountStr.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);

        // Get selected currencies
        String fromCurrency = spinnerFromCurrency.getSelectedItem().toString();
        String toCurrency = spinnerToCurrency.getSelectedItem().toString();

        // Convert the amount
        double convertedAmount = convert(amount, fromCurrency, toCurrency);

        // Display the result
        tvResult.setText("Converted Amount: " + convertedAmount + " " + toCurrency);
    }

    private double convert(double amount, String fromCurrency, String toCurrency) {
        // Convert from the input currency to USD
        double amountInUSD = amount;
        switch (fromCurrency) {
            case "EUR":
                amountInUSD = amount / USD_TO_EUR;
                break;
            case "GBP":
                amountInUSD = amount / USD_TO_GBP;
                break;
            case "INR":
                amountInUSD = amount / USD_TO_INR;
                break;
            case "JPY":
                amountInUSD = amount / USD_TO_JPY;
                break;
            case "USD":
                // No conversion needed
                amountInUSD = amount;
                break;
        }

        // Convert from USD to the output currency
        double convertedAmount = amountInUSD;
        switch (toCurrency) {
            case "EUR":
                convertedAmount = amountInUSD * USD_TO_EUR;
                break;
            case "GBP":
                convertedAmount = amountInUSD * USD_TO_GBP;
                break;
            case "INR":
                convertedAmount = amountInUSD * USD_TO_INR;
                break;
            case "JPY":
                convertedAmount = amountInUSD * USD_TO_JPY;
                break;
            case "USD":
                // No conversion needed
                convertedAmount = amountInUSD;
                break;
        }

        return convertedAmount;
    }
}
