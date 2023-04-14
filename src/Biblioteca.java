import java.sql.SQLException;
import java.util.ArrayList;

public class Biblioteca {
    private  ArrayList<Libro> libros;
    private DataBase dataBase;


    public Biblioteca() {
        this.libros = new ArrayList<>();
        this.dataBase = new DataBase();
    }

    /**
     * este metodo Agrega un libro con el metodo database.executeUpdtes()
     * @param libro
     */
    public void addBook(Libro libro){
        this.libros.add(libro);
        try {
            String query = "SELECT titulo FROM libros WHERE titulo = ?;";
            Object[] value = {libro.getTitulo()};
            if(!dataBase.ifExists(query, value)){
                query = "INSERT INTO libros (codigo, titulo, autor, year_publicacion, num_copias) VALUES (?, ?, ?, ?, ?)";
                Object[] values = {libro.getCodigo(), libro.getTitulo(), libro.getAutor(), libro.getAnPublicacion(), libro.getNumCopias()};
                dataBase.executeUpdate(query, values);
                System.out.println(values);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * este metodo elimina un libro
     * @param libro
     */
    public void deleteBook(String libro){
        try {
            String query = "SELECT titulo FROM libros WHERE titulo = ?;";
            Object[] values = {libro};
            if(dataBase.ifExists(query, values)){
                query = "DELETE FROM libros WHERE titulo = ?;";
                dataBase.executeUpdate(query, values);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * modifca los atributos seleccionados de los libros
     * @param titulo titulo del libro a modificar
     * @param autor actualiza
     * @param year actualiza
     * @param copias actualiza
     */
    public void modifyBook(String titulo, String autor, int year, int copias) {
        try {
            String query = "UPDATE libros SET autor = ?, year_publicacion = ?,  num_copias = ? WHERE titulo = ?;";
            Object[] values = { autor, year, copias, titulo };
            dataBase.executeUpdate(query, values);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }








}
