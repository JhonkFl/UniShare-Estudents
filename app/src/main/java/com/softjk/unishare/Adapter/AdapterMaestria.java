package com.softjk.unishare.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.softjk.unishare.InfoCarreras;
import com.softjk.unishare.Modelo.Carreras;
import com.softjk.unishare.R;

public class AdapterMaestria extends FirestoreRecyclerAdapter<Carreras, AdapterMaestria.ViewHolder> {
    Activity activity;
    public AdapterMaestria(@NonNull FirestoreRecyclerOptions<Carreras> options, Activity activity) {
        super(options);
        this.activity = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterMaestria.ViewHolder viewHolder, int i, @NonNull Carreras carreras) {
        viewHolder.Nombre.setText(carreras.getNombre());

        viewHolder.Carrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(viewHolder.getAdapterPosition());
                String id = documentSnapshot.getId();
                Intent i = new Intent(activity, InfoCarreras.class);
                i.putExtra("id",id);
                i.putExtra("Carrera","Maestrias");
                activity.startActivity(i);
            }
        });
        viewHolder.Fondo.setBackground(Drawable.createFromPath("drawable/e1.png"));
    }


    @NonNull
    @Override
    public AdapterMaestria.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carreras,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Nombre;
        LinearLayoutCompat Carrera;
        CardView Fondo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Nombre = itemView.findViewById(R.id.NombreCarreraItem);
            Carrera = itemView.findViewById(R.id.LinerCarreras);
            Fondo = itemView.findViewById(R.id.cardVCarr);
        }
    }
}
