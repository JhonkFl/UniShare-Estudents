package com.softjk.unishare.Menu;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.softjk.unishare.R;
import com.softjk.unishare.Universidades.ListaUniversidades;

public class FragmentInicio extends Fragment implements View.OnClickListener{

    Button Entrar,Donar,Apps,Militar;
    private AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        getActivity().setTitle("Buscador de Universidades");

        Entrar= view.findViewById(R.id.btnEntrar);
        Donar= view.findViewById(R.id.btnDonar);
        Militar= view.findViewById(R.id.btnMilitar);
       // Apps= view.findViewById(R.id.btnApps);

        Entrar.setOnClickListener(this);
        Donar.setOnClickListener(this);
        Militar.setOnClickListener(this);
        //Apps.setOnClickListener(this);

        //Publicidad
      /*  mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        }); */

        return view;
    }


    @Override
    public void onClick(View v) {
        if(v==Entrar){
            SiguieVentana("");
        }
        if(v==Donar){
            Toast.makeText(getActivity(), "No Disponible", Toast.LENGTH_SHORT).show();
         //   SiguieVentana("");
        }
        if(v==Militar){
            Toast.makeText(getActivity(), "No Disponible", Toast.LENGTH_SHORT).show();
          //  SiguieVentana("");
        }
       /* if(v==Apps){
            SiguieVentana("");
          }*/

    }

    public void SiguieVentana(String EST){
        Intent intent = new Intent(getActivity(), ListaUniversidades.class);
       // intent.putExtra("Estado",EST);
        startActivity(intent);
    }
}