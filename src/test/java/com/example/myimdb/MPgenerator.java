package com.example.myimdb;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.query.SQLQuery;

import java.util.Collections;

public class MPgenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://59.110.49.185:3306/imdb", "dmsTest", "MyIMDBsx123.")
                .globalConfig(builder -> {
                    builder.author("Adonis") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\WorkSpace\\idea\\MyIMDB\\src\\main\\java"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.databaseQueryClass(SQLQuery.class).typeConvert(new MySqlTypeConvert()).dbQuery(new MySqlQuery()))
                .packageConfig(builder -> {
                    builder.parent("com/example/myimdb") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\WorkSpace\\idea\\MyIMDB\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("credits,keywords,links,links_small,movies_metadata,ratings,ratings_small") ;// 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })

                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
