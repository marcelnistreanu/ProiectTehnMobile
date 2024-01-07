package com.example.proiect.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

import com.example.proiect.R;
import com.example.proiect.fragments.ListaDestinatiiFragment;

public class DestinatiiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinatii);
        if (savedInstanceState == null) {
            loadInitialFragment();
        }
    }

    private void loadInitialFragment() {
        // cream un fragment de tipul nostru - CountryListFragment
        ListaDestinatiiFragment listaDestinatiiFragment = new ListaDestinatiiFragment();

        // construim un fragment transactions -> va fi folosit pt operatii cu fragmentul
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // operatie de adaugare a fragmentului nou
        transaction.replace(R.id.fragment_container, listaDestinatiiFragment);

        // finalizare operatie prin commit
        transaction.commit();
    }
}