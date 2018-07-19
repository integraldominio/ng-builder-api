package org.idomine.domain.crud.service;

import static org.idomine.domain.crud.model.vo.TipoTemplateBackend.BACKEND_APP_PROPERTIES;
import static org.idomine.domain.crud.model.vo.TipoTemplateBackend.BACKEND_POM;
import static org.idomine.domain.crud.model.vo.TipoTemplateBackend.BACKEND_README;

import java.util.HashMap;
import java.util.Map;

import org.idomine.domain.crud.model.Projeto;
import org.idomine.domain.crud.reporitory.ConfigGeralRepository;
import org.idomine.domain.crud.reporitory.ProjetoRepository;
import org.idomine.domain.crud.service.helper.FreeMarkerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerationService
{
    @Autowired
    private FreeMarkerEngine freeMarkerEngine;
    @Autowired
    private ConfigGeralRepository configGeralRepository;
    @Autowired
    private ProjetoRepository projetoRepository;

    public void exportarParaPastaOutput(Projeto projeto)
    {
        
        
    }

    public String back_POM(Projeto projeto)
    {
        Map<String, Object> model = new HashMap<>();
        model.put("projeto", projeto);
        return freeMarkerEngine.process(BACKEND_POM, model);
    }

    public String back_README(Projeto projeto)
    {
        Map<String, Object> model = new HashMap<>();
        model.put("projeto", projeto);
        return freeMarkerEngine.process(BACKEND_README, model);
    }

    public String back_APP_PROPERTIES(Projeto projeto)
    {
        Map<String, Object> model = new HashMap<>();
        model.put("projeto", projeto);
        return freeMarkerEngine.process(BACKEND_APP_PROPERTIES, model);
    }

}
