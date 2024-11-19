package com.example.databasess;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OpenPageActivity extends AppCompatActivity {

    private DaaBaseHelper DaaBaseHelper;
    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_page);

        DaaBaseHelper = new DaaBaseHelper(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("bookId")) {
            bookId = intent.getIntExtra("bookId", -1);
            if (bookId != -1) {
                loadBookDetails();
            } else {

                Toast.makeText(this, "Error: Book ID not found", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            Toast.makeText(this, "Error: Invalid Intent", Toast.LENGTH_SHORT).show();
            finish();
        }

        Button deleteButton = findViewById(R.id.deleteBtm);
        deleteButton.setOnClickListener(v -> deleteBook());
    }

    private void loadBookDetails() {
        Cursor cursor = DaaBaseHelper.getBookById(bookId); // You'll need to add this method to DaaBaseHelper
        if (cursor != null && cursor.moveToFirst()) {
            TextView nameTextView = findViewById(R.id.b_name);
            TextView authorTextView = findViewById(R.id.b_author);
            nameTextView.setText(cursor.getString(cursor.getColumnIndexOrThrow(DaaBaseHelper.COLUMN_NAME)));
            authorTextView.setText(cursor.getString(cursor.getColumnIndexOrThrow(DaaBaseHelper.COLUMN_AUTOR)));
            cursor.close();
        } else {
            Toast.makeText(this, "Error loading book details", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void deleteBook() {
        int result = DaaBaseHelper.deleteBookID(bookId);
        if (result > 0) {
            Toast.makeText(this, "Книга удалена", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Ошибка при удалении книги", Toast.LENGTH_SHORT).show();
        }
    }
}
