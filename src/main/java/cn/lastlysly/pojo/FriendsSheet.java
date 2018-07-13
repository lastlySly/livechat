package cn.lastlysly.pojo;

public class FriendsSheet {
    private Integer friendsId;

    private String friendsFriendLoginid;

    private String friendsUserLoginid;

    private String friendsRemarks;

    private Integer friendsFriendgroupsid;

    public Integer getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(Integer friendsId) {
        this.friendsId = friendsId;
    }

    public String getFriendsFriendLoginid() {
        return friendsFriendLoginid;
    }

    public void setFriendsFriendLoginid(String friendsFriendLoginid) {
        this.friendsFriendLoginid = friendsFriendLoginid == null ? null : friendsFriendLoginid.trim();
    }

    public String getFriendsUserLoginid() {
        return friendsUserLoginid;
    }

    public void setFriendsUserLoginid(String friendsUserLoginid) {
        this.friendsUserLoginid = friendsUserLoginid == null ? null : friendsUserLoginid.trim();
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