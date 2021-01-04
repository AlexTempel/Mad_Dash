package com.company;
//Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GraphicsDemo extends JPanel implements KeyListener /* To get Keyboard Input*/, ActionListener {

    Timer timer = new Timer(1000,this); //Constructing a Timer
    //Variables
    int seconds = 0; //Variable for Time
    int playerx = 100, playery = 500; //Variables for the player's position
    boolean space = false; //Variable to see if the space bar has been pressed
    int playersteps = 0; //Variable to count how many steps you've taken
    boolean flat = false, top = false; //Variables to see what part of the track you are on

    public GraphicsDemo() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(222, 184, 135)); //Setting the Background and Colour

        Graphics g2D = (Graphics2D) g;

        //Drawing Inside of the Track
        g2D.setColor(Color.DARK_GRAY);
        ((Graphics2D) g2D).setStroke(new BasicStroke(10));
        g2D.drawLine(450,250,1350,250);
        g2D.drawLine(450,750,1350,750);
        g2D.drawArc(250,250,400,500,90,180);
        g2D.drawArc(1150,250,400,500,90,-180);

        //Drawing Running Lines
        g2D.setColor(Color.WHITE);
        ((Graphics2D) g2D).setStroke(new BasicStroke(5));
        g2D.drawLine(450,100,1350,100);
        g2D.drawLine(450,175,1350,175);
        g2D.drawLine(450,825,1350,825);
        g2D.drawLine(450,900,1350,900);
        g2D.drawArc(75,100,750,800,90,180);
        g2D.drawArc(150,175,600,650,90,180);
        g2D.drawArc(975,100,750,800,90,-180);
        g2D.drawArc(1050,175,600,650,90,-180);

        //Creating the Runner
        g2D.setColor(Color.YELLOW);
        g2D.fillOval(playerx,playery,30,30);

        //Creating the Clock on Screen
        g2D.setColor(Color.BLACK);
        g2D.setFont(new Font("Comic Sans",Font.PLAIN,75));
        g2D.drawString(seconds+"",900,350);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        seconds++;
        System.out.println(seconds + " seconds have passed");

        space = false;
        if (playerx > 450 && playerx < 1350) {
            flat = true;
        } else {
            flat = false;
        }
        if (playery > 500) {
            top = true;
        } else {
            top = false;
        }

        repaint();
    }

    //Keyboard Input
    @Override
    public void keyTyped(KeyEvent e) {
        space = true;
        playersteps++;
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
