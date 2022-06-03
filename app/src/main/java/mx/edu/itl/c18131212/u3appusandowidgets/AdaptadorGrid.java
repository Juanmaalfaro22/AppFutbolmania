/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                              Clase Adaptador del GidView
:*
:*  Archivo     : AdaptadorGrid.java
:*  Autor       : Juan Manuel Alfaro (18131212)
:*  Fecha       : 19/abril/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripcion : Esta clase inserta elementos al GridView de forma dinamica
:*
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131212.u3appusandowidgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdaptadorGrid extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    String [] datos;
    int[] datosImg;

    public AdaptadorGrid(Context contexto, String[] datos, int[] imagenes){
        this.contexto = contexto;
        this.datos = datos;
        this.datosImg = imagenes;

        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.elemento_view,null);
        TextView equipo = (TextView) vista.findViewById(R.id.TVview);
        ImageView imagen = (ImageView) vista.findViewById(R.id.imgView);
        equipo.setText(datos[i]);
        imagen.setImageResource(datosImg[i]);

        return vista;
    }

    @Override
    public int getCount() {
        return datosImg.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

}
