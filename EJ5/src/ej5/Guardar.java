package ej5;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Guardar extends JDialog implements ActionListener {
    
    private LoteriaCurro l;
    private JTextArea txa = new JTextArea();
    private JLabel label = new JLabel("Escribe tu nombre entre las comillas!");
    private JButton aceptar = new JButton("Aceptar");
    private JButton cancelar = new JButton("Cancelar");

    public Guardar(LoteriaCurro c) {
        super(c, "Guardar");
        this.setLayout(null);
        this.l = c;
        
        this.label.setLocation(5,20);
        this.label.setSize(this.label.getPreferredSize());
        this.add(label);

        this.txa.setRows(5);
        this.txa.setColumns(20);
        this.txa.setText("Nombre: \"\"  "+this.l.getAcertados().getText());
        this.txa.setSize(this.txa.getPreferredSize());
        this.txa.setLocation(20,50);
        this.add(this.txa);

        this.aceptar.setLocation(20,150);
        this.aceptar.setSize(this.aceptar.getPreferredSize());
        this.aceptar.addActionListener(this);
        this.add(aceptar);

        this.cancelar.setLocation(160,150);
        this.cancelar.setSize(this.cancelar.getPreferredSize());
        this.cancelar.addActionListener(this);
        this.add(cancelar);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getSource() == this.cancelar){
            this.dispose();
        }else{
            try (PrintWriter esc = new PrintWriter(new FileWriter(this.l.getFile(),true))) {
                String res = this.txa.getText();
                res = res.replace("\"", "");
                esc.append(res.replace("Nombre: ", "")+"\n");
            } catch (SecurityException | IllegalArgumentException | IOException e) {
                JOptionPane.showMessageDialog(this, "Error al escribir el archivo!");
            }
        }
    }
}