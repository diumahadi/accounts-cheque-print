package suvastu.ui.form;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import suvastu.model.AppUser;
import suvastu.ui.report.DailyChequePrintReportUI;
import suvastu.ui.report.PrintVoucherReportUI;

public final class MainUI extends javax.swing.JFrame {

    protected AppUser appUser;
    
    public MainUI(AppUser appUser) {
        initComponents();
        
        try {
            this.appUser = appUser;
            if (appUser == null) {
                hideMenu();
            }
        } catch (Exception e) {
            System.out.println("MainUI Login: " + e);
        }
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) screenSize.getHeight();
        int width = (int) screenSize.getWidth();
        setSize(width, height - 40);
    }
    
    public void showMenu() {

        formMenu.setVisible(true);
        reportMenu.setVisible(true);
        if(appUser.getUserLevel().equals("ROLE_ADMIN")){
            resetChequeMI.setVisible(true);
        }

        loginMI.setText("Log Out");
    }

    public void hideMenu() {
        formMenu.setVisible(false);
        reportMenu.setVisible(false);
        resetChequeMI.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        formMenu = new javax.swing.JMenu();
        chequePrintMI = new javax.swing.JMenuItem();
        aitPaymentMI = new javax.swing.JMenuItem();
        reportMenu = new javax.swing.JMenu();
        dailyPaymentMI = new javax.swing.JMenuItem();
        voucherPrintMI = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        loginMI = new javax.swing.JMenuItem();
        contentMenuItem = new javax.swing.JMenuItem();
        resetChequeMI = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SUVASTU DEVELOPMENT LTD.");
        setIconImage(Toolkit.getDefaultToolkit().getImage("reports//sdl_logo.png"));

        formMenu.setMnemonic('f');
        formMenu.setText("Accounts");

        chequePrintMI.setMnemonic('s');
        chequePrintMI.setText("Cheque Print");
        chequePrintMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chequePrintMIActionPerformed(evt);
            }
        });
        formMenu.add(chequePrintMI);

        aitPaymentMI.setText("AIT Payment");
        aitPaymentMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aitPaymentMIActionPerformed(evt);
            }
        });
        formMenu.add(aitPaymentMI);

        menuBar.add(formMenu);

        reportMenu.setMnemonic('e');
        reportMenu.setText("Reports");

        dailyPaymentMI.setMnemonic('t');
        dailyPaymentMI.setText("Daily Payment");
        dailyPaymentMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dailyPaymentMIActionPerformed(evt);
            }
        });
        reportMenu.add(dailyPaymentMI);

        voucherPrintMI.setText("Voucher Print");
        voucherPrintMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voucherPrintMIActionPerformed(evt);
            }
        });
        reportMenu.add(voucherPrintMI);

        menuBar.add(reportMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Security");

        loginMI.setText("Login");
        loginMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginMIActionPerformed(evt);
            }
        });
        helpMenu.add(loginMI);

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contacts");
        contentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(contentMenuItem);

        resetChequeMI.setText("Reset Cheque");
        resetChequeMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetChequeMIActionPerformed(evt);
            }
        });
        helpMenu.add(resetChequeMI);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void contentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentMenuItemActionPerformed

        JOptionPane.showMessageDialog(this, "Muhammad Mahadi Hasan \n Office Contact : 235");// TODO add your handling code here:
    }//GEN-LAST:event_contentMenuItemActionPerformed

    private void chequePrintMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chequePrintMIActionPerformed

        ChequePrintUI Form = new ChequePrintUI(appUser);
        Dimension dimension = desktopPane.getSize();
        int x = (int) ((dimension.getWidth() - Form.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - Form.getHeight()) / 2);
        Form.setBounds(x, y, 480, 630);
        desktopPane.add(Form);
        Form.setVisible(true);
    }//GEN-LAST:event_chequePrintMIActionPerformed

    private void loginMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginMIActionPerformed

        if (loginMI.getText().equalsIgnoreCase("login")) {

            LoginUI Form = new LoginUI(this);
            Dimension dimension = desktopPane.getSize();
            int x = (int) ((dimension.getWidth() - Form.getWidth()) / 2);
            int y = (int) ((dimension.getHeight() - Form.getHeight()) / 2);
            Form.setBounds(x, y, 450, 180);
            desktopPane.add(Form);
            Form.setVisible(true);
            appUser = Form.getUser();
            if (appUser != null) {
                showMenu();
            }

        } else {
            appUser = null;
            hideMenu();
            JInternalFrame frames[] = desktopPane.getAllFrames();
            for (JInternalFrame frame : frames) {
                frame.dispose();
            }
            loginMI.setText("Login");
        }
    }//GEN-LAST:event_loginMIActionPerformed

    private void dailyPaymentMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dailyPaymentMIActionPerformed

        DailyChequePrintReportUI Report = new DailyChequePrintReportUI();
        int x = desktopPane.getWidth();
        int y = desktopPane.getHeight();
        Report.setBounds(0, 0, x, y);
        desktopPane.add(Report);
        Report.setVisible(true);
    }//GEN-LAST:event_dailyPaymentMIActionPerformed

    private void resetChequeMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetChequeMIActionPerformed
        ResetChequeUI Form = new ResetChequeUI(appUser);
        Dimension dimension = desktopPane.getSize();
        int x = (int) ((dimension.getWidth() - Form.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - Form.getHeight()) / 2);
        Form.setBounds(x, y, 400, 360);
        desktopPane.add(Form);
        Form.setVisible(true);
    }//GEN-LAST:event_resetChequeMIActionPerformed

    private void aitPaymentMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aitPaymentMIActionPerformed

        AITAutoVoucherUI Form = new AITAutoVoucherUI(appUser);
        Dimension dimension = desktopPane.getSize();
        int x = (int) ((dimension.getWidth() - Form.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - Form.getHeight()) / 2);
        Form.setBounds(x, y, 500, 505);
        desktopPane.add(Form);
        Form.setVisible(true);
    }//GEN-LAST:event_aitPaymentMIActionPerformed

    private void voucherPrintMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voucherPrintMIActionPerformed

        PrintVoucherReportUI Report = new PrintVoucherReportUI();
        int x = desktopPane.getWidth();
        int y = desktopPane.getHeight();
        Report.setBounds(0, 0, x, y);
        desktopPane.add(Report);
        Report.setVisible(true);
    }//GEN-LAST:event_voucherPrintMIActionPerformed

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
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainUI(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aitPaymentMI;
    private javax.swing.JMenuItem chequePrintMI;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem dailyPaymentMI;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu formMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem loginMI;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu reportMenu;
    private javax.swing.JMenuItem resetChequeMI;
    private javax.swing.JMenuItem voucherPrintMI;
    // End of variables declaration//GEN-END:variables

}
