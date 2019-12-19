package suvastu.ui.form;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import suvastu.dao.AccountChequeDao;
import suvastu.dao.BillPaymentDao;
import suvastu.dao.ChequeReprintDao;
import suvastu.dao.EmpPaymentDao;
import suvastu.dao.EmployeeDao;
import suvastu.dao.ExternalDao;
import suvastu.dao.InvBillPaidDao;
import suvastu.dao.InvOtherBillPaidDao;
import suvastu.dao.InvSubContractorBillPaidDao;
import suvastu.dao.ProjectDao;
import suvastu.dao.SupplierDao;
import suvastu.entity.Cheque;
import suvastu.entity.ChequeReprint;
import suvastu.entity.InvBillPaid;
import suvastu.entity.InvOtherBillPaid;
import suvastu.exception.UncaughtError;
import suvastu.model.AccountCheque;
import suvastu.model.AppUser;
import suvastu.model.EmpPayment;
import suvastu.model.Employee;
import suvastu.model.External;
import suvastu.model.ItemCombo;
import suvastu.model.Project;
import suvastu.model.Supplier;
import suvastu.ui.report.PrintChequeReportUI;
import suvastu.util.BengaliNumberToWord;

public final class ChequePrintUI extends javax.swing.JInternalFrame {

    private ChequeReprint chequeReprint;
    private ExternalDao externalDao;
    private final AppUser appUser;

    private double grandTotal;

    private LinkedList<Project> tempList;

    public ChequePrintUI(AppUser appUser) {

        initComponents();
        this.appUser=appUser;
        loadFormContent();
        clear();

        /*btnSave.addKeyListener(new KeyListener() {
         @Override
         public void keyPressed(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_ENTER) {
         btnSave.doClick();                            
         }
         }

         @Override
         public void keyTyped(KeyEvent e) {                
         }

         @Override
         public void keyReleased(KeyEvent e) {                
         }
         });*/
        cbPmPerson.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
                    String a = cbPmPerson.getEditor().getItem().toString();
                    String formatedString = a.toUpperCase();
                    cbPmPerson.removeAllItems();
                    cbPmPerson.addItem(null);
                    int st = 0;

                    if (formatedString.length() >= 3) {

                        if (rbSupplier.isSelected()) {

                            SupplierDao supplierDao = new SupplierDao();
                            for (Supplier m : supplierDao.getMatchSupplier(formatedString)) {
                                ItemCombo item = new ItemCombo();
                                item.setId(m.getSupplierId().toString());
                                item.setValue(m.getSupplierName().trim());
                                cbPmPerson.addItem(item);
                                st++;
                            }
                        } else if (rbSubContractor.isSelected()) {

                            SupplierDao supplierDao = new SupplierDao();
                            for (Supplier m : supplierDao.getMatchSubContractor(formatedString)) {
                                ItemCombo item = new ItemCombo();
                                item.setId(m.getSupplierId().toString());
                                item.setValue(m.getSupplierName().trim());
                                cbPmPerson.addItem(item);
                                st++;
                            }
                        } else if (rbEmployee.isSelected()) {
                            ItemCombo itm = (ItemCombo) cbPayTp.getSelectedItem();
                            EmpPaymentDao employeeDao = new EmpPaymentDao();
                            for (EmpPayment emp : employeeDao.getMatch(itm.getId(), formatedString)) {
                                ItemCombo item = new ItemCombo();
                                item.setId(emp.getEmpId() + "-" + emp.getAccCode());
                                item.setValue(emp.getEmpId() + "-" + emp.getEmpName().trim());
                                cbPmPerson.addItem(item);
                                st++;
                            }

                        } else if (rbExternals.isSelected()) {
                            ExternalDao externalDao = new ExternalDao();
                            for (External ext : externalDao.getMatch(formatedString)) {
                                ItemCombo item = new ItemCombo();
                                item.setId(ext.getExternalName());
                                item.setValue(ext.getExternalName().trim());
                                cbPmPerson.addItem(item);
                                st++;
                            }
                        }

                    }

                    cbPmPerson.getEditor().setItem(a);
                    cbPmPerson.hidePopup();
                    if (st != 0) {
                        cbPmPerson.showPopup();
                    }
                }
            }
        });

        cbProject.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
                    String a = cbProject.getEditor().getItem().toString();
                    String formatedString = a.toUpperCase();
                    cbProject.removeAllItems();
                    cbProject.addItem(null);
                    int st = 0;
                    if (formatedString.length() >= 3) {
                        ProjectDao projectDao = new ProjectDao();
                        for (Project pro : projectDao.getMatch(formatedString)) {
                            cbProject.addItem(pro);
                            st++;
                        }
                    }

                    cbProject.getEditor().setItem(a);
                    //cbProject.getEditor().setItem(cbProject);
                    cbProject.hidePopup();
                    if (st != 0) {
                        cbProject.showPopup();
                    }
                }
            }
        });

        cbCheque.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
                    String a = cbCheque.getEditor().getItem().toString();
                    String formatedString = a.toUpperCase();
                    cbCheque.removeAllItems();
                    cbCheque.addItem(null);
                    int st = 0;
                    if (formatedString.length() >= 2) {
                        AccountChequeDao accountChequeDao = new AccountChequeDao();
                        for (AccountCheque chq : accountChequeDao.getMatch(formatedString)) {
                            cbCheque.addItem(chq);
                            st++;
                        }
                    }

                    cbCheque.getEditor().setItem(a);
                    cbCheque.hidePopup();
                    if (st != 0) {
                        cbCheque.showPopup();
                    }
                }
            }
        });

    }

    public void clear() { 
        
        
        tfAit.setText("");
        tfAmount.setText("");
        tfSearch.setText("");
        tfMrNo.setText("");
        tfNotes.setText("");
        tfTotalAmount.setText("");
        tfBankAc.setText("");

        cbCheque.removeAllItems();
        cbPayTp.setSelectedIndex(0);
        cbPmPerson.removeAllItems();
        cbProject.removeAllItems();

        chChequeDate.setDate(new Date());

        rbSupplier.setSelected(true);

        tempList = new LinkedList<>();

        DefaultTableModel model = (DefaultTableModel) tableProject.getModel();
        model.setRowCount(0);
        grandTotal = 0;

        btnChequeRePrint.setEnabled(false);
        btnSave.setEnabled(true);
        
        

    }
    

    public void loadFormContent() {

        cbPayTp.removeAllItems();

        externalDao = new ExternalDao();
        for (ItemCombo itm : externalDao.getPaymentType()) {
            cbPayTp.addItem(itm);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfSearch = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        rbSupplier = new javax.swing.JRadioButton();
        rbSubContractor = new javax.swing.JRadioButton();
        rbEmployee = new javax.swing.JRadioButton();
        rbExternals = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        cbPmPerson = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        cbPayTp = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbProject = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        tfAmount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfAit = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableProject = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnChequeRePrint = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tfNotes = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbCheque = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfMrNo = new javax.swing.JTextField();
        chChequeDate = new com.toedter.calendar.JDateChooser();
        tfBankAc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfTotalAmount = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cheque Print");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Cheque No");

        tfSearch.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tfSearch.setText("jTextField1");
        tfSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfSearchFocusGained(evt);
            }
        });

        btnFind.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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
                .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup1.add(rbSupplier);
        rbSupplier.setSelected(true);
        rbSupplier.setText("Supplier");
        rbSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSupplierActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbSubContractor);
        rbSubContractor.setText("Sub-Contractor");
        rbSubContractor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSubContractorActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbEmployee);
        rbEmployee.setText("Employee");
        rbEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEmployeeActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbExternals);
        rbExternals.setText("Externals");
        rbExternals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbExternalsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbSupplier)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbSubContractor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbEmployee)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbExternals)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbSupplier)
                    .addComponent(rbSubContractor)
                    .addComponent(rbEmployee)
                    .addComponent(rbExternals))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbPmPerson.setEditable(true);
        cbPmPerson.setAutoscrolls(true);

        jLabel11.setText("Pay Type");

        cbPayTp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPayTp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPmPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPmPerson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(cbPayTp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Project");

        cbProject.setEditable(true);
        cbProject.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProjectActionPerformed(evt);
            }
        });

        jLabel4.setText("Amount");

        tfAmount.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tfAmount.setText("jTextField2");
        tfAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfAmountActionPerformed(evt);
            }
        });

        jLabel5.setText("AIT");

        tfAit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tfAit.setText("jTextField3");
        tfAit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfAitActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/suvastu/icon/math-add-icon-16px.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/suvastu/icon/math-minus-icon-16px.png"))); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbProject, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfAit))
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(tfAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(tfAit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdd)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableProject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PROJECT CODE", "PROJECT NAME", "AMOUNT", "AIT"
            }
        ));
        jScrollPane2.setViewportView(tableProject);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSave.setText("Print Cheque");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        btnSave.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnSaveFocusGained(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnChequeRePrint.setText("Cheque Re-Print");
        btnChequeRePrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChequeRePrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnChequeRePrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnClear)
                    .addComponent(btnChequeRePrint))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Notes");

        tfNotes.setText("jTextField5");

        jLabel7.setText("Cheque No");

        cbCheque.setEditable(true);
        cbCheque.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChequeActionPerformed(evt);
            }
        });

        jLabel9.setText("Cheque Date");

        jLabel10.setText("MR NO");

        tfMrNo.setText("jTextField7");

        tfBankAc.setText("jTextField1");

        jLabel8.setText("Account");

        tfTotalAmount.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tfTotalAmount.setText("jTextField4");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Total Amount");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfTotalAmount)
                    .addComponent(tfNotes)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(chChequeDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfMrNo))
                    .addComponent(cbCheque, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfBankAc))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(7, 7, 7)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfBankAc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel10)
                        .addComponent(tfMrNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chChequeDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSupplierActionPerformed

        clear();
        cbPmPerson.requestFocus();
        rbSupplier.setSelected(true);
    }//GEN-LAST:event_rbSupplierActionPerformed

    private void rbSubContractorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSubContractorActionPerformed

        clear();
        cbPmPerson.requestFocus();
        rbSubContractor.setSelected(true);
    }//GEN-LAST:event_rbSubContractorActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed

        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed


        //For Supplier And Sub-Contractor
        if (rbSupplier.isSelected() || rbSubContractor.isSelected()) {
            if (cbPmPerson.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Select Supplier or Sub-Contractor ???");
                cbPmPerson.requestFocus();
            } else if (tempList.size() == 0) {
                JOptionPane.showMessageDialog(this, "No Project Selected !!!");
            } else if (tfNotes.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Insert Notes ???");
                tfNotes.requestFocus();
            } else if (cbCheque.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Select Cheque ???");
                cbCheque.requestFocus();
            } else {

                Object obj = cbCheque.getSelectedItem();
                AccountCheque accountCheque = (AccountCheque) obj;

                try {
                    String isAccountPay = "CH";

                    int check = JOptionPane.showConfirmDialog(this, "Account Pay ???");

                    if (check == 0) {
                        isAccountPay = "AP";
                    } else if (check == 2) {
                        return;
                    }

                    ItemCombo itemCombo = (ItemCombo) cbPmPerson.getSelectedItem();

                    SupplierDao supplierDao = new SupplierDao();
                    Supplier supplier = supplierDao.getSupplier(Integer.parseInt(itemCombo.getId()));

                    DefaultTableModel model = (DefaultTableModel) tableProject.getModel();
                    List<InvBillPaid> billPaidProjectList = new ArrayList<>();

                    String projetString = "";
                    for (int i = 0; i < tempList.size(); i++) {
                        Project project = (Project) model.getValueAt(i, 1);
                        double amount = Double.parseDouble(model.getValueAt(i, 2).toString());
                        double ait = Double.parseDouble(model.getValueAt(i, 3).toString());
                        InvBillPaid billPaid = new InvBillPaid();
                        billPaid.setAitAmount(ait);
                        billPaid.setPaidAmount(amount);
                        billPaid.setAppUser(appUser);
                        billPaid.setCheque(accountCheque);
                        billPaid.setChequeDate(chChequeDate.getDate());
                        billPaid.setPayDate(new Date());
                        billPaid.setPaymentMode("CQ");
                        billPaid.setProject(project);
                        billPaid.setSupplier(supplier);
                        billPaidProjectList.add(billPaid);

                        projetString += project.getProjectName() + "=" + amount + ", ";

                    }

                    projetString += "Total=" + Math.floor(grandTotal);

                    Cheque cheque = new Cheque();
                    cheque.setAppUser(appUser);
                    cheque.setChequeAmount(Double.parseDouble(tfTotalAmount.getText()));
                    cheque.setChequeDate(chChequeDate.getDate());
                    cheque.setChequeNo(accountCheque.getChequeNo());
                    cheque.setExpenseDate(new Date());
                    cheque.setExpenseMode("HO");
                    cheque.setChequeId(accountCheque.getChequeId());

                    chequeReprint = new ChequeReprint();
                    chequeReprint.setBankShortCode(accountCheque.getBankShortName());
                    chequeReprint.setBillTp((rbSupplier.isSelected()) ? "SUP" : "SUB");
                    chequeReprint.setChequeAmount(grandTotal);
                    chequeReprint.setChequeAmountInWord(BengaliNumberToWord.numberToWord(String.valueOf(Math.round(grandTotal))));
                    chequeReprint.setChequeDate(new Date());
                    chequeReprint.setChequeHtml(projetString);
                    chequeReprint.setChequeNo(accountCheque.getChequeNo());
                    chequeReprint.setChequeNoId(accountCheque.getChequeId());
                    chequeReprint.setIsAccountPay(isAccountPay);
                    chequeReprint.setNote(tfNotes.getText().trim());
                    chequeReprint.setPersonName(supplier.getSupplierName());
                    chequeReprint.setPrintDate(new Date());

                    BillPaymentDao billPaymentDao = new BillPaymentDao();

                    if (rbSupplier.isSelected()) {
                        billPaymentDao.supplierBillPayment(billPaidProjectList, cheque, chequeReprint);
                        clear();
                    } else {
                        billPaymentDao.subContractorBillPayment(billPaidProjectList, cheque, chequeReprint);
                        clear();
                    }

                    //ChequeReprint chequeReprint=new ChequeReprint();
                    PrintChequeReportUI showReport = new PrintChequeReportUI(null, closable, chequeReprint);
                    showReport.setLocationRelativeTo(null);
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    double width = screenSize.getWidth();
                    double height = screenSize.getHeight();
                    showReport.setBounds(0, 0, (int) width, (int) height);
                    showReport.setVisible(true);

                } catch (UncaughtError e) {
                    System.out.println("Error.." + e);
                    JOptionPane.showMessageDialog(this, "Error...");
                }
            }
        }

        if (rbEmployee.isSelected() || rbExternals.isSelected()) {

            if (rbEmployee.isSelected() && cbPmPerson.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Select Employee or Externals ???");
                cbPmPerson.requestFocus();
                return;
            }

            if (rbExternals.isSelected() && cbPmPerson.getSelectedItem().toString().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Select Externals ???");
                cbPmPerson.requestFocus();
                return;
            }

            if (tempList.size() == 0) {
                JOptionPane.showMessageDialog(this, "No Project Selected !!!");
            } else if (tfNotes.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Insert Notes ???");
                tfNotes.requestFocus();
            } else if (cbCheque.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Select Cheque ???");
                cbCheque.requestFocus();
            } else if (tfMrNo.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Insert Mr No ???");
                tfMrNo.requestFocus();
            } else {

                try {

                    String isAccountPay = "CH";

                    int check = JOptionPane.showConfirmDialog(this, "Account Pay ???");

                    if (check == 0) {
                        isAccountPay = "AP";
                    } else if (check == 2) {
                        return;
                    }

                    ItemCombo itemCombo = (ItemCombo) cbPmPerson.getSelectedItem();

                    String empId = null;
                    String empAccCode = null;

                    if (rbEmployee.isSelected()) {
                        String TEMP_EMP[] = itemCombo.getId().split("-");
                        empId = TEMP_EMP[0];
                        empAccCode = TEMP_EMP[1];
                    }

                    AccountCheque accountCheque = (AccountCheque) cbCheque.getSelectedItem();
                    String projetString = "";

                    EmployeeDao employeeDao = new EmployeeDao();
                    Employee employee = employeeDao.getEmployee(empId);

                    DefaultTableModel model = (DefaultTableModel) tableProject.getModel();
                    List<InvOtherBillPaid> billPaidProjectList = new ArrayList<>();

                    for (int i = 0; i < tempList.size(); i++) {

                        Project project = (Project) model.getValueAt(i, 1);
                        double amount = Double.parseDouble(model.getValueAt(i, 2).toString());
                        double ait = Double.parseDouble(model.getValueAt(i, 3).toString());

                        InvOtherBillPaid billPaid = new InvOtherBillPaid();
                        billPaid.setAccCode(empAccCode);
                        billPaid.setAccountCheque(accountCheque);
                        billPaid.setAppUser(appUser);
                        billPaid.setChequeDate(chChequeDate.getDate());
                        billPaid.setEmpId(empId);
                        billPaid.setExternalName(cbPmPerson.getSelectedItem().toString());
                        billPaid.setMrNo(tfMrNo.getText());
                        billPaid.setNotes(tfNotes.getText());
                        billPaid.setPaidAmount(amount);
                        billPaid.setPaidDate(new Date());
                        billPaid.setProject(project);

                        billPaidProjectList.add(billPaid);
                        projetString += project.getProjectName() + "=" + amount + ", ";

                    }

                    projetString += "Total=" + Math.floor(grandTotal);

                    Cheque cheque = new Cheque();
                    cheque.setAppUser(appUser);
                    cheque.setChequeAmount(Double.parseDouble(tfTotalAmount.getText()));
                    cheque.setChequeDate(chChequeDate.getDate());
                    cheque.setChequeNo(accountCheque.getChequeNo());
                    cheque.setExpenseDate(new Date());
                    cheque.setExpenseMode("HO");
                    cheque.setChequeId(accountCheque.getChequeId());

                    chequeReprint = new ChequeReprint();
                    chequeReprint.setBankShortCode(accountCheque.getBankShortName());
                    chequeReprint.setBillTp((rbEmployee.isSelected()) ? "EMP" : "EXT");
                    chequeReprint.setChequeAmount(grandTotal);
                    chequeReprint.setChequeAmountInWord(BengaliNumberToWord.numberToWord(String.valueOf(Math.round(grandTotal))));
                    chequeReprint.setChequeDate(new Date());
                    chequeReprint.setChequeHtml(projetString);
                    chequeReprint.setChequeNo(accountCheque.getChequeNo());
                    chequeReprint.setChequeNoId(accountCheque.getChequeId());
                    chequeReprint.setIsAccountPay(isAccountPay);
                    chequeReprint.setNote(tfNotes.getText().trim());
                    if (rbEmployee.isSelected()) {
                        chequeReprint.setPersonName(employee.getEmpName());
                    } else {
                        chequeReprint.setPersonName(cbPmPerson.getSelectedItem().toString());
                    }
                    chequeReprint.setPrintDate(new Date());

                    BillPaymentDao billPaymentDao = new BillPaymentDao();
                    billPaymentDao.externalsBillPayment(billPaidProjectList, cheque, chequeReprint);
                    clear();
                    
                    PrintChequeReportUI showReport = new PrintChequeReportUI(null, closable, chequeReprint);
                    showReport.setLocationRelativeTo(null);
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    double width = screenSize.getWidth();
                    double height = screenSize.getHeight();
                    showReport.setBounds(0, 0, (int) width, (int) height);
                    showReport.setVisible(true);

                } catch (HeadlessException | NumberFormatException e) {
                    System.out.println("Error...." + e);
                }

            }

        }

        if (rbExternals.isSelected()) {
            String externalName = cbPmPerson.getSelectedItem().toString();
        }


    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        if (cbProject.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Select Project ???");
            cbProject.requestFocus();
        } else if (tfAmount.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Insert Amount ???");
            tfAmount.requestFocus();
        } else {

            Project project = (Project) cbProject.getSelectedItem();
            double amount = Double.parseDouble(tfAmount.getText());
            double ait = 0;

            if (!tfAit.getText().trim().equals("")) {
                ait = Double.parseDouble(tfAit.getText());
            }

            try {
                if (!tempList.contains(project)) {
                    DefaultTableModel model = (DefaultTableModel) tableProject.getModel();
                    model.addRow(new Object[]{project.getProjectCode(), project, amount, ait});

                    grandTotal += amount;

                    tableProject.setRowHeight(20);
                    cbProject.removeAllItems();
                    tfAmount.setText("");
                    tfAit.setText("");
                    tfTotalAmount.setText("" + Math.round(grandTotal));
                    cbProject.requestFocus();
                    tempList.add(project);
                } else {
                    JOptionPane.showMessageDialog(this, "Duplicate Project Name !!!");
                }
            } catch (HeadlessException e) {
                System.out.println("e" + e);
            } catch (ClassCastException ee) {
                JOptionPane.showMessageDialog(this, "Select Project Correctly !!!");
            }

        }


    }//GEN-LAST:event_btnAddActionPerformed

    private void tfAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfAmountActionPerformed

        btnAdd.doClick();
    }//GEN-LAST:event_tfAmountActionPerformed

    private void tfAitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfAitActionPerformed

        btnAdd.doClick();
    }//GEN-LAST:event_tfAitActionPerformed

    private void cbChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChequeActionPerformed

        try {
            AccountCheque chq = (AccountCheque) cbCheque.getSelectedItem();
            tfBankAc.setText(chq.getBranch() + "-" + chq.getAccountNumber());

        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbChequeActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        try {
            DefaultTableModel model = (DefaultTableModel) tableProject.getModel();
            int row = tableProject.getSelectedRow();
            Project project = (Project) model.getValueAt(row, 1);
            grandTotal = grandTotal - (double) model.getValueAt(row, 2);
            tempList.remove(project);
            tfTotalAmount.setText("" + Math.round(grandTotal));

            model.removeRow(row);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Select Medicine Row to Remove !!!");
        } catch (Exception e) {
            System.out.println("Sales Form Error..." + e);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void cbProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbProjectActionPerformed

    private void btnChequeRePrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChequeRePrintActionPerformed

        PrintChequeReportUI showReport = new PrintChequeReportUI(null, closable, chequeReprint);
        showReport.setLocationRelativeTo(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        showReport.setBounds(0, 0, (int) width, (int) height);
        showReport.setVisible(true);
    }//GEN-LAST:event_btnChequeRePrintActionPerformed

    private void btnSaveFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnSaveFocusGained

    }//GEN-LAST:event_btnSaveFocusGained

    private void rbEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEmployeeActionPerformed

        clear();
        cbPmPerson.requestFocus();
        rbEmployee.setSelected(true);
    }//GEN-LAST:event_rbEmployeeActionPerformed

    private void rbExternalsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbExternalsActionPerformed

        clear();
        cbPmPerson.requestFocus();
        rbExternals.setSelected(true);
    }//GEN-LAST:event_rbExternalsActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed

        if (tfSearch.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Insert Cheque No ???");
            tfSearch.requestFocus();
        } else {

            String chequeNo = tfSearch.getText();

            clear();
            btnChequeRePrint.setEnabled(true);
            btnSave.setEnabled(false);

            ChequeReprintDao chequeReprintDao = new ChequeReprintDao();

            chequeReprint = chequeReprintDao.getCheque(chequeNo);
            
            if(chequeReprint.getChequeNoId()==null){
                JOptionPane.showMessageDialog(this, "No Such Cheque Found !!!");
                return;
            }

            if (chequeReprint.getBillTp().equals("SUP")) {
                rbSupplier.setSelected(true);

                InvBillPaidDao billPaidDao = new InvBillPaidDao();
                List<InvBillPaid> projectList = billPaidDao.getPaymentsUndearCheque(chequeReprint.getChequeNoId());
                for (InvBillPaid billPaid : projectList) {
                    DefaultTableModel model = (DefaultTableModel) tableProject.getModel();
                    model.addRow(new Object[]{billPaid.getProject().getProjectCode(), billPaid.getProject(), billPaid.getPaidAmount(), billPaid.getAitAmount()});

                    ItemCombo combo = new ItemCombo();
                    combo.setId("" + billPaid.getSupplier().getSupplierId());
                    combo.setValue(billPaid.getSupplier().getSupplierName());
                    cbPmPerson.setSelectedItem(combo);
                    cbPmPerson.setSelectedItem(combo);
                    tfBankAc.setText(billPaid.getCheque().getAccountNumber());
                    tfNotes.setText(chequeReprint.getNote());
                    cbCheque.setSelectedItem(billPaid.getCheque());
                    grandTotal += billPaid.getPaidAmount();
                }
                chChequeDate.setDate(chequeReprint.getChequeDate());
                tfTotalAmount.setText("" + Math.round(grandTotal));

            } else if (chequeReprint.getBillTp().equals("SUB")) {

                rbSubContractor.setSelected(true);
                InvSubContractorBillPaidDao billPaidDao = new InvSubContractorBillPaidDao();
                List<InvBillPaid> projectList = billPaidDao.getPaymentsUndearCheque(chequeReprint.getChequeNoId());
                for (InvBillPaid billPaid : projectList) {
                    DefaultTableModel model = (DefaultTableModel) tableProject.getModel();
                    model.addRow(new Object[]{billPaid.getProject().getProjectCode(), billPaid.getProject(), billPaid.getPaidAmount(), billPaid.getAitAmount()});

                    ItemCombo combo = new ItemCombo();
                    combo.setId("" + billPaid.getSupplier().getSupplierId());
                    combo.setValue(billPaid.getSupplier().getSupplierName());
                    
                    cbPmPerson.setSelectedItem(combo);
                    tfBankAc.setText(billPaid.getCheque().getAccountNumber());
                    tfNotes.setText(chequeReprint.getNote());
                    cbCheque.setSelectedItem(billPaid.getCheque());
                    grandTotal += billPaid.getPaidAmount();
                }
                chChequeDate.setDate(chequeReprint.getChequeDate());
                tfTotalAmount.setText("" + Math.round(grandTotal));
            } else if (chequeReprint.getBillTp().equals("EMP")) {

                rbEmployee.setSelected(true);
                InvOtherBillPaidDao billPaidDao = new InvOtherBillPaidDao();
                List<InvOtherBillPaid> projectList = billPaidDao.getPaymentsUndearCheque(chequeReprint.getChequeNoId());
                for (InvOtherBillPaid billPaid : projectList) {
                    DefaultTableModel model = (DefaultTableModel) tableProject.getModel();
                    model.addRow(new Object[]{billPaid.getProject().getProjectCode(), billPaid.getProject(), billPaid.getPaidAmount(), 0.0});
                    
                    EmployeeDao employeeDao = new EmployeeDao();
                    Employee employee = employeeDao.getEmployee(billPaid.getEmpId());
                    ItemCombo combo = new ItemCombo();
                    combo.setId("" + employee.getEmpId());
                    combo.setValue(employee.getEmpName());
                    
                    cbPmPerson.setSelectedItem(combo);
                    tfMrNo.setText(billPaid.getMrNo());
                    tfBankAc.setText(billPaid.getAccountCheque().getAccountNumber());
                    tfNotes.setText(billPaid.getNotes());
                    cbCheque.setSelectedItem(billPaid.getAccountCheque());
                    grandTotal += billPaid.getPaidAmount();
                }
                chChequeDate.setDate(chequeReprint.getChequeDate());
                tfTotalAmount.setText("" + Math.round(grandTotal));
            } else if (chequeReprint.getBillTp().equals("EXT")) {

                rbExternals.setSelected(true);
                InvOtherBillPaidDao billPaidDao = new InvOtherBillPaidDao();
                List<InvOtherBillPaid> projectList = billPaidDao.getPaymentsUndearCheque(chequeReprint.getChequeNoId());
                for (InvOtherBillPaid billPaid : projectList) {
                    DefaultTableModel model = (DefaultTableModel) tableProject.getModel();
                    model.addRow(new Object[]{billPaid.getProject().getProjectCode(), billPaid.getProject(), billPaid.getPaidAmount(), 0.0});

                    cbPmPerson.setSelectedItem(billPaid.getExternalName());
                    tfMrNo.setText(billPaid.getMrNo());
                    tfBankAc.setText(billPaid.getAccountCheque().getAccountNumber());
                    tfNotes.setText(billPaid.getNotes());
                    cbCheque.setSelectedItem(billPaid.getAccountCheque());
                    grandTotal += billPaid.getPaidAmount();
                }
                chChequeDate.setDate(chequeReprint.getChequeDate());
                tfTotalAmount.setText("" + Math.round(grandTotal));

            }

        }

    }//GEN-LAST:event_btnFindActionPerformed

    private void tfSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfSearchFocusGained

        //cbPmPerson.requestFocus();
    }//GEN-LAST:event_tfSearchFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChequeRePrint;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbCheque;
    private javax.swing.JComboBox cbPayTp;
    private javax.swing.JComboBox cbPmPerson;
    private javax.swing.JComboBox cbProject;
    private com.toedter.calendar.JDateChooser chChequeDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbEmployee;
    private javax.swing.JRadioButton rbExternals;
    private javax.swing.JRadioButton rbSubContractor;
    private javax.swing.JRadioButton rbSupplier;
    private javax.swing.JTable tableProject;
    private javax.swing.JTextField tfAit;
    private javax.swing.JTextField tfAmount;
    private javax.swing.JTextField tfBankAc;
    private javax.swing.JTextField tfMrNo;
    private javax.swing.JTextField tfNotes;
    private javax.swing.JTextField tfSearch;
    private javax.swing.JTextField tfTotalAmount;
    // End of variables declaration//GEN-END:variables
}
