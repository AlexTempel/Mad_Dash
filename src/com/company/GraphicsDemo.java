package com.company;
//Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GraphicsDemo extends JPanel implements KeyListener /* To get Keyboard Input */, ActionListener {

    Timer timer = new Timer(10,this); //Constructing a Timer
    //Variables
    double seconds = 0; //Variable for Time
    int minutes = 0; //Variable for Minutes
    boolean started = false;
    int selectionCircle = 300;
    //Player Attributes
    int playerx = 110, playery = 350; //Variables for the player's position
    boolean space = false; //Variable to see if the space bar has been pressed
    int playersteps = 0; //Variable to count how many steps you've taken
    boolean flat = false, top = false, left = true; //Variables to see what part of the track you are on
    double velocityX = 0, velocityY = 0;
    double degrees = Math.PI; //Degree that the player is at
    int water = 75;
    int colorPicked = 1;
    //Player2 Variables
    int player2X = 160, player2Y = 350;
    double p2Degrees = Math.PI;
    boolean p2flat = false, p2top = false, p2left = true;
    double p2velocityX = 0, p2velocityY = 0;
    int countTime = 0;


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

        if (started == false) { //Start Screen

            g2D.setColor(Color.BLACK);
            g2D.setFont(new Font("Lucida Grande", Font.PLAIN, 150));
            g2D.drawString("Mad Dash", 375, 200);
            g2D.setFont(new Font("Cochin", Font.PLAIN, 80));
            g2D.drawString("Press Enter when Ready", 300, 300);

            //Color Picker
            g2D.setFont(new Font("Baskerville", Font.PLAIN, 60));
            g2D.drawString("Pick your Colour", 525, 450);
            g2D.setColor(Color.YELLOW);
            g2D.fillOval(300,525,100,100);
            g2D.setColor(Color.RED);
            g2D.fillOval(450,525,100,100);
            g2D.setColor(Color.PINK);
            g2D.fillOval(600,525,100,100);
            g2D.setColor(Color.GREEN);
            g2D.fillOval(750,525,100,100);
            g2D.setColor(Color.ORANGE);
            g2D.fillOval(900,525,100,100);
            g2D.setColor(Color.MAGENTA);
            g2D.fillOval(1050,525,100,100);

            g2D.setColor(Color.BLACK);
            ((Graphics2D) g2D).setStroke(new BasicStroke(10));
            g2D.drawOval(selectionCircle,525,100,100);

        } else { //Main Game Screen
            //Drawing Inside of the Track
            g2D.setColor(Color.DARK_GRAY);
            ((Graphics2D) g2D).setStroke(new BasicStroke(10));
            g2D.drawArc(200, 150, 425, 425, 90, 180);
            g2D.drawArc(750, 150, 425, 425, 90, -180);
            g2D.drawLine(412, 150, 963, 150);
            g2D.drawLine(412, 575, 963, 575);

            //Drawing Running Lines
            g2D.setColor(Color.WHITE);
            ((Graphics2D) g2D).setStroke(new BasicStroke(5));
            g2D.drawArc(150, 100, 525, 525, 90, 180);
            g2D.drawArc(100, 50, 625, 625, 90, 180);
            g2D.drawLine(412, 100, 963, 100);
            g2D.drawLine(412, 50, 963, 50);
            g2D.drawLine(412, 625, 963, 625);
            g2D.drawLine(412, 675, 963, 675);
            g2D.drawArc(700, 100, 525, 525, 90, -180);
            g2D.drawArc(650, 50, 625, 625, 90, -180);

            //Creating the Runner
            if (colorPicked == 1) {
                g2D.setColor(Color.YELLOW);
            } else if (colorPicked == 2) {
                g2D.setColor(Color.RED);
            } else if (colorPicked == 3) {
                g2D.setColor(Color.PINK);
            } else if (colorPicked == 4) {
                g2D.setColor(Color.GREEN);
            } else if (colorPicked == 5) {
                g2D.setColor(Color.ORANGE);
            } else if (colorPicked == 6) {
                g2D.setColor(Color.MAGENTA);
            }
            g2D.fillOval(playerx, playery, 30, 30);

            //Competitor runner
            g2D.setColor(Color.BLUE);
            g2D.fillOval(player2X, player2Y, 30, 30);


            //Creating the Clock on Screen
            g2D.setColor(Color.BLACK);
            g2D.setFont(new Font("Comic Sans", Font.PLAIN, 75));
            if (minutes < 1) {
                if (seconds < 10) {
                    g2D.drawString("00:0" + Math.round(seconds), 600, 550);
                } else {
                    g2D.drawString("00:" + Math.round(seconds), 600, 550);
                }
            } else {
                if (minutes < 10) {
                    if (seconds < 10) {
                        g2D.drawString("0" + minutes + ":0" + Math.round(seconds), 600, 550);
                    } else {
                        g2D.drawString("0" + minutes + ":" + Math.round(seconds), 600, 550);
                    }
                } else {
                    if (seconds < 10) {
                        g2D.drawString(minutes + ":0" + Math.round(seconds), 600, 550);
                    } else {
                        g2D.drawString(minutes + ":" + Math.round(seconds), 600, 550);
                    }
                }
            }

            //Added Title and Instructions
            g2D.setFont(new Font("Oxygen", Font.BOLD, 120));
            g2D.drawString("MAD DASH", 350, 300);
            g2D.setFont(new Font("Bahnschrift", Font.PLAIN, 70));
            g2D.drawString("Mash Spacebar", 450, 450);

            //Drawing Water Counter
            ((Graphics2D) g2D).setStroke(new BasicStroke(3));
            g2D.setColor(Color.BLUE);
            g2D.fillRect(1175, 650, (water * 2), 50);
            g2D.fillOval(1340, 660, 40, 40);
            g2D.fillPolygon(new int[]{1344, 1360, 1376}, new int[]{668, 640, 668}, 3);
            g2D.setColor(Color.BLACK);
            g2D.drawRect(1175, 650, 150, 50);
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (seconds >= 60) {
            seconds -= 60;
            minutes++;
        }
        seconds += 0.01; //Time Counter
        countTime += 1;
        //System.out.println(minutes + ":" + Math.round(seconds) + " seconds have passed");

        //Movement
        if (space == true && water > 0) {
            /* System.out.println("The degrees is: " + degrees + " The player x is: " + playerx + " The player Y is: " + playery
            + " The quadrant is: " + "top = " + top + " flat = " + flat + " left = " + left); */
            if (flat == false && top == false && left == true) {
                    degrees += (Math.PI / 48);
                    velocityX = -20 * Math.sin(degrees);
                    velocityY = -20 * Math.cos(degrees);
                            //+ " The velocity x is: " + velocityX + " The velocity y is: " + velocityY); // Debugging tool
            } else if (flat == true && top == false) {
                velocityX = 20;
                velocityY = 0;
            } else if (flat == false && top == false && left == false) {
                degrees += (Math.PI / 48);
                velocityX = -20 * Math.sin(degrees);
                velocityY = -20 * Math.cos(degrees);
            } else if (flat == false && top == true && left == false) {
                degrees += (Math.PI / 48);
                velocityX = -16 * Math.sin(degrees);
                velocityY = -16 * Math.cos(degrees);
            } else if (flat == true && top == true) {
                velocityX = -20;
                velocityY = 0;
            } else if (flat == false && top == true && left == true) {
                degrees += (Math.PI / 48);
                velocityX = -20 * Math.sin(degrees);
                velocityY = -20 * Math.cos(degrees);
            }
        }

        //Determining where the player is on the board
        if (top == false) {
            if (playerx > 395 && playerx < 935) {
                flat = true;
            } else {
                flat = false;
            }
        } else {
            if (playerx > 381 && playerx < 940) {
                flat = true;
            } else {
                flat = false;
            }
        }

        if (left == true) {
            if (playery < 345) {
                top = true;
            } else {
                top = false;
            }
        } else {
            if (playery < 286) {
                top = true;
            } else {
                top = false;
            }
        }
        if (playerx > 700) {
            left = false;
        } else {
            left = true;
        }

        //Move the player when the spacebar is pressed
        if (space == true && water > 0) {
            playerx += velocityX;
            playery += velocityY;
        }

        space = false;

        //Competitor Movements
        if (countTime >= (int)(Math.random() * (35 - 10 + 1) + 10)) {
            countTime = 0;
            if (p2flat == false && p2top == false && p2left == true) {
                p2Degrees += (Math.PI / 48);
                p2velocityX = -16.5 * Math.sin(p2Degrees);
                p2velocityY = -16.5 * Math.cos(p2Degrees);
            } else if (p2flat == true && p2top == false) {
                p2velocityX = 20;
                p2velocityY = 0;
            } else if (p2flat == false && p2top == false && p2left == false) {
                p2Degrees += (Math.PI / 48);
                p2velocityX = -16.5 * Math.sin(p2Degrees);
                p2velocityY = -16.5 * Math.cos(p2Degrees);
            } else if (p2flat == false && p2top == true && p2left == false) {
                p2Degrees += (Math.PI / 48);
                p2velocityX = -16.5 * Math.sin(p2Degrees);
                p2velocityY = -14 * Math.cos(p2Degrees);
            } else if (p2flat == true && p2top == true) {
                p2velocityX = -20;
                p2velocityY = 0;
            } else if (p2flat == false && p2top == true && p2left == true) {
                p2Degrees += (Math.PI / 48);
                p2velocityX = -14 * Math.sin(p2Degrees);
                p2velocityY = -16.5 * Math.cos(p2Degrees);
            }

            if (player2X < 700) {
                p2left = true;
            } else {
                p2left = false;
            }
            if (p2left == true) {
                if (player2Y < 345) {
                    p2top = true;
                } else {
                    p2top = false;
                }
            } else {
                if (player2Y < 380) {
                    p2top = true;
                } else {
                    p2top = false;
                }
            }
            if (p2top == false) {
                if (player2X > 395 && player2X < 935) {
                    p2flat = true;
                } else {
                    p2flat = false;
                }
            } else {
                if (player2X > 400 && player2X < 940) {
                    p2flat = true;
                } else {
                    p2flat = false;
                }
            }

            player2X += p2velocityX;
            player2Y += p2velocityY;
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
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            space = true;
            water--;
            playersteps++;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            water += 10;
            if (water > 75) {
                water = 75;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            started = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            colorPicked += 1;
            selectionCircle += 150;
            if (colorPicked > 6) {
                colorPicked = 6;
            }
            if (selectionCircle > 1050) {
                selectionCircle = 1050;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            colorPicked -= 1;
            selectionCircle -= 150;
            if (colorPicked < 1) {
                colorPicked = 0;
            }
            if (selectionCircle < 300) {
                selectionCircle = 300;
            }
        }
    }
}
