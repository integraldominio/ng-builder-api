package com.idomine.helpers;

import java.io.IOException;

import org.idomine.domain.crud.service.helper.CompactadorHelper;

public class CompactadorTeste
{

    public static void main(String args[])
    {
        try
        {
            CompactadorHelper.compactarParaZip("output/sistema-zipado.zip", "output/README.md");
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
