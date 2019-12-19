package suvastu.ui.form;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import suvastu.dao.AITSupplierUnPaidDao;
import suvastu.dao.AccountChequeDao;
import suvastu.dao.BillPaymentDao;
import suvastu.dao.ExternalDao;
import suvastu.dao.FinancialYearDao;
import suvastu.dao.ProjectDao;
import suvastu.dao.SupplierDao;
import suvastu.dao.VoucherDao;
import suvastu.entity.AccAitPay;
import suvastu.entity.Cheque;
import suvastu.entity.ChequeReprint;
import suvastu.entity.InvOtherBillPaid;
import suvastu.entity.VoucherDetails;
import suvastu.entity.VoucherMaster;
import suvastu.model.AITSupplierUnPaid;
import suvastu.model.AccountCheque;
import suvastu.model.AppUser;
import suvastu.model.External;
import suvastu.model.FinancialYear;
import suvastu.model.ItemCombo;
import suvastu.model.Supplier;
import suvastu.ui.report.PrintChequeReportUI;
import suvastu.util.BengaliNumberToWord;

public final class AITAutoVoucherUI extends javax.swing.JInternalFrame {

    private final AppUser appUser;
    private double grandTotal;

    private ChequeReprint chequeReprint;
    private BillPaymentDao billPaymentDao;
    private VoucherDao voucherDao;

    private LinkedList<String> tempList;

    public AITAutoVoucherUI(AppUser appUser) {
        initComponents();
        this.appUser = appUser;
        clear();

        cbGovtAccount.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
                    String a = cbGovtAccount.getEditor().getItem().toString();
                    String formatedString = a.toUpperCase();
                    cbGovtAccount.removeAllItems();
                    cbGovtAccount.addItem(null);
                    int st = 0;

                    if (formatedString.length() >= 3) {
                        ExternalDao externalDao = new ExternalDao();
                        for (External ext : externalDao.getMatch(formatedString)) {
                            ItemCombo item = new ItemCombo();
                            item.setId(ext.getExternalName());
                            item.setValue(ext.getExternalName().trim());
                            cbGovtAccount.addItem(item);
                            st++;
                        }
                    }

                    cbGovtAccount.getEditor().setItem(a);
                    cbGovtAccount.hidePopup();
                    if (st != 0) {
                        cbGovtAccount.showPopup();
                    }
                }
            }
        });

        //Supplier
        cbSupplier.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
                    String a = cbSupplier.getEditor().getItem().toString();
                    String formatedString = a.toUpperCase();
                    cbSupplier.removeAllItems();
                    cbSupplier.addItem(null);
                    int st = 0;

                    if (formatedString.length() >= 3) {
                        SupplierDao supplierDao = new SupplierDao();
                        for (Supplier m : supplierDao.getMatchSupplier(formatedString)) {
                            ItemCombo item = new ItemCombo();
                            item.setId(m.getSupplierId().toString());
                            item.setValue(m.getSupplierName().trim());
                            cbSupplier.addItem(item);
                            st++;
                        }
                    }

                    cbSupplier.getEditor().setItem(a);
                    cbSupplier.hidePopup();
                    if (st != 0) {
                        cbSupplier.showPopup();
                    }
                }
            }
        });

        cbChequeNo.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
                    String a = cbChequeNo.getEditor().getItem().toString();
                    String formatedString = a.toUpperCase();
                    cbChequeNo.removeAllItems();
                    cbChequeNo.addItem(null);
                    int st = 0;
                    if (formatedString.length() >= 2) {
                        AccountChequeDao accountChequeDao = new AccountChequeDao();
                        for (AccountCheque chq : accountChequeDao.getMatch(formatedString)) {
                            cbChequeNo.addItem(chq);
                            st++;
                        }
                    }

                    cbChequeNo.getEditor().setItem(a);
                    cbChequeNo.hidePopup();
                    if (st != 0) {
                        cbChequeNo.showPopup();
                    }
                }
            }
        });
    }

    public void clear() {

        tfAccountNo.setText("");
        tfGrandTotal.setText("");
        tfNarration.setText("");
        tfVoucherNo.setText("");

        cbChequeNo.removeAllItems();
        cbSupplier.removeAllItems();
        cbVoucher.removeAllItems();
        chChequeDate.setDate(new Date());
        chVoucherDate.setDate(new Date());

        DefaultTableModel model = (DefaultTableModel) tableProject.getModel();
        model.setRowCount(0);
        grandTotal = 0.0;
        tempList = new LinkedList<>();

        cbChequeNo.setSelectedItem(new ItemCombo("0", ""));
        cbSupplier.setSelectedItem(new ItemCombo("0", ""));
        cbVoucher.setSelectedItem(new ItemCombo("0", ""));
        cbGovtAccount.setSelectedItem(new ItemCombo("0", ""));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cbSupplier = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbVoucher = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cbGovtAccount = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProject = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbChequeNo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        tfAccountNo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfNarration = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfGrandTotal = new javax.swing.JTextField();
        chChequeDate = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        chVoucherDate = new com.toedter.calendar.JDateChooser();
        tfVoucherNo = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("AIT Auto Voucher");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbSupplier.setEditable(true);
        cbSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSupplierActionPerformed(evt);
            }
        });

        jLabel2.setText("Supplier");

        cbVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVoucherActionPerformed(evt);
            }
        });

        jLabel4.setText("GOVT. Account");

        cbGovtAccount.setEditable(true);

        jLabel1.setText("Voucher");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbGovtAccount, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbVoucher, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSupplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbGovtAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableProject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PROJECT CODE", "PROJECT NAME", "AIT AMOUNT"
            }
        ));
        jScrollPane1.setViewportView(tableProject);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Cheque No");

        cbChequeNo.setEditable(true);
        cbChequeNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChequeNoActionPerformed(evt);
            }
        });

        jLabel5.setText("Account No");

        tfAccountNo.setText("jTextField1");

        jLabel6.setText("Narration");

        tfNarration.setText("jTextField2");
        tfNarration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNarrationActionPerformed(evt);
            }
        });

        jLabel8.setText("Cheque Date");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Grand Total");

        tfGrandTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tfGrandTotal.setText("jTextField5");

        jLabel7.setText("Last V. No");

        jLabel10.setText("Voucher Date");

        tfVoucherNo.setText("jTextField2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfAccountNo)
                    .addComponent(tfNarration)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(chChequeDate, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfGrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbChequeNo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(tfVoucherNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chVoucherDate, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(tfGrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chChequeDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbChequeNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfAccountNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel10)
                        .addComponent(tfVoucherNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chVoucherDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfNarration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jButton2.setText("Clear");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSupplierActionPerformed

        try {
            tempList = new LinkedList<>();
            ItemCombo itemCombo = (ItemCombo) cbSupplier.getSelectedItem();

            if (!itemCombo.getId().equals("0")) {
                AITSupplierUnPaidDao aITSupplierUnPaidDao = new AITSupplierUnPaidDao();

                cbVoucher.removeAllItems();
                for (AITSupplierUnPaid sup : aITSupplierUnPaidDao.getPaymentsUndearSupplier(Integer.parseInt(itemCombo.getId()))) {

                    if (!tempList.contains(sup.getVoucherNo())) {
                        cbVoucher.addItem(sup);
                    }

                    tempList.add(sup.getVoucherNo());
                }

                cbVoucher.setSelectedItem(null);
                tfGrandTotal.setText("");

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbSupplierActionPerformed

    private void cbVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVoucherActionPerformed

        try {
            DefaultTableModel model = (DefaultTableModel) tableProject.getModel();
            model.setRowCount(0);
            grandTotal = 0;

            AITSupplierUnPaidDao aITSupplierUnPaidDao = new AITSupplierUnPaidDao();

            AITSupplierUnPaid supplierUnPaid = (AITSupplierUnPaid) cbVoucher.getSelectedItem();
            for (AITSupplierUnPaid sup : aITSupplierUnPaidDao.getPaymentsUndearCheque(supplierUnPaid.getAccountCheque().getChequeId())) {
                model = (DefaultTableModel) tableProject.getModel();
                model.addRow(new Object[]{new ItemCombo(sup.getId().toString(), sup.getProjectCode()), sup.getProjectName(), sup.getAitAmount()});
                grandTotal += sup.getAitAmount();
            }

            tfGrandTotal.setText("" + grandTotal);
        } catch (Exception e) {
        }


    }//GEN-LAST:event_cbVoucherActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        if (cbGovtAccount.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Select Account ???");
            cbGovtAccount.requestFocus();
        } else if (cbSupplier.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Select Supplier ???");
            cbSupplier.requestFocus();
        } else if (tableProject.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,"Select AIT Supplier ???");
        } else if (cbChequeNo.getSelectedItem()==null) {
            JOptionPane.showMessageDialog(this,"Select Cheque ???");
            cbChequeNo.requestFocus();
        } else if (tfNarration.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this,"Insert Voucher Narration ???");
            tfNarration.requestFocus();
        } else {

            try {

                ItemCombo sup = (ItemCombo) cbSupplier.getSelectedItem();
                SupplierDao supplierDao = new SupplierDao();
                Supplier supplier = supplierDao.getSupplier(Integer.parseInt(sup.getId()));

                String isAccountPay = "CH";

                int check = JOptionPane.showConfirmDialog(this, "Account Pay ???");

                if (check == 0) {
                    isAccountPay = "AP";
                } else if (check == 2) {
                    return;
                }

                DefaultTableModel model = (DefaultTableModel) tableProject.getModel();
                List<AccAitPay> paymentList = new ArrayList<>();
                List<InvOtherBillPaid> aitProjectList = new ArrayList<>();
                List<VoucherDetails> voucherDetailList = new ArrayList<>();

                FinancialYearDao financialYearDao = new FinancialYearDao();
                FinancialYear financialYear = financialYearDao.getFinYearByVoucherDate(chVoucherDate.getDate(), 1);

                VoucherMaster voucherMaster = new VoucherMaster();
                voucherMaster.setAppUser(appUser);
                voucherMaster.setCompanyId(1);
                voucherMaster.setFinYear(financialYear.getFinId());
                voucherMaster.setNarration(tfNarration.getText().trim().toUpperCase());
                voucherMaster.setVoucherDate(chVoucherDate.getDate());
                voucherDao = new VoucherDao();
                Calendar cal = Calendar.getInstance();
                cal.setTime(chVoucherDate.getDate());
                int month = cal.get(Calendar.MONTH);
                String newVoucherNo = voucherDao.getNewVoucherNumber(1, financialYear.getFinId(), financialYear.getFinNo(), "JV0", month + 1);
                voucherMaster.setVoucherNo(newVoucherNo);

                Object obj = cbChequeNo.getSelectedItem();
                AccountCheque accountCheque = (AccountCheque) obj;

                String projetString = "";
                int count = 0;
                for (int i = 0; i < tableProject.getRowCount(); i++) {
                    ItemCombo itemCombo = (ItemCombo) model.getValueAt(i, 0);

                    String projectName = (String) model.getValueAt(i, 1);
                    double aitAmount = (double) model.getValueAt(i, 2);

                    AccAitPay aitPay = new AccAitPay();
                    aitPay.setChequeId(accountCheque.getChequeId());
                    aitPay.setPaidId(Long.parseLong(itemCombo.getId()));
                    aitPay.setVoucherNo(newVoucherNo);
                    paymentList.add(aitPay);

                    InvOtherBillPaid billPaid = new InvOtherBillPaid();
                    billPaid.setAccountCheque(accountCheque);
                    billPaid.setAppUser(appUser);
                    billPaid.setChequeDate(chChequeDate.getDate());
                    billPaid.setExternalName(cbGovtAccount.getSelectedItem().toString());
                    billPaid.setNotes(tfNarration.getText().trim());
                    billPaid.setPaidAmount(aitAmount);
                    billPaid.setPaidDate(new Date());
                    ProjectDao projectDao = new ProjectDao();
                    billPaid.setProject(projectDao.getProject(itemCombo.getValue()));
                    aitProjectList.add(billPaid);

                    projetString += projectName + "=" + aitAmount + ", ";

                    VoucherDetails voucherDetails = new VoucherDetails();
                    voucherDetails.setSlNo(++count);
                    voucherDetails.setCaCode("401001007");
                    voucherDetails.setChequeNo(accountCheque.getChequeNo());
                    voucherDetails.setDebitAmount(aitAmount);
                    voucherDetails.setCreditAmount(0.0);
                    voucherDetails.setProjectCode(itemCombo.getValue());
                    voucherDetails.setVoucherNo(newVoucherNo);
                    voucherDetailList.add(voucherDetails);

                }

                for (int i = 0; i < tableProject.getRowCount(); i++) {
                    ItemCombo itemCombo = (ItemCombo) model.getValueAt(i, 0);
                    double aitAmount = (double) model.getValueAt(i, 2);

                    VoucherDetails voucherDetails = new VoucherDetails();
                    voucherDetails.setSlNo(++count);
                    voucherDetails.setCaCode(supplier.getCaCode());
                    voucherDetails.setChequeNo(accountCheque.getChequeNo());
                    voucherDetails.setDebitAmount(0.0);
                    voucherDetails.setCreditAmount(aitAmount);
                    voucherDetails.setProjectCode(itemCombo.getValue());
                    voucherDetails.setVoucherNo(newVoucherNo);
                    voucherDetailList.add(voucherDetails);
                }

                projetString += "Total=" + Math.floor(grandTotal);

                Cheque cheque = new Cheque();
                cheque.setAppUser(appUser);
                cheque.setChequeAmount(Double.parseDouble(tfGrandTotal.getText()));
                cheque.setChequeDate(chChequeDate.getDate());
                cheque.setChequeNo(accountCheque.getChequeNo());
                cheque.setExpenseDate(new Date());
                cheque.setExpenseMode("HO");
                cheque.setChequeId(accountCheque.getChequeId());

                chequeReprint = new ChequeReprint();
                chequeReprint.setBankShortCode(accountCheque.getBankShortName());
                chequeReprint.setBillTp("EXT");
                chequeReprint.setChequeAmount(grandTotal);
                chequeReprint.setChequeAmountInWord(BengaliNumberToWord.numberToWord(String.valueOf(Math.round(grandTotal))));
                chequeReprint.setChequeDate(new Date());
                chequeReprint.setChequeHtml(projetString);
                chequeReprint.setChequeNo(accountCheque.getChequeNo());
                chequeReprint.setChequeNoId(accountCheque.getChequeId());
                chequeReprint.setIsAccountPay(isAccountPay);
                chequeReprint.setNote(tfNarration.getText().trim());
                chequeReprint.setPersonName(cbGovtAccount.getSelectedItem().toString());
                chequeReprint.setPrintDate(new Date());

                billPaymentDao = new BillPaymentDao();
                billPaymentDao.supplierAitPayment(paymentList, aitProjectList, voucherMaster, voucherDetailList, cheque, chequeReprint);

                clear();

                tfVoucherNo.setText("" + newVoucherNo);

                PrintChequeReportUI showReport = new PrintChequeReportUI(null, closable, chequeReprint);
                showReport.setLocationRelativeTo(null);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                double width = screenSize.getWidth();
                double height = screenSize.getHeight();
                showReport.setBounds(0, 0, (int) width, (int) height);
                showReport.setVisible(true);
            } catch (Exception e) {
                System.out.println("Error.." + e);
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void cbChequeNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChequeNoActionPerformed

        try {
            AccountCheque chq = (AccountCheque) cbChequeNo.getSelectedItem();
            tfAccountNo.setText(chq.getBranch() + "-" + chq.getAccountNumber());

        } catch (Exception e) {
        }// TODO add your handling code here:
    }//GEN-LAST:event_cbChequeNoActionPerformed

    private void tfNarrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNarrationActionPerformed

        btnSave.doClick();
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNarrationActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cbChequeNo;
    private javax.swing.JComboBox cbGovtAccount;
    private javax.swing.JComboBox cbSupplier;
    private javax.swing.JComboBox cbVoucher;
    private com.toedter.calendar.JDateChooser chChequeDate;
    private com.toedter.calendar.JDateChooser chVoucherDate;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableProject;
    private javax.swing.JTextField tfAccountNo;
    private javax.swing.JTextField tfGrandTotal;
    private javax.swing.JTextField tfNarration;
    private javax.swing.JTextField tfVoucherNo;
    // End of variables declaration//GEN-END:variables
}
