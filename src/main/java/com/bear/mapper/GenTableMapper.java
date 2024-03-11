package com.bear.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bear.entity.GenTable;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 业务 数据层
 *
 * @author ruoyi
 */
public interface GenTableMapper extends BaseMapper<GenTable> {


    @Select("select table_name, table_comment, create_time, update_time from information_schema.tables")
    public List genTableList();
}
