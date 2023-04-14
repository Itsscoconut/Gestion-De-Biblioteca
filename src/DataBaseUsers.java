import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseUsers implements MySqlDataBase, AutoCloseable{
    private final String url;
    private final String user;
    private final String password;
    private Connection connection;
    private static final Logger logger = Logger.getLogger(DataBase.class.getName());

    public DataBaseUsers(){
        this.url = "jdbc:mysql://127.0.0.1/biblioteca?userSSL=false";
        this.user = "pardo";
        this.password = "pardo1234";
    }
    @Override
    public Connection open() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        logger.log(Level.INFO, "\nConexion Establecida");
        return connection;
    }

    @Override
    public ResultSet executeQuery(String query) throws SQLException {
        try(PreparedStatement stmt = open().prepareStatement(query)){
            ResultSet resultSet = stmt.executeQuery();
            logger.log(Level.INFO, "\nConsulta ejecutada con exito");
            close();
            return resultSet;
        }
    }

    @Override
    public void executeUpdate(String query, Object[] values) throws SQLException {

    }

    @Override
    public boolean ifExists(String query, Object[] values) throws SQLException {
        try(PreparedStatement stmt = open().prepareStatement(query)){
            for(int i=0; i<values.length; i++){
                stmt.setObject(i+1, values[i]);
            }
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();
        }
    }

    @Override
    public void close() throws SQLException {
        if(connection != null){
            connection.close();
            logger.log(Level.INFO, "\nConexion Cerrada");
        }
    }
}
