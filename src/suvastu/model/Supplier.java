package suvastu.model;

public class Supplier {
    
    private Integer supplierId;
    private String supplierName;
    private String supplierBank;
    private String caCode;
    private Integer supplierType;
    
    
    //supplier_id,supplier_name,supplier_bank_name,acc_code,supplierTp

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

    public String getSupplierBank() {
        return supplierBank;
    }

    public void setSupplierBank(String supplierBank) {
        this.supplierBank = supplierBank;
    }

    public String getCaCode() {
        return caCode;
    }

    public void setCaCode(String caCode) {
        this.caCode = caCode;
    }

    public Integer getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(Integer supplierType) {
        this.supplierType = supplierType;
    }

    

    @Override
    public String toString() {
        return "Supplier{" + "supplierId=" + supplierId + ", supplierName=" + supplierName + ", supplierBank=" + supplierBank + ", caCode=" + caCode + ", supplierType=" + supplierType + '}';
    }
    
    
}
