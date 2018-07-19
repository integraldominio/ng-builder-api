package org.idomine.domain.crud.service.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GeradorCrudHelper
{
    public String output = "output";

    public static void cerarCrud(String entity)
    {

        gerarRepository(entity);
        gerarService(entity);
        gerarControllerConsulta(entity);
        gerarControllerCadastro(entity);
        gerarValidation(entity);

    }

    public static void criarFolders()
    {
        criarDir("output/backend");
        criarDir("output/backend/src");
        criarDir("output/backend/src/java");
        criarDir("output/backend/src/java/org");
        criarDir("output/backend/src/java/idomine");
        criarDir("output/backend/src/java/idomine/domain");
        criarDir("output/backend/src/java/idomine/domain/crud");
        criarDir("output/backend/src/java/idomine/domain/crud/model");
        criarDir("output/backend/src/java/idomine/domain/crud/repository");
        criarDir("output/backend/src/java/idomine/domain/crud/service");
        criarDir("output/backend/src/main");
        criarDir("output/backend/src/main/resources");

        criarDir("output/frontend");
        criarDir("output/frontend/e2e");
        criarDir("output/frontend/src");
        criarDir("output/frontend/src/app");
        criarDir("output/frontend/src/app/erp");
        criarDir("output/frontend/src/app/infra");
        criarDir("output/frontend/src/app/pages");
        criarDir("output/frontend/src/app/shared");
        criarDir("output/frontend/src/assets");
        criarDir("output/frontend/src/enviroments");
        
    }

    public static boolean criarDir(String nome)
    {
        return (new File(nome)).mkdir();
    }

    
    public static void output(String arquivo, String template)
    {
        try
        {
            File file = new File( arquivo);
            if (!file.exists())
            {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(template);
            bw.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    
    public static void criar(String arquivo, String template)
    {
        try
        {
            File file = new File("resources/xgen/" + arquivo);
            if (!file.exists())
            {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(template);
            bw.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    public static void criarValidation(String[] entitys)
    {
        for (String e : entitys)
        {
            String v = "validation/" + e + "Validation.java";
            criar(v, gerarValidation(e));
        }

    }

    public static void criarView(String[] entitys)
    {
        for (String e : entitys)
        {
            String v = "web/" + e + ".xhtml";
            criar(v, gerarView(e));
        }

    }

    public static void criarCadastro(String[] entitys)
    {
        for (String e : entitys)
        {
            String c = "cadastro/" + e + "cadastro.java";
            criar(c, gerarControllerCadastro(e));
        }

    }

    public static void criarConsulta(String[] entitys)
    {
        for (String e : entitys)
        {
            String c = "consulta/" + e + "Consulta.java";
            criar(c, gerarControllerConsulta(e));
        }

    }

    public static void criarService(String[] entitys)
    {
        for (String e : entitys)
        {
            String s = "service/" + e + "Service.java";
            criar(s, gerarService(e));
        }

    }

    public static void criarRepository(String[] entitys)
    {
        for (String e : entitys)
        {
            String r = "repository/" + e + "Repository.java";
            criar(r, gerarRepository(e));
        }

    }

    public static void criarRule(String[] entitys)
    {
        for (String e : entitys)
        {
            String r = "rule/" + e + "Rule.java";
            criar(r, gerarRule(e));
        }

    }

    @SuppressWarnings("resource")
    public static String lerTemplate(String template)
    {
        String temp = "";
        BufferedReader br = null;
        FileReader fr = null;

        try
        {
            File file = new File(template);
            if (file.exists())
            {
                fr = new FileReader(template);
                br = new BufferedReader(fr);
                String sCurrentLine;
                br = new BufferedReader(new FileReader(template));
                while ((sCurrentLine = br.readLine()) != null)
                {
                    temp += sCurrentLine + "\n";
                }
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            try
            {
                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return temp;
    }

    public static String gerarRepository(String entity)
    {
        String template = lerTemplate("resources/xgen/xtemplate/repository.template");
        return replace(gerarHeader(entity + "Repository") + template, entity);
    }

    public static String gerarRule(String entity)
    {
        String template = lerTemplate("resources/xgen/xtemplate/rule.template");
        return replace(gerarHeader(entity + "Rule") + template, entity);
    }

    public static String gerarControllerConsulta(String entity)
    {
        String template = lerTemplate("resources/xgen/xtemplate/consulta.template");
        return replace(gerarHeader(entity + "ConsultaMB") + template, entity);

    }

    public static String gerarControllerCadastro(String entity)
    {
        String template = lerTemplate("resources/xgen/xtemplate/cadastro.template");
        return replace(gerarHeader(entity + "MB") + template, entity);

    }

    public static String gerarService(String entity)
    {
        String template = lerTemplate("resources/xgen/xtemplate/service.template");
        return replace(gerarHeader(entity + "Service") + template, entity);
    }

    public static String gerarValidation(String entity)
    {
        String template = lerTemplate("resources/xgen/xtemplate/rule.template");
        return replace(gerarHeader(entity + "Rule") + template, entity);
    }

    public static String gerarHeader(String string)
    {
        String template = lerTemplate("resources/xgen/xtemplate/header.template");
        return template.replace("%s", string);
    }

    public static String gerarView(String entity)
    {
        String template = lerTemplate("resources/xgen/xtemplate/view.template");
        return replace(template, entity);
    }

    public static void criarCadastroFaturaNormal(String[] entitys)
    {
        for (String e : entitys)
        {
            String r = "cadastro/" + e + "MB.java";
            criar(r, gerarRepository(e));
        }
    }

    public static String gerarCadastroFatura(String entity)
    {
        String template = lerTemplate("resources/xgen/xtemplate/fatura.template");
        return replace(template, entity);
    }

    private static String replace(String template, String string)
    {
        return template.replace("%n", string)
                .replace("%s", string.toLowerCase())
                .replace("%S", string.toUpperCase());
    }
}
