package suvastu.ui.report;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import suvastu.entity.ChequeReprint;
import suvastu.util.BengaliNumberToWord;
import suvastu.util.ReportUtil;

public class PrintChequeReportUI extends javax.swing.JDialog {

    private ReportUtil reportViewer = null;

    public PrintChequeReportUI(java.awt.Frame parent, boolean modal, ChequeReprint chequeRePrint) {
        super(parent, modal);
        initComponents();
        
        java.util.Date utilDate = chequeRePrint.getChequeDate();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        String SQL_DATE[] = sqlDate.toString().split("-");      
        
        String DAY =  SQL_DATE[2];
        String MONTH = SQL_DATE[1];
        String YEAR= SQL_DATE[0];        
        
        HashMap hashMap = new HashMap();
        
        //System.out.println("Bank Short Code : "+chequeRePrint.getBankShortCode());

        hashMap.put("CHEQUE_NO", chequeRePrint.getChequeNo());
        hashMap.put("CHEQUE_DATE",chequeRePrint.getChequeDate());
        hashMap.put("PROJECTS",chequeRePrint.getChequeHtml());
        hashMap.put("TOTAL_AMOUNT",BengaliNumberToWord.format(chequeRePrint.getChequeAmount()).concat(".00"));
        hashMap.put("NOTES", chequeRePrint.getNote().toUpperCase());
        hashMap.put("CHEQUE_PAYEE",chequeRePrint.getPersonName().toUpperCase());
        hashMap.put("CHEQUE_AMOUNT_IN_WORDS", chequeRePrint.getChequeAmountInWord().concat(" only").toUpperCase());
        
        hashMap.put("D1", String.valueOf(DAY.charAt(0)));
        hashMap.put("D2", String.valueOf(DAY.charAt(1)));
        hashMap.put("MM1",String.valueOf(MONTH.charAt(0)));
        hashMap.put("M2", String.valueOf(MONTH.charAt(1)));
        hashMap.put("Y1", String.valueOf(YEAR.charAt(0)));
        hashMap.put("Y2", String.valueOf(YEAR.charAt(1)));
        hashMap.put("Y3", String.valueOf(YEAR.charAt(2)));
        hashMap.put("Y4", String.valueOf(YEAR.charAt(3)));
        
        
        
        if(chequeRePrint.getBankShortCode().equals("IBBL") && chequeRePrint.getIsAccountPay().equals("CH")){
            reportViewer = new ReportUtil("reports/cheque/IBBL_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("IBBL") && chequeRePrint.getIsAccountPay().equals("AP")){
            reportViewer = new ReportUtil("reports/cheque/IBBL_AP_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("DBBL") && chequeRePrint.getIsAccountPay().equals("CH")){
            reportViewer = new ReportUtil("reports/cheque/DBBL_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("DBBL") && chequeRePrint.getIsAccountPay().equals("AP")){
            reportViewer = new ReportUtil("reports/cheque/DBBL_AP_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("PBL") && chequeRePrint.getIsAccountPay().equals("CH")){
            reportViewer = new ReportUtil("reports/cheque/PBL_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("PBL") && chequeRePrint.getIsAccountPay().equals("AP")){
            reportViewer = new ReportUtil("reports/cheque/PBL_AP_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("WB") && chequeRePrint.getIsAccountPay().equals("CH")){
            reportViewer = new ReportUtil("reports/cheque/WB_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("WB") && chequeRePrint.getIsAccountPay().equals("AP")){
            reportViewer = new ReportUtil("reports/cheque/WB_AP_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("SCB") && chequeRePrint.getIsAccountPay().equals("CH")){
            reportViewer = new ReportUtil("reports/cheque/SCB_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("SCB") && chequeRePrint.getIsAccountPay().equals("AP")){
            reportViewer = new ReportUtil("reports/cheque/SCB_AP_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("MTBL") && chequeRePrint.getIsAccountPay().equals("CH")){
            reportViewer = new ReportUtil("reports/cheque/MTBL_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("MTBL") && chequeRePrint.getIsAccountPay().equals("AP")){
            reportViewer = new ReportUtil("reports/cheque/MTBL_AP_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("SEB") && chequeRePrint.getIsAccountPay().equals("CH")){
            reportViewer = new ReportUtil("reports/cheque/SEB_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("SEB") && chequeRePrint.getIsAccountPay().equals("AP")){
            reportViewer = new ReportUtil("reports/cheque/SEB_AP_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("SBL") && chequeRePrint.getIsAccountPay().equals("CH")){
            reportViewer = new ReportUtil("reports/cheque/SBL_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("SBL") && chequeRePrint.getIsAccountPay().equals("AP")){
            reportViewer = new ReportUtil("reports/cheque/SBL_AP_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("SJIBL") && chequeRePrint.getIsAccountPay().equals("CH")){
            reportViewer = new ReportUtil("reports/cheque/SJIBL_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("SJIBL") && chequeRePrint.getIsAccountPay().equals("AP")){
            reportViewer = new ReportUtil("reports/cheque/SJIBL_AP_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("MGBL") && chequeRePrint.getIsAccountPay().equals("CH")){
            reportViewer = new ReportUtil("reports/cheque/MGBL_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("MGBL") && chequeRePrint.getIsAccountPay().equals("AP")){
            reportViewer = new ReportUtil("reports/cheque/MGBL_AP_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("IFIC") && chequeRePrint.getIsAccountPay().equals("CH")){
            reportViewer = new ReportUtil("reports/cheque/IFIC_CHEQUE_PRINT.jasper", hashMap);
        } else if(chequeRePrint.getBankShortCode().equals("IFIC") && chequeRePrint.getIsAccountPay().equals("AP")){
            reportViewer = new ReportUtil("reports/cheque/IFIC_AP_CHEQUE_PRINT.jasper", hashMap);
        }
        
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SUVASTU DEVELOPMENT LTD.");

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    // End of variables declaration//GEN-END:variables
}
