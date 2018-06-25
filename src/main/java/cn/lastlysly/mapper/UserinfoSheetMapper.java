package cn.lastlysly.mapper;

import cn.lastlysly.pojo.UserinfoSheet;
import cn.lastlysly.pojo.UserinfoSheetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserinfoSheetMapper {
    long countByExample(UserinfoSheetExample example);

    int deleteByExample(UserinfoSheetExample example);

    int insert(UserinfoSheet record);

    int insertSelective(UserinfoSheet record);

    List<UserinfoSheet> selectByExample(UserinfoSheetExample example);

    int updateByExampleSelective(@Param("record") UserinfoSheet record, @Param("example") UserinfoSheetExample example);

    int updateByExample(@Param("record") UserinfoSheet record, @Param("example") UserinfoSheetExample example);
}