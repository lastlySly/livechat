package cn.lastlysly.mapper;

import cn.lastlysly.pojo.RolesSheet;
import cn.lastlysly.pojo.RolesSheetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolesSheetMapper {
    long countByExample(RolesSheetExample example);

    int deleteByExample(RolesSheetExample example);

    int insert(RolesSheet record);

    int insertSelective(RolesSheet record);

    List<RolesSheet> selectByExample(RolesSheetExample example);

    int updateByExampleSelective(@Param("record") RolesSheet record, @Param("example") RolesSheetExample example);

    int updateByExample(@Param("record") RolesSheet record, @Param("example") RolesSheetExample example);
}