package com.example.databasess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity {
    private EditText EditTextAutor, EditTextName;
    private Button Add;
    private DaaBaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_db);

        EditTextName = findViewById(R.id.editTextName);
        EditTextAutor = findViewById(R.id.editTextAutor);
        Add = findViewById(R.id.add);

        db = new DaaBaseHelper(this);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBookToDatabase();
            }
        });
    }

    private void addBookToDatabase() {
        String bookName = EditTextName.getText().toString().trim();
        String bookAuthor = EditTextAutor.getText().toString().trim();

        if (bookName.isEmpty() || bookAuthor.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = db.addBook(bookName, bookAuthor);

        if (result > 0) {
            Toast.makeText(this, "Книга добавлена", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddBookActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Ошибка добавлнеия книги", Toast.LENGTH_SHORT).show();
        }
    }

}
