package com.softjk.unishare.Universidades;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.softjk.unishare.Adapter.AdapterCarreras;
import com.softjk.unishare.Adapter.AdapterUni;
import com.softjk.unishare.ImgEstados;
import com.softjk.unishare.Modelo.Carreras;
import com.softjk.unishare.Modelo.Universidades;
import com.softjk.unishare.R;
import com.softjk.unishare.SpinnerB;

public class ListaUniversidades extends AppCompatActivity {
    Spinner OpcionesSpn, SPEstados;
    SearchView Buscar;
    RecyclerView Lista;
    AdapterUni Adapter;
    AdapterCarreras AdpCarrers;
    RadioButton rbMunicipio, rbCarreras;
    FirebaseFirestore mfFirestore;
    ImageView ImgEstad,flecha,MSG;
    Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_universidades);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseApp.initializeApp(ListaUniversidades.this);

        OpcionesSpn = findViewById(R.id.SPBuscar);
        Buscar = findViewById(R.id.SVBuscar);
        mfFirestore = FirebaseFirestore.getInstance();
        ImgEstad = findViewById(R.id.imgEstado);
        rbMunicipio = findViewById(R.id.RbMunicipio);
        rbCarreras = findViewById(R.id.RbCarreras);
        SPEstados = findViewById(R.id.spEstd);
        flecha = findViewById(R.id.flecha);
        MSG = findViewById(R.id.flecha2);

        //String Estado = getIntent().getStringExtra("Estado");
        String [] OpEstado = {"Seleccionar Estado","Hidalgo","Veracruz"};
        ArrayAdapter<String> Estado = new ArrayAdapter<>(this, R.layout.spinner_item_estilo2,OpEstado);
        SPEstados.setAdapter(Estado);

        SharedPreferences preferences = getSharedPreferences("Click", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        SPEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rbMunicipio.setChecked(false);
                rbCarreras.setChecked(false);
                OpcionesSpn.setSelection(0);
                editor.putString("Estado", SPEstados.getSelectedItem().toString());
                editor.commit();

                if (SPEstados.getSelectedItemPosition() == 0 ){
                    flecha.setVisibility(View.VISIBLE);
                    Glide.with(ListaUniversidades.this)
                            .load(getDrawable(R.drawable.selecc2))
                            .into(flecha);
                }else {
                    flecha.setVisibility(View.GONE);
                }

                if (!rbCarreras.isChecked() && !rbMunicipio.isChecked()){
                    OpcionesSpn.setVisibility(View.INVISIBLE);
                    if (SPEstados.getSelectedItemPosition() != 0) {
                        Glide.with(ListaUniversidades.this)
                                .load(getDrawable(R.drawable.msgselecc))
                                .into(MSG);
                        MSG.setVisibility(View.VISIBLE);
                    }
                }

                rbMunicipio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        MSG.setVisibility(View.GONE);
                        Buscar.setVisibility(View.GONE);

                        String estado = (String) SPEstados.getSelectedItem();
                        ImgEstados.SwiithImg(estado,ImgEstad,ListaUniversidades.this);

                        if (rbMunicipio.isChecked()) {
                            OpcionesSpn.setVisibility(View.VISIBLE);
                            assert estado != null;
                            SpinnerB.SelecciMuni(estado,OpcionesSpn, ListaUniversidades.this);
                            setUpRecyclerView(estado,"Municipio");

                            // SharedPreferences
                            editor.putString("Click","Municipio");
                            editor.commit();
                        } else if (rbCarreras.isChecked()) {
                            OpcionesSpn.setVisibility(View.VISIBLE);
                            if (OpEstado.equals("Seleccionar Estado")){
                                Toast.makeText(ListaUniversidades.this, "Por Favor Seleccione un Estado!!", Toast.LENGTH_SHORT).show();
                            }else {
                                SpinnerB.SpinnerCarreras(SPEstados,OpcionesSpn, ListaUniversidades.this,Buscar);
                                ListarCarreas();
                                editor.putString("Click", "Carreras");
                                editor.commit();
                            }
                        }else {
                            if (!rbCarreras.isChecked() && !rbMunicipio.isChecked()){
                                OpcionesSpn.setVisibility(View.INVISIBLE);
                            }
                        }

                    }
                });
                rbCarreras.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        MSG.setVisibility(View.GONE);
                        if (OpEstado.equals("Seleccionar Estado")){
                            Toast.makeText(ListaUniversidades.this, "Por Favor Seleccione un Estado!!", Toast.LENGTH_SHORT).show();
                        }

                        String estado = (String) SPEstados.getSelectedItem();
                        ImgEstados.SwiithImg(estado,ImgEstad,ListaUniversidades.this);
                        if (rbCarreras.isChecked()) {
                            OpcionesSpn.setVisibility(View.VISIBLE);
                            SpinnerB.SpinnerCarreras(SPEstados,OpcionesSpn, ListaUniversidades.this,Buscar);
                            ListarCarreas( );
                            editor.putString("Click","Carreras");
                            editor.commit();
                        }else if (rbMunicipio.isChecked()) {
                            OpcionesSpn.setVisibility(View.VISIBLE);
                            assert estado != null;
                            SpinnerB.SelecciMuni(estado,OpcionesSpn, ListaUniversidades.this);
                            setUpRecyclerView(estado,"Municipio");

                            // SharedPreferences
                            editor.putString("Click","Municipio");
                            editor.commit();
                        }
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        if (Buscar.equals("Categoria")) {
            Buscar.setVisibility(View.GONE);
            OpcionesSpn.setVisibility(View.VISIBLE);
        }else {
            search_view();
        }
    }

    private void search_view() {
        Buscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                MSG.setVisibility(View.GONE);
                String Item = "Nombre";
                textSearchCarreras(s,Item);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                MSG.setVisibility(View.GONE);
                String Item = "Nombre";
                textSearchCarreras(s,Item);
                return false;
            }
        });
    }

    private void setUpRecyclerView(String Estado,String Buscar) {
        Lista = findViewById(R.id.ListaUni);
        Lista.setLayoutManager(new GridLayoutManager(this,1));
        query = mfFirestore.collection(Estado);
        //query = mFirestore.collection("/"+Estado+"/"+idUni+"/Carreras");
        FirestoreRecyclerOptions<Universidades> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Universidades>().setQuery(query,Universidades.class).build();
        Adapter = new AdapterUni(firestoreRecyclerOptions,this);
        Adapter.notifyDataSetChanged();
        Lista.setAdapter(Adapter);
        // Adapter.startListening();

        OpcionesSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Item = parent.getSelectedItem().toString();
                System.out.println(Item+" ---> "+Buscar);
                textSearch(Item,Buscar);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void ListarCarreas() {
        Lista = findViewById(R.id.ListaUni);
        Lista.setLayoutManager(new GridLayoutManager(this,1));
        query = mfFirestore.collection("Carreras");
        //query = mFirestore.collection("/"+Estado+"/"+idUni+"/Carreras");
        FirestoreRecyclerOptions<Carreras> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Carreras>().setQuery(query,Carreras.class).build();
        AdpCarrers = new AdapterCarreras(firestoreRecyclerOptions,this);
        AdpCarrers.notifyDataSetChanged();
        Lista.setAdapter(AdpCarrers);
        // Adapter.startListening();

        OpcionesSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Item = parent.getSelectedItem().toString();
                SharedPreferences preferences = getSharedPreferences("Click", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Selecc", Item);
                editor.commit();
                System.out.println("Seleccion --> "+Item);
                textSearchCarreras(Item,"Categoria");

                if (Item.equals("Buscar Carrera")){
                    Buscar.setVisibility(View.VISIBLE);
                    OpcionesSpn.setVisibility(View.GONE);

                    Glide.with(ListaUniversidades.this)
                            .load(getDrawable(R.drawable.mensaje2))
                            .into(MSG);
                    MSG.setVisibility(View.VISIBLE);
                    MSG.setTranslationY(30);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void textSearch(String s,String buscar) {
        FirestoreRecyclerOptions<Universidades> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Universidades>()
                        .setQuery(query.orderBy(buscar)
                                .startAt(s).endAt(s + "~"), Universidades.class).build();
        Adapter = new AdapterUni(firestoreRecyclerOptions, this);
        Adapter.startListening();
        Lista.setAdapter(Adapter);
    }

    public void textSearchCarreras(String s,String BuscarPor) {
        FirestoreRecyclerOptions<Carreras> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Carreras>()
                        .setQuery(query.orderBy(BuscarPor)
                                .startAt(s).endAt(s + "~"), Carreras.class).build();
        AdpCarrers = new AdapterCarreras(firestoreRecyclerOptions, this);
        AdpCarrers.startListening();
        Lista.setAdapter(AdpCarrers);
    }


    //fin
}