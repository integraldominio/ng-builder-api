package com.idomine.freemaker;

import org.idomine.NgBuilderApplication;
import org.idomine.domain.crud.model.Projeto;
import org.idomine.domain.crud.service.GenerationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = NgBuilderApplication.class) 
@ActiveProfiles("tdd")
public class FreeMakerTeste02
{
    
    @Autowired
    private GenerationService generationService;
    
    @Test
    public void teste()
    {
        generationService.back_POM( Projeto.getFake()  );
    }
}
