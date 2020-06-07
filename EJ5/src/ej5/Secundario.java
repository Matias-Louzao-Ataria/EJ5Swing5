package ej5;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class Secundario extends JDialog {
    
    private LoteriaCurro c;
    private JTextArea txa = new JTextArea();
    private JButton aceptar = new JButton("Aceptar");
    private JButton cancelar = new JButton("Cancelar");

    public Secundario(LoteriaCurro c) {
        super(c, "EJ5",true);
        this.setLayout(null);
        this.c = c;
        txa.setRows(5);
        txa.setColumns(20);
        txa.setSize(txa.getPreferredSize());
        txa.setLocation(20,50);
        this.add(txa);

        this.aceptar.setLocation(20,150);
        this.aceptar.setSize(this.aceptar.getPreferredSize());
        this.add(aceptar);

        this.cancelar.setLocation(160,150);
        this.cancelar.setSize(this.cancelar.getPreferredSize());
        this.add(cancelar);
    }
}