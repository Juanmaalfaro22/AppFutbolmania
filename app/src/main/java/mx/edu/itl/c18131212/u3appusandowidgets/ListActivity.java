/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                              Clase para el ListView
:*
:*  Archivo     : ListActivity.java
:*  Autor       : Juan Manuel Alfaro (18131212)
:*  Fecha       : 19/abril/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripcion : Esta clase contiene los datos de los equipos de las diferentes ligas
:*                con el metodo setAdapter se insertan todos los elementos de la lista
:*                cuando se selecciona un equipo se envian sus datos a ComprarActivity
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
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {

    String idUsuario;

    ListView lista;

    String[][] datos = {
            {"Selecciones", "Selecciones Nacionales"},
            {"Liga MX","México"},
            {"Premier League","Inglaterra"},
            {"La Liga","España"},
            {"Serie A", "Italia"},
            {"Otras", "Otras Ligas"}
    };

    int [] datoImg = {R.drawable.word, R.drawable.mexico2,R.drawable.inglaterra2,R.drawable.espana2,R.drawable.italia,R.drawable.otros};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//ocultar Action Bar
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        idUsuario = b.getString("ID_USUARIO");

        lista = (ListView)findViewById(R.id.lvLista);
        lista.setAdapter(new Adaptador(this,datos, datoImg));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent grid = new Intent(view.getContext(), ViewActivity.class);
                grid.putExtra("LIGA", datos[position][0]);
                grid.putExtra("ID_USUARIO", idUsuario);
                startActivity(grid);
                //finish();
            }
        });
    }
}