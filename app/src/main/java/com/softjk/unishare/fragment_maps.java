package com.softjk.unishare;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;


public class fragment_maps extends DialogFragment{

    Activity activity;
    String Latitud;
    String Longitud;
    String Abc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        activity = new Activity();

        // Configuración inicial de osmdroid
        Configuration.getInstance().load(getActivity(), PreferenceManager.getDefaultSharedPreferences(getActivity()));
        MapView mapView = view.findViewById(R.id.LinerMapa);
        mapView.setTileSource(TileSourceFactory.MAPNIK);

        Bundle args = getArguments();
        System.out.println(args);
        if (args != null) {
            // Obtén el dato utilizando la clave que usaste para enviarlo desde la actividad
            Latitud = args.getString("Latitud");
            Longitud = args.getString("Longitud");
            double lat = Double.parseDouble(Latitud);
            double lot = Double.parseDouble(Longitud);
            Abc = args.getString("ABC");
            System.out.println("Ubicaion Localizada");
            ObtenerMap(lat,lot,Abc,mapView);

        }

        return view;
    }
    public void ObtenerMap(double Lati,double Longit,String ABC,MapView mapView){
        // Centrar el mapa y agregar un marcador
        MapController mapController = (MapController) mapView.getController();
        mapController.setZoom(18.0);
        GeoPoint startPoint = new GeoPoint(Lati, Longit);
        mapController.setCenter(startPoint);

        Marker startMarker = new Marker(mapView);
        startMarker.setPosition(startPoint);
        startMarker.setTitle(ABC);
        mapView.getOverlays().add(startMarker);
    }

}