package com.idomine.crudhelper;

import org.junit.Test;

public class CreateFolderTeste
{

    @Test
    public void criarFolders()
    {
        criarFolders2("sistemax/backend/src/main/java/org/idomine/domain/crud/model");
        criarFolders2("sistemax/backend/src/main/java/org/idomine/domain/crud/model");
    }

    public static void criarFolders2(String path)
    {
        if (path != null)
        {
            String[] paths = path.split("\\/");
            String p = "";
            for (String s : paths)
            {
                p = p + s;
                System.out.println(p);
                p = p + "/";
            }
        }
    }
}
