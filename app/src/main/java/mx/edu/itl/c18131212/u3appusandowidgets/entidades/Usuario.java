/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                              clase de Usuario
:*
:*  Archivo     : Usuario.java
:*  Autor       : Juan Manuel Alfaro (18131212)
:*  Fecha       : 01/Junio/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripcion : Esta clase permite crear objetos Usuarioi
:*
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131212.u3appusandowidgets.entidades;

public class Usuario {
    private Integer id;
    private String nombreUsuario;
    private String password;

    public Usuario(Integer id, String nombreUsuario, String password) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public Usuario(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
