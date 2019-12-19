package suvastu.ui.report;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import suvastu.dao.VoucherDao;
import suvastu.entity.VoucherDetails;
import suvastu.entity.VoucherMaster;
import suvastu.util.BengaliNumberToWord;
import suvastu.util.ReportUtil;

public class PrintVoucherReportUI extends javax.swing.JInternalFrame {

    private ReportUtil reportViewer = null;

    public PrintVoucherReportUI() {
        initComponents();
        tfSearch.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfSearch = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        desktopPane = new javax.swing.JDesktopPane();

        setClosable(true);
        setIconifiable(true);
        setTitle("Voucher Print");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Voucher No");

        tfSearch.setText("jTextField1");

        btnFind.setText("Find");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(desktopPane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopPane)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed

        if (tfSearch.getText().trim().length() != 15) {
            JOptionPane.showMessageDialog(this, "Insert Valid Voucher Number !!!");
        } else {

            String voucherTitle = "";
            String voucherNo = tfSearch.getText().trim();

            switch (voucherNo.substring(6, 9)) {
                case "PVC":
                    voucherTitle = "Cash Payment Voucher";
                    break;
                case "RVC":
                    voucherTitle = "Cash Receive Voucher";
                    break;
                case "PVB":
                    voucherTitle = "Bank Payment Voucher";
                    break;
                case "RVB":
                    voucherTitle = "Bank Receive Voucher";
                    break;
                default:
                    voucherTitle = "Journal Voucher";
                    break;
            }

            VoucherDao voucherDao = new VoucherDao();
            VoucherMaster voucherMaster = voucherDao.getVoucher(voucherNo);
            double totalAmount=0;
            
            for(VoucherDetails det:voucherMaster.getVoucherDetailList()){
                totalAmount +=det.getDebitAmount();
            }
            
            

            HashMap hashMap = new HashMap();
            hashMap.put("VOUCHER_TITLE", voucherTitle);
            hashMap.put("SHORT_CODE", "Voucher No : ".concat(voucherNo.substring(9)));
            hashMap.put("VOUCHER_NO", voucherNo);
            hashMap.put("VOUCHER_NARRATION", voucherMaster.getNarration());
            hashMap.put("TAKA_IN_WORD", BengaliNumberToWord.numberToWord(String.valueOf(Math.round(totalAmount))).concat(" only").toUpperCase());

            reportViewer = new ReportUtil("reports/VOUCHER_REPORT.jasper", hashMap);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double width = screenSize.getWidth();
            double height = screenSize.getHeight();
            reportViewer.setSize((int) width - 40, (int) height - 20);
            desktopPane.add(reportViewer);
            try {
                reportViewer.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(PrintChequeReportUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_btnFindActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFind;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
}
