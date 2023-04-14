import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase implements MySqlDataBase, AutoCloseable{
    private static final Logger logger = Logger.getLogger(DataBase.class.getName());
    private final String url;
    private final String user;
    private final String password;
    private Connection connection;


    public DataBase(){
        this.url = "jdbc:mysql://127.0.0.1/biblioteca?userSSL=false";
        this.user = "pardo";
        this.password = "pardo1234";
    }


    @Override
    public Connection open() throws SQLException{
        connection = DriverManager.getConnection(url, user, password);
        logger.log(Level.INFO, "\nConexion Establecida");
        return connection;
    }

    /**
     * realiza una consulta a la base de datos
     * @param query
     * @return los registros llamado del query
     * @throws SQLException
     */
    @Override
    public ResultSet executeQuery(String query) throws SQLException{
        try(PreparedStatement stmt = open().prepareStatement(query)){
            ResultSet resultSet = stmt.executeQuery();
            logger.log(Level.INFO, "\nConsulta ejecutada con exito");
            close();
            return resultSet;
        }
    }

    /**
     * realiza un cambios como (agregar, elimnar, etc) libros
     * @param query
     * @param values
     * @throws SQLException
     */
    @Override
    public void executeUpdate(String query, Object[] values) throws SQLException {
        try(PreparedStatement stmt = open().prepareStatement(query)){
            for(int i=0; i<values.length; i++){
                stmt.setObject(i+1, values[i]);
            }
            stmt.executeUpdate();
            logger.log(Level.INFO, "\nRegistro ejecutado con exito");
        }catch (SQLException e){
            logger.log(Level.WARNING, "\nError ejecutando el Registro Execute Update");
        }

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

    public int ultimoCodigo() throws SQLException{
        int ultimoCodigo = 0;
        String query = "SELECT MAX(codigo) FROM libros;";
        try(PreparedStatement stmt = open().prepareStatement(query)){
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                ultimoCodigo = resultSet.getInt(1);
            }
        }
        return ultimoCodigo;
    }




    /**
     * muestra los libros en un tabla
     * @param table
     * @throws SQLException
     */
    public void tablaDataLibro(DefaultTableModel table) throws SQLException {
        String query = "SELECT * FROM libros";
        Object [] row = new Object[5];

        table.setColumnCount(0);
        table.setRowCount(0);

        table.addColumn("Codigo");
        table.addColumn("Titulo");
        table.addColumn("Autor");
        table.addColumn("Anio");
        table.addColumn("Copias");

        try(PreparedStatement stmt = open().prepareStatement(query)) {
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    row[0] = resultSet.getString("codigo");
                    row[1] = resultSet.getString("titulo");
                    row[2] = resultSet.getString("autor");
                    row[3] = resultSet.getString("year_publicacion");
                    row[4] = resultSet.getString("num_copias");
                    table.addRow(row);
                }

            } catch (SQLException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
                e.printStackTrace();
            }
        }
    }

    public void buscarLibro(DefaultTableModel table, String libro) throws SQLException {
        String query = "SELECT * FROM libros WHERE titulo = '"+libro+"';";
        Object [] row = new Object[5];

        table.setColumnCount(0);
        table.setRowCount(0);

        table.addColumn("Codigo");
        table.addColumn("Titulo");
        table.addColumn("Autor");
        table.addColumn("Anio");
        table.addColumn("Copias");

        try(PreparedStatement stmt = open().prepareStatement(query)) {
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    row[0] = resultSet.getString("codigo");
                    row[1] = resultSet.getString("titulo");
                    row[2] = resultSet.getString("autor");
                    row[3] = resultSet.getString("year_publicacion");
                    row[4] = resultSet.getString("num_copias");
                    table.addRow(row);
                }

            } catch (SQLException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() throws SQLException{
        if(connection != null){
            connection.close();
            logger.log(Level.INFO, "\nConexion Cerrada");
        }
    }
}