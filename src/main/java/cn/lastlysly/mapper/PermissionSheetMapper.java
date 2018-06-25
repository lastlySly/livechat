package cn.lastlysly.mapper;

import cn.lastlysly.pojo.PermissionSheet;
import cn.lastlysly.pojo.PermissionSheetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionSheetMapper {
    long countByExample(PermissionSheetExample example);

    int deleteByExample(PermissionSheetExample example);

    int insert(PermissionSheet record);

    int insertSelective(PermissionSheet record);

    List<PermissionSheet> selectByExample(PermissionSheetExample example);

    int updateByExampleSelective(@Param("record") PermissionSheet record, @Param("example") PermissionSheetExample example);

    int updateByExample(@Param("record") PermissionSheet record, @Param("example") PermissionSheetExample example);
}