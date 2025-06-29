package com.softjk.unishare;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImgEstados {

    public static void SwiithImg(String Estado, ImageView img, Context activityIMG){
        switch (Estado){
            case "Aguascalientes":
                Glide.with(activityIMG)
                        .load("https://www.google.com/url?sa=i&url=https%3A%2F%2Fparatodomexico.com%2Festados-de-mexico%2Festado-aguascalientes%2Fescudo-y-lema-aguascalientes.html&psig=AOvVaw3vTaBz7t4isopYwXvKYUMc&ust=1726000069777000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCLitkLrZtogDFQAAAAAdAAAAABAo")
                        .into(img);
                break;
            case "Baja-California":
                Glide.with(activityIMG)
                        .load("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/Escudo_Baja_California.png/417px-Escudo_Baja_California.png")
                        .into(img);
                break;
            case "Baja-California-Sur":
                Glide.with(activityIMG)
                        .load("https://upload.wikimedia.org/wikipedia/commons/2/2a/Coat_of_Arms_of_Baja_California_Sur.svg")
                        .into(img);
                break;
            case "Campeche":
                Glide.with(activityIMG)
                        .load("https://upload.wikimedia.org/wikipedia/commons/0/07/Escudo_Campeche.png")
                        .into(img);
                break;
            case "CDMX":
                Glide.with(activityIMG)
                        .load("https://upload.wikimedia.org/wikipedia/commons/6/60/Escudo_Veracruz.png")
                        .into(img);
                break;
            case "Chiapas":
                Glide.with(activityIMG)
                        .load("https://w1.pngwing.com/pngs/870/695/png-transparent-yellow-flower-escudo-de-chiapas-politician-governor-mexico-food-area-recreation-thumbnail.png")
                        .into(img);
                break;
            case "Chihuahua":
                Glide.with(activityIMG)
                        .load("https://chihuahua.gob.mx/sites/default/files/pictures/350px-coat_of_arms_of_chihuahua.svg.png")
                        .into(img);
                break;
            case "Coahuila":
                Glide.with(activityIMG)
                        .load("https://e7.pngegg.com/pngimages/340/638/png-clipart-saltillo-zaragoza-municipality-coahuila-graphy-escudo-de-coahuila-coahuila-y-tejas-saltillo-zaragoza-municipality-coahuila-thumbnail.png")
                        .into(img);
                break;
            case "Colima":
                Glide.with(activityIMG)
                        .load("https://upload.wikimedia.org/wikipedia/commons/0/07/Escudo_de_Colima_Version_2.png")
                        .into(img);
                break;
            case "Durango":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "EstadoMx":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "Guanajuato":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "Guerrero":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "Hidalgo":
                Glide.with(activityIMG)
                        .load("https://images.jifo.co/26591617_1524939310260.gif")
                        .into(img);
                break;
            case "Jalisco":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "Michoacan":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "Morelos":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "Nayarit":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "Nuevo-Leon":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "Oaxaca":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "Puebla":
                Glide.with(activityIMG)
                        .load("https://th.bing.com/th/id/R.6b70d10c9fa88168c85c529929d8d797?rik=6VqKR8%2bprdpCZw&riu=http%3a%2f%2frumboalaigualdad.inmujeres.gob.mx%2fimages%2fassets%2fescudos%2fpue.png&ehk=5%2fgbmmuw9lE%2fnD5PIE%2fdawYHOYxP8F9MApyN6Gi%2fHLs%3d&risl=&pid=ImgRaw&r=0")
                        .into(img);
                break;
            case "Queretaro":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "Quintana-Roo":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "San-Luis-Potosi":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "Sinaloa":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "Sonora":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "Tabasco":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "Tamaulipas":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "Taxcala":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "Veracruz":
                String URL = "https://upload.wikimedia.org/wikipedia/commons/6/60/Escudo_Veracruz.png";
                IMG(img,activityIMG,URL);
                break;
            case "Yucatan":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;
            case "Zacatecas":
                Glide.with(activityIMG)
                        .load("https")
                        .into(img);
                break;

        }
    }

    public static void IMG(ImageView img, Context activityIMG, String URL){
        Glide.with(activityIMG)
                .load(URL)
                .into(img);
    }
}
