package com.example.proiect.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proiect.R;
import com.example.proiect.models.Utilizator;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etParola;
    private Button butonAutentificare;

    List<Utilizator> listaUtilizatori = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.editTextUsername);
        etParola = findViewById(R.id.editTextParola);
        butonAutentificare = findViewById(R.id.butonAutentificare);
        listaUtilizatori.add(new Utilizator(1L, "Marcel", "marcelnist", "m123", false));
        listaUtilizatori.add(new Utilizator(2L, "Adelina", "adelinah", "ade12", true));

        butonAutentificare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean utilizatorGasit = false;
                String numeUtilizator = String.valueOf(etUsername.getText());
                String parolaIntrodusa = String.valueOf(etParola.getText());
                for (Utilizator utilizator : listaUtilizatori) {
                    if (numeUtilizator.equals(utilizator.getUsername())) {
                        utilizatorGasit = true;
                        if (utilizator.verificaParola(parolaIntrodusa)) {
                            Toast.makeText(getApplicationContext(), "Autentificare reusita", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), DestinatiiActivity.class);
                            startActivity(intent);
                            break;
                        } else {
                            Toast.makeText(getApplicationContext(), "Parola incorecta", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                if (!utilizatorGasit) {
                    Toast.makeText(getApplicationContext(), "Utilizatorul nu există", Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();
            }
        });
    }
}