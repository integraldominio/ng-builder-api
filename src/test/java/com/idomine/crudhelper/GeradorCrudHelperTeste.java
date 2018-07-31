package com.idomine.crudhelper;

import org.idomine.domain.crud.service.helper.FolderHelper;
import org.idomine.domain.crud.service.helper.TemplatePathHelper;
import org.junit.Test;

public class GeradorCrudHelperTeste
{

    @Test
    public void outputfolder()
    {
        // assertTrue( GeradorCrudHelper.criarDir("c:/output") );
        // assertTrue( GeradorCrudHelper.criarDir("c:/output/backend") );
        // assertTrue( GeradorCrudHelper.criarDir("c:/output/frontend") );

        // String output = SystemHelper.diretorioOutputWindows();

        // System.out.println(output);

        // assertTrue(GeradorCrudHelper.criarDir(output));
        // assertTrue(GeradorCrudHelper.criarDir(output + "backend"));
        // assertTrue(GeradorCrudHelper.criarDir(output + "frontend"));

        FolderHelper.criarFolders("");
        FolderHelper.output("output/"+TemplatePathHelper.BACKEND_POM, "pom");
        
    }

}
