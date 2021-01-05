package com.company;
//Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GraphicsDemo extends JPanel implements KeyListener /* To get Keyboard Input*/, ActionListener {

    Timer timer = new Timer(10,this); //Constructing a Timer
    //Variables
    double seconds = 0; //Variable for Time
    int minutes = 0; //Variable for Minutes
    int playerx = 100, playery = 500; //Variables for the player's position
    boolean space = false; //Variable to see if the space bar has been pressed
    int playersteps = 0; //Variable to count how many steps you've taken
    boolean flat = false, top = false, left = true; //Variables to see what part of the track you are on
    double velocityX = 3, velocityY = 0;

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
        if (minutes < 1) {
            if (seconds < 10 ) {
                g2D.drawString("00:0" + Math.round(seconds),800,550);
            } else{
                g2D.drawString("00:" + Math.round(seconds),800,550);
            }
        } else {
            if (minutes < 10) {
                if (seconds < 10) {
                    g2D.drawString("0" + minutes + ":0" + Math.round(seconds),800,550);
                } else {
                    g2D.drawString("0" + minutes + ":" + Math.round(seconds),800,550);
                }
            } else {
                if (seconds < 10) {
                    g2D.drawString( minutes + ":0" + Math.round(seconds),800,550);
                } else {
                    g2D.drawString(minutes + ":" + Math.round(seconds),800,550);
                }
            }
        }
        //Added Title and Instructions
        g2D.setFont(new Font("Oxygen",Font.BOLD,120));
        g2D.drawString("MAD DASH", 600,400);
        g2D.setFont(new Font("Bahnschrift",Font.PLAIN,80));
        g2D.drawString("Mash Spacebar", 650,700);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (seconds >= 60) {
            seconds -= 60;
            minutes++;
        }
        seconds += 0.01; //Time Counter
        //System.out.println(minutes + ":" + Math.round(seconds) + " seconds have passed");

        //Move the player when the spacebar is pressed
        if (space == true) {
            playerx += velocityX;
            playery += velocityY;
        }

        //Move diagonally when on curve
        if (flat == false && top == false && left == true) {
            velocityX = 3;
            velocityY = 4;
        } else if (flat == true && top == false) {
            velocityX = 10;
            velocityY = 0;
        } else if (flat == false && top == false && left == false) {
            velocityX = 4;
            velocityY = -4;
        } else if (flat == false && top == true && left == false) {
            velocityX = -3;
            velocityY = -4;
        } else if (flat == true && top == true) {
            velocityX = -10;
            velocityY = 0;
        } else if (flat == false && top == true && left == true) {
            velocityX = -3;
            velocityY = 4;
        }

        space = false;
        if (playerx > 350 && playerx < 1350) {
            flat = true;
            //System.out.println("Flat is True");
        } else {
            flat = false;
            //System.out.println("Flat is False");
        }
        if (playery < 500) {
            top = true;
            System.out.println("Top is True");
        } else {
            top = false;
            //System.out.println("Top is False");
        }
        if (playerx > 900) {
            left = false;
        } else {
            left = true;
        }

        repaint();
    }

    //Keyboard Input
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
        space = true;
        playersteps++;
    }
}
