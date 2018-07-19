package org.idomine.domain.crud.service.helper;

import java.io.File;

public final class SystemHelper
{
    private static final  String separador = File.separator;
    
    private SystemHelper()
    {
    }

    public static String versaoSistemaOperacional()
    {
        return System.getProperty("os.name").toLowerCase();
    }

    public static boolean sistemaOperacionalWindows()
    {
        return versaoSistemaOperacional().contains("windows");
    }

    public static boolean sistemaOperacionalLinux()
    {
        return !sistemaOperacionalWindows();
    }

    public static String diretorioOutputLinux()
    {
        return separador + "opt" + separador + "ng-builder-api" + separador + "output" + separador;
    }

    public static String diretorioOutputWindows()
    {
        String diretorio = System.getProperty("user.dir");
        if (diretorio.contains("bin"))
        {
            diretorio = diretorio.replace("bin", "standalone");
        }
        return diretorio + separador + "output" +separador;
    }


}
