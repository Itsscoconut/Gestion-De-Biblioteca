import java.sql.SQLException;

public class IniciarSesion {
    private DataBaseUsers data;

    public IniciarSesion(){
        this.data = new DataBaseUsers();
    }

    public boolean iniciarSesion(String usuario, String contrasena) throws SQLException {
        String query = "SELECT usuario, contrasena FROM inicio_sesion WHERE usuario = ? AND contrasena = ?";
        Object [] values = {usuario, contrasena};
        return data.ifExists(query, values);
    }

}
