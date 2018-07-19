package org.idomine.domain.crud.service.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.StringJoiner;


public class GeradorBuilderHelper
{
    
    public static void criarBuilder(Class<?> o)
    {
        String template = gerarBuilder(o);
        String arquivo = o.getSimpleName()+"Builder";
        try{
            File file = new File("resources/xgen/builder/"+arquivo+".java");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(template);
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static String gerarBuilder(Class<?> o)
    {
        String template="";
        template+= "public class "+o.getSimpleName()+"Builder \n"+
        "{ \n";
        template+=atributosPrivados(o);
        template+=metodoSet(o);
        template+=metodoGetBuilder(o)+" \n}";
        return template;
    }

    public static String atributosPrivados(Class<?> o)
    {
        String template = "";

        for (Field field : o.getDeclaredFields())
        {
            template += " private " + field.getType().getSimpleName() + " " + field.getName() + "; \n";
        }
        return template;
    }

    public static String listaAtributosParametros(Class<?> o)
    {
        StringJoiner template = new StringJoiner(",");

        for (Field field : o.getDeclaredFields())
        {
            template.add( field.getType().getSimpleName()+" "+field.getName() );
        }
        return template.toString();
    }

    public static String metodoSet(Class<?> o)
    {
        String template = "";

        for (Field field : o.getDeclaredFields())
        {
            template += "\npublic Builder " + field.getName() + "(" + field.getType().getSimpleName() + " " + field.getName()
                    + ") \n"
                    + "{ \n" +
                    "   this." + field.getName() + " = " + field.getName() + "; \n" +
                    "   return this; \n" +
                    "}";
        }
        return template;
    }

    public static String metodoGetBuilder(Class<?> o)
    {
        String nome= o.getSimpleName();
        String template =

                "\npublic "+nome+" build() \n" +
                        "{ \n" +
                        "  return new "+nome+"( "+listaAtributosParametros(o)+" ); \n" +
                        "} \n" +
                        "public static "+nome+"Builder getBuilder() \n" +
                        "{ \n" +
                        "return new "+nome+"Builder(); \n" +
                        "} \n";
        return template;
    }

}
