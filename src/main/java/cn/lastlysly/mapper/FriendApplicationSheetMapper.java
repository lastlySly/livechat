package cn.lastlysly.mapper;

import cn.lastlysly.pojo.FriendApplicationSheet;
import cn.lastlysly.pojo.FriendApplicationSheetExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendApplicationSheetMapper {
    long countByExample(FriendApplicationSheetExample example);

    int deleteByExample(FriendApplicationSheetExample example);

    int insert(FriendApplicationSheet record);

    int insertSelective(FriendApplicationSheet record);

    List<FriendApplicationSheet> selectByExample(FriendApplicationSheetExample example);

    int updateByExampleSelective(@Param("record") FriendApplicationSheet record, @Param("example") FriendApplicationSheetExample example);

    int updateByExample(@Param("record") FriendApplicationSheet record, @Param("example") FriendApplicationSheetExample example);
}