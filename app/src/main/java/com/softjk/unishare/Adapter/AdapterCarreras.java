package com.softjk.unishare.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.softjk.unishare.InfoCarreras;
import com.softjk.unishare.Modelo.Carreras;
import com.softjk.unishare.R;

public class AdapterCarreras extends FirestoreRecyclerAdapter<Carreras,AdapterCarreras.ViewHolder> {
    Activity activity;
    SharedPreferences preferences;
    public AdapterCarreras(@NonNull FirestoreRecyclerOptions<Carreras> options,Activity activity) {
        super(options);
        this.activity = activity;
        this.preferences = activity.getSharedPreferences("Click", Context.MODE_PRIVATE);

    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterCarreras.ViewHolder viewHolder, int i, @NonNull Carreras carreras) {
        viewHolder.Nombre.setText(carreras.getNombre());
        String Categ = carreras.getCategoria();
        System.out.println("Ver Cat3egoria 1 : "+Categ);

        //Agregar Icono segun la categoria
        if (Categ != null){
        switch (Categ) {
            case "Arte":
                Glide.with(activity.getApplicationContext()).
                        load(R.drawable.arte).
                        into(viewHolder.Cat);
                break;
            case "Ciencias":
                Glide.with(activity.getApplicationContext()).
                        load(R.drawable.ciencias).
                        into(viewHolder.Cat);
                break;
            case "Comunicación":
                Glide.with(activity.getApplicationContext()).
                        load(R.drawable.comunicaciones).
                        into(viewHolder.Cat);
                break;
            case "Derecho":
                Glide.with(activity.getApplicationContext()).
                        load(R.drawable.justicia).
                        into(viewHolder.Cat);
                break;
            case "Educación":
                Glide.with(activity.getApplicationContext()).
                        load(R.drawable.educacion).
                        into(viewHolder.Cat);
                break;
            case "Finanzas":
                Glide.with(activity.getApplicationContext()).
                        load(R.drawable.finanzas).
                        into(viewHolder.Cat);
                break;
            case "Humanidades":
                Glide.with(activity.getApplicationContext()).
                        load(R.drawable.cuidado).
                        into(viewHolder.Cat);
                break;
            case "Ingeniería":
                Glide.with(activity.getApplicationContext()).
                        load(R.drawable.ingenieria).
                        into(viewHolder.Cat);
                break;
            case "Salud":
                Glide.with(activity.getApplicationContext()).
                        load(R.drawable.salud).
                        into(viewHolder.Cat);
                break;
            case "Militar":
                Glide.with(activity.getApplicationContext()).
                        load(R.drawable.teniente).
                        into(viewHolder.Cat);
                break;
            case "Servicios":
                Glide.with(activity.getApplicationContext()).
                        load(R.drawable.serv).
                        into(viewHolder.Cat);
                break;
            case "Tecnología":
                Glide.with(activity.getApplicationContext()).
                        load(R.drawable.tec).
                        into(viewHolder.Cat);
                break;
            default:
                System.out.println("Sin Categoria");
                break;
        }
        }

        viewHolder.Carrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(viewHolder.getAdapterPosition());
                String id = documentSnapshot.getId();
                String Opcion = preferences.getString("Click","");
                String Est = preferences.getString("Estado","");
                Intent i = new Intent(activity, InfoCarreras.class);
                i.putExtra("id",id);
                i.putExtra("Carrera","Carreras");
                i.putExtra("Click",Opcion);

                i.putExtra("Estado",Est);
                activity.startActivity(i);
            }
        });

        //viewHolder.Fondo.setBackground(Drawable.createFromPath("drawable/e1.png"));
    }


    @NonNull
    @Override
    public AdapterCarreras.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carreras,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Nombre;
        LinearLayoutCompat Carrera;
        ImageView Cat;
        CardView Fondo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Nombre = itemView.findViewById(R.id.NombreCarreraItem);
            Carrera = itemView.findViewById(R.id.LinerCarreras);
            Fondo = itemView.findViewById(R.id.cardVCarr);
            Cat = itemView.findViewById(R.id.ImgCar);
        }
    }
}
