package com.softjk.unishare;

import static com.softjk.unishare.InfoUni.idUni;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.softjk.unishare.Adapter.AdapterUni;
import com.softjk.unishare.Modelo.Universidades;

public class InfoCarreras extends AppCompatActivity {
    TextView Info,Campo1, Nombre,Campo2,Campo3,Campo4,Campo5,Campo6,Campo7,Campo8,Campo9,Campo10,Campo11,lblEstado;
    ImageView Imagen;
    LinearLayout LinerUnis;
    FirebaseFirestore mFirestore;
    Query query;
    RecyclerView ListaU;
    AdapterUni Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_carreras);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Info = findViewById(R.id.informacionInfo);
        Nombre = findViewById(R.id.CarreraInfo);
        Imagen = findViewById(R.id.ImgCarrera);
        Campo1 = findViewById(R.id.Campo1);
        Campo2 = findViewById(R.id.Campo2);
        Campo3 = findViewById(R.id.Campo3);
        Campo4 = findViewById(R.id.Campo4);
        Campo5 = findViewById(R.id.Campo5);
        Campo6 = findViewById(R.id.Campo6);
        Campo7 = findViewById(R.id.Campo7);
        Campo8 = findViewById(R.id.Campo8);
        Campo9 = findViewById(R.id.Campo9);
        Campo10 = findViewById(R.id.Campo10);
        Campo11 = findViewById(R.id.Campo11);
        ListaU = findViewById(R.id.LisUniXCarre);
        LinerUnis = findViewById(R.id.LinerListUniCarr);
        lblEstado = findViewById(R.id.lblEstado);


        mFirestore = FirebaseFirestore.getInstance();

        ObtenerDatos();

        String opcion = getIntent().getStringExtra("Click");
        String estado = getIntent().getStringExtra("Estado");
        System.out.println("Ver Opcion1.. "+opcion);
        lblEstado.setText(estado);
        String id = getIntent().getStringExtra("id");

        assert opcion != null;
        if (opcion.equals("Carreras")){
                 LinerUnis.setVisibility(View.VISIBLE);
                 ListarUni(estado, id);
             }else {
                 LinerUnis.setVisibility(View.GONE);
             }
    }

    private void ListarUni(String Estado,String item) {
        String id = getIntent().getStringExtra("id");
        ListaU.setLayoutManager(new GridLayoutManager(this,1));
       //query = mFirestore.collection(Estado);
        query = mFirestore.collection("Carreras/"+id+"/Uni");
        FirestoreRecyclerOptions<Universidades> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Universidades>().setQuery(query,Universidades.class).build();
        Adapter = new AdapterUni(firestoreRecyclerOptions,this);
        Adapter.notifyDataSetChanged();
        ListaU.setAdapter(Adapter);

        SharedPreferences preferences = getSharedPreferences("Click", Context.MODE_PRIVATE);
        String selec = preferences.getString("Selecc","");

        System.out.println("Estado: "+Estado);
        String buscar = "Carrreras."+item;
        System.out.println("Seleccion: "+selec);
        System.out.println("Buscar por: "+buscar);
        System.out.println("idCarr: "+item);
        //textSearch(selec,item);
        textSearch(Estado);
        // Adapter.startListening();

     /*   Query query = mFirestore.collection("Hidalgo")
                .whereArrayContains("Carreras", item);

        FirestoreRecyclerOptions<Universidades> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Universidades>()
                        .setQuery(query, Universidades.class)
                        .build();

        Adapter = new AdapterUni(firestoreRecyclerOptions, this);
        Adapter.startListening();
        ListaU.setAdapter(Adapter); */
    }

    private void ObtenerDatos() {
        String id = getIntent().getStringExtra("id");
        String TCarrera = getIntent().getStringExtra("Carrera");

        mFirestore.collection(TCarrera).document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String NombreCarr = documentSnapshot.getString("Nombre");
                String InfoCarr = documentSnapshot.getString("Info");
                String CampoCarr = documentSnapshot.getString("Campo1");
                String CampoC2 = documentSnapshot.getString("Campo2");
                String CampoC3 = documentSnapshot.getString("Campo3");
                String CampoC4 = documentSnapshot.getString("Campo4");
                String CampoC5 = documentSnapshot.getString("Campo5");
                String CampoC6 = documentSnapshot.getString("Campo6");
                String CampoC7 = documentSnapshot.getString("Campo7");
                String CampoC8 = documentSnapshot.getString("Campo8");
                String CampoC9 = documentSnapshot.getString("Campo9");
                String CampoC10 = documentSnapshot.getString("Campo10");
                String CampoC11 = documentSnapshot.getString("Campo11");
                String ImagenCarr = documentSnapshot.getString("IMG");

                Nombre.setText(NombreCarr);
                Info.setText(InfoCarr);
                Campo1.setText(CampoCarr);
                Campo2.setText(CampoC2);
                Campo3.setText(CampoC3);
                Campo4.setText(CampoC4);
                Campo5.setText(CampoC5);
                Campo6.setText(CampoC6);
                Campo7.setText(CampoC7);
                Campo8.setText(CampoC8);
                Campo9.setText(CampoC9);
                Campo10.setText(CampoC10);
                Campo11.setText(CampoC11);

                //--------------Mostrar Campos -------------

                if (CampoC6 == null) {
                    Campo6.setVisibility(View.GONE);
                }
                if (CampoC7 == null) {
                    Campo7.setVisibility(View.GONE);
                }
                if (CampoC8 == null) {
                    Campo8.setVisibility(View.GONE);
                }
                if (CampoC9 == null) {
                    Campo9.setVisibility(View.GONE);
                }
                if (CampoC10 == null) {
                    Campo10.setVisibility(View.GONE);
                }
                if (CampoC11 == null) {
                    Campo11.setVisibility(View.GONE);
                }


                //--------------Mostrar Foto------------
                try {
                    if (!ImagenCarr.equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(),"Cargando Foto",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP,0,200);
                        toast.show();

                        Glide.with(InfoCarreras.this)
                                .load(ImagenCarr)
                                .into(Imagen);
                    }
                }catch (Exception e){
                    Log.v("Error","e:"+e);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(InfoCarreras.this, "Error al Obtener los Datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

   /* public void textSearch(String s,String Buscar) {
        FirestoreRecyclerOptions<Universidades> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Universidades>()
                        .setQuery(query.orderBy("Carreras."+Buscar)
                                .startAt(s).endAt(s + "~"), Universidades.class).build();
        Adapter = new AdapterUni(firestoreRecyclerOptions, this);
        Adapter.startListening();
        ListaU.setAdapter(Adapter);
    }*/
   public void textSearch(String s) {
       FirestoreRecyclerOptions<Universidades> firestoreRecyclerOptions =
               new FirestoreRecyclerOptions.Builder<Universidades>()
                       .setQuery(query.orderBy("Estado")
                               .startAt(s).endAt(s + "~"), Universidades.class).build();
       Adapter = new AdapterUni(firestoreRecyclerOptions, this);
       Adapter.startListening();
       ListaU.setAdapter(Adapter);
   }
}