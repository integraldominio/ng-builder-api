package com.idomine.generation;

import org.idomine.domain.crud.model.Projeto;
import org.idomine.domain.crud.service.helper.GenerationHelper;

public class GenerateFrontend
{
    public static void main(String[] args)
    {
        /*
         *  Call this to Frontend  
         */
        Projeto p = Projeto.getFake();
        p.setOutputDirectory("output");
        GenerationHelper.generateFrontend(p);
    }

}
