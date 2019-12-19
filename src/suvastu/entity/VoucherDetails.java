
package suvastu.entity;

public class VoucherDetails {
    
    private Integer detailId;
    private Integer slNo;
    private String voucherNo;
    private String caCode;
    private String projectCode;
    private Double debitAmount;
    private Double creditAmount;
    private String chequeNo;
    
    //vd_vno,vd_ca_id,vd_project_code,vd_debit,vd_credit,vd_chq_no
    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getSlNo() {
        return slNo;
    }

    public void setSlNo(Integer slNo) {
        this.slNo = slNo;
    }

    
    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getCaCode() {
        return caCode;
    }

    public void setCaCode(String caCode) {
        this.caCode = caCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
    
    

    public Double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    @Override
    public String toString() {
        return "VoucherDetails{" + "detailId=" + detailId + ", slNo=" + slNo + ", voucherNo=" + voucherNo + ", caCode=" + caCode + ", projectCode=" + projectCode + ", debitAmount=" + debitAmount + ", creditAmount=" + creditAmount + ", chequeNo=" + chequeNo + '}';
    }

    

    
    
    
}
