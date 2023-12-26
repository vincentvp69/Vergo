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
                "Seaside - Smith Rock - 16 Feb 2023 - 9.30 A.M - 10 Person Max",
                "Inside - Smith Rock - 19 Feb 2023 - 10.30 AM - 10 Person Max",
                // Add more historical entries as needed
        };

        // Create an ArrayAdapter to display the historical entries
        ArrayAdapter<String> historyAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, historicalEntries);

        // Set the adapter for the ListView
        historyListView.setAdapter(historyAdapter);

        return rootView;
    }
}