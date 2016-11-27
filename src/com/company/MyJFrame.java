package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Calendar;

public class MyJFrame extends JFrame {
    int width = 700;
    int height = 700;

    public MyJFrame() {
        setBounds(0, 0, 1500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        createBufferStrategy(2);
    }

    public double getArrowAngle(double divisions, double division) {
        return Math.PI / 2.0 - Math.PI * 2 * division / divisions;
    }

    public void startRendering() {
        Vector2D center = new Vector2D(getWidth() / 2, getHeight() / 2);
        String[] str = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        while (true) {
            BufferStrategy bs = getBufferStrategy();
            Graphics2D g2D = (Graphics2D) bs.getDrawGraphics();
            //Циферблат
            g2D.setStroke(new BasicStroke(2.0f));
            g2D.setBackground(Color.WHITE);
            g2D.clearRect(0, 0, getWidth(), getHeight());
            g2D.setColor(Color.BLACK);
            g2D.drawOval((int) center.getX() - width / 2, (int) center.getY() - height / 2, width, height);
            Calendar cal = Calendar.getInstance();
            //Секундная стрелка
            int seconds = cal.get(Calendar.SECOND);
            double secAng = getArrowAngle(60, seconds);
            Vector2D secArrow = new Vector2D(325, 0);
            secArrow = secArrow.rotate(secAng);
            g2D.setPaint(Color.GREEN);
            g2D.drawLine((int) center.getX(), (int) center.getY(), (int) center.add(secArrow).getX(),
                    (int) center.add(secArrow).getY());
            //Минутная стрелка ромбиком
            int minutes = cal.get(Calendar.MINUTE);
            double minAng = getArrowAngle(60.0, minutes);
            Vector2D minArrow1 = new Vector2D(125.0, 0.0);
            Vector2D minArrow2 = new Vector2D(125.0, 0.0);
            Vector2D minArrow3 = new Vector2D(250.0, 0.0);
            Vector2D minArrow4 = new Vector2D(250.0, 0.0);
            minArrow1 = minArrow1.rotate(minAng + (Math.PI / 18.0));
            minArrow2 = minArrow2.rotate(minAng - (Math.PI / 18.0));
            minArrow3 = minArrow3.rotate(minAng + (Math.PI / 2000.0));
            minArrow4 = minArrow4.rotate(minAng - (Math.PI / 2000.0));
            g2D.setPaint(Color.BLUE);
            g2D.drawLine((int) center.getX(), (int) center.getY(), (int) center.add(minArrow1).getX(), (int) center.add(minArrow1).getY());
            g2D.drawLine((int) center.getX(), (int) center.getY(), (int) center.add(minArrow2).getX(), (int) center.add(minArrow2).getY());
            g2D.drawLine((int) center.add(minArrow1).getX(), (int) center.add(minArrow1).getY(), (int) center.add(minArrow3).getX(), (int) center.add(minArrow3).getY());
            g2D.drawLine((int) center.add(minArrow2).getX(), (int) center.add(minArrow2).getY(), (int) center.add(minArrow4).getX(), (int) center.add(minArrow4).getY());
            //Часовая стрелка ромбиком
            int hours = cal.get(Calendar.HOUR_OF_DAY);
            double hAng = getArrowAngle(12.0, hours);
            Vector2D hArrow1 = new Vector2D(75.0, 0.0);
            Vector2D hArrow2 = new Vector2D(75.0, 0.0);
            Vector2D hArrow3 = new Vector2D(150.0, 0.0);
            Vector2D hArrow4 = new Vector2D(150.0, 0.0);
            hArrow1 = hArrow1.rotate(hAng + (Math.PI / 9.0));
            hArrow2 = hArrow2.rotate(hAng - (Math.PI / 9.0));
            hArrow3 = hArrow3.rotate(hAng + Math.PI / 2000.0);
            hArrow4 = hArrow4.rotate(hAng - Math.PI / 2000.0);
            g2D.setPaint(Color.RED);
            g2D.drawLine((int) center.getX(), (int) center.getY(), (int) center.add(hArrow1).getX(), (int) center.add(hArrow1).getY());
            g2D.drawLine((int) center.getX(), (int) center.getY(), (int) center.add(hArrow2).getX(), (int) center.add(hArrow2).getY());
            g2D.drawLine((int) center.add(hArrow1).getX(), (int) center.add(hArrow1).getY(), (int) center.add(hArrow3).getX(), (int) center.add(hArrow3).getY());
            g2D.drawLine((int) center.add(hArrow2).getX(), (int) center.add(hArrow2).getY(), (int) center.add(hArrow4).getX(), (int) center.add(hArrow4).getY());
            //Деления+Цифры
            for (int i = 1; i < 13; i++) {
                double division = getArrowAngle(12, i);
                Vector2D divArrow = new Vector2D(350, 0);
                Vector2D divArrow2 = new Vector2D(300, 0);
                Vector2D divArrow3 = new Vector2D(270, 0);
                divArrow = divArrow.rotate(division);
                divArrow2 = divArrow2.rotate(division);
                divArrow3 = divArrow3.rotate(division);
                g2D.setPaint(Color.BLACK);
                g2D.drawLine((int) center.add(divArrow).getX(), (int) center.add(divArrow).getY(),
                        (int) center.add(divArrow2).getX(), (int) center.add(divArrow2).getY());
                g2D.setFont(new Font("Times New Roman", Font.BOLD, 30));
                g2D.drawString(str[i - 1], (int) center.add(divArrow3).getX() - 10, (int) center.add(divArrow3).getY() + 10);
            }
            bs.show();
            Toolkit.getDefaultToolkit().sync();
        }
    }
}
