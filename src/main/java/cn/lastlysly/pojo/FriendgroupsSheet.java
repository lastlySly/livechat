package cn.lastlysly.pojo;

public class FriendgroupsSheet {
    private Integer friendgroupsId;

    private String friendgroupsName;

    private String friendgroupsUserLoginid;

    public Integer getFriendgroupsId() {
        return friendgroupsId;
    }

    public void setFriendgroupsId(Integer friendgroupsId) {
        this.friendgroupsId = friendgroupsId;
    }

    public String getFriendgroupsName() {
        return friendgroupsName;
    }

    public void setFriendgroupsName(String friendgroupsName) {
        this.friendgroupsName = friendgroupsName == null ? null : friendgroupsName.trim();
    }

    public String getFriendgroupsUserLoginid() {
        return friendgroupsUserLoginid;
    }

    public void setFriendgroupsUserLoginid(String friendgroupsUserLoginid) {
        this.friendgroupsUserLoginid = friendgroupsUserLoginid == null ? null : friendgroupsUserLoginid.trim();
    }
}