/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package barbreplusavecframe;
import java.awt.Graphics;
import javax.swing.ImageIcon;
public class PanneauImage extends javax.swing.JPanel {
     private ImageIcon logo;
    public PanneauImage(String st) {
        logo=new ImageIcon(getClass().getResource(st));
       initComponents();
    }

    @Override
    public void paintComponent(Graphics g){
           super.paintComponent(g);
               g.drawImage(logo.getImage(),0,0,getSize().width,getSize().height,null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
   public static void main(String[] args){
    //new PanneauImage("/images/im1.png");
    }

}
