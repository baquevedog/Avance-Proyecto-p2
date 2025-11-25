package com.proyectoint.ui;

import com.proyectoint.model.Rol;
import com.proyectoint.model.Usuario;
import com.proyectoint.service.UsuarioService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UsuariosForm {

    private JPanel mainPanel;
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JComboBox<Rol> cbRol;
    private JButton btnCrear;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscarCedula;
    private JButton btnBuscarCorreo;
    private JButton btnFiltrarRol;
    private JButton btnUndo;
    private JTable tblUsuarios;
    private JComboBox<Rol> cbFiltroRol;

    private final UsuarioService usuarioService;

    public UsuariosForm(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        inicializarCombos();
        inicializarTabla();
        inicializarListeners();
        recargarTablaUsuarios();
    }

    private void inicializarCombos() {
        DefaultComboBoxModel<Rol> model = new DefaultComboBoxModel<>(Rol.values());
        cbRol.setModel(model);
        cbFiltroRol.setModel(new DefaultComboBoxModel<>(Rol.values()));
    }

    private void inicializarTabla() {
        tblUsuarios.setModel(new DefaultTableModel(
                new Object[]{"Cédula", "Nombre", "Correo", "Rol"}, 0
        ));
    }

    private void inicializarListeners() {
        btnCrear.addActionListener(e -> crearUsuario());
        btnActualizar.addActionListener(e -> actualizarUsuario());
        btnEliminar.addActionListener(e -> eliminarUsuario());
        btnBuscarCedula.addActionListener(e -> buscarPorCedula());
        btnBuscarCorreo.addActionListener(e -> buscarPorCorreo());
        btnFiltrarRol.addActionListener(e -> filtrarPorRol());
        btnUndo.addActionListener(e -> deshacer());
    }

    private void crearUsuario() {
        usuarioService.crearUsuario(
                txtCedula.getText(),
                txtNombre.getText(),
                txtCorreo.getText(),
                (Rol) cbRol.getSelectedItem()
        );
        recargarTablaUsuarios();
        limpiarCampos();
    }

    private void actualizarUsuario() {
        usuarioService.actualizarUsuario(
                txtCedula.getText(),
                txtNombre.getText(),
                txtCorreo.getText(),
                (Rol) cbRol.getSelectedItem()
        );
        recargarTablaUsuarios();
    }

    private void eliminarUsuario() {
        usuarioService.eliminarUsuario(txtCedula.getText());
        recargarTablaUsuarios();
        limpiarCampos();
    }

    private void buscarPorCedula() {
        Usuario u = usuarioService.buscarPorCedula(txtCedula.getText());
        mostrarUsuarioEnCampos(u);
    }

    private void buscarPorCorreo() {
        Usuario u = usuarioService.buscarPorCorreo(txtCorreo.getText());
        mostrarUsuarioEnCampos(u);
    }

    private void filtrarPorRol() {
        Rol rol = (Rol) cbFiltroRol.getSelectedItem();
        List<Usuario> lista = usuarioService.listarPorRol(rol);
        llenarTabla(lista);
    }

    private void deshacer() {
        usuarioService.deshacer();
        recargarTablaUsuarios();
    }

    private void mostrarUsuarioEnCampos(Usuario u) {
        if (u == null) {
            JOptionPane.showMessageDialog(mainPanel, "Usuario no encontrado");
            return;
        }
        txtCedula.setText(u.getCedula());
        txtNombre.setText(u.getNombre());
        txtCorreo.setText(u.getCorreo());
        cbRol.setSelectedItem(u.getRol());
    }

    private void recargarTablaUsuarios() {
        llenarTabla(usuarioService.listarTodos());
    }

    private void llenarTabla(List<Usuario> usuarios) {
        DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();
        model.setRowCount(0);
        for (Usuario u : usuarios) {
            model.addRow(new Object[]{
                    u.getCedula(),
                    u.getNombre(),
                    u.getCorreo(),
                    u.getRol()
            });
        }
    }

    private void limpiarCampos() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
        cbRol.setSelectedIndex(0);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
