package placa;

import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Welcome extends JFrame implements ActionListener {

    JLabel lb1, lbIdBusqueda, lbNuevoId, lbPlaca, lbModelo, lbNombre, lbDescripcion, lbEstado, lbStatus, lbMenuCrud;
    JTextField txtIdBusqueda, txtNuevoId, txtPlaca, txtModelo, txtNombre, txtDescripcion, txtEstado, txtStatus;
    JButton btnCrear, btnLeer, btnActualizar, btnEliminar, btnEjecutarMenu;
    JTable tablaPlacas;
    DefaultTableModel modeloTabla;
    JScrollPane scrollTabla;

    JComboBox<String> cbOpcionesCRUD;
    JMenuBar bm;
    JMenu mArchivo, mBD;
    JMenuItem mBD_C, mBD_Consulta, mBD_Eliminar;

    Placa placaObj;

    Welcome() {

        this.setBounds(100, 30, 950, 760);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        // Barra
        bm = new JMenuBar();
        this.setJMenuBar(bm);

        mArchivo = new JMenu("Archivo");
        bm.add(mArchivo);

        mBD = new JMenu("Base de datos");
        bm.add(mBD);

        this.mBD_C = new JMenuItem("Crear");
        mBD.add(mBD_C);

        this.mBD_Consulta = new JMenuItem("Consulta");
        mBD.add(mBD_Consulta);

        this.mBD_Eliminar = new JMenuItem("Eliminar");
        mBD.add(mBD_Eliminar);

        this.mBD_C.addActionListener(this);
        this.mBD_Consulta.addActionListener(this);
        this.mBD_Eliminar.addActionListener(this);

        // Título
        this.lb1 = new JLabel("Control de Placas..");
        this.lb1.setBounds(380, 10, 250, 30);
        this.add(lb1);

   
        this.lbIdBusqueda = new JLabel("ID Actual (Buscar):");
        this.lbIdBusqueda.setBounds(50, 50, 140, 30);
        this.add(lbIdBusqueda);

        this.txtIdBusqueda = new JTextField();
        this.txtIdBusqueda.setBounds(190, 50, 150, 30);
        this.add(txtIdBusqueda);

        this.lbNuevoId = new JLabel("Nuevo ID (Modificar):");
        this.lbNuevoId.setBounds(50, 90, 140, 30);
        this.add(lbNuevoId);

        this.txtNuevoId = new JTextField();
        this.txtNuevoId.setBounds(190, 90, 150, 30);
        this.add(txtNuevoId);

        this.lbPlaca = new JLabel("Placa:");
        this.lbPlaca.setBounds(50, 130, 140, 30);
        this.add(lbPlaca);

        this.txtPlaca = new JTextField();
        this.txtPlaca.setBounds(190, 130, 150, 30);
        this.add(txtPlaca);

        this.lbModelo = new JLabel("Modelo:");
        this.lbModelo.setBounds(50, 170, 140, 30);
        this.add(lbModelo);

        this.txtModelo = new JTextField();
        this.txtModelo.setBounds(190, 170, 150, 30);
        this.add(txtModelo);

       
        this.lbNombre = new JLabel("Propietario:");
        this.lbNombre.setBounds(450, 50, 100, 30);
        this.add(lbNombre);

        this.txtNombre = new JTextField();
        this.txtNombre.setBounds(560, 50, 180, 30);
        this.add(txtNombre);

        this.lbDescripcion = new JLabel("Descripción:");
        this.lbDescripcion.setBounds(450, 90, 100, 30);
        this.add(lbDescripcion);

        this.txtDescripcion = new JTextField();
        this.txtDescripcion.setBounds(560, 90, 180, 30);
        this.add(txtDescripcion);

        this.lbEstado = new JLabel("Estado:");
        this.lbEstado.setBounds(450, 130, 100, 30);
        this.add(lbEstado);

        this.txtEstado = new JTextField();
        this.txtEstado.setBounds(560, 130, 180, 30);
        this.add(txtEstado);

        this.lbStatus = new JLabel("Status:");
        this.lbStatus.setBounds(450, 170, 100, 30);
        this.add(lbStatus);

        this.txtStatus = new JTextField("ACTIVO");
        this.txtStatus.setBounds(560, 170, 180, 30);
        this.add(txtStatus);

        //CRUD
        this.btnCrear = new JButton("Crear");
        this.btnCrear.setBounds(180, 220, 120, 35);
        this.add(btnCrear);

        this.btnLeer = new JButton("Leer");
        this.btnLeer.setBounds(310, 220, 120, 35);
        this.add(this.btnLeer);

        this.btnActualizar = new JButton("Actualizar");
        this.btnActualizar.setBounds(440, 220, 120, 35);
        this.add(this.btnActualizar);

        this.btnEliminar = new JButton("Eliminar");
        this.btnEliminar.setBounds(570, 220, 120, 35);
        this.add(this.btnEliminar);

        // Menu
        this.lbMenuCrud = new JLabel("Menú CRUD:");
        this.lbMenuCrud.setBounds(50, 265, 100, 30);
        this.add(lbMenuCrud);

        String[] opciones = {"-- Seleccione Opción --", "Crear (Insertar)", "Leer (Consultar)", "Actualizar", "Eliminar"};
        this.cbOpcionesCRUD = new JComboBox<>(opciones);
        this.cbOpcionesCRUD.setBounds(150, 265, 180, 30);
        this.add(cbOpcionesCRUD);

        this.btnEjecutarMenu = new JButton("Ejecutar Opción");
        this.btnEjecutarMenu.setBounds(340, 265, 150, 30);
        this.add(btnEjecutarMenu);

        // Tabla
        this.modeloTabla = new DefaultTableModel();
        this.modeloTabla.addColumn("ID");
        this.modeloTabla.addColumn("Placa");
        this.modeloTabla.addColumn("Modelo");
        this.modeloTabla.addColumn("Propietario");
        this.modeloTabla.addColumn("Descripción");
        this.modeloTabla.addColumn("Estado");
        this.modeloTabla.addColumn("Status");

        this.tablaPlacas = new JTable(this.modeloTabla);
        this.scrollTabla = new JScrollPane(this.tablaPlacas);
        this.scrollTabla.setBounds(50, 310, 840, 360);
        this.add(this.scrollTabla);

        this.btnCrear.addActionListener(this);
        this.btnLeer.addActionListener(this);
        this.btnActualizar.addActionListener(this);
        this.btnEliminar.addActionListener(this);
        this.btnEjecutarMenu.addActionListener(this);

        this.setVisible(true);
        consultar();
    }

    public static void main(String[] args) {
        Welcome placa = new Welcome();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.btnLeer || e.getSource() == this.mBD_Consulta) {
            if (!this.txtIdBusqueda.getText().isEmpty()) {
                buscarPorId();
            } else {
                consultar();
            }
        }

        if (e.getSource() == this.btnCrear || e.getSource() == this.mBD_C) {
            agregar();
        }

        if (e.getSource() == this.btnActualizar) {
            actualizar();
        }

        if (e.getSource() == this.btnEliminar || e.getSource() == this.mBD_Eliminar) {
            eliminar();
        }

        if (e.getSource() == this.btnEjecutarMenu) {
            int seleccion = cbOpcionesCRUD.getSelectedIndex();
            if (seleccion == 1) agregar();
            else if (seleccion == 2) consultar();
            else if (seleccion == 3) actualizar();
            else if (seleccion == 4) eliminar();
        }
    }

    public void agregar() {
        if (this.txtPlaca.getText().isEmpty() || this.txtModelo.getText().isEmpty()) {
            System.out.println("Por favor llene los campos vacíos");
            return;
        }

        try {
            Connection cnx;
            PreparedStatement ps;

            placaObj = new Placa();
            placaObj.setNombre(this.txtNombre.getText()); 
            placaObj.setPlaca(this.txtPlaca.getText());
            placaObj.setModelo(this.txtModelo.getText());
            placaObj.setDescripcion(this.txtDescripcion.getText());
            placaObj.setEstado(this.txtEstado.getText());
            placaObj.setStatus(this.txtStatus.getText());

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/placa", "root", "");
            ps = cnx.prepareStatement("insert into placas(placa, modelo, propietario, descripcion, estado, status) values(?, ?, ?, ?, ?, ?)");

            ps.setString(1, placaObj.getPlaca());
            ps.setString(2, placaObj.getModelo());
            ps.setString(3, placaObj.getNombre()); 
            ps.setString(4, placaObj.getDescripcion());
            ps.setString(5, placaObj.getEstado());
            ps.setString(6, placaObj.getStatus());

            ps.execute();

            System.out.println("Registro agregado correctamente");
            consultar();
            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getSQLState() + " : " + ex.getMessage());
        }
    }

    public void consultar() {
        try {
            Connection cnx;
            PreparedStatement ps;
            ResultSet rs;

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/placa", "root", "");
            ps = cnx.prepareStatement("select * from placas");
            rs = ps.executeQuery();

            this.modeloTabla.setRowCount(0);

            while (rs.next()) {
                placaObj = new Placa();
                placaObj.setId(rs.getInt("id")); 
                placaObj.setPlaca(rs.getString("placa"));
                placaObj.setModelo(rs.getString("modelo"));
                placaObj.setNombre(rs.getString("propietario")); 
                placaObj.setDescripcion(rs.getString("descripcion"));
                placaObj.setEstado(rs.getString("estado"));
                placaObj.setStatus(rs.getString("status"));

                Object[] fila = new Object[7];
                fila[0] = placaObj.getId();
                fila[1] = placaObj.getPlaca();
                fila[2] = placaObj.getModelo();
                fila[3] = placaObj.getNombre();
                fila[4] = placaObj.getDescripcion();
                fila[5] = placaObj.getEstado();
                fila[6] = placaObj.getStatus();

                this.modeloTabla.addRow(fila);
            }

            System.out.println("Conexion exitosa y datos cargados");
            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getSQLState() + " : " + ex.getMessage());
        }
    }

    public void buscarPorId() {
        try {
            Connection cnx;
            PreparedStatement ps;
            ResultSet rs;

            int idBusqueda = Integer.parseInt(this.txtIdBusqueda.getText());

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/placa", "root", "");
            ps = cnx.prepareStatement("select * from placas where id = ?");
            ps.setInt(1, idBusqueda);
            rs = ps.executeQuery();

            if (rs.next()) {
                placaObj = new Placa();
                placaObj.setId(rs.getInt("id"));
                placaObj.setPlaca(rs.getString("placa"));
                placaObj.setModelo(rs.getString("modelo"));
                placaObj.setNombre(rs.getString("propietario")); // Carga Propietario
                placaObj.setDescripcion(rs.getString("descripcion"));
                placaObj.setEstado(rs.getString("estado"));
                placaObj.setStatus(rs.getString("status"));

             
                this.txtNuevoId.setText(String.valueOf(placaObj.getId()));
                this.txtPlaca.setText(placaObj.getPlaca());
                this.txtModelo.setText(placaObj.getModelo());
                this.txtNombre.setText(placaObj.getNombre()); 
                this.txtDescripcion.setText(placaObj.getDescripcion());
                this.txtEstado.setText(placaObj.getEstado());
                this.txtStatus.setText(placaObj.getStatus());

                System.out.println("Registro encontrado");
            } else {
                System.out.println("No se encontro ningun registro con ese ID");
            }

            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getSQLState() + " : " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error: Verifique que el ID sea un numero");
        }
    }

    public void actualizar() {
        if (this.txtIdBusqueda.getText().isEmpty() || this.txtNuevoId.getText().isEmpty()) {
            System.out.println("Ingrese el ID Actual y el Nuevo ID");
            return;
        }

        try {
            Connection cnx;
            PreparedStatement ps;

            int idActual = Integer.parseInt(this.txtIdBusqueda.getText());

            placaObj = new Placa();
            placaObj.setId(Integer.parseInt(this.txtNuevoId.getText()));
            placaObj.setNombre(this.txtNombre.getText()); 
            placaObj.setPlaca(this.txtPlaca.getText());
            placaObj.setModelo(this.txtModelo.getText());
            placaObj.setDescripcion(this.txtDescripcion.getText());
            placaObj.setEstado(this.txtEstado.getText());
            placaObj.setStatus(this.txtStatus.getText());

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/placa", "root", "");
            ps = cnx.prepareStatement("update placas set id = ?, placa = ?, modelo = ?, propietario = ?, descripcion = ?, estado = ?, status = ? where id = ?");

            ps.setInt(1, placaObj.getId());
            ps.setString(2, placaObj.getPlaca());
            ps.setString(3, placaObj.getModelo());
            ps.setString(4, placaObj.getNombre()); 
            ps.setString(5, placaObj.getDescripcion());
            ps.setString(6, placaObj.getEstado());
            ps.setString(7, placaObj.getStatus());
            ps.setInt(8, idActual);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Registro actualizado correctamente");
                consultar();
            } else {
                System.out.println("No se encontro ningun registro con el ID Actual");
            }

            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getSQLState() + " : " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error: Verifique que los campos de ID sean numeros");
        }
    }

    public void eliminar() {
        if (this.txtIdBusqueda.getText().isEmpty()) {
            System.out.println("Ingrese el ID a eliminar");
            return;
        }

        try {
            Connection cnx;
            PreparedStatement ps;

            int idBusqueda = Integer.parseInt(this.txtIdBusqueda.getText());

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/placa", "root", "");
            ps = cnx.prepareStatement("delete from placas where id = ?");
            ps.setInt(1, idBusqueda);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Registro eliminado correctamente");
                consultar();
            } else {
                System.out.println("No se encontro ningun registro con ese ID");
            }

            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getSQLState() + " : " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error: Verifique que el ID sea un numero");
        }
    }
}