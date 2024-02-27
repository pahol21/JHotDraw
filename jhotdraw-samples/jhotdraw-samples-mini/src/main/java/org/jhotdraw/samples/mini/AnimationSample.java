/*
 * @(#)AnimationSample.java
 *
 * Copyright (c) 2009-2010 The authors and contributors of JHotDraw.
 *
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.samples.mini;

import org.jhotdraw.draw.figure.EllipseFigure;
import java.awt.Color;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import org.jhotdraw.draw.*;
import static org.jhotdraw.draw.AttributeKeys.*;

/**
 * AnimationSample displays a rotating ellipse figure.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class AnimationSample extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Creates new form AnimationSample
     */
    public AnimationSample() {
        initComponents();
        DefaultDrawingView view = new DefaultDrawingView();
        view.setDrawingDoubleBuffered(false);
        add(view);
        setSize(400, 400);
        Drawing d = new DefaultDrawing();
        d.set(CANVAS_FILL_COLOR, new Color(0x76a9d2));
        final EllipseFigure ellipse = new EllipseFigure(160, 160, 80, 80);
        ellipse.set(STROKE_WIDTH, 7d);
        d.add(ellipse);
        view.setDrawing(d);
        Timer t = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double alpha = 2d * Math.PI * (System.currentTimeMillis() % 1000) / 1000d;
                ellipse.willChange();
                ellipse.setBounds(new Rectangle2D.Double(160 + Math.sin(alpha) * 100, 160 + Math.cos(alpha) * 100, 80, 80));
                ellipse.changed();
            }
        });
        t.setRepeats(true);
        t.start();
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.setProperty("apple.awt.graphics.UseQuartz", "false");
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AnimationSample().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
