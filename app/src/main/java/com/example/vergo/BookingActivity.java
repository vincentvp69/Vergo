package com.example.vergo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {

    private EditText editTextZone, editTextTable, editTextDate, editTextName, editTextPerson,editTextTel;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        editTextZone = findViewById(R.id.editTextZone);
        editTextTable = findViewById(R.id.editTextTable);
        editTextDate = findViewById(R.id.editTextDate);
        editTextName = findViewById(R.id.editTextName);
        editTextPerson = findViewById(R.id.editTextPerson);
        editTextTel = findViewById(R.id.editTextTel);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });



        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String zone = editTextZone.getText().toString();
                String table = editTextTable.getText().toString();
                String name = editTextName.getText().toString();
                String date = editTextDate.getText().toString();
                String tel = editTextTel.getText().toString();
                String person = editTextPerson.getText().toString();

                Reservation reservation = new Reservation(zone, table, date, name, tel, person);
                ReservationApi apiService = RetrofitClient.getClient("https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/api/").create(ReservationApi.class);

                Call<ResponseBody> call = apiService.postReservation(reservation);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(BookingActivity.this, "Reservation submitted successfully!", Toast.LENGTH_SHORT).show();
                        // Handle response
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(BookingActivity.this, "Error submitting reservation.", Toast.LENGTH_SHORT).show();
                        // Handle failure
                    }
                });
            }
        });

    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editTextDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }


}
