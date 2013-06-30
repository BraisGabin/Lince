/*
 *  Lince - Automatizacion de datos observacionales
 *  Copyright (C) 2010  Brais Gabin Moreira
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.braisgabin.lince;

import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author Brais
 */
public class LinceAcercaDe extends JDialog {

    private JButton closeButton;
    private final String title = java.util.ResourceBundle.getBundle("i18n.Bundle").getString("ACERCA DE LINCE");
    private final String applicationTitle = java.util.ResourceBundle.getBundle("i18n.Bundle").getString("LINCE");
    private final String versionLabelText = java.util.ResourceBundle.getBundle("i18n.Bundle").getString("VERSION:");
    private final String ApplicationVersion = "v1.3";
    private final String autorLabelText = java.util.ResourceBundle.getBundle("i18n.Bundle").getString("AUTORES:");
    private final String applicationAutor = "Brais Gabín, Oleguer Camerino";
    private final String homepageLabelText = java.util.ResourceBundle.getBundle("i18n.Bundle").getString("WEB:");
    private final String applicationHomepage = "http://www.observesport.com/";
    private final String appDescLabelText = java.util.ResourceBundle.getBundle("i18n.Bundle").getString("<HTML>AUTOMATIZACIÓN DE DATOS OBSERVACIONALES PARA “AVANCES TECNOLÓGICOS Y METODOLÓGICOS EN LA AUTOMATIZACIÓN DE ESTUDIOS OBSERVACIONALES EN DEPORTE”");
    private final Icon imageLabelIcon = new ImageIcon(getClass().getResource("/iconos/imagenes/acercaDeLince.jpg"));

    public LinceAcercaDe() {
        initComponents();
    }

    private void initComponents() {
        closeButton = new JButton(new AbstractAction(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("CERRAR")) {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JLabel appTitleLabel = new JLabel();
        JLabel versionLabel = new JLabel();
        JLabel appVersionLabel = new JLabel();
        JLabel vendorLabel = new JLabel();
        JLabel appVendorLabel = new JLabel();
        JLabel homepageLabel = new JLabel();
        JLabel appHomepageLabel = new JLabel();
        JLabel appDescLabel = new JLabel();
        JLabel imageLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(title);
        setModalityType(DEFAULT_MODALITY_TYPE);
        setResizable(false);

        appTitleLabel.setFont(appTitleLabel.getFont().deriveFont(appTitleLabel.getFont().getStyle() | Font.BOLD, appTitleLabel.getFont().getSize() + 4));
        appTitleLabel.setText(applicationTitle);

        versionLabel.setFont(versionLabel.getFont().deriveFont(versionLabel.getFont().getStyle() | Font.BOLD));
        versionLabel.setText(versionLabelText);

        appVersionLabel.setFont(new Font(appVersionLabel.getFont().getFamily(), 0, appVersionLabel.getFont().getSize()));
        appVersionLabel.setText(ApplicationVersion);

        vendorLabel.setFont(vendorLabel.getFont().deriveFont(vendorLabel.getFont().getStyle() | Font.BOLD));
        vendorLabel.setText(autorLabelText);

        appVendorLabel.setFont(new Font(appVendorLabel.getFont().getFamily(), 0, appVendorLabel.getFont().getSize()));
        appVendorLabel.setText(applicationAutor);

        homepageLabel.setFont(homepageLabel.getFont().deriveFont(homepageLabel.getFont().getStyle() | Font.BOLD));
        homepageLabel.setText(homepageLabelText);

        appHomepageLabel.setFont(new Font(appHomepageLabel.getFont().getFamily(), 0, appHomepageLabel.getFont().getSize()));
        appHomepageLabel.setText(applicationHomepage);

        appDescLabel.setFont(new Font(appDescLabel.getFont().getFamily(), 0, appDescLabel.getFont().getSize()));
        appDescLabel.setText(appDescLabelText);

        imageLabel.setIcon(imageLabelIcon);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(imageLabel).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(versionLabel).addComponent(vendorLabel).addComponent(homepageLabel)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(appVersionLabel).addComponent(appVendorLabel).addComponent(appHomepageLabel))).addComponent(appTitleLabel, GroupLayout.Alignment.LEADING).addComponent(appDescLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE).addComponent(closeButton)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(appTitleLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(appDescLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(versionLabel).addComponent(appVersionLabel)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(vendorLabel).addComponent(appVendorLabel)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(homepageLabel).addComponent(appHomepageLabel)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE).addComponent(closeButton).addContainerGap()));
        pack();
    }
}
