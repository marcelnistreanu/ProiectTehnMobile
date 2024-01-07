package com.example.proiect.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiect.OnItemsClickedListener;
import com.example.proiect.OnItemsDeletedListener;
import com.example.proiect.OnItemsEditedListener;
import com.example.proiect.R;
import com.example.proiect.SharedPreferencesUtils;
import com.example.proiect.models.Destinatie;

import java.util.List;

public class DestinatieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Destinatie> destinatieList;
    static OnItemsClickedListener onItemsClickedListener;
    static OnItemsDeletedListener onItemsDeletedListener;
    static OnItemsEditedListener onItemsEditedListener;

    public DestinatieAdapter(List<Destinatie> destinatieList, OnItemsClickedListener onItemsClickedListener, OnItemsDeletedListener onItemsDeletedListener, OnItemsEditedListener onItemsEditedListener) {
        this.destinatieList = destinatieList;
        this.onItemsClickedListener = onItemsClickedListener;
        this.onItemsDeletedListener = onItemsDeletedListener;
        this.onItemsEditedListener = onItemsEditedListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destinatie_item,
                parent, false);


        return new DestinatieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Destinatie destinatie = destinatieList.get(position);
        ((DestinatieViewHolder) holder).bind(destinatie);

        Button button1 = ((DestinatieViewHolder) holder).view.findViewById(R.id.butonStergere);
        Button button2 = ((DestinatieViewHolder) holder).view.findViewById(R.id.butonEditare);

        boolean isAdmin = SharedPreferencesUtils.IsUserAdmin(((DestinatieViewHolder) holder).view.getContext());
        if (!isAdmin) {
            button1.setVisibility(View.INVISIBLE);
            button2.setVisibility(View.INVISIBLE);
        }

        button1.setOnClickListener(v -> {
            if (onItemsDeletedListener != null) {
                int positionInList = holder.getAdapterPosition();
                Destinatie destinationClicked = destinatieList.get(positionInList);
                onItemsDeletedListener.onItemClicked(destinationClicked);
            }
        });

        button2.setOnClickListener(v -> {
            if (onItemsEditedListener != null) {
                int positionInList = holder.getAdapterPosition();
                Destinatie destinationClicked = destinatieList.get(positionInList);
                onItemsEditedListener.onItemClicked(destinationClicked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return destinatieList.size();
    }

    public static class DestinatieViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewNumeDestinatie, textViewPret;
        private final View view;

        public DestinatieViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumeDestinatie = itemView.findViewById(R.id.editTextDenumireDestinatie);
            textViewPret = itemView.findViewById(R.id.editTextPret);
            this.view = itemView;
        }

        void bind(Destinatie destinatie) {
            textViewNumeDestinatie.setText(destinatie.getNume());
            textViewPret.setText(String.valueOf(destinatie.getPret()) + " EUR");
            view.setOnClickListener(v -> {
                if (onItemsClickedListener != null) {
                    onItemsClickedListener.onItemClicked(destinatie);
                }
            });
        }
    }
}
