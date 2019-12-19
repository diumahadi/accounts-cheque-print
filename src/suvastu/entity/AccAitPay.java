package suvastu.entity;

public class AccAitPay {
    private Integer Id;
    private Long paidId;
    private String voucherNo;
    private Long chequeId;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Long getPaidId() {
        return paidId;
    }

    public void setPaidId(Long paidId) {
        this.paidId = paidId;
    }

    

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public Long getChequeId() {
        return chequeId;
    }

    public void setChequeId(Long chequeId) {
        this.chequeId = chequeId;
    }

    @Override
    public String toString() {
        return "AccAitPay{" + "Id=" + Id + ", paidId=" + paidId + ", voucherNo=" + voucherNo + ", chequeId=" + chequeId + '}';
    }

   
    
    
}
