package com.bear.rbac.service;


import com.bear.rbac.entity.GenTableColumn;

import java.util.List;

/**
 * 业务字段 服务层
 * 
 * @author ruoyi
 */
public interface IGenTableColumnService
{
    /**
     * 查询业务字段列表
     * 
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
    List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId);

    /**
     * 新增业务字段
     * 
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    void insertGenTableColumn(GenTableColumn genTableColumn);

    /**
     * 修改业务字段
     * 
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    void updateGenTableColumn(GenTableColumn genTableColumn);

    /**
     * 删除业务字段信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    void deleteGenTableColumnByIds(String ids);
}
