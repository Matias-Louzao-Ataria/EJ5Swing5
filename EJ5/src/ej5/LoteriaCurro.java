package ej5;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class LoteriaCurro extends JFrame implements ActionListener, ItemListener {
    
    private int x = 10,y = 30;
    private JButton jugar = new JButton("Jugar");
    private ArrayList<JComponent> componentes = new ArrayList<JComponent>();
    private JLabel acertados = new JLabel("Acertados:");
    private JLabel fallados = new JLabel("Fallados:");
    private ArrayList<Integer> numeros = new ArrayList<Integer>();
    private ArrayList<JCheckBox> numerosusuario = new ArrayList<JCheckBox>();
    private JMenuBar menu = new JMenuBar();
    private JMenuItem guardar = new JMenuItem("Guardar");
    private JMenuItem records = new JMenuItem("Ver records");
    private Guardar s;
    private File file = new File(System.getProperty("user.home")+System.getProperty("file.separator")+".records.txt");
    private Records record;
    private Timer timer = new Timer(300, this);
    private int conttitulo = 1;
    private int contnum = 0;

    public LoteriaCurro() {
        super("Lotería");
        this.setLayout(null);

        for(int i = 0;i < 49;i++){
            int inc = 0;
            
            if(i != 0){
                inc = 50;
            }

            JCheckBox chkbox = new JCheckBox(String.valueOf((i+1)));
            chkbox.setSize(chkbox.getPreferredSize());
            
            if(i % 7 == 0 && i != 0){
                x = 10;
                y+=20;
                chkbox.setLocation(x,y);
            }else{
                x+=inc;
                chkbox.setLocation(x,y);
            }
            chkbox.addItemListener(this);
            this.componentes.add(chkbox);
            this.add(chkbox);
        }
        
        this.jugar.setLocation(150,240);
        this.jugar.setSize(this.jugar.getPreferredSize());
        this.jugar.addActionListener(this);
        this.jugar.setEnabled(comprobar());
        this.add(jugar);

        this.acertados.setForeground(Color.GREEN);
        this.acertados.setLocation(150,280);
        this.acertados.setSize(this.acertados.getPreferredSize());
        this.add(acertados);

        this.fallados.setForeground(Color.RED);
        this.fallados.setLocation(150,320);
        this.fallados.setSize(this.fallados.getPreferredSize());
        this.add(fallados);

        //Menú
        JMenu opciones = new JMenu("Opciones");
        guardar.addActionListener(this);
        opciones.add(guardar);

        records.addActionListener(this);
        opciones.add(records);

        this.menu.add(opciones);
        this.menu.setSize(this.menu.getPreferredSize());
        this.setJMenuBar(menu);

        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if(arg0.getSource() == this.guardar){
            s = new Guardar(this);
            s.setSize(270,250);
            s.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            s.setVisible(true);
        }else if(arg0.getSource() == this.jugar){
            this.acertados.setText("Acertados:");
            this.fallados.setText("Fallados:");

            generar();

            for(int i = 0;i < numerosusuario.size();i++){
                JCheckBox actual = this.numerosusuario.get(i);
                actual.setForeground(null);
                if(this.numeros.contains(Integer.parseInt(actual.getText()))){
                    this.acertados.setText(this.acertados.getText()+" "+actual.getText());
                    this.acertados.setSize(this.acertados.getPreferredSize());
                    actual.setForeground(Color.GREEN);
                }else{
                    this.fallados.setText(this.fallados.getText()+" "+actual.getText());
                    this.fallados.setSize(this.fallados.getPreferredSize());
                    actual.setForeground(Color.RED);
                }
            }
            
        }else if(arg0.getSource() == this.timer){
            String titulo = "Lotería";
            if(this.conttitulo > titulo.length()){
                this.conttitulo = 1;
            }
            this.setTitle("");
            for(int i = 0;i < this.conttitulo;i++){
                this.setTitle(this.getTitle()+titulo.charAt(i));
            }
            this.conttitulo++;
        }else{
            record = new Records(this);
            record.pack();
            record.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            record.setVisible(true);
        }
        
    }

    @Override
    public void itemStateChanged(ItemEvent arg0) {
        JCheckBox chk = (JCheckBox)arg0.getSource();
        if(chk.isSelected()){
            this.contnum++;
            this.numerosusuario.add(chk);
        }else{
            this.contnum--;
            this.numerosusuario.remove(chk);
        }
        this.jugar.setEnabled(comprobar());
    }

    private boolean comprobar() {
        return this.contnum == 6;
    }

    private void generar() {
        int num = 0;
        this.numeros = new ArrayList<Integer>();
        for(int i = 0;i < 6;i++){
            num = (int)(Math.random()*49+1);
            
            if(this.numeros.contains(num)){
                i--;
            }else{
              this.numeros.add(num);
            }
        }
    }

    public JLabel getAcertados() {
        return acertados;
    }

    public File getFile() {
        return file;
    }
}