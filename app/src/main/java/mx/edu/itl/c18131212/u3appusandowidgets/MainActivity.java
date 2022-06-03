/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                                  Clase Principal
:*
:*  Archivo     : MainActivity.java
:*  Autor       : Juan Manuel Alfaro (18131212)
:*  Fecha       : 19/abril/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripcion : Esta clase lanza el activity_main, cuando se da click en algun boton
:*                se lanza el activity correspondiente
:*
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131212.u3appusandowidgets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import mx.edu.itl.c18131212.u3appusandowidgets.utilidades.utilidades;

public class MainActivity extends AppCompatActivity {

    String id;
    TextView tvUsuario;
    public ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().setTitle("futbolmania⚽");//ocultar Action Bar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFCFCDCD")));
        getSupportActionBar().setDisplayOptions (ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        tvUsuario = (TextView) findViewById(R.id.tvUsuario);

        //RECUPERAR ID DE USUARIO
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        id = b.getString("ID");

        conn = new ConexionSQLiteHelper(this, "fm.db", null, 1);
        ConsultarDB(id.toString());

        Toast.makeText(getApplicationContext(), "HAS INICIADO SESION!", Toast.LENGTH_SHORT).show();

        //tvUsuario.setText(id + "");
    }

    public void camisetasClik(View v){
        // Preparamos la invocacion al BlancoActivity
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("ID_USUARIO", id);
        startActivity(intent);
    }

    public void AcercaDe(View v){
        // Preparamos la invocacion al BlancoActivity
        Intent intent = new Intent(this, AcercaDe.class);
        startActivity(intent);
    }

    public  void contacto(View v){
        // Preparamos la invocacion al BlancoActivity
        Intent intent = new Intent(this, Contacto.class);
        startActivity(intent);
    }

    public  void Pedidos(View v){
        // Preparamos la invocacion al BlancoActivity
        Intent intent = new Intent(this, Pedidos.class);
        intent.putExtra("ID_USUARIO", id);
        intent.putExtra("NOMBRE_USUARIO", tvUsuario.getText().toString());
        startActivity(intent);
        //finish();
    }

    public void ConsultarDB(String id){
        SQLiteDatabase db = conn.getWritableDatabase ();
        if(db != null){
            String[] parametros = {id};
            String[] campos = {utilidades.CAMPO_NOMBRE_USUARIO};

            try {
                Cursor cursor = db.query(utilidades.TABLA_USUARIO, campos, utilidades.CAMPO_ID+"=?",parametros,null,null,null);
                cursor.moveToFirst();
                tvUsuario.setText(cursor.getString(0));
                cursor.close();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "ERROR AQUI ENTRA!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId()){
           case R.id.item1:
               AlertDialog.Builder builder = new AlertDialog.Builder(this);
               builder.setTitle("futbolmaniaApp V.1.0")
                       .setMessage("Desarrollada Por:\n\n" +
                                    "Juan Manuel Alfaro Aguilera (18131212)\n\n"+
                                    "TecNM campus La Laguna")
                       .setIcon(R.drawable.itl)
                       .create()
                       .show();
               return true;
           case R.id.item2:
               //finish();
               Toast.makeText(getApplicationContext(), "HAS CERRADO SESION!", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(this, Login.class);
               startActivity(intent);
               finish();
               return true;
           default:
               return super.onOptionsItemSelected(item);
       }
    }

    @Override
    public void onBackPressed() {

    }
}