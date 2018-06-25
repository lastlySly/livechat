package cn.lastlysly.mapper;

import cn.lastlysly.pojo.MessagestypeSheet;
import cn.lastlysly.pojo.MessagestypeSheetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessagestypeSheetMapper {
    long countByExample(MessagestypeSheetExample example);

    int deleteByExample(MessagestypeSheetExample example);

    int insert(MessagestypeSheet record);

    int insertSelective(MessagestypeSheet record);

    List<MessagestypeSheet> selectByExample(MessagestypeSheetExample example);

    int updateByExampleSelective(@Param("record") MessagestypeSheet record, @Param("example") MessagestypeSheetExample example);

    int updateByExample(@Param("record") MessagestypeSheet record, @Param("example") MessagestypeSheetExample example);
}