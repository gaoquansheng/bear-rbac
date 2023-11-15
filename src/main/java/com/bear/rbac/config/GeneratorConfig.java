package com.bear.rbac.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;

public class GeneratorConfig {

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir")+"/src/main/java";
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/bear","root","gm211318")
                // 全局配置
                .globalConfig(builder -> {
                    builder.outputDir(projectPath).author("bear");
                })
                // 包配置
                .packageConfig(builder -> builder.parent("com.bear.rbac"))
                // 模版配置
                .templateConfig(builder -> builder.disable(TemplateType.XML))
                // 表配置
                .strategyConfig(builder -> builder.addInclude("sys_user"))
                .execute();
    }
}
