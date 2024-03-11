package com.bear.entity;

import com.bear.base.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 业务表 gen_table
 *
 * @author ruoyi
 */
@Data
public class GenTable extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Long tableId;

    /**
     * 表名称
     */
    @NotBlank(message = "表名称不能为空")
    private String tableName;

    /**
     * 表描述
     */
    @NotBlank(message = "表描述不能为空")
    private String tableComment;

    /**
     * 实体类名称(首字母大写)
     */
    @NotBlank(message = "实体类名称不能为空")
    private String className;


    /**
     * 生成包路径
     */
    @NotBlank(message = "生成包路径不能为空")
    private String packageName;

    /**
     * 生成模块名
     */
    @NotBlank(message = "生成模块名不能为空")
    private String moduleName;

    /**
     * 生成业务名
     */
    @NotBlank(message = "生成业务名不能为空")
    private String businessName;

    /**
     * 生成功能名
     */
    @NotBlank(message = "生成功能名不能为空")
    private String functionName;

    /**
     * 生成作者
     */
    @NotBlank(message = "作者不能为空")
    private String functionAuthor;

    /**
     * 生成代码方式（0zip压缩包 1自定义路径）
     */
    private String genType;

    /**
     * 生成路径（不填默认项目路径）
     */
    private String genPath;

    /**
     * 主键信息
     */
//    private GenTableColumn pkColumn;

    /**
     * 表列信息
     */
//    @Valid
//    private List<GenTableColumn> columns;

    /**
     * 其它生成选项
     */
//    private String options;
//
//
//    /**
//     * 上级菜单ID字段
//     */
//    private String parentMenuId;
//
//    /**
//     * 上级菜单名称字段
//     */
//    private String parentMenuName;

}