package org.idomine.domain.crud.service.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class GeradorCrudHelper
{
    public static void criarFolders(String base)
    {
        base=base.trim();
        if ( "".equals(base))
            base="output";
        
        criarDir(base);
        criarDir(base+"/backend");
        criarDir(base+"/backend/src");
        criarDir(base+"/backend/src/main");
        criarDir(base+"/backend/src/main/java");
        criarDir(base+"/backend/src/main/java/org");
        criarDir(base+"/backend/src/main/java/org/idomine");
        criarDir(base+"/backend/src/main/java/org/idomine/domain");
        criarDir(base+"/backend/src/main/java/org/idomine/domain/crud");
        criarDir(base+"/backend/src/main/java/org/idomine/domain/crud/model");
        criarDir(base+"/backend/src/main/java/org/idomine/domain/crud/repository");
        criarDir(base+"/backend/src/main/java/org/idomine/domain/crud/resource");
        criarDir(base+"/backend/src/main/resources");

        criarDir(base+"/frontend");
        criarDir(base+"/frontend/e2e");
        criarDir(base+"/frontend/src");
        criarDir(base+"/frontend/src/app");
        criarDir(base+"/frontend/src/app/erp");
        criarDir(base+"/frontend/src/app/infra");
        criarDir(base+"/frontend/src/app/infra/comps");
        criarDir(base+"/frontend/src/app/infra/comps/dashcard");
        criarDir(base+"/frontend/src/app/infra/pipes");
        criarDir(base+"/frontend/src/app/infra/security");
        criarDir(base+"/frontend/src/app/pages");
        criarDir(base+"/frontend/src/app/pages/base");
        criarDir(base+"/frontend/src/app/pages/erro");
        criarDir(base+"/frontend/src/app/pages/home");
        criarDir(base+"/frontend/src/app/pages/login");
        criarDir(base+"/frontend/src/app/pages/sidenav");
        criarDir(base+"/frontend/src/app/pages/sobre");
        criarDir(base+"/frontend/src/app/shared");
        criarDir(base+"/frontend/src/app/shared/autocomplete");
        criarDir(base+"/frontend/src/assets");
        criarDir(base+"/frontend/src/assets/icon");
        criarDir(base+"/frontend/src/assets/images");
        criarDir(base+"/frontend/src/assets/svg-loaders");
        criarDir(base+"/frontend/src/environments");

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

    @SuppressWarnings("resource")
    public static void copyFile(File source, File destination) throws IOException
    {
        if (destination.exists())
            destination.delete();
        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;
        try
        {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),
                    destinationChannel);
        }
        finally
        {
            if (sourceChannel != null && sourceChannel.isOpen())
                sourceChannel.close();
            if (destinationChannel != null && destinationChannel.isOpen())
                destinationChannel.close();
        }
    }
}
