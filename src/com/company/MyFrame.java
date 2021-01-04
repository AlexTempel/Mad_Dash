package com.company;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    GraphicsDemo graphicDemo = new GraphicsDemo();
    int windowHeight = 1000;
    int windowLength = 1800;

    public MyFrame() {
        this.setSize(windowLength,windowHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(graphicDemo);
        this.setVisible(true);
    }
}
