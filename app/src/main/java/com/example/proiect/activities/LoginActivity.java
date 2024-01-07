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

import com.example.proiect.NotificationHelper;
import com.example.proiect.SharedPreferencesUtils;
import android.app.PendingIntent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.annotation.NonNull;
import android.content.pm.PackageManager;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etParola;
    private Button butonAutentificare;

    public static final int PERMISSION_REQUEST_CODE = 123;

    List<Utilizator> listaUtilizatori = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.editTextUsername);
        etParola = findViewById(R.id.editTextParola);
        butonAutentificare = findViewById(R.id.butonAutentificare);
        listaUtilizatori.add(new Utilizator(1L, "Marcel", "marcelnist", "m123", true));
        listaUtilizatori.add(new Utilizator(2L, "Adelina", "adelinah", "ade12", false));

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
                            SharedPreferencesUtils.SaveAdminRights(view.getContext(), utilizator.isEsteAdmin());

                            Toast.makeText(getApplicationContext(), "Autentificare reusita", Toast.LENGTH_SHORT).show();

                            // Notification logic
                            showNotification();

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

    private void showNotification() {
        if (checkSelfPermission(android.Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED) {

            NotificationHelper.createNotificationChannel(this);

            Intent intent = new Intent(this, DestinatiiActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            // Build the notification
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NotificationHelper.CHANNEL_ID)
                    .setSmallIcon(R.drawable.notification)
                    .setContentTitle("Logged In Successfully")
                    .setContentText("Welcome to the app!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            // Show the notification
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(1, builder.build());
        }else{
            requestPermissions(new String[]{android.Manifest.permission.VIBRATE}, PERMISSION_REQUEST_CODE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showNotification();
            }
        }
    }
};