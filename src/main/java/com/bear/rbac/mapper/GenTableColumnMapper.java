package com.bear.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bear.rbac.entity.GenTableColumn;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 业务字段 数据层
 *
 * @author ruoyi
 */
public interface GenTableColumnMapper extends BaseMapper<GenTableColumn> {

    @Select("select column_name, (case when (is_nullable = 'no' <![CDATA[ && ]]> column_key != 'PRI') then '1' else null end) " +
            "as is_required, (case when column_key = 'PRI' then '1' else '0' end) as is_pk, ordinal_position as sort, column_comment," +
            " (case when extra = 'auto_increment' then '1' else '0' end) as is_increment, column_type" +
            "from information_schema.columns where table_schema = (select database()) and table_name = (#{tableName})" +
            "order by ordinal_position")
    List<GenTableColumn> selectDbTableColumnsByName(String tableName);

}
