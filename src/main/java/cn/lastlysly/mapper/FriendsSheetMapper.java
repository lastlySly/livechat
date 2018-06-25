package cn.lastlysly.mapper;

import cn.lastlysly.pojo.FriendsSheet;
import cn.lastlysly.pojo.FriendsSheetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendsSheetMapper {
    long countByExample(FriendsSheetExample example);

    int deleteByExample(FriendsSheetExample example);

    int insert(FriendsSheet record);

    int insertSelective(FriendsSheet record);

    List<FriendsSheet> selectByExample(FriendsSheetExample example);

    int updateByExampleSelective(@Param("record") FriendsSheet record, @Param("example") FriendsSheetExample example);

    int updateByExample(@Param("record") FriendsSheet record, @Param("example") FriendsSheetExample example);
}