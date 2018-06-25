package cn.lastlysly.mapper;

import cn.lastlysly.pojo.MessagesSheet;
import cn.lastlysly.pojo.MessagesSheetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessagesSheetMapper {
    long countByExample(MessagesSheetExample example);

    int deleteByExample(MessagesSheetExample example);

    int insert(MessagesSheet record);

    int insertSelective(MessagesSheet record);

    List<MessagesSheet> selectByExampleWithBLOBs(MessagesSheetExample example);

    List<MessagesSheet> selectByExample(MessagesSheetExample example);

    int updateByExampleSelective(@Param("record") MessagesSheet record, @Param("example") MessagesSheetExample example);

    int updateByExampleWithBLOBs(@Param("record") MessagesSheet record, @Param("example") MessagesSheetExample example);

    int updateByExample(@Param("record") MessagesSheet record, @Param("example") MessagesSheetExample example);
}