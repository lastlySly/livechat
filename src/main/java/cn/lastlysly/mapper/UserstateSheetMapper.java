package cn.lastlysly.mapper;

import cn.lastlysly.pojo.UserstateSheet;
import cn.lastlysly.pojo.UserstateSheetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserstateSheetMapper {
    long countByExample(UserstateSheetExample example);

    int deleteByExample(UserstateSheetExample example);

    int insert(UserstateSheet record);

    int insertSelective(UserstateSheet record);

    List<UserstateSheet> selectByExample(UserstateSheetExample example);

    int updateByExampleSelective(@Param("record") UserstateSheet record, @Param("example") UserstateSheetExample example);

    int updateByExample(@Param("record") UserstateSheet record, @Param("example") UserstateSheetExample example);
}