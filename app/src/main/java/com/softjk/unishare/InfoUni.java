package com.softjk.unishare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.softjk.unishare.Adapter.AdapterCarreras;
import com.softjk.unishare.Adapter.AdapterCarreras2;
import com.softjk.unishare.Adapter.AdapterDoctorado;
import com.softjk.unishare.Adapter.AdapterMaestria;
import com.softjk.unishare.Adapter.AdapterPosgrado;
import com.softjk.unishare.Modelo.Carreras;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class InfoUni extends AppCompatActivity {

    FirebaseFirestore mFirestore;
    RatingBar Califica;
    ImageView Logo, Mapas;
    TabHost TablaSeccion;
    TextView Locali, Tipo, Municipi, Ubicacion, Nombre, Telefono, Correo, Faceboock, Pagina;
    RecyclerView Lista,ListaPosgra,ListaMaestr,ListaDoctu;
    Button Ficha;
    AdapterCarreras Adapter;
    AdapterCarreras2 Adapter2;
    AdapterPosgrado AdapterPos;
    AdapterMaestria AdapterMaes;
    AdapterDoctorado AdapterDoc;
    Query query;
    static String idUni;
    SharedPreferences preferences;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_uni);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Califica = findViewById(R.id.CalificaUni);
        Locali = findViewById(R.id.AbcInfo);
        Tipo = findViewById(R.id.TipoInfo);
        Municipi = findViewById(R.id.EstadoInfo);
        Ubicacion = findViewById(R.id.UbicacionInfo);
        Nombre = findViewById(R.id.NombreUniInfo);
        Telefono = findViewById(R.id.TelefonoInfo);
        Correo = findViewById(R.id.CorreoInfo);
        Faceboock = findViewById(R.id.FaceInfo);
        // clave = findViewById(R.id.ClaveInfo);
        Pagina = findViewById(R.id.PaginaInfo);
        Logo = findViewById(R.id.logoUniInfo);
        Mapas = findViewById(R.id.btnMaps);

        ListaMaestr = findViewById(R.id.ListaMaestria);
        ListaDoctu = findViewById(R.id.ListaDoctorado);
        Ficha = findViewById(R.id.btnFicha);
        TablaSeccion = findViewById(R.id.TablaHost);

        mFirestore = FirebaseFirestore.getInstance();
        preferences = getSharedPreferences("Click", Context.MODE_PRIVATE);

        String id = getIntent().getStringExtra("id");
        String EstadoBD = getIntent().getStringExtra("Estado");
        System.out.println("Ver id Estado "+EstadoBD);
        idUni = id; //para pasar el id en otra Actividad

        ObtenerDatos(id,EstadoBD);

        String Modo = preferences.getString("Click","");
        if (Modo.equals("Municipio")){
            setUpRecyclerView(id,EstadoBD);
        } else if (Modo.equals("Carreras")) {
            setUpRecyclerView2(id,EstadoBD);
        }

        Califica.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(InfoUni.this, rating + " Estrellas", Toast.LENGTH_SHORT).show();
                String NC = String.valueOf(rating);
                GuardarCalificacion(EstadoBD,NC);
            }
        });


    }

    private void GuardarCalificacion(String idEstado,String NC) {
        Map<String, Object> map = new HashMap<>();
        map.put("Calificacion", NC);

        mFirestore.collection(idEstado).document(idUni).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Calificado", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al Calificar", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void ObtenerDatos(String id,String EstadoBD) {
        mFirestore.collection(EstadoBD).document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String NombreUni = documentSnapshot.getString("Nombre");
                String ABc = documentSnapshot.getString("ABC");
                String Localidad = documentSnapshot.getString("Localidad");
                String TipoUni = documentSnapshot.getString("Tipo");
                String Municipio = documentSnapshot.getString("Municipio");
                String UbicacionUni = documentSnapshot.getString("Ubicacion");
                String TelefonoUni = documentSnapshot.getString("Telefono");
                String CorreoUni = documentSnapshot.getString("Correo");
                String FaceboockUni= documentSnapshot.getString("Facebook");
                String PaginaUni = documentSnapshot.getString("Pagina");
                String LogoUni = documentSnapshot.getString("IMG");
                String Latitud = documentSnapshot.getString("Latitud");
                String Longitud = documentSnapshot.getString("Longitud");
                String Posgra = documentSnapshot.getString("Especialidades");
                String Maest = documentSnapshot.getString("Maestrias");
                String Doct = documentSnapshot.getString("Doctorado");
                String Fich = documentSnapshot.getString("Ficha");
                String Requisitos = documentSnapshot.getString("Publicidad1");
                String FechaClas = documentSnapshot.getString("Publicidad2");

                Nombre.setText(NombreUni);
                Locali.setText(Localidad);
                Tipo.setText(TipoUni);
                Municipi.setText(Municipio);
                Ubicacion.setText(UbicacionUni);
                Telefono.setText(TelefonoUni);
                Correo.setText(CorreoUni);
                Faceboock.setText(FaceboockUni);
                Pagina.setText(PaginaUni);
                System.out.println("Latitu: "+Latitud+" lon: "+Longitud);
                System.out.println("M: "+Maest+" D: "+Doct+ " E: "+Posgra);

                try {
                    if (!LogoUni.equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Cargando foto", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 200);
                        toast.show();
                        Glide.with(InfoUni.this)
                                .load(LogoUni)
                                .into(Logo);

                        System.out.println("Logo Agregado... "+LogoUni);
                    }
                }catch (Exception e){
                    Log.v("Error", "e: " + e);
                    System.out.println("Logo NO Agregado... "+LogoUni);
                }


                try {
                    if (!Requisitos.equals("") || !FechaClas.equals("")){

                        ImageCarousel carousel = findViewById(R.id.carousel);
                        carousel.setVisibility(View.VISIBLE);
                        carousel.registerLifecycle(getLifecycle());
                        List<CarouselItem> list = new ArrayList<>();

                        list.add( new CarouselItem( Requisitos));
                        list.add( new CarouselItem( FechaClas));
                        carousel.setData(list);
                    }
                }catch (Exception e){
                    Log.v("Error", "e: " + e);
                    System.out.println("Imagen NO Agregado... "+Requisitos);
                }

//--------------------Mostrar Tabla Carreras
                TablaSeccion.setup();
                TabHost.TabSpec tab1 = TablaSeccion.newTabSpec("Carreras");
                tab1.setIndicator("Carreras");
                tab1.setContent(R.id.Carreras);

                TabHost.TabSpec tab2 = TablaSeccion.newTabSpec("Maestrias");
                tab2.setIndicator("Maestrias");
                tab2.setContent(R.id.Maestrias);

                TabHost.TabSpec tab3 = TablaSeccion.newTabSpec("Doctorado");
                tab3.setIndicator("Doctorado");
                tab3.setContent(R.id.Doctorado);

                TablaSeccion.addTab(tab1);

                if (Maest != null){
                    if (Maest.equals("Si")){
                   TablaSeccion.addTab(tab2);
                    setUpRecyclerMaestrias(id, EstadoBD);
                    }
                }if (Doct != null){
                    if (Doct.equals("Si")){
                    TablaSeccion.addTab(tab3);
                    setUpRecyclerDoc(id, EstadoBD);
                    }
                }

                if (Fich != null){
                    Ficha.setVisibility(View.VISIBLE);
                    Ficha.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uri Link = Uri.parse(Fich);
                            Intent intent = new Intent(Intent.ACTION_VIEW,Link);
                            startActivity(intent);
                        }
                    });
                }



                //Publicidad
             /*   MobileAds.initialize(InfoUni.this, new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

                    }
                });

                AdRequest adRequest = new AdRequest.Builder().build();
                InterstitialAd.load(InfoUni.this, "ca-app-pub-4502497099059343/5099949323", adRequest, new InterstitialAdLoadCallback() {
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
                }); */

                Mapas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Mostrar Publicidad
                     /*   if (mInterstitialAd != null){
                            mInterstitialAd.show(InfoUni.this);
                        }else {
                          //  Toast.makeText(InfoUni.this, "No se Cargó el Anuncio", Toast.LENGTH_SHORT).show();
                        System.out.println("No se Cargó el Anuncio: "+mInterstitialAd);
                        } */

                        if (Latitud == null && Longitud == null || Latitud.equals("") && Longitud.equals("")){
                            VentanaMsg("No Cuenta con Ubicación");
                            System.out.println("Sin Ubicacion------");
                        }else {
                            fragment_maps fragmentMapas = new fragment_maps();
                            Bundle args = new Bundle();
                            args.putString("Latitud", Latitud); // Aquí se asigna el dato al Bundle
                            args.putString("Longitud", Longitud);
                            args.putString("ABC", ABc);
                            fragmentMapas.setArguments(args);
                            fragmentMapas.show(getSupportFragmentManager(), "open fragment");
                        }
                    }
                });

                Faceboock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Titulo = "Abrir Faceboock";
                        String URL = Faceboock.getText().toString();
                        VentanaMsgDialog(Titulo,URL);
                    }
                });

                Pagina.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Titulo = "Abrir Página";
                        VentanaMsgDialog(Titulo,PaginaUni);
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(InfoUni.this, "Error al Obtener los Datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpRecyclerView(String idUni,String idEstado) {
        Lista = findViewById(R.id.ListaCarreras);
        Lista.setLayoutManager(new GridLayoutManager(this,1));
        query = mFirestore.collection("/"+idEstado+"/"+idUni+"/Carreras");

        FirestoreRecyclerOptions<Carreras> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Carreras>()
                        .setQuery(query, Carreras.class).build();

        Adapter = new AdapterCarreras(firestoreRecyclerOptions,this);
        Adapter.notifyDataSetChanged();
        Lista.setAdapter(Adapter);
    }

    private void setUpRecyclerView2(String idUni,String idEstado) {
        Lista = findViewById(R.id.ListaCarreras);
        Lista.setLayoutManager(new GridLayoutManager(this,1));
        query = mFirestore.collection("/"+idEstado+"/"+idUni+"/Carreras");

        FirestoreRecyclerOptions<Carreras> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Carreras>()
                        .setQuery(query, Carreras.class).build();

        Adapter2 = new AdapterCarreras2(firestoreRecyclerOptions,this);
        Adapter2.notifyDataSetChanged();
        Lista.setAdapter(Adapter2);
    }

   /* private void setUpRecyclerPosgrado(String idUni,String idEstado) {
        System.out.println("ver Ids que recibe Posgrado Uni= "+idUni+" y estado = "+idEstado);
        ListaPosgra = findViewById(R.id.ListaPosgrados);
        ListaPosgra.setLayoutManager(new GridLayoutManager(this,1));
        query = mFirestore.collection(idEstado+"/"+idUni+"/Especializaciones");

        FirestoreRecyclerOptions<Carreras> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Carreras>().setQuery(query, Carreras.class).build();

        AdapterPos = new AdapterPosgrado(firestoreRecyclerOptions,this);
        AdapterPos.notifyDataSetChanged();
        ListaPosgra.setAdapter(AdapterPos);
        AdapterPos.startListening();

    } */
    private void setUpRecyclerMaestrias(String idUni,String idEstado) {
        ListaMaestr = findViewById(R.id.ListaMaestria);
        ListaMaestr.setLayoutManager(new GridLayoutManager(this,1));
        query = mFirestore.collection("/"+idEstado+"/"+idUni+"/Maestrias");

        FirestoreRecyclerOptions<Carreras> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Carreras>().setQuery(query, Carreras.class).build();

        AdapterMaes = new AdapterMaestria(firestoreRecyclerOptions,this);
        AdapterMaes.notifyDataSetChanged();
        ListaMaestr.setAdapter(AdapterMaes);
        AdapterMaes.startListening();

    }
    private void setUpRecyclerDoc(String idUni,String idEstado) {
        ListaDoctu = findViewById(R.id.ListaDoctorado);
        ListaDoctu.setLayoutManager(new GridLayoutManager(this,1));
        query = mFirestore.collection("/"+idEstado+"/"+idUni+"/Doctorado");

        FirestoreRecyclerOptions<Carreras> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Carreras>().setQuery(query, Carreras.class).build();

        AdapterDoc = new AdapterDoctorado(firestoreRecyclerOptions,this);
        AdapterDoc.notifyDataSetChanged();
        ListaDoctu.setAdapter(AdapterDoc);
        AdapterDoc.startListening();

    }

    @Override
    protected void onStart() {
        super.onStart();

        String Modo = preferences.getString("Click","");
        if (Modo.equals("Municipio")){
            Adapter.startListening();
        } else if (Modo.equals("Carreras")) {
            Adapter2.startListening();
        }
    }

    private void VentanaMsgDialog(String Titulo,String URL) {
        new SweetAlertDialog(InfoUni.this, SweetAlertDialog.NORMAL_TYPE).setTitleText("Aviso")
                .setContentText(Titulo)
                .setCancelText("No").setConfirmText("Si")
                .showCancelButton(true).setCancelClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    // startActivity(new Intent(RegistroLocal.this, HorarioNGC.class));


                }).setConfirmClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.dismissWithAnimation();
                    Uri Link = Uri.parse(URL);
                    Intent intent = new Intent(Intent.ACTION_VIEW,Link);
                    startActivity(intent);
                }).show();
    }

    private void VentanaMsg(String Titulo) {
        new SweetAlertDialog(InfoUni.this, SweetAlertDialog.NORMAL_TYPE).setTitleText("Aviso")
                .setContentText(Titulo)
                .setConfirmText("OK")
                .showCancelButton(true).setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                }).show();
    }
}