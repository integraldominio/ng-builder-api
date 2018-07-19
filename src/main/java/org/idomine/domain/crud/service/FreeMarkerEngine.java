package org.idomine.domain.crud.service;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@Component
public class FreeMarkerEngine {

    Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);

    public FreeMarkerEngine() throws IOException { 

        cfg.setDirectoryForTemplateLoading(new File("templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true); 

    }

    public String process(String templateName, Object model) {
        try {

            Template template = cfg.getTemplate(templateName+".ftl");
            StringWriter stringWriter = new StringWriter();
            template.process(model, stringWriter);

            return stringWriter.toString();

        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

}