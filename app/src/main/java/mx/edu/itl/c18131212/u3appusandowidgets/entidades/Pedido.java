/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                              clase de pedido
:*
:*  Archivo     : Pedido.java
:*  Autor       : Juan Manuel Alfaro (18131212)
:*  Fecha       : 01/Junio/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripcion : Esta clase permite crear objetos pedido
:*
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131212.u3appusandowidgets.entidades;

public class Pedido {
    private int id;
    private int idUsuario;
    private String descripcion;
    private String cantidad;
    private String total;
    private String direccion;

    public Pedido(int id, int idUsuario, String descripcion, String cantidad, String total, String direccion) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.total = total;
        this.direccion = direccion;
    }

    public Pedido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
