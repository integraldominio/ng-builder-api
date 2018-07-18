package org.idomine.domain.crud.service;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

public class TemplateService
{
    
    //http://jtwig.org/documentation/quick-start/application

    public static void main(String[] args) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/teste.twig");
        JtwigModel model = JtwigModel.newModel().with("var", "World");

        template.render(model, System.out);
    }
    
}
