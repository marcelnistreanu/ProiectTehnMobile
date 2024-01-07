package com.example.proiect.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.proiect.R;
import com.example.proiect.SharedPreferencesUtils;
import com.example.proiect.adapters.RecenzieAdapter;
import com.example.proiect.models.Destinatie;
import com.google.android.material.tabs.TabLayout;

public class DetaliiDestinatieFragment extends Fragment {

    private TextView textViewDescriere, textViewPret;
    private ViewPager viewPager;
    private RecenzieAdapter recenzieAdapter;

    public DetaliiDestinatieFragment() {
        // Required empty public constructor
    }

    public static DetaliiDestinatieFragment newInstance(String param1, String param2) {
        DetaliiDestinatieFragment fragment = new DetaliiDestinatieFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalii_destinatie, container, false);

        textViewDescriere = view.findViewById(R.id.textViewDescriere);
        textViewPret = view.findViewById(R.id.textViewPret);
        Bundle args = getArguments();
        if (args != null) {
            Destinatie destinatie = (Destinatie) args.getSerializable("cheie-destinatie");
            textViewDescriere.setText(destinatie.getDescriere());
            double pret = destinatie.getPret();
            String pretString = String.format("%.0f", pret);
            textViewPret.setText(pretString + " EUR");
            viewPager = view.findViewById(R.id.viewPager);
            recenzieAdapter = new RecenzieAdapter(getActivity(), destinatie.getListaRecenzii());
            viewPager.setAdapter(recenzieAdapter);
            TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
            tabLayout.setupWithViewPager(viewPager, true);
        }
        return view;
    }
}