package org.idomine.domain.crud.service;

import static org.idomine.domain.crud.service.helper.TemplateBackendHelper.BACKEND_APP_PROPERTIES;
import static org.idomine.domain.crud.service.helper.TemplateBackendHelper.BACKEND_ENTITY;
import static org.idomine.domain.crud.service.helper.TemplateBackendHelper.BACKEND_POM;
import static org.idomine.domain.crud.service.helper.TemplateBackendHelper.BACKEND_README;
import static org.idomine.domain.crud.service.helper.TemplateBackendHelper.BACKEND_REPOSITORY;
import static org.idomine.domain.crud.service.helper.TemplateBackendHelper.BACKEND_RESOURCE;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.idomine.domain.crud.model.Artefato;
import org.idomine.domain.crud.model.Projeto;
import org.idomine.domain.crud.model.vo.TipoArtefato;
import org.idomine.domain.crud.reporitory.ProjetoRepository;
import org.idomine.domain.crud.service.helper.FreeMarkerEngine;
import org.idomine.domain.crud.service.helper.GeradorCrudHelper;
import org.idomine.domain.crud.service.helper.TemplateBackendHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerationService
{
    
    private String ngxbuilder="0.0.1";
    
    @Autowired
    private FreeMarkerEngine fm;
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
        frontReadmeToOutput(projeto);
        frontJsonsToOutput(projeto);
        frontAssets(projeto);
        frontEnviroment(projeto);
        frontendSecurityFiles(projeto);
        frontendPages(projeto);
        frontendShared(projeto);
        fromendAppModule(projeto);
        frontendErp(projeto);
    }

    public void frontEnviroment(Projeto projeto)
    {
        String o = TemplateBackendHelper.FRONTEND_SRC_ENV;
        String d = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_ENV;
        try
        {
            GeradorCrudHelper.output(d + "environment.prod.ts", fm.process(o + "environment.prod.ts", model(projeto)));
            GeradorCrudHelper.output(d + "environment.ts", fm.process(o + "environment.ts", model(projeto)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void frontAssets(Projeto projeto)
    {
        String d = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_ASSETS;
        String o = "templates/" + TemplateBackendHelper.FRONTEND_SRC_ASSETS;
        try
        {
            GeradorCrudHelper.copyFile(new File(o + "icon/auth.svg"), new File(d + "icon/auth.svg"));
            GeradorCrudHelper.copyFile(new File(o + "icon/facebook.svg"), new File(d + "icon/facebook.svg"));
            GeradorCrudHelper.copyFile(new File(o + "icon/github-logo.svg"), new File(d + "icon/github-logo.svg"));
            GeradorCrudHelper.copyFile(new File(o + "icon/github-plus.png"), new File(d + "icon/github-plus.png"));
            GeradorCrudHelper.copyFile(new File(o + "icon/google-plus.svg"), new File(d + "icon/google-plus.svg"));
            GeradorCrudHelper.copyFile(new File(o + "images/face-7.jpg"), new File(d + "images/face-7.jpg"));
            GeradorCrudHelper.copyFile(new File(o + "images/logo.svg"), new File(d + "images/logo.svg"));
            GeradorCrudHelper.copyFile(new File(o + "svg-loaders/puff.svg"), new File(d + "svg-loaders/puff.svg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void frontJsonsToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_ANGULAR_JSON,
                fm.process(TemplateBackendHelper.FRONTEND_ANGULAR_JSON, null));
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_DBJSON,
                fm.process(TemplateBackendHelper.FRONTEND_DBJSON, null));
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_PACKAGE_JSON,
                fm.process(TemplateBackendHelper.FRONTEND_PACKAGE_JSON, null));
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_TSCONFIG_JSON,
                fm.process(TemplateBackendHelper.FRONTEND_TSCONFIG_JSON, null));
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_TSLINT_JSON,
                fm.process(TemplateBackendHelper.FRONTEND_TSLINT_JSON, null));

        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_INDEX,
                fm.process(TemplateBackendHelper.FRONTEND_SRC_INDEX, null));
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_STYLE,
                fm.process(TemplateBackendHelper.FRONTEND_SRC_STYLE, null));
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_INDEX,
                fm.process(TemplateBackendHelper.FRONTEND_SRC_INDEX, null));
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_MAIN,
                fm.process(TemplateBackendHelper.FRONTEND_SRC_MAIN, null));

        GeradorCrudHelper.output(
                projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_APP_INFRA_SECURITY
                        + "config.service.ts",
                fm.process(TemplateBackendHelper.FRONTEND_SRC_APP_INFRA_SECURITY + "config.service.ts",
                        model(projeto)));

        String d = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC;
        String o = "templates/" + TemplateBackendHelper.FRONTEND_SRC;
        try
        {
            GeradorCrudHelper.copyFile(new File(o + "favicon.ico"), new File(d + "favicon.ico"));
            GeradorCrudHelper.copyFile(new File(o + "browserslist"), new File(d + "browserslist"));
            GeradorCrudHelper.copyFile(new File(o + "karma.conf.js"), new File(d + "karma.conf.js"));
            GeradorCrudHelper.copyFile(new File(o + "polyfills.ts"), new File(d + "polyfills.ts"));
            GeradorCrudHelper.copyFile(new File(o + "test.ts"), new File(d + "test.ts"));
            GeradorCrudHelper.copyFile(new File(o + "tsconfig.app.json"), new File(d + "tsconfig.app.json"));
            GeradorCrudHelper.copyFile(new File(o + "tsconfig.spec.json"), new File(d + "tsconfig.spec.json"));
            GeradorCrudHelper.copyFile(new File(o + "tslint.json"), new File(d + "tslint.json"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void frontendErp(Projeto projeto)
    {
        if (projeto.getArtefatos() != null)
        {
            for (Artefato artefato : projeto.getArtefatos())
            {
                String folder = artefato.getClassFolder();
                String dir = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_APP_ERP + folder
                        + "/";

                GeradorCrudHelper.criarDir(
                        projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_APP_ERP + folder
                                + "/");

                if (TipoArtefato.Crud.equals(artefato.getTipo()))
                {
                    GeradorCrudHelper.output(dir + folder + ".component.ts", fm.process(
                            TemplateBackendHelper.FRONTEND_SRC_APP_ERP + "/cliente/cliente.component.ts",
                            model(artefato)));
                    GeradorCrudHelper.output(dir + folder + ".component.css",
                            fm.process(
                                    TemplateBackendHelper.FRONTEND_SRC_APP_ERP + "/cliente/cliente.component.css",
                                    model(artefato)));
                    GeradorCrudHelper.output(dir + folder + ".component.html",
                            fm.process(
                                    TemplateBackendHelper.FRONTEND_SRC_APP_ERP + "/cliente/cliente.component.html",
                                    model(artefato)));
                    GeradorCrudHelper.output(dir + folder + ".service.ts", fm.process(
                            TemplateBackendHelper.FRONTEND_SRC_APP_ERP + "/cliente/cliente.service.ts",
                            model(artefato)));
                }
                else if (TipoArtefato.Template.equals(artefato.getTipo()))
                {
                    if (artefato.getTemplateTs() != null)
                        GeradorCrudHelper.output(dir + folder + ".component.ts", artefato.getTemplateTs());

                    if (artefato.getTemplateCss() != null)
                        GeradorCrudHelper.output(dir + folder + ".component.css", artefato.getTemplateCss());
                    
                    if (artefato.getTemplateHtml() != null)
                        GeradorCrudHelper.output(dir + folder + ".component.html", artefato.getTemplateHtml());
                }
            }
        }

    }

    public void fromendAppModule(Projeto projeto)
    {
        String d = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_APP;
        String o = TemplateBackendHelper.FRONTEND_SRC_APP;
        try
        {
            GeradorCrudHelper.output(d + "app-rotas.module.ts", fm.process(o + "app-rotas.module.ts", model(projeto)));
            GeradorCrudHelper.output(d + "app.module.ts", fm.process(o + "app.module.ts", model(projeto)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void frontendShared(Projeto projeto)
    {
        String d = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_APP_SHARED;
        String o = "templates/" + TemplateBackendHelper.FRONTEND_SRC_APP_SHARED;
        try
        {
            GeradorCrudHelper.copyFile(new File(o + "material.module.ts"), new File(d + "material.module.ts"));
            GeradorCrudHelper.copyFile(new File(o + "autocomplete/autocomplete-type.component.ts"), new File(d + "autocomplete/autocomplete-type.component.ts"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void frontendPages(Projeto projeto)
    {
        String d = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_APP_PAGES;
        String o = "templates/" + TemplateBackendHelper.FRONTEND_SRC_APP_PAGES;
        try
        {
            GeradorCrudHelper.copyFile(new File(o + "base/base.component.html"),
                    new File(d + "base/base.component.html"));
            GeradorCrudHelper.copyFile(new File(o + "base/base.component.ts"), new File(d + "base/base.component.ts"));

            GeradorCrudHelper.copyFile(new File(o + "erro/erro.component.html"),
                    new File(d + "erro/erro.component.html"));
            GeradorCrudHelper.copyFile(new File(o + "erro/erro.component.css"),
                    new File(d + "erro/erro.component.css"));
            GeradorCrudHelper.copyFile(new File(o + "erro/erro.component.ts"), new File(d + "erro/erro.component.ts"));

            GeradorCrudHelper.copyFile(new File(o + "home/home.component.html"),
                    new File(d + "home/home.component.html"));
            GeradorCrudHelper.copyFile(new File(o + "home/home.component.css"),
                    new File(d + "home/home.component.css"));
            GeradorCrudHelper.copyFile(new File(o + "home/home.component.ts"), new File(d + "home/home.component.ts"));

            GeradorCrudHelper.copyFile(new File(o + "login/login.component.html"),
                    new File(d + "login/login.component.html"));
            GeradorCrudHelper.copyFile(new File(o + "login/login.component.scss"),
                    new File(d + "login/login.component.scss"));
            GeradorCrudHelper.copyFile(new File(o + "login/login.component.ts"),
                    new File(d + "login/login.component.ts"));

            GeradorCrudHelper.copyFile(new File(o + "sidenav/sidenav.component.css"),
                    new File(d + "sidenav/sidenav.component.css"));
            GeradorCrudHelper.copyFile(new File(o + "sidenav/sidenav.component.ts"),
                    new File(d + "sidenav/sidenav.component.ts"));
            GeradorCrudHelper.copyFile(new File(o + "sidenav/sidenav.service.ts"),
                    new File(d + "sidenav/sidenav.service.ts"));

            GeradorCrudHelper.output(d + "sidenav/sidenav.component.html", fm.process(
                    TemplateBackendHelper.FRONTEND_SRC_APP_PAGES + "sidenav/sidenav.component.html", model(projeto)));

            GeradorCrudHelper.copyFile(new File(o + "sobre/sobre.component.html"),
                    new File(d + "sobre/sobre.component.html"));
            GeradorCrudHelper.copyFile(new File(o + "sobre/sobre.component.css"),
                    new File(d + "sobre/sobre.component.css"));
            GeradorCrudHelper.copyFile(new File(o + "sobre/sobre.component.ts"),
                    new File(d + "sobre/sobre.component.ts"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void frontendSecurityFiles(Projeto projeto)
    {
        String d = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_APP_INFRA_SECURITY;
        String o = "templates/" + TemplateBackendHelper.FRONTEND_SRC_APP_INFRA_SECURITY;
        try
        {
            GeradorCrudHelper.copyFile(new File(o + "admin.guard.ts"), new File(d + "admin.guard.ts"));
            GeradorCrudHelper.copyFile(new File(o + "auth.guard.ts"), new File(d + "auth.guard.ts"));
            GeradorCrudHelper.copyFile(new File(o + "authentication.service.ts"),
                    new File(d + "authentication.service.ts"));
            GeradorCrudHelper.copyFile(new File(o + "guest.guard.ts"), new File(d + "guest.guard.ts"));
            GeradorCrudHelper.copyFile(new File(o + "index.ts"), new File(d + "index.ts"));
            GeradorCrudHelper.copyFile(new File(o + "jwt.interceptor.ts"), new File(d + "jwt.interceptor.ts"));
            GeradorCrudHelper.copyFile(new File(o + "message.service.ts"), new File(d + "message.service.ts"));
            GeradorCrudHelper.copyFile(new File(o + "resource.service.ts"), new File(d + "resource.service.ts"));
            GeradorCrudHelper.copyFile(new File(o + "user.service.ts"), new File(d + "user.service.ts"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void frontReadmeToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_README,
                frontendReadmeToString(projeto));
    }

    public String frontendReadmeToString(Projeto projeto)
    {
        return fm.process(TemplateBackendHelper.FRONTEND_README, model(projeto));
    }

    public void backendApplicationToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.BACKEND_APPLICATION,
                backendApplicationToString(projeto));
    }

    public String backendApplicationToString(Projeto projeto)
    {
        return fm.process(TemplateBackendHelper.BACKEND_APPLICATION, model(projeto));
    }

    public void backendEntityToOutput(Projeto projeto)
    {
        if (projeto.getArtefatos() != null)
        {
            for (Artefato artefato : projeto.getArtefatos())
            {

                if (TipoArtefato.Crud.equals(artefato.getTipo()))
                {

                    String arq = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.BACKEND_ENTITY_PATH
                            + artefato.getClassName() + ".java";
                    String rep = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.BACKEND_REPOSITORY_PATH
                            + artefato.getClassName() + "Repository.java";
                    String res = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.BACKEND_RESOURCE_PATH
                            + artefato.getClassName() + "Resource.java";
                    GeradorCrudHelper.output(arq, backendEntityToString(artefato));
                    GeradorCrudHelper.output(rep, backendRepositoryToString(artefato));
                    GeradorCrudHelper.output(res, backendResourceToString(artefato));
                }
            }
        }
    }

    public String backendEntityToString(Artefato artefato)
    {
        Map<String, Object> model = model(artefato);
        return fm.process(BACKEND_ENTITY, model);
    }

    public String backendEntityToString(Projeto projeto, Long artefatoId)
    {
        Artefato artefato = projeto.getArtefatos().get(artefatoId.intValue());
        Map<String, Object> model = model(artefato);
        return fm.process(BACKEND_ENTITY, model);
    }

    public String backendRepositoryToString(Artefato artefato)
    {
        return fm.process(BACKEND_REPOSITORY, model(artefato));
    }

    public String backendRepositoryToString(Projeto projeto, Long artefatoId)
    {
        Artefato artefato = projeto.getArtefatos().get(artefatoId.intValue());
        return fm.process(BACKEND_REPOSITORY, model(artefato));
    }

    public String backendResourceToString(Artefato artefato)
    {
        return fm.process(BACKEND_RESOURCE, model(artefato));
    }

    public String backendResourceToString(Projeto projeto, Long artefatoId)
    {
        Artefato artefato = projeto.getArtefatos().get(artefatoId.intValue());
        return fm.process(BACKEND_RESOURCE, model(artefato));
    }

    public void backendPomToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.BACKEND_POM,
                backendPomToString(projeto));
    }

    public String backendPomToString(Projeto projeto)
    {
        return fm.process(BACKEND_POM, model(projeto));
    }

    public void readmeToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.README,
                readmeToString(projeto));
    }

    public String readmeToString(Projeto projeto)
    {
        return fm.process(TemplateBackendHelper.README, model(projeto));
    }

    public void backendReadmeToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.BACKEND_README,
                backendReadmeToString(projeto));
    }

    public String backendReadmeToString(Projeto projeto)
    {
        return fm.process(BACKEND_README, model(projeto));
    }

    public void backendAppPropertiesToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.BACKEND_APP_PROPERTIES,
                backendAppPropertiesToString(projeto));

        String d = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.BACKEND_APP_RESOURCE + "data.sql";
        String o = "templates/" + TemplateBackendHelper.BACKEND_APP_RESOURCE + "data.sql";
        try
        {
            // GeradorCrudHelper.copyFile(new File(o), new File(d));
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String backendAppPropertiesToString(Projeto projeto)
    {
        return fm.process(BACKEND_APP_PROPERTIES, model(projeto));
    }

    public Map<String, Object> model(Object o)
    {
        Map<String, Object> model = new HashMap<>();
        model.put(o.getClass().getSimpleName().toLowerCase(), o);
        model.put("ngxbuilder", ngxbuilder);
        return model;
    }

 

}
