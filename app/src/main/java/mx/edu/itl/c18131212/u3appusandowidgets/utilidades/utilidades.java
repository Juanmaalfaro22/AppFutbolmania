/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                              clase de utilidades
:*
:*  Archivo     : utilidades.java
:*  Autor       : Juan Manuel Alfaro (18131212)
:*  Fecha       : 01/Junio/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripcion : Esta clase tiene utilidades para reutilizar
:*
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131212.u3appusandowidgets.utilidades;

public class utilidades {

    //TABLA USUARIO
    public static final String TABLA_USUARIO = "usuario";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE_USUARIO = "nombreUsuario";
    public static final String CAMPO_PASSWORD = "password";

    //TABLA PEDIDO
    public static final String TABLA_PEDIDO = "pedido";
    public static final String CAMPO_IDP = "id";
    public static final String CAMPO_IDU = "idUsuario";
    public static final String CAMPO_DESC = "descripcion";
    public static final String CAMPO_CANTIDAD = "cantidad";
    public static final String CAMPO_TOTAL = "total";
    public static final String CAMPO_DIRECCION = "direccion";

    //SQL PARA CREAR TABLA USUARIO
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " + TABLA_USUARIO + "("+ CAMPO_ID +
                                                     " INTEGER PRIMARY KEY AUTOINCREMENT,"
                                                     +CAMPO_NOMBRE_USUARIO + " TEXT NOT NULL UNIQUE," +  CAMPO_PASSWORD
                                                     + " TEXT NOT NULL "  + " )";

    //SQL PARA CREAR TABLA PEDIDO
    public static final String CREAR_TABLA_PEDIDO =  "CREATE TABLE " + TABLA_PEDIDO + "("+ CAMPO_IDP +
                                                     " INTEGER PRIMARY KEY AUTOINCREMENT,"  + CAMPO_IDU + " INTEGER NOT NULL,"
                                                     + CAMPO_DESC + " TEXT NOT NULL," +  CAMPO_CANTIDAD + " TEXT NOT NULL," +
                                                     CAMPO_TOTAL + " TEXT NOT NULL," + CAMPO_DIRECCION + " TEXT NOT NULL, "
                                                     + "FOREIGN KEY(" + CAMPO_IDU + ") REFERENCES " +  TABLA_USUARIO + "(" +CAMPO_ID+")" +" )";
}
