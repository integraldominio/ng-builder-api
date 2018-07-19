package com.idomine.crudhelper;

import org.idomine.domain.crud.service.helper.SystemHelper;
import org.junit.Test;

public class SystemHelperTeste
{

    @Test
    public void teste()
    {
        System.out.println(SystemHelper.versaoSistemaOperacional());
        System.out.println(SystemHelper.diretorioOutputLinux());
        System.out.println(SystemHelper.diretorioOutputWindows());
    }

}
