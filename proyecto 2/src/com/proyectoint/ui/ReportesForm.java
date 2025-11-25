package com.proyectoint.ui;

import com.proyectoint.model.Reporte;
import com.proyectoint.model.TipoResiduo;
import com.proyectoint.model.ZonaEstadistica;
import com.proyectoint.service.ReporteService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ReportesForm {

    private JPanel mainPanel;
    private JTextField txtFecha;
    private JTextField txtZona;
    private JComboBox<TipoResiduo> cbTipoResiduo;
    private JTextField txtPesoKg;
    private JButton btnCrearReporte;
    private JTable tblReportes;
    private JTextField txtDesdeFecha;
    private JTextField txtHastaFecha;
    private JButton btnFiltrarFecha;
    private JTextField txtZonaFiltro;
    private JButton btnFiltrarZona;
    private JButton btnTopZonas;
    private JTable tblTopZonas;

    private final ReporteService reporteService;

    public ReportesForm(ReporteService reporteService) {
        this.reporteService = reporteService;
        inicializarCombos();
        inicializarTablas();
        inicializarListeners();
        recargarTablaReportes();
    }

    private void inicializarCombos() {
        cbTipoResiduo.setModel(new DefaultComboBoxModel<>(TipoResiduo.values()));
    }

    private void inicializarTablas() {
        tblReportes.setModel(new DefaultTableModel(
                new Object[]{"ID", "Fecha", "Zona", "Tipo", "Peso (Kg)"}, 0
        ));

        tblTopZonas.setModel(new DefaultTableModel(
                new Object[]{"Zona", "Total Kg"}, 0
        ));
    }

    private void inicializarListeners() {
        btnCrearReporte.addActionListener(e -> crearReporte());
        btnFiltrarFecha.addActionListener(e -> filtrarPorFecha());
        btnFiltrarZona.addActionListener(e -> filtrarPorZona());
        btnTopZonas.addActionListener(e -> mostrarTopZonas());
    }

    private void crearReporte() {
        try {
            LocalDate fecha = LocalDate.parse(txtFecha.getText()); // formato ISO: 2025-11-23
            String zona = txtZona.getText();
            TipoResiduo tipo = (TipoResiduo) cbTipoResiduo.getSelectedItem();
            double peso = Double.parseDouble(txtPesoKg.getText());

            reporteService.crearReporte(fecha, zona, tipo, peso);
            recargarTablaReportes();
            limpiarCampos();
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Fecha inválida (usa formato AAAA-MM-DD)");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Peso inválido");
        }
    }

    private void filtrarPorFecha() {
        try {
            LocalDate desde = LocalDate.parse(txtDesdeFecha.getText());
            LocalDate hasta = LocalDate.parse(txtHastaFecha.getText());
            List<Reporte> lista = reporteService.buscarPorRangoFechas(desde, hasta);
            llenarTablaReportes(lista);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Fechas inválidas (usa formato AAAA-MM-DD)");
        }
    }

    private void filtrarPorZona() {
        String zona = txtZonaFiltro.getText();
        List<Reporte> lista = reporteService.buscarPorZona(zona);
        llenarTablaReportes(lista);
    }

    private void mostrarTopZonas() {
        String input = JOptionPane.showInputDialog(mainPanel, "¿Cuántas zonas mostrar?", "Top zonas", JOptionPane.QUESTION_MESSAGE);
        if (input == null) return;
        try {
            int k = Integer.parseInt(input);
            List<ZonaEstadistica> top = reporteService.topZonas(k);
            llenarTablaTopZonas(top);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Número inválido");
        }
    }

    private void recargarTablaReportes() {
        llenarTablaReportes(reporteService.listarTodos());
    }

    private void llenarTablaReportes(List<Reporte> lista) {
        DefaultTableModel model = (DefaultTableModel) tblReportes.getModel();
        model.setRowCount(0);
        for (Reporte r : lista) {
            model.addRow(new Object[]{
                    r.getId(),
                    r.getFecha(),
                    r.getZona(),
                    r.getTipoResiduo(),
                    r.getPesoKg()
            });
        }
    }

    private void llenarTablaTopZonas(List<ZonaEstadistica> lista) {
        DefaultTableModel model = (DefaultTableModel) tblTopZonas.getModel();
        model.setRowCount(0);
        for (ZonaEstadistica z : lista) {
            model.addRow(new Object[]{
                    z.getZona(),
                    z.getTotalKg()
            });
        }
    }

    private void limpiarCampos() {
        txtFecha.setText("");
        txtZona.setText("");
        txtPesoKg.setText("");
        cbTipoResiduo.setSelectedIndex(0);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
