package com.example.vergo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment {

    private EditText editTextUsername;
    private EditText editTextFavMeal;
    private EditText editTextFavLocation;
    private Switch switchNotification;
    private Button buttonSubmit;

    private DatabaseHelper dbHelper;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        dbHelper = new DatabaseHelper(getContext());
        sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        editTextUsername = view.findViewById(R.id.editTextUsername);
        editTextFavMeal = view.findViewById(R.id.editTextFavMeal);
        editTextFavLocation = view.findViewById(R.id.editTextFavLocation);
        switchNotification = view.findViewById(R.id.switchNotification);
        buttonSubmit = view.findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });

        loadSettings();

        return view;
    }

    private void saveSettings() {
        String email = sharedPreferences.getString("email", ""); // Retrieve user's email
        String username = editTextUsername.getText().toString();
        String favMeal = editTextFavMeal.getText().toString();
        String favLocation = editTextFavLocation.getText().toString();
        int notificationStatus = switchNotification.isChecked() ? 1 : 0;

        new SaveSettingsTask(email, username, favMeal, favLocation, notificationStatus).execute();
    }

    private class SaveSettingsTask extends AsyncTask<Void, Void, Boolean> {
        private final String email;
        private final String username;
        private final String favMeal;
        private final String favLocation;
        private final int notificationStatus;

        SaveSettingsTask(String email, String username, String favMeal, String favLocation, int notificationStatus) {
            this.email = email;
            this.username = username;
            this.favMeal = favMeal;
            this.favLocation = favLocation;
            this.notificationStatus = notificationStatus;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return dbHelper.insertSettings(email, username, favMeal, favLocation, notificationStatus);
        }

        @Override
        protected void onPostExecute(Boolean isSaved) {
            if (isSaved) {
                Toast.makeText(getContext(), "Settings saved successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Failed to save settings", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loadSettings() {
        String email = sharedPreferences.getString("email", ""); // Retrieve user's email
        new LoadSettingsTask().execute(email);
    }

    private class LoadSettingsTask extends AsyncTask<String, Void, Cursor> {
        @Override
        protected Cursor doInBackground(String... emails) {
            if (emails.length == 0) return null;
            return dbHelper.loadSettings(emails[0]);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToFirst()) {
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.colUsername));
                @SuppressLint("Range") String favMeal = cursor.getString(cursor.getColumnIndex(DatabaseHelper.colFavMeal));
                @SuppressLint("Range") String favLocation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.colFavLocation));
                @SuppressLint("Range") boolean notificationStatus = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.colNotification)) > 0;

                editTextUsername.setText(username);
                editTextFavMeal.setText(favMeal);
                editTextFavLocation.setText(favLocation);
                switchNotification.setChecked(notificationStatus);

                cursor.close(); // Close the cursor
            }
        }
    }
}
