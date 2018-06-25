package cn.lastlysly.pojo;

public class PermissionSheet {
    private Integer permissionId;

    private String permissionRolename;

    private String permissionName;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionRolename() {
        return permissionRolename;
    }

    public void setPermissionRolename(String permissionRolename) {
        this.permissionRolename = permissionRolename == null ? null : permissionRolename.trim();
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }
}