import java.sql.SQLException;

public class test {
    public static void main(String [] args) throws SQLException {

        Biblioteca biblioteca = new Biblioteca();
        biblioteca.deleteBook("naruto");
        DataBase dataBase = new DataBase();
        System.out.println(dataBase.ultimoCodigo());




    }
}
