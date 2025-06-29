package com.softjk.unishare;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.softjk.unishare.Universidades.ListaUniversidades;

public class SpinnerB {

    public static void SelecciMuni(String Estado, android.widget.Spinner Municipio, Context context){

        switch (Estado) {

            case "Aguascalientes":
                String [] Opciones = {"Veracruz"};
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,Opciones);
                Municipio.setAdapter(adapter);
                break;

            case "Baja-California":
                String [] OpcionesBCalif = {"ds","ca"};
                ArrayAdapter<String> adapterBCalif = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesBCalif);
                Municipio.setAdapter(adapterBCalif);
                break;

            case "Baja-California-Sur":
                String [] OpcionesBCaliSur = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterBajCalSur = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesBCaliSur);
                Municipio.setAdapter(adapterBajCalSur);
                break;

            case "Campeche":
                String [] OpcionesCamp = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterCamp = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesCamp);
                Municipio.setAdapter(adapterCamp);
                break;

            case "CDMX":
                String [] OpcionesCDMX = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterCDMX = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesCDMX);
                Municipio.setAdapter(adapterCDMX);
                break;

            case "Chiapas":
                String [] OpcionesChia = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterChia = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesChia);
                Municipio.setAdapter(adapterChia);
                break;

            case "Chihuahua":
                String [] OpcionesChihu = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterChihu = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesChihu);
                Municipio.setAdapter(adapterChihu);
                break;

            case "Coahuila":
                String [] OpcionesCoa = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterCoa = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesCoa);
                Municipio.setAdapter(adapterCoa);
                break;

            case "Colima":
                String [] OpcionesColi = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterColi = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesColi);
                Municipio.setAdapter(adapterColi);
                break;

            case "Durango":
                String [] OpcionesDur = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterDur = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesDur);
                Municipio.setAdapter(adapterDur);
                break;

            case "EstadoMx":
                String [] OpcionesEsMx = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterEsMx = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesEsMx);
                Municipio.setAdapter(adapterEsMx);
                break;

            case "Guanajuato":
                String [] OpcionesGua = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterGua = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesGua);
                Municipio.setAdapter(adapterGua);
                break;

            case "Guerrero":
                String [] OpcionesGue = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterGue = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesGue);
                Municipio.setAdapter(adapterGue);
                break;

            case "Jalisco":
                String [] OpcionesJa = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterJa = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesJa);
                Municipio.setAdapter(adapterJa);
                break;

            case "Michoacan":
                String [] OpcionesMic = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterMic = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesMic);
                Municipio.setAdapter(adapterMic);
                break;

            case "Morelos":
                String [] OpcionesMor = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterMor = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesMor);
                Municipio.setAdapter(adapterMor);
                break;

            case "Nayarit":
                String [] OpcionesNay = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterNay = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesNay);
                Municipio.setAdapter(adapterNay);
                break;

            case "Puebla":
                String [] OpcionesPuebla = {"Seleccione un Municipio","Acatlán","Acatzingo","Ajalpan","Amozoc","Atlixco","Chalchicomula De S.","Chiautla","Chignahuapan",
                        "Chilchotla","Cuatlancingo","Cuetzalan","Huauchinango","Huehuetla","Huejotzingo","Izúcar Matamoros","Juan Bonilla","Libres","Nicolás Bravo",
                        "CD. Puebla","S. N. De Los Ranchos","San Andrés Cholula","San M. Texmelucan","San Pedro Cholula","Tecamachalco","Tecomatlán","Tehuacán","Tepeaca","Tepexi De Rodríguez","Tlatlauquitepec",
                        "Tetela De Ocampo","Teziutlán", "Venust. Carranza","Xicotepec De Juárez","Zacapoaxtla","Zacatlán","Zaragoza","Zautla","Otro Municipio"};

                ArrayAdapter<String> adapterMuni = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesPuebla);
                Municipio.setAdapter(adapterMuni);
                break;

            case "Hidalgo":
                String [] OpcionesHidal = {"Seleccione un Municipio","Actopan","Apan","Huejutla","Huichapan","Ixmiquilpan","La loma","Mineral de la Reforma","Mixquiahuala",
                        "P. Obregon","Pachuca","Sahagún","Tepeji del Río de Ocampo","Tezontepec","Tizayuca","Tlahuelilpan","Tlaxiaca","Tolcayuca","Tula de Allende",
                        "Tulancingo","Zacualtipán","Zempoala","Zimapan","Otro Municipio"};

                ArrayAdapter<String> adapterHidal = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesHidal);
                Municipio.setAdapter(adapterHidal);
                break;

            case "Oaxaca":
                String [] OpcionesOax = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterOax = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesOax);
                Municipio.setAdapter(adapterOax);
                break;

            case "Veracruz":
                String [] OpcionesVer = {"Seleccione un Municipio","Acayucan","Álamo Temapache","Alvarado","Boca del Río","Camerino Mendoza","Chicontepec","Coatzacoalcos","Córdoba",
                        "Cerro Azul","Cosamaloapan","Coscomatepec","Cuitláhuac","Espinal","Huayacocotla","Huatusco","Ixhuatlán de Madero","Jesús Carranza","J. Rodríguez Clara",
                        "La Antigua","Las Choapas","Mart. De La Torre","Minatitlán","Mecayapan","Medellín","Misantla","Naranjos","Nogales","Pánuco","Orizaba",
                        "Papantla","Perote", "Poza Rica","Río Blanco","San Andrés Tuxtla","Tantoyuca","Tequila","Tierra Blanca","Tuxpan",
                        "Tres Valles","Veracruz","Úrsulo Galván","Xalapa","Zongolica","Otro Municipio"};

                ArrayAdapter<String> adapterVer = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesVer);
                Municipio.setAdapter(adapterVer);
                break;

            case "Nuevo-Leon":
                String [] OpcionesLeo = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterLeo = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesLeo);
                Municipio.setAdapter(adapterLeo);
                break;

            case "Queretaro":
                String [] OpcionesQuer = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterQuer = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesQuer);
                Municipio.setAdapter(adapterQuer);
                break;

            case "Quintana-Roo":
                String [] OpcionesQui = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterQui = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesQui);
                Municipio.setAdapter(adapterQui);
                break;

            case "San-Luis-Potosi":
                String [] OpcionesPoto = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterPoto = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesPoto);
                Municipio.setAdapter(adapterPoto);
                break;

            case "Sinaloa":
                String [] OpcionesSin = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterSin = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesSin);
                Municipio.setAdapter(adapterSin);
                break;

            case "Sonora":
                String [] OpcionesSon = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterSon = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesSon);
                Municipio.setAdapter(adapterSon);
                break;

            case "Tabasco":
                String [] OpcionesTab = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterTab = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesTab);
                Municipio.setAdapter(adapterTab);
                break;

            case "Tamaulipas":
                String [] OpcionesTam = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterTam = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesTam);
                Municipio.setAdapter(adapterTam);
                break;

            case "Tlaxcala":
                String [] OpcionesTax = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterTax = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesTax);
                Municipio.setAdapter(adapterTax);
                break;

            case "Yucatan":
                String [] OpcionesYu = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterYu = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesYu);
                Municipio.setAdapter(adapterYu);
                break;

            case "Zacatecas":
                String [] OpcionesZa = {"Veracruz","Chicontepec"};
                ArrayAdapter<String> adapterZa = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,OpcionesZa);
                Municipio.setAdapter(adapterZa);
                break;

            default:
                Toast.makeText(context, "Por Favor Selecciona un Estado", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    public static void SpinnerCarreras(Spinner Estado, Spinner sp, Context context, SearchView Buscar){
        if (Estado.getSelectedItem().equals("Seleccionar Estado")){
            Toast.makeText(context, "Por Favor Seleccione un Estado!!", Toast.LENGTH_SHORT).show();
        }else {
        String [] Opciones = {"Seleccione una Categoria","Arte","Ciencias","Comunicación","Derecho","Educación","Finanzas","Humanidades",
        "Ingeniería","Salud","Militar","Servicios","Tecnología","Buscar Carrera"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.spinner_item_estilo,Opciones);
        sp.setAdapter(adapter);

        }
    }
}
