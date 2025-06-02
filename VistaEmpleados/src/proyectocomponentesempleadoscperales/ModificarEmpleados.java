/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectocomponentesempleadoscperales;

import com.proyecto.componentes.componente2B.Componente2BListener;
import com.proyecto.componentes.componente2B.Componente2BObject;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Meu
 */
public class ModificarEmpleados extends javax.swing.JFrame {

    /**
     * Creates new form ModificarEmpleados
     */
    private Connection con = null;
    private ResultSet resul = null;
    private Statement stmt = null;
    
    public ModificarEmpleados() {
        initComponents();
        this.setTitle("Añadir o modificar empleados");
        
        //Variables
        Object[][] paises;
        Object[][] ciudades;
        
        //Conexión a BBDD
        String bbdd = "jdbc:hsqldb:hsql://localhost/";
        String usuario = "SA";
        String clave = "SA";
        
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection(bbdd, usuario, clave);
            if (con != null) {
                System.out.println("Connection created successfully");
            } else {
                System.out.println("Problem with creating connection");
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            // Cerrar la conexión
            try {
                con.close(); //con contiene la conexión a la base de datos.
            } catch (SQLException ex) {
                System.out.println("Oops");
            }
        }
        
        componente1B1.setjLabelText("Nombre");
        componente1B2.setjLabelText("Apellidos");
        componente31.setJLabelText("Cargo");
        cargarCargosEnComponente();
        componente2B1.setJLabelText("País");
        componente2B2.setJLabelText("Ciudad");
        try {
            paises = obtenerPaises(con);
            componente2B1.cargarDatos(paises);
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        componente2B1.addComponente2BListener(new Componente2BListener() {
    @Override
    public void onItemSelected(Componente2BObject evento) {
        try {
            // Obtener el código del país seleccionado
            int codPais = Integer.parseInt(evento.getCodigo());
            
            // Obtener las ciudades desde la base de datos
            Object[][] ciudades = obtenerCiudades(con, codPais);
            
            // Cargar las ciudades en el segundo componente
            componente2B2.setTieneMensaje(false); // Opcional: quitar mensaje
            componente2B2.setMatrizDatos(ciudades);

        } catch (Exception e) {
            e.printStackTrace();
            // También puedes mostrar un JOptionPane de error si lo deseas
        }
    }
});
        
    }
    
    public static Object[][] obtenerPaises(Connection con) throws SQLException {
        String sql = "SELECT codigo, nombre FROM paises";

        try (PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = ps.executeQuery()) {

            // 1. Contar filas
            rs.last();
            int numFilas = rs.getRow();
            rs.beforeFirst(); // Volver al inicio

            Object[][] datos = new Object[numFilas][2];

            // 2. Llenar datos
            int fila = 0;
            while (rs.next()) {
                datos[fila][0] = rs.getObject("codigo");
                datos[fila][1] = rs.getObject("nombre");
                fila++;
            }

            return datos;
        }
    }
    
    public static Object[][] obtenerCiudades(Connection con, int codPais) throws SQLException {
    String sql = "SELECT codigo, nombre FROM Ciudades WHERE codPais = ?";

    try (PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
        ps.setInt(1, codPais);
        try (ResultSet rs = ps.executeQuery()) {

            // Contar filas
            rs.last();
            int numFilas = rs.getRow();
            rs.beforeFirst();

            Object[][] datos = new Object[numFilas][2];
            int fila = 0;

            while (rs.next()) {
                datos[fila][0] = rs.getObject("codigo");
                datos[fila][1] = rs.getObject("nombre");
                fila++;
            }

            return datos;
        }
    }
}
    
     public void cargarCargosEnComponente() {
        try {
            String sql = "SELECT codigo, nombre FROM CARGOS";
            stmt = con.createStatement();
            resul = stmt.executeQuery(sql);

            // Usamos un ArrayList para almacenar los datos
            ArrayList<Object[]> listaCargos = new ArrayList<>();

            // Recorremos el ResultSet y almacenamos los datos en el ArrayList
            while (resul.next()) {
                listaCargos.add(new Object[]{
                    resul.getObject("codigo"),  // Código del cargo
                    resul.getObject("nombre")   // Nombre del cargo
                });
            }

            // Convertimos el ArrayList a un array bidimensional directamente
            Object[][] datosCargos = listaCargos.toArray(new Object[0][2]);

            // Asignar datos al componente
            componente31.setMatrizDatos(datosCargos);

    } catch (SQLException e) {
         e.printStackTrace();
      }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        componente1B1 = new com.proyecto.componentes.componente1B.Componente1B();
        componente1B2 = new com.proyecto.componentes.componente1B.Componente1B();
        componente31 = new com.proyecto.componentes.componente3.Componente3();
        componente2B1 = new com.proyecto.componentes.componente2B.Componente2B();
        componente2B2 = new com.proyecto.componentes.componente2B.Componente2B();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EMPLEADO");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reiniciar");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setText("VISOR EVENTOS");

        jMenu1.setText("Archivo");

        jMenuItem2.setText("Buscar empleado");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText("Añadir o modificar empleado");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Funciones");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(componente1B1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(componente1B2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(110, 110, 110)
                        .addComponent(componente31, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(componente2B1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(componente2B2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 170, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(117, 117, 117))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(componente31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(componente1B1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(componente1B2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(componente2B2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(componente2B1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        BuscarEmpleados be = new BuscarEmpleados();
        be.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String nombre = componente1B1.getjTextField1Text();
        String apellidos = componente1B2.getjTextField1Text();
        String codCiudad = componente2B2.getCodigoSeleccionado();

        // Validaciones básicas
        if (nombre.isEmpty() || apellidos.isEmpty() || codCiudad == null) {
            JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos antes de guardar.");
            return;
        }

        String sql = "INSERT INTO empleados (nombre, apellidos, codCiudad) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, codCiudad);

            int filasInsertadas = ps.executeUpdate();

            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(null, "Empleado guardado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar el empleado.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar en la base de datos: " + ex.getMessage());
        };
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModificarEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModificarEmpleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.proyecto.componentes.componente1B.Componente1B componente1B1;
    private com.proyecto.componentes.componente1B.Componente1B componente1B2;
    private com.proyecto.componentes.componente2B.Componente2B componente2B1;
    private com.proyecto.componentes.componente2B.Componente2B componente2B2;
    private com.proyecto.componentes.componente3.Componente3 componente31;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
