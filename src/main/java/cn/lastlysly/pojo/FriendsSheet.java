package cn.lastlysly.pojo;

public class FriendsSheet {
    private Integer friendsId;

    private String friendsFriendid;

    private String friendsUserid;

    private String friendsRemarks;

    private Integer friendsFriendgroupsid;

    public Integer getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(Integer friendsId) {
        this.friendsId = friendsId;
    }

    public String getFriendsFriendid() {
        return friendsFriendid;
    }

    public void setFriendsFriendid(String friendsFriendid) {
        this.friendsFriendid = friendsFriendid == null ? null : friendsFriendid.trim();
    }

    public String getFriendsUserid() {
        return friendsUserid;
    }

    public void setFriendsUserid(String friendsUserid) {
        this.friendsUserid = friendsUserid == null ? null : friendsUserid.trim();
    }

    public String getFriendsRemarks() {
        return friendsRemarks;
    }

    public void setFriendsRemarks(String friendsRemarks) {
        this.friendsRemarks = friendsRemarks == null ? null : friendsRemarks.trim();
    }

    public Integer getFriendsFriendgroupsid() {
        return friendsFriendgroupsid;
    }

    public void setFriendsFriendgroupsid(Integer friendsFriendgroupsid) {
        this.friendsFriendgroupsid = friendsFriendgroupsid;
    }
}