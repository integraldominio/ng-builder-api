package org.idomine.domain.crud.service;

import static org.idomine.domain.crud.model.vo.TipoTemplateBackend.BACKEND_APP_PROPERTIES;
import static org.idomine.domain.crud.model.vo.TipoTemplateBackend.BACKEND_ENTITY;
import static org.idomine.domain.crud.model.vo.TipoTemplateBackend.BACKEND_POM;
import static org.idomine.domain.crud.model.vo.TipoTemplateBackend.BACKEND_README;
import static org.idomine.domain.crud.model.vo.TipoTemplateBackend.BACKEND_REPOSITORY;
import static org.idomine.domain.crud.model.vo.TipoTemplateBackend.BACKEND_RESOURCE;

import java.util.HashMap;
import java.util.Map;

import org.idomine.domain.crud.model.Artefato;
import org.idomine.domain.crud.model.Elemento;
import org.idomine.domain.crud.model.Projeto;
import org.idomine.domain.crud.model.vo.TipoField;
import org.idomine.domain.crud.model.vo.TipoTemplateBackend;
import org.idomine.domain.crud.reporitory.ProjetoRepository;
import org.idomine.domain.crud.service.helper.FreeMarkerEngine;
import org.idomine.domain.crud.service.helper.GeradorCrudHelper;
import org.idomine.domain.crud.service.helper.TemplateImports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerationService
{
    @Autowired
    private FreeMarkerEngine freeMarkerEngine;
    @Autowired
    private ProjetoRepository projetoRepository;

    public void backendAllToOutput(Long id)
    {
        Projeto projeto = projetoRepository.findById(id).get();
        backendAllToOutput(projeto);
    }

    public void backendAllToOutput(Projeto projeto)
    {
        readmeToOutput(projeto);
        backendPomToOutput(projeto);
        backendReadmeToOutput(projeto);
        backendAppPropertiesToOutput(projeto);
        backendApplicationToOutput(projeto);
        backendEntityToOutput(projeto);
    }

    private void backendApplicationToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TipoTemplateBackend.BACKEND_APPLICATION, backendApplicationToString(projeto));
    }
    
    public String backendApplicationToString(Projeto projeto)
    {
        return freeMarkerEngine.process(TipoTemplateBackend.BACKEND_APPLICATION, model(projeto));
    }

    public void backendEntityToOutput(Projeto projeto)
    {
        if (projeto.getArtefatos() != null)
        {
            for (Artefato artefato : projeto.getArtefatos())
            {
                String arq = projeto.getOutputDirectory() + "/" + TipoTemplateBackend.BACKEND_ENTITY_PATH + artefato.getClassName() + ".java";
                String rep = projeto.getOutputDirectory() + "/" + TipoTemplateBackend.BACKEND_REPOSITORY_PATH + artefato.getClassName() + "Repository.java";
                String res = projeto.getOutputDirectory() + "/" + TipoTemplateBackend.BACKEND_RESOURCE_PATH + artefato.getClassName() + "Resource.java";
                GeradorCrudHelper.output(arq, backendEntityToString(artefato));
                GeradorCrudHelper.output(rep, backendRepositoryToString(artefato));
                GeradorCrudHelper.output(res, backendResourceToString(artefato));
            }
        }
    }

    public String backendEntityToString(Artefato artefato)
    {
        Map<String, Object> model = model(artefato);
        model.put("import", imports(artefato));
        return freeMarkerEngine.process(BACKEND_ENTITY, model);
    }

    public String backendEntityToString(Projeto projeto, Long artefatoId)
    {
        Artefato artefato = projeto.getArtefatos().get(artefatoId.intValue());
        Map<String, Object> model = model(artefato);
        model.put("import", imports(artefato));
        return freeMarkerEngine.process(BACKEND_ENTITY, model);
    }

    public String backendRepositoryToString(Artefato artefato)
    {
        return freeMarkerEngine.process(BACKEND_REPOSITORY, model(artefato));
    }

    public String backendRepositoryToString(Projeto projeto, Long artefatoId)
    {
        Artefato artefato = projeto.getArtefatos().get(artefatoId.intValue());
        return freeMarkerEngine.process(BACKEND_REPOSITORY, model(artefato));
    }

    public String backendResourceToString(Artefato artefato)
    {
        return freeMarkerEngine.process(BACKEND_RESOURCE, model(artefato));
    }

    public String backendResourceToString(Projeto projeto, Long artefatoId)
    {
        Artefato artefato = projeto.getArtefatos().get(artefatoId.intValue());
        return freeMarkerEngine.process(BACKEND_RESOURCE, model(artefato));
    }

    public void backendPomToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TipoTemplateBackend.BACKEND_POM, backendPomToString(projeto));
    }

    public String backendPomToString(Projeto projeto)
    {
        return freeMarkerEngine.process(BACKEND_POM, model(projeto));
    }

    
    public void readmeToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TipoTemplateBackend.README, readmeToString(projeto));
    }

    public String readmeToString(Projeto projeto)
    {
        return freeMarkerEngine.process(TipoTemplateBackend.README, model(projeto));
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

    private Object imports(Artefato artefato)
    {
        boolean iDate = false;

        if (artefato.getElementos() != null)
            for (Elemento e : artefato.getElementos())
            {
                iDate = (e.getTipoField() == TipoField.Date || e.getTipoField() == TipoField.Time || e.getTipoField() == TipoField.DateTime);
            }

        TemplateImports importe = TemplateImports
                .builder()
                .idate(iDate)
                .ilist(false).build();
        return importe;
    }

}
