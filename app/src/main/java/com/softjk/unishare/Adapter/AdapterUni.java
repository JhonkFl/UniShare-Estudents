package com.softjk.unishare.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.softjk.unishare.InfoUni;
import com.softjk.unishare.Modelo.Universidades;
import com.softjk.unishare.R;

public class AdapterUni extends FirestoreRecyclerAdapter<Universidades,AdapterUni.ViewHolder> {
    Activity activity;
    private InterstitialAd mInterstitialAd;

    public AdapterUni(@NonNull FirestoreRecyclerOptions<Universidades> options,Activity activity) {
        super(options);
        this.activity = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Universidades universidades) {
        viewHolder.Nombre.setText(universidades.getNombre());
        viewHolder.Tipo.setText(universidades.getTipo());
        viewHolder.Asentamiento.setText(universidades.getLocalidad());

        String Cali = universidades.getCalificacion();
        if (Cali != null) {
            Float Calificacion = Float.valueOf(Cali);
            viewHolder.Califi.setRating(Calificacion);
        }

        String Foto = universidades.getLogo();
        String EstadoUni = universidades.getEstado();

        try {
            if (!Foto.equals("") ){
                Glide.with(activity.getApplicationContext()).
                        load(Foto).
                        into(viewHolder.Logo);
            }
        }catch (Exception e){
            Log.d("Exception","e: "+e);
        }

        //Publicidad
        MobileAds.initialize(activity, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

         AdRequest adRequest = new AdRequest.Builder().build();
         //ID Prueba: ca-app-pub-3940256099942544~3347511713
        //ID Real: ca-app-pub-4502497099059343/5099949323
       InterstitialAd.load(activity, "ca-app-pub-3940256099942544~3347511713", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd = null;
                super.onAdFailedToLoad(loadAdError);
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
                super.onAdLoaded(interstitialAd);
            }
        });

        viewHolder.Universidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd != null){
                    mInterstitialAd.show(activity);
                }else {
                   // Toast.makeText(activity, "No se Cargó el Anuncio", Toast.LENGTH_SHORT).show();
                    System.out.println("ver id AD "+mInterstitialAd);
                }
                DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(viewHolder.getAdapterPosition());
                String id = documentSnapshot.getId();
                Intent i = new Intent(activity, InfoUni.class);
                i.putExtra("id",id);
                i.putExtra("Estado",EstadoUni);
                activity.startActivity(i);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_uni,parent,false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Nombre, Tipo, Asentamiento;
        ImageView Logo;
        LinearLayoutCompat Universidades;
        RatingBar Califi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Nombre = itemView.findViewById(R.id.NombreUniItem);
            Tipo = itemView.findViewById(R.id.TipoUniItem);
            Logo = itemView.findViewById(R.id.LogoItem);
            Universidades = itemView.findViewById(R.id.LinerUni);
            Asentamiento = itemView.findViewById(R.id.AsentamientoUniItem);
            Califi = itemView.findViewById(R.id.CaficicaUniBD);
        }
    }
}
