package cn.lastlysly.mapper;

import cn.lastlysly.pojo.NationSheet;
import cn.lastlysly.pojo.NationSheetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NationSheetMapper {
    long countByExample(NationSheetExample example);

    int deleteByExample(NationSheetExample example);

    int insert(NationSheet record);

    int insertSelective(NationSheet record);

    List<NationSheet> selectByExample(NationSheetExample example);

    int updateByExampleSelective(@Param("record") NationSheet record, @Param("example") NationSheetExample example);

    int updateByExample(@Param("record") NationSheet record, @Param("example") NationSheetExample example);
}