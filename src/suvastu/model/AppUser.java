package suvastu.model;

public class AppUser {
    
    private String userId;
    private String userName;
    private String userPassword;
    private String userLevel;
    private Integer isActive;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    } 

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }
    
    

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "AppUser{" + "userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userLevel=" + userLevel + ", isActive=" + isActive + '}';
    }

    

     
    
    
}
