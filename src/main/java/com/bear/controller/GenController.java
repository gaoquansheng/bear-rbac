package com.bear.controller;


import cn.hutool.core.convert.Convert;
import com.bear.common.Response;
import com.bear.entity.GenTableColumn;
import com.bear.entity.GenTable;
import com.bear.service.IGenTableColumnService;
import com.bear.service.IGenTableService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/tool/gen")
public class GenController {
    @Autowired
    private IGenTableService genTableService;

    @Autowired
    private IGenTableColumnService genTableColumnService;

    /**
     * 查询代码生成列表
     */
    @GetMapping("/list")
    public Response<List<GenTable>> genList(GenTable genTable) {

        List<GenTable> list = genTableService.selectGenTableList(genTable);
        return Response.success(list);
    }

    /**
     * 修改代码生成业务
     */
    @GetMapping(value = "/{tableId}")
    public Response getInfo(@PathVariable Long tableId) {

        GenTable table = genTableService.selectGenTableById(tableId);
        List<GenTable> tables = genTableService.selectGenTableAll();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", table);
        map.put("rows", list);
        map.put("tables", tables);
        return Response.success(map);
    }

    /**
     * 查询数据库列表
     */
    @GetMapping("/db/list")
    public Response dataList(GenTable genTable) {
        List<GenTable> list = genTableService.selectDbTableList(genTable);
        return Response.success(list);
    }

    /**
     * 查询数据表字段列表
     */
    @GetMapping(value = "/column/{tableId}")
    public Response columnList(Long tableId) {
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);

        return Response.success(list);
    }

    /**
     * 导入表结构（保存）
     */
    @PostMapping("/importTable")
    public Response importTableSave(String tables) {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(Arrays.asList(tableNames));
        genTableService.importGenTable(tableList);
        return Response.success();
    }

    /**
     * 修改保存代码生成业务
     */
    @PutMapping
    public Response editSave(@Validated @RequestBody GenTable genTable) {
        genTableService.updateGenTable(genTable);
        return Response.success();
    }

    /**
     * 删除代码生成
     */
    @DeleteMapping("/{tableIds}")
    public Response remove(@PathVariable Long[] tableIds) {
        genTableService.deleteGenTableByIds(Arrays.asList(tableIds));
        return Response.success();
    }

    /**
     * 预览代码
     */
    @GetMapping("/preview/{tableId}")
    public Response preview(@PathVariable("tableId") Long tableId) throws IOException {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return Response.success(dataMap);
    }

    /**
     * 生成代码（下载方式）
     */
    @GetMapping("/download/{tableName}")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = genTableService.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 生成代码（自定义路径）
     */
    @GetMapping("/genCode/{tableName}")
    public Response genCode(@PathVariable("tableName") String tableName) {
        genTableService.generatorCode(tableName);
        return Response.success();
    }

    /**
     * 同步数据库
     */
    @GetMapping("/synchDb/{tableName}")
    public Response synchDb(@PathVariable("tableName") String tableName) {
        genTableService.synchDb(tableName);
        return Response.success();
    }

    /**
     * 批量生成代码
     */
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.downloadCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}