/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                              Clase para el GridView
:*
:*  Archivo     : ViewActivity.java
:*  Autor       : Juan Manuel Alfaro (18131212)
:*  Fecha       : 19/abril/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripcion : Esta clase contiene los datos de los equipos de las diferentes ligas disponibles
:*                dependiendo de la liga seleccionada se utiliza el metodo setAdapter para insertar
:*                los elementos en el gridView, cuando se da click en algun equipo, se envia la
:*                informacion a ComprarActivity
:*
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131212.u3appusandowidgets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {

    String idUsuario;

    GridView grid;
    int liga;

    String [] ligaMX = {"Santos","America","Tigres","Chivas","Puebla","Cruz Azul"};
    String [] LaLiga = {"Barcelona","Real Madrid","Atletico","Sevilla","Real Betis","Valencia"};
    String [] Premier = {"Arsenal","Chelsea","Manchester City","Manchester United","Liverpool","Tottenham"};
    String [] serieA = {"Juventus","Inter","Milan","Roma","Lazio","Napoli"};
    String [] otras = {"Bayern Munich","Boca Juniors","Borussia Dortmund","Ajax","PSG","Porto"};
    String [] selecciones = {"Alemania","Brasil","Mexico","Argentina","Francia","Inglaterra"};

    int [] ImgLigaMX = {R.drawable.santos, R.drawable.america, R.drawable.tigres,R.drawable.chivas, R.drawable.puebla, R.drawable.cruzazul};
    int [] ImgLaLiga = {R.drawable.barcelona, R.drawable.rm,R.drawable.atletico,R.drawable.sevilla,R.drawable.betis,R.drawable.valencia};
    int [] ImgPremier = {R.drawable.arsenal,R.drawable.chelsea,R.drawable.city,R.drawable.manchester,R.drawable.liberpool,R.drawable.tottenham};
    int [] ImgSeriaA = {R.drawable.juventus,R.drawable.inter,R.drawable.milan,R.drawable.roma,R.drawable.lazio,R.drawable.napoli};
    int [] ImgOtras = {R.drawable.bayern,R.drawable.boca,R.drawable.bvb,R.drawable.ajax,R.drawable.psg,R.drawable.porto};
    int [] ImgSelecciones = {R.drawable.alemania,R.drawable.brasil,R.drawable.mexico,R.drawable.argentina,R.drawable.francia,R.drawable.inglaterra};

    int [] preciosLigaMX = {1200,1350,1200,1350,950,1050};
    int [] preciosLaLiga = {1650,1650,1500,1300,1200,1300};
    int [] preciosPremier = {1650,1650,1650,1650,1650,1650};
    int [] preciosSerieA = {1650,1450,1450,1300,1150,3400};
    int [] preciosOtras = {1650,1150,1200,1200,1780,1200};
    int [] preciosSelecciones = {1350,1350,1350,1350,1350,1350};

    int [] jerseyLigaMX = {R.drawable.jerseysantos, R.drawable.jerseyamerica, R.drawable.jerseytigres,R.drawable.jerseychivas, R.drawable.jerseypuebla, R.drawable.jerseycruzazul};
    int [] jerseyLaLiga = {R.drawable.jerseybarcelona, R.drawable.jerseyrm,R.drawable.jerseyatletico,R.drawable.jerseysevilla,R.drawable.jerseybetis,R.drawable.jerseyvalencia};
    int [] jerseyPremier = {R.drawable.jerseyarsenal,R.drawable.jerseychelsea,R.drawable.jerseycity,R.drawable.jerseymanchester,R.drawable.jerseyliverpool,R.drawable.jerseytottenham};
    int [] jerseySeriaA = {R.drawable.jerseyjuventus,R.drawable.jerseyinter,R.drawable.jerseymilan,R.drawable.jerseyroma,R.drawable.jerseylazio,R.drawable.jerseynapoli};
    int [] jerseyOtras = {R.drawable.jerseybayern,R.drawable.jerseyboca,R.drawable.jerseybvb,R.drawable.jerseyajax,R.drawable.jerseypsg,R.drawable.jerseyporto};
    int [] jerseySelecciones = {R.drawable.jerseyalemania,R.drawable.jerseybrasil,R.drawable.jerseymexico,R.drawable.jerseyargentina,R.drawable.jerseyfrancia,R.drawable.jerseyinglaterra};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//ocultar Action Bar
        setContentView(R.layout.activity_view);

        //recibe informacion del list view
        TextView txt = (TextView)findViewById(R.id.TVejemplo);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String id = b.getString("LIGA");
        idUsuario = b.getString("ID_USUARIO");
        txt.setText(id);
        if(id.equals("Selecciones")) {
            //Hace la magia
            liga = 0;
            grid = (GridView) findViewById(R.id.GVgrid);
            grid.setAdapter(new AdaptadorGrid(this, selecciones, ImgSelecciones));
        }
        else if(id.equals("Liga MX")) {
            //Hace la magia
            liga = 1;
            grid = (GridView) findViewById(R.id.GVgrid);
            grid.setAdapter(new AdaptadorGrid(this, ligaMX, ImgLigaMX));
        }
        else if(id.equals("Premier League")) {
            //Hace la magia
            liga = 2;
            grid = (GridView) findViewById(R.id.GVgrid);
            grid.setAdapter(new AdaptadorGrid(this, Premier, ImgPremier));
        }
        else if(id.equals("La Liga")) {
            //Hace la magia
            liga = 3;
            grid = (GridView) findViewById(R.id.GVgrid);
            grid.setAdapter(new AdaptadorGrid(this, LaLiga, ImgLaLiga));
        }
        else if(id.equals("Serie A")) {
            //Hace la magia
            liga = 4;
            grid = (GridView) findViewById(R.id.GVgrid);
            grid.setAdapter(new AdaptadorGrid(this, serieA, ImgSeriaA));
        }
        else if(id.equals("Otras")) {
            //Hace la magia
            liga = 5;
            grid = (GridView) findViewById(R.id.GVgrid);
            grid.setAdapter(new AdaptadorGrid(this, otras, ImgOtras));
        }

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent comprar = new Intent(view.getContext(), ComprarActivity.class);
                if(liga == 0){
                    comprar.putExtra("EQUIPO", selecciones[position]);
                    comprar.putExtra("PRECIO", preciosSelecciones[position]);
                    comprar.putExtra("JERSEY", jerseySelecciones[position]);
                    comprar.putExtra("ID_USUARIO", idUsuario);
                    startActivity(comprar);
                }else if(liga == 1){
                    comprar.putExtra("EQUIPO", ligaMX[position]);
                    comprar.putExtra("PRECIO", preciosLigaMX[position]);
                    comprar.putExtra("JERSEY", jerseyLigaMX[position]);
                    comprar.putExtra("ID_USUARIO", idUsuario);
                    startActivity(comprar);
                }else if(liga == 2){
                    comprar.putExtra("EQUIPO", Premier[position]);
                    comprar.putExtra("PRECIO", preciosPremier[position]);
                    comprar.putExtra("JERSEY", jerseyPremier[position]);
                    comprar.putExtra("ID_USUARIO", idUsuario);
                    startActivity(comprar);
                }else if(liga == 3){
                    comprar.putExtra("EQUIPO", LaLiga[position]);
                    comprar.putExtra("PRECIO", preciosLaLiga[position]);
                    comprar.putExtra("JERSEY", jerseyLaLiga[position]);
                    comprar.putExtra("ID_USUARIO", idUsuario);
                    startActivity(comprar);
                }else if(liga == 4){
                    comprar.putExtra("EQUIPO", serieA[position]);
                    comprar.putExtra("PRECIO", preciosSerieA[position]);
                    comprar.putExtra("JERSEY", jerseySeriaA[position]);
                    comprar.putExtra("ID_USUARIO", idUsuario);
                    startActivity(comprar);
                }else if(liga == 5){
                    comprar.putExtra("EQUIPO", otras[position]);
                    comprar.putExtra("PRECIO", preciosOtras[position]);
                    comprar.putExtra("JERSEY", jerseyOtras[position]);
                    comprar.putExtra("ID_USUARIO", idUsuario);
                    startActivity(comprar);
                }
            }
        });
    }
}