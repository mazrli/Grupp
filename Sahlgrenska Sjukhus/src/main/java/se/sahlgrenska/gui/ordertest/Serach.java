package se.sahlgrenska.gui.ordertest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Serach extends JFrame implements ActionListener {

    Ordertest Ordertest = new Ordertest();

     JFrame f;


     JButton b;

     JLabel l;

     JTextArea jt;


   public Serach()
    {
    }

    // main class
    public  void main(String[] args)
    {
        f = new JFrame("textfield");

        l = new JLabel("nothing entered");

        b = new JButton("submit");

        Serach te = new Serach();

        b.addActionListener(te);

        jt = new JTextArea(10, 10);

        JPanel p = new JPanel();

        p.add(jt);
        p.add(b);
        p.add(l);

        f.add(p);
        f.setSize(300, 300);

        f.show();



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("submit")) {
            l.setText(jt.getText());
        }
    }


}
