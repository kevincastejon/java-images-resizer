/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundaries;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.ImageFilter;
import java.awt.image.Kernel;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import lepiou.FileNameInfo;
import org.imgscalr.Scalr;
import sun.awt.image.BufferedImageGraphicsConfig;

/**
 *
 * @author lepioo
 */
public class MainFrame extends javax.swing.JFrame {

    private FileChooseFrame fileChooseFrame;
    private OutputChooseFrame outputChooseFrame;
    private ColorPickFrame colorPickFrame;
    private ProgressFrame progressFrame;
    private String outputPath;
    private Color canvasColor = Color.BLACK;
    public static MainFrame instance;
    List<File> picFiles;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        super("ImageResizer");
        instance = this;
        initComponents();
        pack();
        setVisible(true);
        btColor.setVisible(false);
        btResize.setVisible(false);
    }

    private void openFileChoose() {
        mainPanel.setVisible(false);
        fileChooseFrame = new FileChooseFrame();
        fileChooseFrame.setVisible(true);
        fileChooseFrame.setTitle("Select one or several images files or directories");
        fileChooseFrame.setClosable(false);
        fileChooseFrame.setResizable(false);
        fileChooseFrame.setMaximizable(false);
        fileChooseFrame.setIconifiable(false);
        fileChooseFrame.toFront();
        fileChooseFrame.setSize(new Dimension(780, 560));
        add(fileChooseFrame);
        pack();
        // setVisible(true);
        //setSize(new Dimension(800, 600));
    }

    private void openOutputChoose() {

        mainPanel.setVisible(false);
        outputChooseFrame = new OutputChooseFrame(outputPath);
        outputChooseFrame.setVisible(true);
        outputChooseFrame.setTitle("Select output directory");
        outputChooseFrame.setClosable(false);
        outputChooseFrame.setResizable(false);
        outputChooseFrame.setMaximizable(false);
        outputChooseFrame.setIconifiable(false);
        outputChooseFrame.toFront();
        outputChooseFrame.setSize(new Dimension(780, 560));
        add(outputChooseFrame);
        pack();
        //setVisible(true);
        //setSize(new Dimension(800, 600));
    }

    private void openColorPick() {
        mainPanel.setVisible(false);
        colorPickFrame = new ColorPickFrame();
        colorPickFrame.setVisible(true);
        colorPickFrame.setTitle("Select a color");
        colorPickFrame.setClosable(false);
        colorPickFrame.setResizable(false);
        colorPickFrame.setMaximizable(false);
        colorPickFrame.setIconifiable(false);
        colorPickFrame.toFront();
        colorPickFrame.setSize(new Dimension(780, 560));
        add(colorPickFrame);
        pack();
        //setVisible(true);
        //setSize(new Dimension(800, 600));
    }

    private void openProgressFrame() {
        mainPanel.setVisible(false);
        progressFrame = new ProgressFrame();
        progressFrame.setVisible(true);
        progressFrame.setTitle("Resizing...");
        progressFrame.setClosable(false);
        progressFrame.setResizable(false);
        progressFrame.setMaximizable(false);
        progressFrame.setIconifiable(false);
        progressFrame.toFront();
        progressFrame.setSize(new Dimension(780, 560));
        add(progressFrame);
        //pack();
        //setVisible(true);
        //setSize(new Dimension(800, 600));
    }

    public void onFileChoice(List<File> picFiles) {
        this.picFiles = picFiles;
        try {
            fileChooseFrame.setClosed(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        mainPanel.setVisible(true);
        if (picFiles.size() > 0) {
            picList.removeAll();
            DefaultListModel<String> model = new DefaultListModel<>();
            for (int i = 0; i < picFiles.size(); i++) {
                model.addElement(picFiles.get(i).getPath());
            }
            picList.setModel(model);
            String firstFilePath = picFiles.get(0).getPath();
            mainLabel.setText(picFiles.size() + " loaded image(s)");
            pack();
            setVisible(true);
            FileNameInfo fni = FileNameInfo.getFileInfo(picFiles.get(0).getPath());
            
            outputPath = fni.getLocation();
            refreshOutputLabel();
            btResize.setVisible(true);
        } else {
            btResize.setVisible(false);
        }
    }

    public void onOutputChoice(File outputDir) {
        try {
            outputChooseFrame.setClosed(true);
            mainPanel.setVisible(true);
            if (outputDir != null) {
                outputPath = outputDir.getPath() + "/";
                refreshOutputLabel();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void onColorPicked(Color color) {
        try {
            colorPickFrame.setClosed(true);
            canvasColor = color;
            mainPanel.setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void refreshOutputLabel() {
        outputPathLabel.setText(outputPath + "FILE_NAME" + suffixTfld.getText() + ".FILE_EXTENSION");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        mainLabel = new javax.swing.JLabel();
        lbHei = new javax.swing.JLabel();
        heiSpin = new javax.swing.JSpinner();
        cbForceSize = new javax.swing.JCheckBox();
        lbWid = new javax.swing.JLabel();
        btFileChoose = new javax.swing.JButton();
        widSpin = new javax.swing.JSpinner();
        btResize = new javax.swing.JButton();
        btOutputPath = new javax.swing.JButton();
        outputPathLabel = new javax.swing.JLabel();
        suffixTfld = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btColor = new javax.swing.JButton();
        cbRatio = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        picList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));

        mainPanel.setMaximumSize(new java.awt.Dimension(800, 300));

        mainLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainLabel.setText("0 loaded image(s)");

        lbHei.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHei.setText("max-height");

        heiSpin.setValue(50);

        cbForceSize.setText("force canvas size");
        cbForceSize.setActionCommand("");
        cbForceSize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cbForceSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCbForceSizeChange(evt);
            }
        });

        lbWid.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbWid.setText("max-width");

        btFileChoose.setText("change images selection");
        btFileChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onBtFileClick(evt);
            }
        });

        widSpin.setName(""); // NOI18N
        widSpin.setValue(50);

        btResize.setText("Resize");
        btResize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onBtResizeClick(evt);
            }
        });

        btOutputPath.setText("set output path");
        btOutputPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onBtOutputClicked(evt);
            }
        });

        suffixTfld.setText(".min");
        suffixTfld.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                suffixChanged(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Suffix");

        jLabel4.setText("Output name preview");

        btColor.setText("canvas background color");
        btColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onBtColorClicked(evt);
            }
        });

        cbRatio.setSelected(true);
        cbRatio.setText("keep proportions");
        cbRatio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cbRatio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRatioChange(evt);
            }
        });

        jScrollPane1.setViewportView(picList);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btOutputPath, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btColor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbForceSize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbHei, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(heiSpin, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(suffixTfld, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(outputPathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mainLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btFileChoose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbRatio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbWid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(widSpin, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btResize)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btFileChoose)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbRatio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbWid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(widSpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHei)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(heiSpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbForceSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btColor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btOutputPath)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(suffixTfld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(outputPathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btResize))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onBtResizeClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onBtResizeClick
        openProgressFrame();
        this.paintAll(this.getGraphics());
        System.out.println("debut");
        Scalr.Mode scaleMode = Scalr.Mode.BEST_FIT_BOTH;
        if (!cbRatio.isSelected()) {
            scaleMode = Scalr.Mode.FIT_EXACT;
        }
        for (int i = 0; i < picFiles.size(); i++) {
            File picFile = picFiles.get(i);
            progressFrame.setRatio(i + 1, picFiles.size());
            progressFrame.setImageName(picFile.getName());
            this.paintAll(this.getGraphics());
            try {
                BufferedImage resizedImg = Scalr.resize(ImageIO.read(picFile),
                        Scalr.Method.ULTRA_QUALITY,
                        scaleMode,
                        (int) widSpin.getValue(),
                        (int) heiSpin.getValue());
                if (cbForceSize.isSelected()) {
                    int heiPad = (int) widSpin.getValue() - resizedImg.getWidth();
                    int widPad = (int) heiSpin.getValue() - resizedImg.getHeight();
                    if (heiPad > 1 || widPad > 1) {
                        resizedImg = Scalr.pad(resizedImg, Math.max(widPad, heiPad) / 2, canvasColor);
                        resizedImg = Scalr.crop(resizedImg, widPad / 2, heiPad / 2, resizedImg.getWidth() - widPad, resizedImg.getHeight() - heiPad);
                    }
                }
                savePic(resizedImg, picFile.getPath());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        try {
            progressFrame.setClosed(true);
            mainPanel.setVisible(true);
            System.out.println("fin");
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_onBtResizeClick

    private void onBtFileClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onBtFileClick
        openFileChoose();
    }//GEN-LAST:event_onBtFileClick

    private void suffixChanged(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_suffixChanged
        refreshOutputLabel();
    }//GEN-LAST:event_suffixChanged

    private void onBtOutputClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onBtOutputClicked
        openOutputChoose();
    }//GEN-LAST:event_onBtOutputClicked

    private void onBtColorClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onBtColorClicked
        openColorPick();
    }//GEN-LAST:event_onBtColorClicked

    private void onCbForceSizeChange(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onCbForceSizeChange
        btColor.setVisible(cbForceSize.isSelected());
    }//GEN-LAST:event_onCbForceSizeChange

    private void cbRatioChange(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRatioChange
        if (cbRatio.isSelected()) {
            lbWid.setText("max-width");
            lbHei.setText("max-height");
            cbForceSize.setVisible(true);
        } else {
            lbWid.setText("width");
            lbHei.setText("height");
            cbForceSize.setSelected(false);
            cbForceSize.setVisible(false);
            btColor.setVisible(false);
        }
    }//GEN-LAST:event_cbRatioChange

    /**
     *
     * @param image
     * @param path
     */
    private void savePic(BufferedImage image, String pPath) {
        try {
            FileNameInfo fni = FileNameInfo.getFileInfo(pPath);
            ImageIO.write(image, fni.getExtension(), new File(outputPath + fni.getBaseName() + suffixTfld.getText() + "." + fni.getExtension()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btColor;
    private javax.swing.JButton btFileChoose;
    private javax.swing.JButton btOutputPath;
    private javax.swing.JButton btResize;
    private javax.swing.JCheckBox cbForceSize;
    private javax.swing.JCheckBox cbRatio;
    private javax.swing.JSpinner heiSpin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbHei;
    private javax.swing.JLabel lbWid;
    private javax.swing.JLabel mainLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel outputPathLabel;
    private javax.swing.JList<String> picList;
    private javax.swing.JTextField suffixTfld;
    private javax.swing.JSpinner widSpin;
    // End of variables declaration//GEN-END:variables
}
