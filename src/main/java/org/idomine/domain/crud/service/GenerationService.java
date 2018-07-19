package org.idomine.domain.crud.service;

import static org.idomine.domain.crud.model.vo.TipoTemplateBackend.BACKEND_APP_PROPERTIES;
import static org.idomine.domain.crud.model.vo.TipoTemplateBackend.BACKEND_POM;
import static org.idomine.domain.crud.model.vo.TipoTemplateBackend.BACKEND_README;

import java.util.HashMap;
import java.util.Map;

import org.idomine.domain.crud.model.Projeto;
import org.idomine.domain.crud.model.vo.TipoTemplateBackend;
import org.idomine.domain.crud.reporitory.ConfiguracaoRepository;
import org.idomine.domain.crud.reporitory.ProjetoRepository;
import org.idomine.domain.crud.service.helper.FreeMarkerEngine;
import org.idomine.domain.crud.service.helper.GeradorCrudHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerationService
{
    @Autowired
    private FreeMarkerEngine freeMarkerEngine;
    @Autowired
    private ConfiguracaoRepository configuracaoepository;
    @Autowired
    private ProjetoRepository projetoRepository;

    public void backendAllToOutput(Long id)
    {
        Projeto projeto = projetoRepository.findById(id).get();
        backendAllToOutput(projeto);
    }

    public void backendAllToOutput(Projeto projeto)
    {
        backendPomToOutput(projeto);
        backendReadmeToOutput(projeto);
        backendAppPropertiesToOutput(projeto);
    }

    public void backendPomToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TipoTemplateBackend.BACKEND_POM, backendPomToString(projeto));
    }

    public String backendPomToString(Projeto projeto)
    {
        return freeMarkerEngine.process(BACKEND_POM, model(projeto));
    }

    public void backendReadmeToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TipoTemplateBackend.BACKEND_README, backendReadmeToString(projeto));
    }

    public String backendReadmeToString(Projeto projeto)
    {
        return freeMarkerEngine.process(BACKEND_README, model(projeto));
    }

    public void backendAppPropertiesToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TipoTemplateBackend.BACKEND_APP_PROPERTIES, backendAppPropertiesToString(projeto));
    }

    public String backendAppPropertiesToString(Projeto projeto)
    {
        return freeMarkerEngine.process(BACKEND_APP_PROPERTIES, model(projeto));
    }

    private Map<String, Object> model(Object o)
    {
        Map<String, Object> model = new HashMap<>();
        model.put(o.getClass().getSimpleName().toLowerCase(), o);
        return model;
    }
}
