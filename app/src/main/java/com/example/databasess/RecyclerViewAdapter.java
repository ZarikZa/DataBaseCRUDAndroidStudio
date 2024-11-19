package com.example.databasess;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kotlin.experimental.BitwiseOperationsKt;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static Context context;
    private static ArrayList<Books> booksArrayList;
    private AdapterView.OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter(Context context, ArrayList<Books> booksArrayList){
        this.booksArrayList = booksArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_book_card, parent, false);
        return new ViewHolder(view);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Books book = booksArrayList.get(position);
        holder.boookNameTV.setText(book.getName());
        holder.bookAutorTV.setText(book.getAutor());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, OpenPageActivity.class);
            intent.putExtra("bookId", book.getID());
            context.startActivity(intent);
        });

        holder.izmenBtm.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditBookActivity.class);
            intent.putExtra("bookId", book.getID());
            intent.putExtra("bookName", book.getName());
            intent.putExtra("bookAuthor", book.getAutor());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return booksArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView boookNameTV;
        TextView bookAutorTV;
        Button izmenBtm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            boookNameTV = itemView.findViewById(R.id.b_name);
            bookAutorTV = itemView.findViewById(R.id.b_author);
            izmenBtm = itemView.findViewById(R.id.izmen_Btm);
        }
    }
}
