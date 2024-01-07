package com.example.proiect.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proiect.OnItemsClickedListener;
import com.example.proiect.OnItemsDeletedListener;
import com.example.proiect.OnItemsEditedListener;
import com.example.proiect.R;
import com.example.proiect.activities.CreateDestination;
import com.example.proiect.activities.DestinatiiActivity;
import com.example.proiect.adapters.DestinatieAdapter;
import com.example.proiect.models.Destinatie;
import com.example.proiect.models.Recenzie;
import com.example.proiect.SharedPreferencesUtils;

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

        boolean isAdmin = SharedPreferencesUtils.IsUserAdmin(view.getContext());
        Button butonAdaugaDestintie = view.findViewById(R.id.butonAdaugaDestinatie);

        if (!isAdmin) {
            butonAdaugaDestintie.setVisibility(View.INVISIBLE);
        }
        butonAdaugaDestintie.setOnClickListener((View viewParam) -> {
           Intent intent = new Intent(getActivity(), CreateDestination.class);
           startActivityForResult(intent, 1);
        });

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

        destinatieAdapter = new DestinatieAdapter(listaDestinatii,
        new OnItemsClickedListener() {
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
        }, new OnItemsDeletedListener() {
        @Override
        public void onItemClicked(Destinatie destinatie) {
            deleteDestination(destinatie);
        }
        }, new OnItemsEditedListener() {
            @Override
            public void onItemClicked(Destinatie destinatie) {
                editDestination(destinatie);
            }
        });

        recyclerView.setAdapter(destinatieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("NAME");
            String location = data.getStringExtra("LOCATION");
            String description = data.getStringExtra("DESCRIPTION");
            String price = data.getStringExtra("PRICE");
            Destinatie destinatie = new Destinatie(4L, name, location, description, Double.parseDouble(price == null ? "0" : price), new ArrayList<>());
            listaDestinatii.add(destinatie);

            destinatieAdapter.notifyDataSetChanged();
        }

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            int index = data.getIntExtra("INDEX", 0);
            String name = data.getStringExtra("NAME");
            String location = data.getStringExtra("LOCATION");
            String description = data.getStringExtra("DESCRIPTION");
            String price = data.getStringExtra("PRICE");

            Destinatie destinatie = listaDestinatii.get(index);
            destinatie.setNume(name);
            destinatie.setDescriere(description);
            destinatie.setLocatie(location);
            destinatie.setPret(Double.parseDouble(price == null ? "0" : price));

            destinatieAdapter.notifyDataSetChanged();
        }
    }

    public void deleteDestination(Destinatie destinatie) {
        listaDestinatii.remove(destinatie);
        destinatieAdapter.notifyDataSetChanged();
    }

    public void editDestination(Destinatie destinatie) {
        Intent intent = new Intent(getActivity(), CreateDestination.class);
        intent.putExtra("NAME", destinatie.getNume());
        intent.putExtra("LOCATION", destinatie.getLocatie());
        intent.putExtra("DESCRIPTION", destinatie.getDescriere());
        intent.putExtra("PRICE", destinatie.getPret());
        intent.putExtra("INDEX", listaDestinatii.indexOf(destinatie));

        startActivityForResult(intent, 2);

        destinatieAdapter.notifyDataSetChanged();
    }
}