package cn.lastlysly.pojo;

public class MessagestypeSheet {
    private Integer messagestypeId;

    private String mesagestypeName;

    public Integer getMessagestypeId() {
        return messagestypeId;
    }

    public void setMessagestypeId(Integer messagestypeId) {
        this.messagestypeId = messagestypeId;
    }

    public String getMesagestypeName() {
        return mesagestypeName;
    }

    public void setMesagestypeName(String mesagestypeName) {
        this.mesagestypeName = mesagestypeName == null ? null : mesagestypeName.trim();
    }
}