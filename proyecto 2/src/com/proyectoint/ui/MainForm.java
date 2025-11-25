package com.proyectoint.ui;

import com.proyectoint.datastructures.PuntosIndex;
import com.proyectoint.datastructures.ReportesIndex;
import com.proyectoint.datastructures.UsuariosIndex;
import com.proyectoint.service.PuntoService;
import com.proyectoint.service.ReporteService;
import com.proyectoint.service.UsuarioService;

import javax.swing.*;

public class MainForm extends JFrame {

    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;

    public MainForm(UsuariosIndex usuariosIndex,
                    PuntosIndex puntosIndex,
                    ReportesIndex reportesIndex) {
        setTitle("Proyecto INT - Gestión RAEE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        UsuarioService usuarioService = new UsuarioService(usuariosIndex);
        PuntoService puntoService = new PuntoService(puntosIndex);
        ReporteService reporteService = new ReporteService(reportesIndex);

        UsuariosForm usuariosForm = new UsuariosForm(usuarioService);
        PuntosForm puntosForm = new PuntosForm(puntoService);
        ReportesForm reportesForm = new ReportesForm(reporteService);

        tabbedPane1.add("Usuarios", usuariosForm.getMainPanel());
        tabbedPane1.add("Puntos de recolección", puntosForm.getMainPanel());
        tabbedPane1.add("Reportes", reportesForm.getMainPanel());
    }
}
