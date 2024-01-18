package com.bear.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bear.entity.GenTableColumn;
import com.bear.mapper.GenTableColumnMapper;
import com.bear.service.IGenTableColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 业务字段 服务层实现
 *
 * @author ruoyi
 */
@Service
public class GenTableColumnServiceImpl extends ServiceImpl<GenTableColumnMapper, GenTableColumn> implements IGenTableColumnService {


    @Autowired
    private GenTableServiceImpl genTableService;

    /**
     * 查询业务字段列表
     *
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
    @Override
    public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId) {

        return lambdaQuery().eq(GenTableColumn::getTableId, tableId).list();
    }

    /**
     * 新增业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    @Override
    public void insertGenTableColumn(GenTableColumn genTableColumn) {
        save(genTableColumn);
    }

    /**
     * 修改业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    @Override
    public void updateGenTableColumn(GenTableColumn genTableColumn) {
        updateById(genTableColumn);
    }

    /**
     * 删除业务字段对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGenTableColumnByIds(String ids) {

        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        removeBatchByIds(idList);
    }
}
