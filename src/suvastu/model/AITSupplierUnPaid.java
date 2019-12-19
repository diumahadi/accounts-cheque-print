package suvastu.model;

import java.sql.Timestamp;
import java.util.Objects;

public class AITSupplierUnPaid {
    
    private Long id;
    private Integer supplierId;
    private String supplierName;
    private String projectCode;
    private String projectName;
    private AccountCheque accountCheque;
    private Double aitAmount;
    private String voucherNo;
    private String createdBy;
    private Timestamp createdAt;

    public AITSupplierUnPaid() {
    }

    public AITSupplierUnPaid(Long id, Integer supplierId, String supplierName) {
        this.id = id;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
    }
  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public AccountCheque getAccountCheque() {
        return accountCheque;
    }

    public void setAccountCheque(AccountCheque accountCheque) {
        this.accountCheque = accountCheque;
    }

    public Double getAitAmount() {
        return aitAmount;
    }

    public void setAitAmount(Double aitAmount) {
        this.aitAmount = aitAmount;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.supplierId);
        hash = 67 * hash + Objects.hashCode(this.projectCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AITSupplierUnPaid other = (AITSupplierUnPaid) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.supplierId, other.supplierId)) {
            return false;
        }
        if (!Objects.equals(this.projectCode, other.projectCode)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return voucherNo + " ["+accountCheque+"]";
    }
    
    
    
    
}
