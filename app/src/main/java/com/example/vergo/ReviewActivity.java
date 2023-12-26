package com.example.vergo;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {

    private EditText reviewEditText;
    private Button submitReviewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        reviewEditText = findViewById(R.id.reviewEditText);
        submitReviewButton = findViewById(R.id.submitReviewButton);

        submitReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String review = reviewEditText.getText().toString();

                // You can handle the review submission here, e.g., save it to a database or display it
                // For this example, we'll just display a toast message
                Toast.makeText(ReviewActivity.this, "Review Submitted: " + review, Toast.LENGTH_SHORT).show();
                finish(); // Close the review activity and return to the home screen
            }
        });
    }
}