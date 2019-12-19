package suvastu.entity;

import java.util.Date;
import java.util.List;
import suvastu.model.AppUser;

public class VoucherMaster {
    
    private String voucherNo;
    private Date voucherDate;
    private Integer finYear;
    private String narration;
    private Integer companyId;
    private AppUser appUser;
    private List<VoucherDetails> voucherDetailList;
    
    //vm_vno,vm_vdate,vm_fin_year,vm_narration,company_id,vm_created_by
    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public Date getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(Date voucherDate) {
        this.voucherDate = voucherDate;
    }

    public Integer getFinYear() {
        return finYear;
    }

    public void setFinYear(Integer finYear) {
        this.finYear = finYear;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public List<VoucherDetails> getVoucherDetailList() {
        return voucherDetailList;
    }

    public void setVoucherDetailList(List<VoucherDetails> voucherDetailList) {
        this.voucherDetailList = voucherDetailList;
    }    
    

    @Override
    public String toString() {
        return "VoucherMaster{" + "voucherNo=" + voucherNo + ", voucherDate=" + voucherDate + ", finYear=" + finYear + ", narration=" + narration + ", companyId=" + companyId + ", appUser=" + appUser + '}';
    }

    
    
    
    
}
