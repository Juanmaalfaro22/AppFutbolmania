/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                         Clase para la compra de camisetas
:*
:*  Archivo     : Comprar.java
:*  Autor       : Juan Manuel Alfaro (18131212)
:*  Fecha       : 19/abril/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripcion : Esta clase obtiene los datos de la camiseta del equipo seleccionado
:*                y permite seleccionar la cantidad y la talla para la compra
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeUtils;

public class ComprarActivity extends AppCompatActivity{

    String idUsuario;

    String descripcion;
    int Precio = 0;
    String Cantidad;
    String Talla;
    EditText cant;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//ocultar Action Bar
        setContentView(R.layout.activity_comprar);

        spinner = findViewById(R.id.talla2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tallas,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        TextView desc = (TextView)findViewById(R.id.descripcion);
        TextView precio = (TextView)findViewById(R.id.precio2);
        ImageView imgComprar = (ImageView) findViewById(R.id.imgComprar);

        cant = (EditText)findViewById(R.id.cantidad);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        imgComprar.setImageResource(b.getInt("JERSEY"));
        descripcion = "Jersey Local " + b.getString("EQUIPO") + " 2021/2022";
        Precio = b.getInt("PRECIO");
        idUsuario = b.getString("ID_USUARIO");

        desc.setText(descripcion);
        precio.setText("$"+Precio+".00");
    }

    public void onClick(View view) {
        Intent pago = new Intent(view.getContext(), PagoActivity.class);
        Cantidad = ( cant.getText().toString() );
        Talla = (spinner.getSelectedItem().toString());
        if(Talla.equals("Talla"))
            Talla = "M";
        pago.putExtra("DESC", descripcion);
        pago.putExtra("PRECIO", Precio);
        pago.putExtra("CANTIDAD",Cantidad);
        pago.putExtra("TALLA",Talla);
        pago.putExtra("ID_USUARIO", idUsuario);
        startActivity(pago);
        finish();
    }
}