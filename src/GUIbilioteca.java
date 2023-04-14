import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class GUIbilioteca extends JFrame implements ActionListener {
    private Biblioteca biblioteca;
    public static void main(String[] args) {
        new GUIbilioteca();
    }

    private JPanel panel;

    private JMenuBar menuBar;
    private JMenu libro;
    private JMenuItem item1;
    JTextField codigotf;

    private DefaultTableModel model;
    private JButton limpiar, agregar, subirImagen;
    public GUIbilioteca(){
        biblioteca = new Biblioteca();
        this.setTitle("Biblioteca");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setLayout(null);

        panel = new JPanel();
        panel.setBackground(new Color(0, 139, 139));
        panel.setBounds(0 , 0, 1000, 700);
        panel.setLayout(null);
        this.add(panel);


        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        libro = new JMenu("Libros");
        menuBar.add(libro);
        libro();

        this.setVisible(true);
    }

    private JButton eliminarLibro, modificarLibro, buscarLibro, traerLibro;

    private JLabel titulolbl, autorlbl, yearPublicacionlbl, copiaslbl;
    private JTextField titulo, autortf, yearPublicaciontf, copiastf;
    //private ImageIcon imageIcon;
    public void libro(){
        //imagen de libro



        //Titulo TextField and JtextField
        titulolbl = new JLabel("Titulo");
        titulolbl.setBounds(300, 310, 70, 20);
        panel.add(titulolbl);

        titulo = new JTextField();
        titulo.setBounds(340, 310, 300, 20);
        panel.add(titulo);

        //Autor TextField and JtextField
        autorlbl = new JLabel("Autor");
        autorlbl.setBounds(300, 330, 70, 20);
        panel.add(autorlbl);

        autortf = new JTextField();
        autortf.setBounds(340, 330, 300, 20);
        panel.add(autortf);

        //ano de publicacion TextField and JtextField
        yearPublicacionlbl = new JLabel("Ano de Publicacion");
        yearPublicacionlbl.setBounds(220, 350, 150, 20);
        panel.add(yearPublicacionlbl);

        yearPublicaciontf = new JTextField();
        yearPublicaciontf.setBounds(340, 350, 140, 20);
        panel.add(yearPublicaciontf);

        //codigo TextField and JtextField
        JLabel codigolbl = new JLabel("Codigo");
        codigolbl.setBounds(490, 350, 70, 20);
        panel.add(codigolbl);

        codigotf = new JTextField();
        codigotf.setBounds(540, 350, 100, 20);
        codigotf.setEnabled(false);
        panel.add(codigotf);


        //Tema TextField and JtextField
        JLabel temalbl = new JLabel("Tema");
        temalbl.setBounds(300, 370, 70, 20);
        panel.add(temalbl);

        JTextField tematf = new JTextField();
        tematf.setBounds(340, 370, 300, 20);
        panel.add(tematf);

        //formato TextField and JtextField
        JLabel formatolbl = new JLabel("Formato");
        formatolbl.setBounds(285, 390, 70, 20);
        panel.add(formatolbl);

        JTextField formatotf = new JTextField();
        formatotf.setBounds(340, 390, 300, 20);
        panel.add(formatotf);

        //Idioma TextField and JtextField
        JLabel idiomalbl = new JLabel("Idioma");
        idiomalbl.setBounds(295, 410, 70, 20);
        panel.add(idiomalbl);

        JTextField idiomatf = new JTextField();
        idiomatf.setBounds(340, 410, 300, 20);
        panel.add(idiomatf);

        //Grupo TextField and JtextField
        JLabel grupolbl = new JLabel("Grupo");
        grupolbl.setBounds(295, 430, 70, 20);
        panel.add(grupolbl);

        JTextField grupotf = new JTextField();
        grupotf.setBounds(340, 430, 300, 20);
        panel.add(grupotf);

        //Editorial TextField and JtextField
        JLabel editorialbl = new JLabel("Editorial");
        editorialbl.setBounds(285, 450, 70, 20);
        panel.add(editorialbl);

        JTextField editorialtf = new JTextField();
        editorialtf.setBounds(340, 450, 300, 20);
        panel.add(editorialtf);

        //Descripcion TextField and JtextField
        JLabel descripcionlbl = new JLabel("Descripcion");
        descripcionlbl.setBounds(265, 470, 70, 20);
        panel.add(descripcionlbl);

        JTextArea descripcionjt = new JTextArea();
        descripcionjt.setBounds(340, 472, 300, 100);
        panel.add(descripcionjt);

        //Cantida de Copias TextField and JtextField
        copiaslbl = new JLabel("Copias");
        copiaslbl.setBounds(285, 570, 70, 20);
        panel.add(copiaslbl);

        copiastf = new JTextField();
        copiastf.setBounds(340, 570, 300, 20);
        panel.add(copiastf);


        Object[][] data = {{"John", "Doe", "40"}, {"Jane", "Doe", "35"}, {"Bob", "Smith", "50"}};
        String[] columnNames = {"Codigo", "Titulo", "Autor", "Anio", "Tema", "Formato", "Idioma", "Grupo", "Editorial"};


        //tabla
        model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 35, 960, 250);
        panel.add(scrollPane);


        traerLibro = new JButton("Traer Libro");
        traerLibro.setBounds(660, 310, 120, 30);
        traerLibro.addActionListener(this);
        panel.add(traerLibro);

        agregar = new JButton("Agregar Libro");
        agregar.setBounds(660, 360, 120, 30);
        agregar.addActionListener(this);
        panel.add(agregar);

        eliminarLibro = new JButton("Eliminar Libro");
        eliminarLibro.setBounds(660, 410, 120, 30);
        eliminarLibro.addActionListener(this);
        panel.add(eliminarLibro);

        modificarLibro = new JButton("Modificar Libro");
        modificarLibro.setBounds(660, 460, 120, 30);
        modificarLibro.addActionListener(this);
        panel.add(modificarLibro);

        limpiar = new JButton("Limpiar Texto");
        limpiar.setBounds(660, 510, 120, 30);
        limpiar.addActionListener(this);
        panel.add(limpiar);

        buscarLibro = new JButton("Buscar Libro");
        buscarLibro.setBounds(660, 560, 120, 30);
        buscarLibro.addActionListener(this);
        panel.add(buscarLibro);

        subirImagen = new JButton("seleccionar imagen");
        //subirImagen.setBounds(900, 560);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==1){
                    int fila = table.getSelectedRow();
                    codigotf.setText(table.getValueAt(fila, 0).toString());
                    titulo.setText(table.getValueAt(fila, 1).toString());
                    autortf.setText(table.getValueAt(fila, 2).toString());
                    yearPublicaciontf.setText(table.getValueAt(fila, 3).toString());
                    copiastf.setText(table.getValueAt(fila, 4).toString());
                }
                //super.mouseClicked(e);
            }
        });
    }

    public void listar(){
        try(DataBase dataBase = new DataBase()){
            dataBase.tablaDataLibro(model);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void searchBook(){
        try(DataBase dataBase = new DataBase()){
            dataBase.buscarLibro(model, titulo.getText());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void imagenDelLibro(){
        //this.add(panel);
        String search = "src/img/"+titulo.getText().toLowerCase()+".jpg";
        System.out.println(search);
        try{
            BufferedImage image = ImageIO.read(new File(search));
            ImageIcon imageIcon = new ImageIcon(image); //"src/img/naruto.jpg"
            JLabel imagenLibro = new JLabel();
            imagenLibro.setIcon(imageIcon);
            imagenLibro.setBounds(10, 310, 200, 300);
            panel.add(imagenLibro);//
            System.out.println(imageIcon);


        }catch(IOException e){
            System.out.println("error al cargar la imagen");
        }

        //ImageIcon imageIcon = new ImageIcon(imageIcon);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == traerLibro){
            listar();
        }

        if(e.getSource()==limpiar){
            titulo.setText("");
            autortf.setText("");
            yearPublicaciontf.setText("");
            copiastf.setText("");
        }

        if(e.getSource()==agregar){
            biblioteca.addBook(new Libro(titulo.getText(), autortf.getText(), Integer.parseInt(yearPublicaciontf.getText()), Integer.parseInt(copiastf.getText())));
        }

        if(e.getSource()==eliminarLibro){
            biblioteca.deleteBook(titulo.getText());
            System.out.println("elimnando libro");
        }

        if(e.getSource()==modificarLibro){
            biblioteca.modifyBook(titulo.getText(), autortf.getText(), Integer.parseInt(yearPublicaciontf.getText()), Integer.parseInt(copiastf.getText()));
        }

        if(e.getSource()==buscarLibro){
            searchBook();
            imagenDelLibro();
        }
    }
}
