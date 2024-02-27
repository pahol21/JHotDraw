/*
 * @(#)ArrangeToolBar.java
 *
 * Copyright (c) 2008 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.samples.svg.gui;

import dk.sdu.mmmi.featuretracer.lib.FeatureEntryPoint;
import org.jhotdraw.draw.action.arrange.ArrangeService;
import org.jhotdraw.draw.SPILocator;
import org.jhotdraw.gui.plaf.palette.PaletteButtonUI;
import java.awt.*;
import java.util.Collection;
import javax.swing.*;
import javax.swing.border.*;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.action.*;
import org.jhotdraw.draw.event.SelectionComponentDisplayer;
import org.jhotdraw.util.*;

/**
 * ArrangeToolBar.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class ArrangeToolBar extends AbstractToolBar {

    private static final long serialVersionUID = 1L;
    private SelectionComponentDisplayer displayer;

    /**
     * Creates new instance.
     */
    public ArrangeToolBar() {
        setName(ResourceBundleUtil.getBundleSamplesSvgLabels().getString(getID() + ".toolbar"));
    }

    @Override
    public void setEditor(DrawingEditor newValue) {
        if (displayer != null) {
            displayer.dispose();
            displayer = null;
        }
        super.setEditor(newValue);
        if (newValue != null) {
            displayer = new SelectionComponentDisplayer(editor, this);
            displayer.setVisibleIfCreationTool(false);
        }
    }

    private Collection<? extends ArrangeService> getArrangeServices() {
        return SPILocator.locateAll(ArrangeService.class);
    }

    @FeatureEntryPoint(value = "arrangeToolBar")
    @Override
    protected JComponent createDisclosedComponent(int state) {
        if (state != 1) {
            return null;
        } else {
            JPanel panel = createPanelWithBorder();
            if (editor == null) {
                return panel;
            }

            ResourceBundleUtil labels = ResourceBundleUtil.getBundleSamplesSvgLabels();

            GridBagConstraints gbc = new GridBagConstraints();
            GridBagLayout layout = new GridBagLayout();
            panel.setLayout(layout);

            int yPos = 0;
            for (ArrangeService arrangeService : getArrangeServices()) {
                addButtonToPanel(panel, arrangeService.createWithEditor(editor), arrangeService.getID(), labels, gbc, yPos);
                yPos++;
            }
            return panel;
        }
    }

    private JPanel createPanelWithBorder() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(5, 5, 5, 8));
        return panel;
    }

    private void addButtonToPanel(JPanel panel, AbstractSelectedAction action, String id, ResourceBundleUtil labels, GridBagConstraints gbc, int yPos) {
        AbstractButton button = new JButton(action);
        disposables.add(action);
        button.setUI((PaletteButtonUI) PaletteButtonUI.createUI(button));
        button.setText(null);
        labels.configureToolBarButton(button, id);
        button.putClientProperty("hideActionText", Boolean.TRUE);

        gbc.gridy = yPos;
        gbc.anchor = (yPos == 0) ? GridBagConstraints.EAST : GridBagConstraints.NORTH;
        if (yPos == 1) {
            gbc.insets = new Insets(3, 0, 0, 0);
            gbc.weighty = 1f;
        }
        panel.add(button, gbc);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setOpaque(false);
    }// </editor-fold>//GEN-END:initComponents
    @Override
    protected String getID() {
        return "arrange";
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
