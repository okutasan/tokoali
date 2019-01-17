package com.example.d2a.tokoali;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class KeranjangAdapter extends RecyclerView.Adapter<KeranjangAdapter.ViewHolder> {

    private List<ListItemKeranjang> listItems;
    private Context context;

    public KeranjangAdapter(List<ListItemKeranjang> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItemKeranjang listItem = listItems.get(position);
        holder.barangJudul.setText(listItem.getJudul());
        holder.barangHarga.setText(listItem.getHarga());

        Picasso.get().load(listItem.getGambar()).into(holder.barangGambar);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView barangGambar;
        public TextView barangJudul;
        public TextView barangHarga;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            barangGambar = (ImageView) itemView.findViewById(R.id.barangGambar);
            barangJudul = (TextView) itemView.findViewById(R.id.barangJudul);
            barangHarga = (TextView) itemView.findViewById(R.id.barangHarga);


        }
    }
}
