package com.bear.rbac.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bear.rbac.constants.GenConstants;
import com.bear.rbac.entity.GenTable;
import com.bear.rbac.entity.GenTableColumn;
import com.bear.rbac.exception.BusinessException;
import com.bear.rbac.mapper.GenTableColumnMapper;
import com.bear.rbac.mapper.GenTableMapper;
import com.bear.rbac.service.IGenTableService;
import com.bear.rbac.utils.GenUtils;
import com.bear.rbac.utils.VelocityInitializer;
import com.bear.rbac.utils.VelocityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 业务 服务层实现
 *
 * @author ruoyi
 */
@Slf4j
@Service
public class GenTableServiceImpl extends ServiceImpl<GenTableMapper,GenTable> implements IGenTableService {

    @Autowired
    private GenTableColumnServiceImpl genTableColumnService;

    @Autowired
    private GenTableColumnMapper genTableColumnMapper;
    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    @Override
    public GenTable selectGenTableById(Long id) {

        GenTable genTable = getById(id);
        setTableFromOptions(genTable);
        return genTable;
    }

    /**
     * 查询业务列表
     *
     * @param genTable 业务信息
     * @return 业务集合
     */
    @Override
    public List<GenTable> selectGenTableList(GenTable genTable) {


        return list();
    }

    /**
     * 查询据库列表
     *
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    @Override
    public List<GenTable> selectDbTableList(GenTable genTable) {

        return list();
    }

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    @Override
    public List<GenTable> selectDbTableListByNames(List<String> tableNames) {
        return lambdaQuery().in(GenTable::getTableName,tableNames).list();
    }

    /**
     * 查询所有表信息
     *
     * @return 表信息集合
     */
    @Override
    public List<GenTable> selectGenTableAll() {
        return list();
    }

    /**
     * 修改业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGenTable(GenTable genTable) {

        updateById(genTable);
        List<GenTableColumn> columns = genTable.getColumns();
        genTableColumnService.updateBatchById(columns);

    }

    /**
     * 删除业务对象
     *
     * @param tableIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGenTableByIds(List<Long> tableIds) {
        removeBatchByIds(tableIds);
    }

    /**
     * 导入表结构
     *
     * @param tableList 导入表列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importGenTable(List<GenTable> tableList) {
        String operName = "";
        // TODO: 2023/11/29 获取用户信息
        tableList = tableList.parallelStream()
                .peek(table -> {
                    GenUtils.initTable(table, operName);
                }).toList();
        saveBatch(tableList);
        List<GenTableColumn> columnList = tableList.parallelStream()
                .map(table -> {
                    List<GenTableColumn> columns = genTableColumnMapper.selectDbTableColumnsByName(table.getTableName());
                    for (GenTableColumn column : columns) {
                        GenUtils.initColumnField(column, table);
                    }
                    return columns;
                })
                .flatMap(Collection::parallelStream)
                .toList();
        genTableColumnService.saveBatch(columnList);
    }

    /**
     * 预览代码
     *
     * @param tableId 表编号
     * @return 预览数据列表
     */
    @Override
    public Map<String, String> previewCode(Long tableId) {
        Map<String, String> dataMap = new LinkedHashMap<>();
        // 查询表信息
        GenTable table = getById(tableId);
        // 设置主键列信息
        setPkColumn(table);
        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList();
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            dataMap.put(template, sw.toString());
        }
        return dataMap;
    }

    /**
     * 生成代码（下载方式）
     *
     * @param tableName 表名称
     * @return 数据
     */
    @Override
    public byte[] downloadCode(String tableName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        generatorCode(tableName, zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 生成代码（自定义路径）
     *
     * @param tableName 表名称
     */
    @Override
    public void generatorCode(String tableName) {
        // 查询表信息
        GenTable table = lambdaQuery().eq(GenTable::getTableName,tableName).one();

        // 设置主键列信息
        setPkColumn(table);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList();
        for (String template : templates) {
            if (!StringUtils.containsAny(template, "sql.vm", "api.js.vm", "index.vue.vm", "index-tree.vue.vm")) {
                // 渲染模板
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(template, "UTF-8");
                tpl.merge(context, sw);
                try {
                    String path = getGenPath(table, template);
                    FileUtils.writeStringToFile(new File(path), sw.toString(), "UTF-8");
                } catch (IOException e) {
                    throw new BusinessException("渲染模板失败，表名：" + table.getTableName());
                }
            }
        }
    }

    /**
     * 同步数据库
     *
     * @param tableName 表名称
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void synchDb(String tableName) {

        GenTable table = lambdaQuery().eq(GenTable::getTableName,tableName).one();
        List<GenTableColumn> tableColumns = table.getColumns();
        Map<String, GenTableColumn> tableColumnMap = tableColumns.stream().collect(Collectors.toMap(GenTableColumn::getColumnName, Function.identity()));

        List<GenTableColumn> dbTableColumns = genTableColumnMapper.selectDbTableColumnsByName(tableName);
        if (CollectionUtils.isEmpty(dbTableColumns)) {
            throw new BusinessException("同步数据失败，原表结构不存在");
        }
        List<String> dbTableColumnNames = dbTableColumns.stream().map(GenTableColumn::getColumnName).toList();

        dbTableColumns.forEach(column -> {
            GenUtils.initColumnField(column, table);
            if (tableColumnMap.containsKey(column.getColumnName())) {
                GenTableColumn prevColumn = tableColumnMap.get(column.getColumnName());
                column.setColumnId(prevColumn.getColumnId());
                if (column.isList()) {
                    // 如果是列表，继续保留查询方式/字典类型选项
                    column.setDictType(prevColumn.getDictType());
                    column.setQueryType(prevColumn.getQueryType());
                }
                if (StringUtils.isNotEmpty(prevColumn.getIsRequired()) && !column.isPk()
                        && (column.isInsert() || column.isEdit())
                        && ((column.isUsableColumn()) || (!column.isSuperColumn()))) {
                    // 如果是(新增/修改&非主键/非忽略及父属性)，继续保留必填/显示类型选项
                    column.setIsRequired(prevColumn.getIsRequired());
                    column.setHtmlType(prevColumn.getHtmlType());
                }
                genTableColumnService.updateById(column);
            } else {
                genTableColumnService.save(column);
            }
        });

        List<Long> delColumns = tableColumns.stream()
                .filter(column -> !dbTableColumnNames.contains(column.getColumnName()))
                .map(GenTableColumn::getColumnId)
                .toList();
        if (!CollectionUtils.isEmpty(delColumns)) {
            genTableColumnService.removeBatchByIds(delColumns);
        }
    }

    /**
     * 批量生成代码（下载方式）
     *
     * @param tableNames 表数组
     * @return 数据
     */
    @Override
    public byte[] downloadCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            generatorCode(tableName, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 查询表信息并生成代码
     */
    private void generatorCode(String tableName, ZipOutputStream zip) {
        // 查询表信息
        GenTable table = lambdaQuery().eq(GenTable::getTableName,tableName).one();
        // 设置主子表信息
        // 设置主键列信息
        setPkColumn(table);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList();
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.flush();
                zip.closeEntry();
            } catch (IOException e) {
                log.error("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }
    }



    /**
     * 设置主键列信息
     *
     * @param table 业务表信息
     */
    public void setPkColumn(GenTable table) {
        for (GenTableColumn column : table.getColumns()) {
            if (column.isPk()) {
                table.setPkColumn(column);
                break;
            }
        }
        if (table.getPkColumn() != null) {
            table.setPkColumn(table.getColumns().get(0));
        }
    }



    /**
     * 设置代码生成其他选项值
     *
     * @param genTable 设置后的生成对象
     */
    public void setTableFromOptions(GenTable genTable) {
        JSONObject paramsObj = JSON.parseObject(genTable.getOptions());
        if (paramsObj != null) {

            String parentMenuId = paramsObj.getString(GenConstants.PARENT_MENU_ID);
            String parentMenuName = paramsObj.getString(GenConstants.PARENT_MENU_NAME);

            genTable.setParentMenuId(parentMenuId);
            genTable.setParentMenuName(parentMenuName);
        }
    }

    /**
     * 获取代码生成地址
     *
     * @param table    业务表信息
     * @param template 模板文件路径
     * @return 生成地址
     */
    public static String getGenPath(GenTable table, String template) {
        String genPath = table.getGenPath();
        if (StringUtils.equals(genPath, "/")) {
            return System.getProperty("user.dir") + File.separator + "src" + File.separator + VelocityUtils.getFileName(template, table);
        }
        return genPath + File.separator + VelocityUtils.getFileName(template, table);
    }
}