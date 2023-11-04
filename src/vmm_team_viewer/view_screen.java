/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vmm_team_viewer;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.vmm.NanoHTTPD;
import com.vmm.NanoHTTPD.Response;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author new user
 */
public class view_screen extends javax.swing.JFrame {

    String ip;
    /**
     * Creates new form view_screen
     */
    public view_screen(String IP) {
        initComponents();
        ip = IP;
        Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        System.out.println(d);
        setSize(d);
        jsp.setBounds(0, 0, d.width - 300, d.height - 70);
        jPanel.setPreferredSize(new Dimension(d.width, d.height));
        photoLB.setBounds(0, 0, d.width, d.height);

        getScreen();

        setVisible(true);
    }

    public void getScreen() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        HttpResponse<String> res = Unirest.get("http://"+ip+":8000/getScreen")
                                .asString();

                        if (res.getStatus() == 200) {
                            String path = res.getBody();
                            if(path.length()>0){
                                URL url = new URL("http://"+ip+":8000/GetResource/"+path);
                                BufferedImage img = ImageIO.read(url);
                                Image Resized = img.getScaledInstance(photoLB.getWidth(), photoLB.getHeight(), Image.SCALE_SMOOTH);
                                ImageIcon I1 = new ImageIcon(Resized);
                                photoLB.setIcon(I1);
                            }
                            
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jsp = new javax.swing.JScrollPane();
        jPanel = new javax.swing.JPanel();
        photoLB = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel.setLayout(null);

        photoLB.setText("jLabel1");
        jPanel.add(photoLB);
        photoLB.setBounds(6, 28, 37, 16);

        jsp.setViewportView(jPanel);

        getContentPane().add(jsp);
        jsp.setBounds(10, 20, 70, 80);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(view_screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(view_screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(view_screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(view_screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new view_screen("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jsp;
    private javax.swing.JLabel photoLB;
    // End of variables declaration//GEN-END:variables

}
