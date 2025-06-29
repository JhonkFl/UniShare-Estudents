package com.softjk.unishare.Menu;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.softjk.unishare.Menu.FragmentAcercade;
import com.softjk.unishare.Menu.FragmentInicio;
import com.softjk.unishare.Menu.FragmentNovedades;
import com.softjk.unishare.Menu.FragmentPolitica;
import com.softjk.unishare.R;

public class MenuPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = findViewById(R.id.TollbarAdmin);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.DrawerLayoutAdmin);
        NavigationView navigationView = findViewById(R.id.navigatioAdmin);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Mostrar Fragmento por Defecto
        String frag = getIntent().getStringExtra("Msg");
        System.out.println("Ver frag = "+frag);

        if (frag == null){
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentAdmin, new FragmentInicio()).commit();
                navigationView.setCheckedItem(R.id.Inicio);
                //Toast.makeText(this, "ver "+frag, Toast.LENGTH_SHORT).show();
            }
        }else{
            if (frag.equals("Novedades")){
           // if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentAdmin, new FragmentNovedades()).commit();
                navigationView.setCheckedItem(R.id.Novedades);
           // Toast.makeText(this, "ver "+frag, Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int ItemId = item.getItemId();
        if (ItemId == R.id.Inicio ){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentAdmin, new FragmentInicio()).commit();
        } else if (ItemId == R.id.Novedades ) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentAdmin, new FragmentNovedades()).commit();
        } else if (ItemId == R.id.Politica) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentAdmin, new FragmentPolitica()).commit();
        } else if (ItemId == R.id.Acercade) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentAdmin, new FragmentAcercade()).commit();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}