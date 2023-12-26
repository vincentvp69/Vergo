package com.example.vergo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class HistoryFragment extends Fragment {

    private ListView historyListView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        // Initialize the ListView
        historyListView = rootView.findViewById(R.id.historyListView);

        // Create an array of historical entries (replace with your data)
        String[] historicalEntries = {
                "Seaside - Breakfast - 2023-12-26 - Vincent - 601121417797 - 10 Person Max",
                "Inside - Breakfast - 2023-12-29 - Vincent - 601121417797 - 10 Person Max",
                // Add more historical entries as needed
        };

        // Create an ArrayAdapter to display the historical entries
        ArrayAdapter<String> historyAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, historicalEntries);

        // Set the adapter for the ListView
        historyListView.setAdapter(historyAdapter);

        return rootView;
    }
}