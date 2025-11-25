package com.proyectoint.ui;

import com.proyectoint.model.PuntoRecoleccion;
import com.proyectoint.model.TipoResiduo;
import com.proyectoint.service.PuntoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PuntosForm {

    private JPanel mainPanel;
    private JTextField txtDireccion;
    private JTextField txtBarrio;
    private JTextField txtLatitud;
    private JTextField txtLongitud;
    private JComboBox<TipoResiduo> cbTipoResiduo;
    private JButton btnAgregar;
    private JButton btnBuscarDireccion;
    private JButton btnFiltrarTipo;
    private JTextField txtBuscarDireccion;
    private JTable tblPuntos;
    private JComboBox<TipoResiduo> cbFiltroTipo;

    private final PuntoService puntoService;

    public PuntosForm(PuntoService puntoService) {
        this.puntoService = puntoService;
        inicializarCombos();
        inicializarTabla();
        inicializarListeners();
        recargarTablaPuntos();
    }

    private void inicializarCombos() {
        cbTipoResiduo.setModel(new DefaultComboBoxModel<>(TipoResiduo.values()));
        cbFiltroTipo.setModel(new DefaultComboBoxModel<>(TipoResiduo.values()));
    }

    private void inicializarTabla() {
        tblPuntos.setModel(new DefaultTableModel(
                new Object[]{"ID", "Dirección", "Barrio", "Lat", "Lon", "Tipo"}, 0
        ));
    }

    private void inicializarListeners() {
        btnAgregar.addActionListener(e -> agregarPunto());
        btnBuscarDireccion.addActionListener(e -> buscarPorDireccion());
        btnFiltrarTipo.addActionListener(e -> filtrarPorTipo());
    }

    private void agregarPunto() {
        try {
            double lat = Double.parseDouble(txtLatitud.getText());
            double lon = Double.parseDouble(txtLongitud.getText());
            TipoResiduo tipo = (TipoResiduo) cbTipoResiduo.getSelectedItem();

            puntoService.crearPunto(
                    txtDireccion.getText(),
                    txtBarrio.getText(),
                    lat,
                    lon,
                    tipo
            );
            recargarTablaPuntos();
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Latitud/Longitud inválidas");
        }
    }

    private void buscarPorDireccion() {
        String direccion = txtBuscarDireccion.getText();
        PuntoRecoleccion p = puntoService.buscarPorDireccionExacta(direccion);
        if (p == null) {
            List<PuntoRecoleccion> lista = puntoService.buscarPorPrefijoDireccion(direccion);
            llenarTabla(lista);
        } else {
            llenarTabla(List.of(p));
        }
    }

    private void filtrarPorTipo() {
        TipoResiduo tipo = (TipoResiduo) cbFiltroTipo.getSelectedItem();
        List<PuntoRecoleccion> lista = puntoService.listarPorTipo(tipo);
        llenarTabla(lista);
    }

    private void recargarTablaPuntos() {
        llenarTabla(puntoService.listarTodos());
    }

    private void llenarTabla(List<PuntoRecoleccion> lista) {
        DefaultTableModel model = (DefaultTableModel) tblPuntos.getModel();
        model.setRowCount(0);
        for (PuntoRecoleccion p : lista) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getDireccion(),
                    p.getBarrio(),
                    p.getLatitud(),
                    p.getLongitud(),
                    p.getTipoResiduo()
            });
        }
    }

    private void limpiarCampos() {
        txtDireccion.setText("");
        txtBarrio.setText("");
        txtLatitud.setText("");
        txtLongitud.setText("");
        cbTipoResiduo.setSelectedIndex(0);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
