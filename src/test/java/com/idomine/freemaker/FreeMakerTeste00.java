package com.idomine.freemaker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;


public class FreeMakerTeste00
{

    public static void main(String[] args) throws IOException, TemplateException
    {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDirectoryForTemplateLoading(new File("templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        
        Map<String, Object> root = new HashMap<>();
        root.put("name", "Big Joe");
        Template temp = cfg.getTemplate("test.ftl");

        Writer out = new OutputStreamWriter(System.out);
        //temp.process(root, out);
       
        StringWriter stringWriter = new StringWriter();
        temp.process(root, stringWriter);
        System.out.println( stringWriter.toString());
        
        
        
    }
}
