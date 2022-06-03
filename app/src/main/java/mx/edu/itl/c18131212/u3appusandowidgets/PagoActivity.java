/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                       Clase para el pago de las camisetas
:*
:*  Archivo     : PagoActivity.java
:*  Autor       : Juan Manuel Alfaro (18131212)
:*  Fecha       : 19/abril/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripcion : Esta clase obtiene los datos de la compra, y si se completaron los datos de envio
:*                se realiza la compra
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
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import mx.edu.itl.c18131212.u3appusandowidgets.utilidades.utilidades;

public class PagoActivity extends AppCompatActivity {

    //OBJETO CONEXION
    public ConexionSQLiteHelper conn;

    String idUsuario;

    int tot;
    int pre;
    String Talla;
    String cant;
    String descripcion;

    EditText edtNombre;
    EditText edtCorreo;
    EditText edtCP;
    EditText edtDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//ocultar Action Bar
        setContentView(R.layout.activity_pago);

        //CREAR BASE DE DATOS
        conn = new ConexionSQLiteHelper(this, "fm.db", null, 1);

        TextView desc = (TextView)findViewById(R.id.txtDesc);
        TextView talla = (TextView)findViewById(R.id.txtTalla);
        TextView cantidad = (TextView)findViewById(R.id.txtCantidad);
        TextView precio = (TextView)findViewById(R.id.txtPrecio);
        TextView total = (TextView)findViewById(R.id.txtTotal);

        edtNombre = (EditText)findViewById(R.id.edtNombre);
        edtCorreo = (EditText)findViewById(R.id.edtCorreo);
        edtCP = (EditText)findViewById(R.id.edtCP);
        edtDireccion = (EditText)findViewById(R.id.edtDireccion);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        pre = b.getInt("PRECIO");
        cant = getIntent().getStringExtra("CANTIDAD");
        if(cant.equals(""))
            cant = "1";
        Talla = getIntent().getStringExtra("TALLA");
        idUsuario = getIntent().getStringExtra("ID_USUARIO");
        descripcion = getIntent().getStringExtra("DESC");

        desc.setText(descripcion);
        talla.setText(Talla);
        cantidad.setText(cant);
        precio.setText("$" +pre +".00");
        tot = pre * Integer.parseInt(cant);
        total.setText("$"+tot+"");

    }

    public void registrarPedido(int idUsuario, String desc, String cant, String tot, String direccion){
        SQLiteDatabase db = conn.getWritableDatabase ();
        if(db != null){
            ContentValues values = new ContentValues();
            values.put(utilidades.CAMPO_IDU, idUsuario);
            values.put(utilidades.CAMPO_DESC, desc);
            values.put(utilidades.CAMPO_CANTIDAD, cant);
            values.put(utilidades.CAMPO_TOTAL, tot);
            values.put(utilidades.CAMPO_DIRECCION, direccion);
            try {
                Long idResultante = db.insert(utilidades.TABLA_PEDIDO, utilidades.CAMPO_IDP, values);
                db.close();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "ERROR!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "ERROR!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void Pago(View v){
        String direccion = edtDireccion.getText().toString();
        String nombre = edtNombre.getText().toString();
        if(nombre.equals("") || edtCorreo.getText().toString().equals("")
                || edtCP.getText().toString().equals("") || direccion.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Datos Incompletos! ")
                    .setMessage("No olvides completar los datos de envio!\n\n")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Intent main = new Intent(PagoActivity.this,MainActivity.class);
                            //startActivity(main);
                            //finish();
                        }
                    })
                    .setIcon(R.drawable.error)
                    .create()
                    .show();
        }
        else {
            //REALIZAR PEDIDO
            String total = String.valueOf(tot);
            int id  = Integer.parseInt(idUsuario);
            registrarPedido(id,descripcion, cant, total, direccion);

            //PAGO CON OXXO
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Gracias por tu compra! " + nombre)
                    .setMessage("Desposita en tu tienda OXXO mas cercana:\n\n" +
                            "Cantidad: $ " + tot + "\n\n" +
                            "Referencia: 9345-1032-9281-82\n\n" +
                            "Tu pedido llegara de 3 a 10 dias habiles\n\n" +
                            "Pagar detro de las proximas 48 hrs.\n\n" +
                            "Si el pago no se realiza el pedido sera cancelado")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent main = new Intent(PagoActivity.this, Pedidos.class);
                            startActivity(main);
                            //finish();
                        }
                    })
                    .setIcon(R.drawable.oxxo)
                    .create()
                    .show();
        }
    }
}