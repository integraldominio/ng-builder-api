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

    public static void criarFolders()
    {
        criarDir("output/backend");
        criarDir("output/backend/src");
        criarDir("output/backend/src/main");
        criarDir("output/backend/src/main/java");
        criarDir("output/backend/src/main/java/org");
        criarDir("output/backend/src/main/java/org/idomine");
        criarDir("output/backend/src/main/java/org/idomine/domain");
        criarDir("output/backend/src/main/java/org/idomine/domain/crud");
        criarDir("output/backend/src/main/java/org/idomine/domain/crud/model");
        criarDir("output/backend/src/main/java/org/idomine/domain/crud/repository");
        criarDir("output/backend/src/main/java/org/idomine/domain/crud/resource");
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
            File file = new File(arquivo);
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

}
