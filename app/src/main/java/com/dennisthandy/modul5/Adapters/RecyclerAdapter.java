package com.dennisthandy.modul5.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dennisthandy.modul5.Database.DatabaseHelper;
import com.dennisthandy.modul5.MainActivity;
import com.dennisthandy.modul5.Models.Mahasiswa;
import com.dennisthandy.modul5.R;
import com.dennisthandy.modul5.Read;
import com.dennisthandy.modul5.Update;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{

    private ArrayList<Mahasiswa> data;
    private Context context;
    private DatabaseHelper databaseHelper;

    public RecyclerAdapter(ArrayList<Mahasiswa> data, Context context){

        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.data_view, viewGroup, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, final int i) {
        recyclerViewHolder.nama.setText(data.get(i).getNama());
        recyclerViewHolder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("Pilihan");
                menu.add(data.get(i).getNrp(), v.getId(), 0, "Lihat").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(context, Read.class);
                        intent.putExtra("id", data.get(i).getNrp());
                        context.startActivity(intent);
                        //Toast.makeText(context, data.get(i).getId(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });

                menu.add(data.get(i).getNrp(), v.getId(), 1, "Edit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(context, Update.class);
                        intent.putExtra("id", data.get(i).getNrp());
                        context.startActivity(intent);
                        //Toast.makeText(context, String.valueOf(item.getGroupId()), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });

                menu.add(data.get(i).getNrp(), v.getId(), 2, "Hapus").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(final MenuItem item) {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setMessage("Apakah anda yakin ingin menghapus Mahasiswa ini?");

                        alertDialogBuilder.setPositiveButton("Ya",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        _deleteBiodata(data.get(i).getNrp());
                                        context.startActivity(new Intent(context, MainActivity.class));
                                        Toast.makeText(context, "Berhasil menghapus data", Toast.LENGTH_SHORT);
                                    }
                                });

                        alertDialogBuilder.setNegativeButton("Tidak",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {

                                    }
                                });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();

                        //Toast.makeText(context, String.valueOf(item.getGroupId()), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        private TextView nama;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.labelnama);
        }
    }

    private void _deleteBiodata(int nrp){

        databaseHelper = new DatabaseHelper(context);

        databaseHelper._delete(nrp);
    }
}