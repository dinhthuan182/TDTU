package com.example.blockingcallsapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BlockerAdapter extends RecyclerView.Adapter<BlockerAdapter.MyViewHolder>{

    private static ArrayList<Contact> data;
    Context context;
    private static BlockedDatabase db;
    private static Contact contact;

    public BlockerAdapter(ArrayList<Contact> data,Context context){
        this.data = data;
        this.context = context;
        db = new BlockedDatabase(context);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder Holder, int position) {
        Holder.tvPersonName.setText(data.get(position).getName());
        Holder.tvPersonNumber.setText(data.get(position).getNumber());
        Holder.imgPerson.setImageResource(R.drawable.person);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView tvPersonName, tvPersonNumber;
        ImageView imgPerson;
        CardView contactCardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPersonName =(TextView) itemView.findViewById(R.id.tvPersonName);
            tvPersonNumber =(TextView) itemView.findViewById(R.id.tvPersonNumber);
            imgPerson = (ImageView) itemView.findViewById(R.id.imgPerson);
            contactCardView = itemView.findViewById(R.id.contactCardView);
            contactCardView.setOnCreateContextMenuListener(MyViewHolder.this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            MenuItem edit = menu.add(this.getAdapterPosition(), 121, 0, "Edit");
            MenuItem delete = menu.add(this.getAdapterPosition(), 122, 1, "Delete");
            MenuItem about = menu.add(this.getAdapterPosition(), 123, 2, "About");
            edit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    TextView tvDeleteNumber =(TextView) itemView.findViewById(R.id.tvPersonNumber);
                    TextView tvDeleteName =(TextView) itemView.findViewById(R.id.tvPersonName);
                    String deleteNumber = tvDeleteNumber.getText().toString();
                    String deleteName = tvDeleteName.getText().toString();

                    return false;
                }
            });
            delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    TextView tvDeleteNumber =(TextView) itemView.findViewById(R.id.tvPersonNumber);
                    TextView tvDeleteName =(TextView) itemView.findViewById(R.id.tvPersonName);
                    String deleteNumber = tvDeleteNumber.getText().toString();
                    String deleteName = tvDeleteName.getText().toString();
                    contact = new Contact(deleteName, deleteNumber, false);
                    db.deleteContact(contact);
                    db.deleteSms(contact);
                    BlockerAdapter.this.notifyDataSetChanged();
                    return true;
                }
            });
            about.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    TextView tvDeleteNumber =(TextView) itemView.findViewById(R.id.tvPersonNumber);
                    TextView tvDeleteName =(TextView) itemView.findViewById(R.id.tvPersonName);
                    String deleteNumber = tvDeleteNumber.getText().toString();
                    String deleteName = tvDeleteName.getText().toString();

                    return false;
                }
            });

        }
    }
}
