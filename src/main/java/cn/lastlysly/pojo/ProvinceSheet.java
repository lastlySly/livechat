package cn.lastlysly.pojo;

public class ProvinceSheet {
    private Integer provinceId;

    private String provinceName;

    private Integer provinceNationid;

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public Integer getProvinceNationid() {
        return provinceNationid;
    }

    public void setProvinceNationid(Integer provinceNationid) {
        this.provinceNationid = provinceNationid;
    }
}