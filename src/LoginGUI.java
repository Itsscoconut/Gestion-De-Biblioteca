import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginGUI extends JFrame implements ActionListener {

    private IniciarSesion sesion;
    private JTextField usuarioTF;
    private JPasswordField passwordPF;
    private JButton ingresarBtn;


    public LoginGUI() {
        super("Inicio de sesión");
        this.sesion = new IniciarSesion();
        usuarioTF = new JTextField(20);
        passwordPF = new JPasswordField(20);
        ingresarBtn = new JButton("Ingresar");
        ingresarBtn.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Nombre de usuario:"));
        panel.add(usuarioTF);
        panel.add(new JLabel("Contraseña:"));
        panel.add(passwordPF);
        panel.add(new JLabel(""));
        panel.add(ingresarBtn);

        this.add(panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ingresarBtn) {
            String usuario = usuarioTF.getText();
            String password = new String(passwordPF.getPassword());

            try {
                if (validarCredenciales(usuario, password)) {
                    JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso");
                    new GUIbilioteca();
                    this.setVisible(false);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Nombre de usuario o contraseña incorrectos");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private boolean validarCredenciales(String usuario, String password) throws SQLException {
        if (usuario.isEmpty() || password.isEmpty()) {
            return false;
        }
        return sesion.iniciarSesion(usuario, password);
    }



}
