package com.idomine.generation;

import org.idomine.domain.crud.model.Projeto;
import org.idomine.domain.crud.service.helper.GenerationHelper;

public class GenerateBackend
{
    public static void main(String[] args)
    {
        /*
         *  Call this to Backend  
         */
        Projeto p = Projeto.getFake();
        p.setOutputDirectory("output");
        GenerationHelper.generateBackend(p);
    }
}
