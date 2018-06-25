package cn.lastlysly.pojo;

public class RolesSheet {
    private Integer rolesId;

    private String rolesUsername;

    private String rolesName;

    public Integer getRolesId() {
        return rolesId;
    }

    public void setRolesId(Integer rolesId) {
        this.rolesId = rolesId;
    }

    public String getRolesUsername() {
        return rolesUsername;
    }

    public void setRolesUsername(String rolesUsername) {
        this.rolesUsername = rolesUsername == null ? null : rolesUsername.trim();
    }

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName == null ? null : rolesName.trim();
    }
}