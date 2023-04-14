import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Libro {
    private int codigo;
    private String titulo;
    private String autor;
    private int anPublicacion;
    private int numCopias;
    private static int codigoActual = 1; // Iniciar el contador de códigos en 1

    // Resto del código de la clase...


    public Libro() {
    }
    public Libro(String titulo, String autor, int anPublicacion, int numCopias) {
        //this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.anPublicacion = anPublicacion;
        this.numCopias = numCopias;
        this.codigoAT();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnPublicacion() {
        return anPublicacion;
    }

    public void setAnPublicacion(int anPublicacion) {
        this.anPublicacion = anPublicacion;
    }

    public int getNumCopias() {
        return numCopias;
    }

    public void setNumCopias(int numCopias) {
        this.numCopias = numCopias;
    }


    public void codigoAT() {
        try(DataBase dataBase = new DataBase()){
            int ult = dataBase.ultimoCodigo();
            ult++;
            this.setCodigo(ult);
        }catch (SQLException e){
            e.printStackTrace();
        }

        //this.setCodigo(codigoActual); // Asignar el código actual al libro
        // Asignar el código actual al libro
        //codigoActual++; // Incrementar el contador de códigos
        System.out.println("Código de libro generado: " + this.getCodigo());
    }



}
