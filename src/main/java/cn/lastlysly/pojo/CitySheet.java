package cn.lastlysly.pojo;

public class CitySheet {
    private Integer cityId;

    private String cityName;

    private Integer cityProvinceid;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Integer getCityProvinceid() {
        return cityProvinceid;
    }

    public void setCityProvinceid(Integer cityProvinceid) {
        this.cityProvinceid = cityProvinceid;
    }
}