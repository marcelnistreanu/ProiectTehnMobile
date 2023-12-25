package com.example.proiect.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.proiect.R;
import com.example.proiect.models.Recenzie;

import java.util.List;

public class RecenzieAdapter extends PagerAdapter {
    private List<Recenzie> listaRecenzii;
    private LayoutInflater layoutInflater;
    private Context context;

    public RecenzieAdapter(Context context, List<Recenzie> listaRecenzii) {
        this.listaRecenzii = listaRecenzii;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaRecenzii.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.item_comentariu, container, false);

        TextView textViewComentariu = view.findViewById(R.id.textViewComentariu);
        Recenzie recenzie = listaRecenzii.get(position);
        textViewComentariu.setText(recenzie.getComentariu());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
