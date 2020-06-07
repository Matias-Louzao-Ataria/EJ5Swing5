package ej5;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

public class LoteriaCurro extends JFrame implements ActionListener{
    
    private int x = 10,y = 30;
    private JButton jugar = new JButton("Jugar");
    private ArrayList<JComponent> componentes = new ArrayList<JComponent>();
    private JLabel acertados = new JLabel("Acertados:");
    private JLabel fallados = new JLabel("Fallados:");
    private ArrayList<Integer> numeros = new ArrayList<Integer>();
    private ArrayList<Integer> numerosusuario = new ArrayList<Integer>();
    private JMenuBar menu = new JMenuBar();
    JMenuItem guardar = new JMenuItem("Guardar");
    JMenuItem records = new JMenuItem("Ver records");
    private Secundario s;

    public LoteriaCurro() {
        super("EJ5");
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
            this.componentes.add(chkbox);
            this.add(chkbox);
        }
        
        this.jugar.setLocation(150,240);
        this.jugar.setSize(this.jugar.getPreferredSize());
        this.jugar.addActionListener(this);
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

        opciones.addActionListener(this);
        opciones.add(records);

        this.menu.add(opciones);
        this.menu.setSize(this.menu.getPreferredSize());
        this.add(menu);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if(arg0.getSource() == this.guardar){
            s = new Secundario(this);
            s.setSize(270,500);
            s.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            s.setVisible(true);
        }else if(arg0.getSource() == this.jugar){
            generar();

            if(comprobar()){
                for(int i = 0;i < numerosusuario.size();i++){
                    if(this.numeros.contains(this.numerosusuario.get(i))){
                        this.acertados.setText(this.acertados.getText()+" "+this.numerosusuario.get(i));
                        this.acertados.setSize(this.acertados.getPreferredSize());
                    }else{
                        this.fallados.setText(this.fallados.getText()+" "+this.numerosusuario.get(i));
                        this.fallados.setSize(this.fallados.getPreferredSize());
                    }
                }
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione 6 números.");
            }
        }else{

        }
        
    }

    private boolean comprobar() {
        int cont = 0;
        this.numerosusuario = new ArrayList<Integer>();
        for(int i = 0;i < componentes.size() ;i++){
            JCheckBox j = (JCheckBox)componentes.get(i);

            if(j.isSelected()){
                cont++;
                this.numerosusuario.add(Integer.parseInt(j.getText()));
            }
        }
        if(cont != 6){
            return false;
        }else{
            return true;
        }
    }

    private void generar() {
        int num = 0;
        for(int i = 0;i < 6;i++){
            num = (int)(Math.random()*49+1);
            
            if(this.numeros.contains(num)){
                i--;
            }else{
              this.numeros.add(num);
            }
        }
    }
}