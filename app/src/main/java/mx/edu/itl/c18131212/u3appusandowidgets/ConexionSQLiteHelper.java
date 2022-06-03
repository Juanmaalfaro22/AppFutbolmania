/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                              clase de Conexion SQLiteHelper
:*
:*  Archivo     : ConexionSQLiteHelper.java
:*  Autor       : Juan Manuel Alfaro (18131212)
:*  Fecha       : 01/Junio/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripcion : Esta clase permite crear bases de datos, tablas y conexiones a la base de datos
:*
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131212.u3appusandowidgets;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mx.edu.itl.c18131212.u3appusandowidgets.utilidades.utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(utilidades.CREAR_TABLA_USUARIO);
        db.execSQL(utilidades.CREAR_TABLA_PEDIDO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ utilidades.TABLA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS "+ utilidades.TABLA_PEDIDO);
        onCreate(db);
    }
}
