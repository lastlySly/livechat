package cn.lastlysly.controller;

import cn.lastlysly.myutils.MyResult;
import cn.lastlysly.pojo.CitySheet;
import cn.lastlysly.pojo.NationSheet;
import cn.lastlysly.pojo.ProvinceSheet;
import cn.lastlysly.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-07-12 10:02
 **/
@Controller
@RequestMapping("/userinforevise")
public class UserInfoReviseController {

    @Autowired
    private AddressService addressService;

    /**
     * 查询国家列表
     * @return
     */
    @CrossOrigin
    @RequestMapping(value="/listnation",method = RequestMethod.POST)
    @ResponseBody
    public MyResult listNation(){
        List<NationSheet> nationSheetList = addressService.listNation();
        return new MyResult(1,"查询国家列表成功",nationSheetList);
    }

    /**
     * 根据国家ID获取省份
     * @param nationId
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/listprovince",method = RequestMethod.POST)
    @ResponseBody
    public MyResult listProvince(@RequestParam(value = "nationID",required = false) Integer nationId){
        List<ProvinceSheet> provinceSheetList = addressService.listProvince(nationId);
        return new MyResult(1,"根据国家ID查询省份列表成功",provinceSheetList);
    }

    /**
     * 根据省份ID查询城市
     * @param provinceId
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/listcity",method = RequestMethod.POST)
    @ResponseBody
    public MyResult listCity(@RequestParam(value = "provinceId",required = false) Integer provinceId){
        List<CitySheet> citySheetList = addressService.listCity(provinceId);
        return new MyResult(1,"根据省份ID查询城市列表成功",citySheetList);
    }

}
