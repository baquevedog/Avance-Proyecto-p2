package com.proyectoint;

import com.proyectoint.datastructures.PuntosIndex;
import com.proyectoint.datastructures.ReportesIndex;
import com.proyectoint.datastructures.UsuariosIndex;
import com.proyectoint.ui.MainForm;

import javax.swing.*;

public class MainApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UsuariosIndex usuariosIndex = new UsuariosIndex();
            PuntosIndex puntosIndex = new PuntosIndex();
            ReportesIndex reportesIndex = new ReportesIndex();

            MainForm frame = new MainForm(usuariosIndex, puntosIndex, reportesIndex);
            frame.setVisible(true);
        });
    }
}
