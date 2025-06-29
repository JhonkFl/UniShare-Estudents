package com.softjk.unishare.Menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.softjk.unishare.R;

public class FragmentAcercade extends Fragment {

ImageView Faceboock, Instagram, Youtube;
RatingBar Calificar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_acercade, container, false);
        getActivity().setTitle("Acerca De...");

        Faceboock = view.findViewById(R.id.Faceboock);
        Faceboock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "https://www.facebook.com/profile.php?id=61572182170275";
                Uri Link = Uri.parse(URL);
                Intent intent = new Intent(Intent.ACTION_VIEW,Link);
                startActivity(intent);
            }
        });

        Youtube = view.findViewById(R.id.Youtube);
        Youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "https://www.youtube.com/@Soft-jkCompany";
                Uri Link = Uri.parse(URL);
                Intent intent = new Intent(Intent.ACTION_VIEW,Link);
                startActivity(intent);
            }
        });

        Calificar = view.findViewById(R.id.ratingBar);
        Calificar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                String URL = "https://play.google.com/store/apps/details?id=com.softjk.unishare";
                Uri Link = Uri.parse(URL);
                Intent intent = new Intent(Intent.ACTION_VIEW,Link);
                startActivity(intent);
            }
        });

        return view;
    }
}