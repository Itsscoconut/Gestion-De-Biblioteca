import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface MySqlDataBase {
    //metodo para abrir la base de datos
    public Connection open() throws SQLException;
    //metodo para extraer registro de la base de datpos
    public ResultSet executeQuery(String query) throws SQLException;
    //metdo para actulizaf la base de datos
    public void executeUpdate(String query, Object [] values) throws SQLException;
    //metodo para verificar el regitsro existente
    public boolean ifExists(String query, Object[] values) throws SQLException;
}
