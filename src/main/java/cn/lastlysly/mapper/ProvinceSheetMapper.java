package cn.lastlysly.mapper;

import cn.lastlysly.pojo.ProvinceSheet;
import cn.lastlysly.pojo.ProvinceSheetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProvinceSheetMapper {
    long countByExample(ProvinceSheetExample example);

    int deleteByExample(ProvinceSheetExample example);

    int insert(ProvinceSheet record);

    int insertSelective(ProvinceSheet record);

    List<ProvinceSheet> selectByExample(ProvinceSheetExample example);

    int updateByExampleSelective(@Param("record") ProvinceSheet record, @Param("example") ProvinceSheetExample example);

    int updateByExample(@Param("record") ProvinceSheet record, @Param("example") ProvinceSheetExample example);
}