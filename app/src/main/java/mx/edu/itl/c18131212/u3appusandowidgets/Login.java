/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                              clase de Login
:*
:*  Archivo     : Login.java
:*  Autor       : Juan Manuel Alfaro (18131212)
:*  Fecha       : 01/Junio/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripcion : Esta clase permite refistrar usuarios e iniciar sesion
:*
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131212.u3appusandowidgets;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import androidx.annotation.Nullable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import mx.edu.itl.c18131212.u3appusandowidgets.utilidades.utilidades;
import mx.edu.itl.c18131212.u3appusandowidgets.ConexionSQLiteHelper;

public class Login extends AppCompatActivity {

    //OBJETO CONEXION
    public ConexionSQLiteHelper conn;

    //CAMPOS DE TEXTO
    EditText campoUsuario, campoPassword, campoPassConf; //registro
    EditText campoUsuario2, campoPassword2; //login

    //VARIBLES PARA EL RESULTADO DE CONSULTA
    public String idResult;
    public String passResult;

    //VARIABLE ID PARA ENVIAR A MAINACTIVITY
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();//ocultar Action Bar

        //CREAR BASE DE DATOS
        conn = new ConexionSQLiteHelper(this, "fm.db", null, 1);

        //CAMPOS DE REGISTRO
        campoUsuario = (EditText) findViewById(R.id.edtUsuario);
        campoPassword = (EditText) findViewById(R.id.edtPass);
        campoPassConf = (EditText) findViewById(R.id.edtPassConf);

        //CAMPOS DE LOGIN
        campoUsuario2 = (EditText) findViewById(R.id.edtUsuario2);
        campoPassword2 = (EditText) findViewById(R.id.edtPass2);
    }

    public  void main(View v){
        // Preparamos la invocacion al BlancoActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //---------------------------------------------------------------------------------------------
    //METODO CUANDO EL USUARIO HACE CLICK EN CREAR CUENTA
    public void registro(View v){
        String usuario = campoUsuario.getText().toString();
        String password = campoPassword.getText().toString();
        String passwordConf = campoPassConf.getText().toString();
        if(usuario.equals("")||password.equals("")||passwordConf.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Datos Incompletos! ")
                    .setMessage("No olvides completar todos los datos!\n\n")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) { }
                    })
                    .setIcon(R.drawable.error)
                    .create()
                    .show();
        }
        else if(!password.equals(passwordConf)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Datos Incorrectos! ")
                    .setMessage("Las Contraseñas no coinciden!\n\n")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) { }
                    })
                    .setIcon(R.drawable.error)
                    .create()
                    .show();
        }
        else {
            registrarBD(usuario, password, v);

        }
    }
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    //METODO CUANDO EL USUARIO HACE CLICK EN INICIAR SESION
    public void iniciarSesion(View v){
        String user = campoUsuario2.getText().toString();
        String pass = campoPassword2.getText().toString();
        if(user.equals("") || pass.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Datos Incompletos! ")
                    .setMessage("No olvides completar todos los datos!\n\n")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) { }
                    })
                    .setIcon(R.drawable.error)
                    .create()
                    .show();
        }
        else{
            consultarBD(user, pass, v);
        }
    }
    //----------------------------------------------------------------------------------------------

    //---------------------------------------------------------------------------------------------
    //METODO PARA HACER UN REGISTRO EN LA BASE DE DATOS
    public void registrarBD(String usuario, String password,View view){
        SQLiteDatabase db = conn.getWritableDatabase ();
        if(db != null){
            ContentValues values = new ContentValues();
            values.put(utilidades.CAMPO_NOMBRE_USUARIO, usuario);
            values.put(utilidades.CAMPO_PASSWORD, password);
            try {
                Long idResultante = db.insert(utilidades.TABLA_USUARIO, utilidades.CAMPO_ID, values);
                db.close();
                if(idResultante != 0){
                    Intent idIntent = new Intent(view.getContext(), MainActivity.class);
                    Long id = idResultante;
                    ID = id.toString();
                    idIntent.putExtra("ID", ID);
                    Toast.makeText(getApplicationContext(), "USUARIO REGISTRADO!", Toast.LENGTH_SHORT).show();
                    startActivity(idIntent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "USUARIO YA REGISTRADO!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "USUARIO YA REGISTRADO!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "ERROR!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    //----------------------------------------------------------------------------------------------

    public void consultarBD(String nombre, String password, View view){
        SQLiteDatabase db = conn.getWritableDatabase ();
        if(db != null){
            String[] parametros = {nombre};
            String[] campos = {utilidades.CAMPO_ID, utilidades.CAMPO_NOMBRE_USUARIO, utilidades.CAMPO_PASSWORD};
            try {
                Cursor cursor = db.query(utilidades.TABLA_USUARIO, campos, utilidades.CAMPO_NOMBRE_USUARIO+"=?",parametros,null,null,null);
                cursor.moveToFirst();
                ID = cursor.getString(0);
                passResult = cursor.getString(2);
                cursor.close();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "EL USUARIO NO EXISTE!", Toast.LENGTH_SHORT).show();
            }

            if(passResult.equals(password)){
                Intent idIntent = new Intent(view.getContext(), MainActivity.class);
                idIntent.putExtra("ID", ID);
                startActivity(idIntent);
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error! ")
                        .setMessage("Los datos no coinciden!\n\n")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { }
                        })
                        .setIcon(R.drawable.error)
                        .create()
                        .show();
            }
        }
    }
}