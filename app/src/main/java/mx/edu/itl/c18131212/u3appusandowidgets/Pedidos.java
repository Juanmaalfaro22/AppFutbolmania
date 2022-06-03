/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                              clase de pedidos
:*
:*  Archivo     : Pedidos.java
:*  Autor       : Juan Manuel Alfaro (18131212)
:*  Fecha       : 01/Junio/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripcion : Esta clase realiza y cancela pedidos
:*
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131212.u3appusandowidgets;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import mx.edu.itl.c18131212.u3appusandowidgets.entidades.Pedido;
import mx.edu.itl.c18131212.u3appusandowidgets.utilidades.utilidades;

import java.util.ArrayList;

public class Pedidos extends AppCompatActivity {

    String idUsuario;
    String nombreUsuario;
    TextView tvUsuario;

    TextView tvNo;
    TextView tvDesc;
    TextView tvCant;
    TextView tvTot;
    TextView tvDireccion;

    Spinner comboPedidos;
    ArrayList<String> listaPedidos;
    ArrayList<Pedido> pedidos;

    public ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        getSupportActionBar().hide();//ocultar Action Bar

        tvUsuario = (TextView) findViewById(R.id.tvUsuario);
        tvNo = (TextView) findViewById(R.id.tv1);
        tvDesc = (TextView) findViewById(R.id.tv2);
        tvCant = (TextView) findViewById(R.id.tv3);
        tvTot = (TextView) findViewById(R.id.tv4);
        tvDireccion = (TextView) findViewById(R.id.tv5);
        //SPINNER
        comboPedidos = (Spinner) findViewById(R.id.spinner);

        //RECUPERAR ID DE USUARIO
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        idUsuario = b.getString("ID_USUARIO");
        nombreUsuario = b.getString("NOMBRE_USUARIO");
        tvUsuario.setText(nombreUsuario);

        //CONEXION CON LA BASE DE DATOS
        conn = new ConexionSQLiteHelper(this, "fm.db", null, 1);

        //CONSULTAR PEDIDOS
        consultarPedidos(idUsuario);

        //ADAPTER SPINNER
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaPedidos);
        comboPedidos.setAdapter(adapter);

        comboPedidos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != 0 ){
                    tvNo.setText(pedidos.get(i-1).getId()+"");
                    tvDesc.setText(pedidos.get(i-1).getDescripcion()+"");
                    tvCant.setText(pedidos.get(i-1).getCantidad()+"");
                    tvTot.setText(pedidos.get(i-1).getTotal()+"");
                    tvDireccion.setText(pedidos.get(i-1).getDireccion() + "");
                }else {
                    tvNo.setText("");
                    tvDesc.setText("");
                    tvCant.setText("");
                    tvTot.setText("");
                    tvDireccion.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void consultarPedidos(String idUsuario){
        SQLiteDatabase db = conn.getReadableDatabase();

        Pedido pedido = null;
        pedidos = new ArrayList<Pedido>();

        //select * from pedido where id = idUsuario
        Cursor cursor = db.rawQuery("SELECT * FROM " + utilidades.TABLA_PEDIDO + " WHERE "+ utilidades.CAMPO_IDU + " = " + idUsuario, null);

        while(cursor.moveToNext()){
            pedido = new Pedido();
            pedido.setId(cursor.getInt(0));
            pedido.setDescripcion(cursor.getString(2));
            pedido.setCantidad(cursor.getString(3));
            pedido.setTotal(cursor.getString(4));
            pedido.setDireccion(cursor.getString(5));

            pedidos.add(pedido);
        }

        obtenerLista();
    }

    private void obtenerLista(){
        listaPedidos = new ArrayList<String>();
        listaPedidos.add("Pedidos");

        for(int i=0; i<pedidos.size();i++){
            listaPedidos.add("Pedido " + pedidos.get(i).getId());
        }
    }

    private void eliminarPedido (int id){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {id+""};
        db.delete(utilidades.TABLA_PEDIDO,utilidades.CAMPO_IDP+"=?", parametros);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("El pedido ha sido cancelado!")
                .setMessage("Se te hara un reembolso dentro de los proximos 5 dias habiles")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent main = new Intent(Pedidos.this, MainActivity.class);
                        startActivity(main);
                        finish();
                    }
                })
                .setIcon(R.drawable.info)
                .create()
                .show();
        db.close();
    }

    public void cancelar(View v){
        eliminarPedido(Integer.parseInt(tvNo.getText().toString()));
    }
}