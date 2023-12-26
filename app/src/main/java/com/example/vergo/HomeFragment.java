package com.example.vergo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private Button bookTableButton;

    private Button reviewButton;
    private Button signOutButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        reviewButton = view.findViewById(R.id.reviewButton);
        signOutButton = view.findViewById(R.id.signOutButton);
        bookTableButton = view.findViewById(R.id.bookTableButton);

        if (bookTableButton != null) {
            bookTableButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate to the BookingActivity
                    Intent intent = new Intent(getActivity(), BookingActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            // If the button is null, log an error message
            Log.e("HomeFragment", "bookTableButton is null. Check if it's defined in fragment_home.xml");
        }

        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the review activity
                Intent intent = new Intent(getActivity(), ReviewActivity.class);
                startActivity(intent);
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform sign-out logic here
                // This may include clearing user session data, logging out, etc.

                // For example, if you want to go back to the login screen after signing out:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish(); // Finish the hosting activity to prevent going back to it with the back button
            }
        });

        return view;
    }
}
