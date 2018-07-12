package cn.lastlysly.service.serviceimpl;


import cn.lastlysly.mapper.CitySheetMapper;
import cn.lastlysly.mapper.NationSheetMapper;
import cn.lastlysly.mapper.ProvinceSheetMapper;
import cn.lastlysly.pojo.*;
import cn.lastlysly.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-07-12 09:41
 **/
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private NationSheetMapper nationSheetMapper;
    @Autowired
    private ProvinceSheetMapper provinceSheetMapper;
    @Autowired
    private CitySheetMapper citySheetMapper;

    /**
     * 查询国家列表
     * @return
     */
    @Override
    public List<NationSheet> listNation() {
        List<NationSheet> nationSheetList = nationSheetMapper.selectByExample(null);
        if (nationSheetList.size() > 0){
            return nationSheetList;
        }
        return null;
    }

    /**
     * 根据国家Id查询省份
     * @param nationId
     * @return
     */
    @Override
    public List<ProvinceSheet> listProvince(Integer nationId) {
//        ProvinceSheetExample provinceSheetExample = new ProvinceSheetExample();
//        ProvinceSheetExample.Criteria criteria = provinceSheetExample.createCriteria();
//        criteria.andProvinceNationidEqualTo(nationId);
        List<ProvinceSheet> provinceSheetList = provinceSheetMapper.selectByExample(null);
        if (provinceSheetList.size() > 0){
            return provinceSheetList;
        }
        return null;
    }

    /**
     * 根据省份Id查询城市
     * @param provinceId
     * @return
     */
    @Override
    public List<CitySheet> listCity(Integer provinceId) {
        CitySheetExample citySheetExample = new CitySheetExample();
        CitySheetExample.Criteria criteria = citySheetExample.createCriteria();
        criteria.andCityProvinceidEqualTo(provinceId);
        List<CitySheet> citySheetList = citySheetMapper.selectByExample(citySheetExample);
        if (citySheetList.size() > 0){
            return citySheetList;
        }
        return null;
    }
}
