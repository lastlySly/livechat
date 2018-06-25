package cn.lastlysly.pojo;

public class FriendgroupsSheet {
    private Integer friendgroupsId;

    private String friendgroupsName;

    private String friendgroupsUserid;

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

    public String getFriendgroupsUserid() {
        return friendgroupsUserid;
    }

    public void setFriendgroupsUserid(String friendgroupsUserid) {
        this.friendgroupsUserid = friendgroupsUserid == null ? null : friendgroupsUserid.trim();
    }
}