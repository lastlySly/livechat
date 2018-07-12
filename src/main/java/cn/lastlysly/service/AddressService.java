package cn.lastlysly.service;

import cn.lastlysly.pojo.CitySheet;
import cn.lastlysly.pojo.NationSheet;
import cn.lastlysly.pojo.ProvinceSheet;

import java.util.List;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-07-12 09:41
 **/
public interface AddressService {

    /**
     * 查询国家表
     * @return
     */
    List<NationSheet> listNation();

    /**
     * 根据国家ID查询省份
     * @return
     */
    List<ProvinceSheet> listProvince(Integer nationId);

    /**
     * 根据省份ID查城市
     * @param provinceId
     * @return
     */
    List<CitySheet> listCity(Integer provinceId);
}
