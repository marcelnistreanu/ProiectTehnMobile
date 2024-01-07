package com.example.proiect.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proiect.models.Destinatie;

import com.example.proiect.R;
import com.example.proiect.models.Recenzie;

import java.util.ArrayList;
import java.util.List;

public class CreateDestination extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_destination);

        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextLocation = findViewById(R.id.editTextLocation);
        EditText editTextDescription = findViewById(R.id.editTextDescription);
        EditText editTextPrice = findViewById(R.id.editTextPrice);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);

        Intent data = getIntent();

        int index = data.getIntExtra("INDEX", -1);
        boolean isEdit = index != -1;
        if(isEdit) {
            String name = data.getStringExtra("NAME");
            String location = data.getStringExtra("LOCATION");
            String description = data.getStringExtra("DESCRIPTION");
            Double price = data.getDoubleExtra("PRICE", 0);

            editTextName.setText(name);
            editTextLocation.setText(location);
            editTextDescription.setText(description);
            editTextPrice.setText(price.toString());
        }

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve values from the fields
                String name = editTextName.getText().toString();
                String location = editTextLocation.getText().toString();
                String description = editTextDescription.getText().toString();
                String price = editTextPrice.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("NAME", name);
                resultIntent.putExtra("LOCATION", location);
                resultIntent.putExtra("DESCRIPTION", description);
                resultIntent.putExtra("PRICE", price);
                if(isEdit) {
                    resultIntent.putExtra("INDEX", index);
                }

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
