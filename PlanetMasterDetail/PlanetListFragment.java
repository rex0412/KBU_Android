package com.example.report01;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class PlanetListFragment extends ListFragment {
    public interface OnPlanetSelectedListener {
        void onPlanetSelected(String koreanName);
    }

    private OnPlanetSelectedListener listener;
    private View lastSelectedView = null;

    String[] englishPlanets = {
            "Sun", "Mercury", "Venus", "Earth", "Mars", "Jupiter",
            "Saturn", "Uranus", "Neptune", "Pluto"
    };

    String[] koreanPlanets = {
            "태양", "수성", "금성", "지구", "화성", "목성",
            "토성", "천왕성", "해왕성", "명왕성"
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (OnPlanetSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context
                    + " must implement OnPlanetSelectedListener");
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireActivity(),
                android.R.layout.simple_list_item_1,
                englishPlanets
        );
        setListAdapter(adapter);
    }
    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (lastSelectedView != null) {
            lastSelectedView.setBackgroundColor(Color.TRANSPARENT);
            ((TextView) lastSelectedView).setTextColor(Color.BLACK);
        }
        v.setBackgroundColor(Color.RED);
        ((TextView) v).setTextColor(Color.WHITE);
        lastSelectedView = v;

        if (listener != null) {
            listener.onPlanetSelected(koreanPlanets[position]);
        }
    }
}