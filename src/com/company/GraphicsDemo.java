package com.company;
//Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GraphicsDemo extends JPanel implements KeyListener /* To get Keyboard Input */, ActionListener {

    Timer timer = new Timer(10,this); //Creating a Timer
    //Variables
    double seconds = 0; //Variable for Time
    int minutes = 0; //Variable for Minutes
    boolean started = false, go = false, finished = false; //See what part of the game are you a part of
    int selectionCircle = 300; //Where the selected color circle is
    int difficulty = 3; //What difficulty is it
    int laps = 1; //How many Laps
    //Player Attributes
    int playerx = 110, playery = 350; //Variables for the player's position
    boolean space = false; //Variable to see if the space bar has been pressed
    int playersteps = 0; //Variable to count how many steps you've taken
    boolean flat = false, top = false, left = true; //Variables to see what part of the track you are on
    double velocityX = 0, velocityY = 0; //Speed that the player moves
    double degrees = Math.PI; //Degree that the player is at
    int water = 75; //How hydrated is the player
    int colorPicked = 1; //What color is the player
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

            //Title and Instructions
            g2D.setColor(Color.BLACK);
            g2D.setFont(new Font("Lucida Grande", Font.PLAIN, 150));
            g2D.drawString("Mad Dash", 375, 200);
            g2D.setFont(new Font("Cochin", Font.PLAIN, 80));
            g2D.drawString("Press Enter when Ready", 350, 300);

            //Difficulty
            g2D.setFont(new Font("Menlo", Font.PLAIN, 50));
            g2D.drawString("Difficulty: " + difficulty, 125,375);
            //Laps
            g2D.drawString("Laps: " + laps, 1000,375);

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

            g2D.setColor(Color.BLACK); //Show which colour you've chosen
            ((Graphics2D) g2D).setStroke(new BasicStroke(10));
            g2D.drawOval(selectionCircle,525,100,100);



        } else if (started == true && finished == false){ //Main Game Screen

            if (go == false) {
                //Ready Text
                g2D.setColor(Color.BLACK);
                g2D.setFont(new Font("Futura", Font.BOLD, 85));
                g2D.drawString("Get Ready", 450, 375);
            }


            //Drawing Finishing line
            ((Graphics2D) g2D).setStroke(new BasicStroke(2));
            g2D.setColor(Color.BLACK);
            g2D.fillRect(100,350,100,20);
            g2D.setColor(Color.WHITE);
            g2D.fillRect(100,350,25,10);
            g2D.fillRect(125,360,25,10);
            g2D.fillRect(150,350,25,10);
            g2D.fillRect(175,360,25,10);

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
                if (seconds < 9.5) {
                    g2D.drawString("00:0" + Math.round(seconds), 600, 550);
                } else {
                    g2D.drawString("00:" + Math.round(seconds), 600, 550);
                }
            } else {
                if (minutes < 10) {
                    if (seconds < 9.5) {
                        g2D.drawString("0" + minutes + ":0" + Math.round(seconds), 600, 550);
                    } else {
                        g2D.drawString("0" + minutes + ":" + Math.round(seconds), 600, 550);
                    }
                } else {
                    if (seconds < 9.5) {
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
            g2D.setColor(Color.WHITE);
            g2D.setFont(new Font("Arial", Font.PLAIN, 30));
            g2D.drawString("Press W", 1200,685);
            g2D.setColor(Color.BLUE);
            g2D.fillRect(1175, 650, (water * 2), 50);
            g2D.fillOval(1340, 660, 40, 40);
            g2D.fillPolygon(new int[]{1344, 1360, 1376}, new int[]{668, 640, 668}, 3);
            g2D.setColor(Color.BLACK);
            g2D.drawRect(1175, 650, 150, 50);


        } else if (finished == true && started == true) {
            g2D.setColor(Color.BLACK);
            g2D.setFont(new Font("Big Caslon", Font.BOLD,140));
            g2D.drawString("Game Over", 400,125);
            g2D.setFont(new Font("Avenir Next", Font.PLAIN,100));
            //Final Time
            g2D.drawString("Time", 100,250);
            g2D.drawString("Place", 1000,250);
            g2D.setFont(new Font("American Typewriter", Font.PLAIN,90));
            if (minutes < 1) {
                if (seconds < 9.5) {
                    g2D.drawString("00:0" + Math.round(seconds), 100, 350);
                } else {
                    g2D.drawString("00:" + Math.round(seconds), 100, 350);
                }
            } else {
                if (minutes < 10) {
                    if (seconds < 9.5) {
                        g2D.drawString("0" + minutes + ":0" + Math.round(seconds), 100, 350);
                    } else {
                        g2D.drawString("0" + minutes + ":" + Math.round(seconds), 100, 350);
                    }
                } else {
                    if (seconds < 9.5) {
                        g2D.drawString(minutes + ":0" + Math.round(seconds), 100, 350);
                    } else {
                        g2D.drawString(minutes + ":" + Math.round(seconds), 100, 350);
                    }
                }
            }

            //Place
            if (degrees > p2Degrees) {
                g2D.drawString("First", 1000, 350);
            } else if (degrees == p2Degrees) {
                g2D.drawString("Tie", 1050,350);
            } else {
                g2D.drawString("Second", 975,350);
            }

        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (started == true && go == true && finished == false) {
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
            if (countTime >= (int) (Math.random() * (40 - 15 + 1) + 15) / difficulty * 3) {
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
        if (e.getKeyCode() == KeyEvent.VK_SPACE && started == true) { //What happens when spacebar is pressed
            space = true;
            go = true;
            if (water > 0) {
                water--;
                playersteps++;
            }
            if (playersteps > 150 * laps) {
                finished = true;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_W && started == true) { //What happens when W is pressed
            water += 10;
            if (water > 75) {
                water = 75;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER && started == false) { //What happens when enter is pressed
            started = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && started == false) { //What happens when the right arrow is pressed
            colorPicked++;
            selectionCircle += 150;
            if (colorPicked > 6) {
                colorPicked = 6;
            }
            if (selectionCircle > 1050) {
                selectionCircle = 1050;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && started == false) { //What happens when the left arrow is pressed
            colorPicked--;
            selectionCircle -= 150;
            if (colorPicked < 1) {
                colorPicked = 0;
            }
            if (selectionCircle < 300) {
                selectionCircle = 300;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && started == false) { //What happens when the up arrow is pressed
            difficulty++;
            if (difficulty > 10) {
                difficulty = 10;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && started == false) { //What happens when the down arrow is pressed
            difficulty--;
            if (difficulty < 1) {
                difficulty = 1;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_1 && started == false) { //Changes the amount of laps
            laps = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_2 && started == false) {
            laps = 2;
        } else if (e.getKeyCode() == KeyEvent.VK_3 && started == false) {
            laps = 3;
        }
    }
}
