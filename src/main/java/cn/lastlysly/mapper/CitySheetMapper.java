package cn.lastlysly.mapper;

import cn.lastlysly.pojo.CitySheet;
import cn.lastlysly.pojo.CitySheetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CitySheetMapper {
    long countByExample(CitySheetExample example);

    int deleteByExample(CitySheetExample example);

    int insert(CitySheet record);

    int insertSelective(CitySheet record);

    List<CitySheet> selectByExample(CitySheetExample example);

    int updateByExampleSelective(@Param("record") CitySheet record, @Param("example") CitySheetExample example);

    int updateByExample(@Param("record") CitySheet record, @Param("example") CitySheetExample example);
}