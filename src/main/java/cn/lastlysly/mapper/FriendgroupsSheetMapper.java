package cn.lastlysly.mapper;

import cn.lastlysly.pojo.FriendgroupsSheet;
import cn.lastlysly.pojo.FriendgroupsSheetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendgroupsSheetMapper {
    long countByExample(FriendgroupsSheetExample example);

    int deleteByExample(FriendgroupsSheetExample example);

    int insert(FriendgroupsSheet record);

    int insertSelective(FriendgroupsSheet record);

    List<FriendgroupsSheet> selectByExample(FriendgroupsSheetExample example);

    int updateByExampleSelective(@Param("record") FriendgroupsSheet record, @Param("example") FriendgroupsSheetExample example);

    int updateByExample(@Param("record") FriendgroupsSheet record, @Param("example") FriendgroupsSheetExample example);
}