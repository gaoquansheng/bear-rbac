package com.bear.service.impl;


import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.StringWriter;
import java.util.Properties;

@SpringBootTest
class SysUserServiceImplTest {


    @Test
    public void test() {
        VelocityEngine velocityEngine = new VelocityEngine();

        Properties props = new Properties();
        props.setProperty("resource.loader", "classpath");
        props.setProperty("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        velocityEngine.init(props);

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("name","world");

        Template template = velocityEngine.getTemplate("Hello.vm");
        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext,stringWriter);
        System.out.println(stringWriter.toString());
    }
}