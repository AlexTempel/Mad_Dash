package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GraphicsDemo extends JPanel implements KeyListener, ActionListener {

    Timer timer = new Timer(1000,this);
    int seconds = 0;

    public GraphicsDemo() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(222, 184, 135));

        Graphics g2D = (Graphics2D) g;

        g2D.setColor(Color.DARK_GRAY);
        ((Graphics2D) g2D).setStroke(new BasicStroke(10));
        g2D.drawLine(450,200,1350,200);
        g2D.drawLine(450,800,1350,800);
        g2D.drawArc(250,200,400,600,90,180);
        g2D.drawArc(1150,200,400,600,90,-180);

        g2D.setColor(Color.BLACK);
        g2D.setFont(new Font("Comic Sans",Font.PLAIN,75));
        g2D.drawString(seconds+"",900,350);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        seconds++;
        System.out.println(seconds + " seconds have passed");

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
