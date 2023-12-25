package com.example.proiect.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proiect.OnItemsClickedListener;
import com.example.proiect.R;
import com.example.proiect.adapters.DestinatieAdapter;
import com.example.proiect.models.Destinatie;
import com.example.proiect.models.Recenzie;

import java.util.ArrayList;
import java.util.List;

public class ListaDestinatiiFragment extends Fragment {

    private RecyclerView recyclerView;
    private DestinatieAdapter destinatieAdapter;

    private List<Destinatie> listaDestinatii = new ArrayList<>();

    public ListaDestinatiiFragment() {
        // Required empty public constructor
    }

    public static ListaDestinatiiFragment newInstance(String param1, String param2) {
        ListaDestinatiiFragment fragment = new ListaDestinatiiFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_destinatii, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        List<Recenzie> listaRecenzii = new ArrayList<>();
        listaRecenzii.add(new Recenzie("Comentariu foarte lung..", 1));
        listaRecenzii.add(new Recenzie("Alt comentariu foarte lung..", 2));
        listaRecenzii.add(new Recenzie("Si un alt comentariu foarte foarte lung..", 5));
        listaDestinatii.add(new Destinatie(1L, "Castelul Bran", "Bran, Romania",
                "Unul dintre cele mai impunătoare monumente istorice ale României se află în Pasul Bran-Rucăr. " +
                        "Astfel, Castelul Bran se situează la aproximativ 30 de kilometri de oraşul Braşov, pe şoseaua ce iese prin " +
                        "vechiul cartier Bartolomeu şi care leagă Braşovul de Câmpulung Muscel, prima capitală a Ţării Româneşti, " +
                        "acolo unde se întâlnesc Munţii Piatra Craiului cu masivul Bucegi.", 50, listaRecenzii));
        listaDestinatii.add(new Destinatie(2L, "Turnul Eifel", "Paris, Franta", "Bla-bla-bla..", 100, listaRecenzii));
        listaDestinatii.add(new Destinatie(3L, "Sfinx", "Muntii Bucegi, Busteni, Romania", "Bla-bla-bla..", 80, listaRecenzii));

        destinatieAdapter = new DestinatieAdapter(listaDestinatii, new OnItemsClickedListener() {
            @Override
            public void onItemClicked(Destinatie destinatie) {
                // declaram noul fragment
                DetaliiDestinatieFragment detaliiDestinatieFragment = new DetaliiDestinatieFragment();

                // impachetam tara selectata ca si bundle
                Bundle bundle = new Bundle();
                bundle.putSerializable("cheie-destinatie", destinatie);

                // atasam la fragment acest bundle
                detaliiDestinatieFragment.setArguments(bundle);

                // incepem tranzactia de fragment
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, detaliiDestinatieFragment);
                transaction.commit();
            }
        });

        recyclerView.setAdapter(destinatieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return view;
    }
}