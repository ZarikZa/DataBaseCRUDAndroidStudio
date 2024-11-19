package com.example.databasess;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditBookActivity extends AppCompatActivity {

    private EditText editTextName, editTextAuthor;
    private Button updateButton;
    private DaaBaseHelper db;
    private int bookId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editTextName = findViewById(R.id.editTextName);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        updateButton = findViewById(R.id.updateButton);
        db = new DaaBaseHelper(this);

        Intent intent = getIntent();
        if (intent != null) {
            bookId = intent.getIntExtra("bookId", -1);
            editTextName.setText(intent.getStringExtra("bookAuthor"));  // Название книги
            editTextAuthor.setText(intent.getStringExtra("bookName"));  // Автор
        }

        updateButton.setOnClickListener(v -> updateBook());
    }

    private void updateBook() {
        String bookName = editTextName.getText().toString().trim();
        String bookAuthor = editTextAuthor.getText().toString().trim();

        if (bookName.isEmpty() || bookAuthor.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Обновляем книгу в базе данных с правильным порядком: сначала название, потом автор
        int result = db.updateBook(bookId, bookName, bookAuthor);
        if (result > 0) {
            Toast.makeText(this, "Book updated successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EditBookActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Error updating book", Toast.LENGTH_SHORT).show();
        }
    }
}
