package com.softjk.unishare;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.softjk.unishare.Menu.MenuPrincipal;

public class Carga extends AppCompatActivity {
    ImageView Logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carga);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Logo = findViewById(R.id.imageView);

       /* Glide.with(Carga.this)
                .load("https://i.gifer.com/QPAW.gif")
                .into(Logo); */

        String frag = getIntent().getStringExtra("Msg");
       // Toast.makeText(this, "Notificacion"+frag, Toast.LENGTH_SHORT).show();

        final int DURACION = 3400;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Carga.this, MenuPrincipal.class);
                startActivity(intent);
                finish();
            }
        },DURACION);

    }

    @Override
    protected void onStart() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //No ModoOscuro por Defecto
        super.onStart();

    }


}