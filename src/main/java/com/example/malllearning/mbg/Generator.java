package com.example.malllearning.mbg;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    public static void main(String[] args) throws Exception {
        // 收集警告信息
        List<String> warnings = new ArrayList<>();
        // 当执行代码重复时覆盖代码
        boolean overwrite = true;
        // read mbg configuration
        InputStream is = Generator.class.getResourceAsStream("/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        assert is != null;
        is.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        // 创建mgb
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        // 执行生成代码
        myBatisGenerator.generate(null);

        for (String warning : warnings) {
            System.out.println(warning);
        }

    }
}
