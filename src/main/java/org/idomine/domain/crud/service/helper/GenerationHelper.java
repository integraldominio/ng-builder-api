
/**
 * The MIT License
 *
 *  Copyright (c) 2018, Lyndon Tavares (integraldominio@gmail.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package org.idomine.domain.crud.service.helper;

import static org.idomine.domain.crud.service.helper.FolderHelper.copyFile;
import static org.idomine.domain.crud.service.helper.FolderHelper.criarDir;
import static org.idomine.domain.crud.service.helper.FolderHelper.criarFolders;
import static org.idomine.domain.crud.service.helper.FolderHelper.output;
import static org.idomine.domain.crud.service.helper.TemplatePathHelper.BACKEND_APP_PROPERTIES;
import static org.idomine.domain.crud.service.helper.TemplatePathHelper.BACKEND_ENTITY;
import static org.idomine.domain.crud.service.helper.TemplatePathHelper.BACKEND_POM;
import static org.idomine.domain.crud.service.helper.TemplatePathHelper.BACKEND_README;
import static org.idomine.domain.crud.service.helper.TemplatePathHelper.BACKEND_REPOSITORY;
import static org.idomine.domain.crud.service.helper.TemplatePathHelper.BACKEND_RESOURCE;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.idomine.domain.crud.model.Artefato;
import org.idomine.domain.crud.model.Projeto;
import org.idomine.domain.crud.model.vo.TipoArtefato;

public class GenerationHelper
{
    private static String ngxbuilder = "0.0.2";
    private static FreeMarkerHelper fm = FreeMarkerHelper.of();

    public static void backendAllToOutput(Projeto projeto)
    {
        if (projeto != null)
        {
            criarFolders(projeto.getOutputDirectory());
            readmeToOutput(projeto);
            backendPomToOutput(projeto);
            backendReadmeToOutput(projeto);
            backendMigrationToOutput(projeto);
            backendAppPropertiesToOutput(projeto);
            backendApplicationToOutput(projeto);
            backendEntityToOutput(projeto);
            backendSecurity(projeto);
            backendInfrastructure(projeto);

            frontReadmeToOutput(projeto);
            frontJsonsToOutput(projeto);
            frontAssets(projeto);
            frontEnviroment(projeto);
            frontendSecurityFiles(projeto);
            frontendInfra(projeto);
            frontendInfraComps(projeto);
            frontendInfraPipes(projeto);
            frontendPages(projeto);
            frontendShared(projeto);
            fromendAppModule(projeto);
            frontendErp(projeto);
        }
    }

    private static void backendInfrastructure(Projeto projeto)
    {
        String o = "templates/" + TemplatePathHelper.BACKEND_INFRA;
        String d = projeto.getOutputDirectory() + "/" + TemplatePathHelper.BACKEND_INFRA;
        try
        {
            copyFile(new File(o + "ImageBase64Helper.java"),new File(d + "ImageBase64Helper.java"));
        }
        catch (IOException e)
        {
            System.err.println(">>>" + e);
        }           
    }

    private static void frontendInfra(Projeto projeto)
    {
        String o = "templates/" + TemplatePathHelper.FRONTEND_SRC_APP_INFRA;
        String d = projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_SRC_APP_INFRA;
        try
        {
            copyFile(new File(o + "auth/api.service.ts"),new File(d + "auth/api.service.ts"));
            copyFile(new File(o + "auth/auth.service.ts"),new File(d + "auth/auth.service.ts"));
            copyFile(new File(o + "auth/index.ts"),new File(d + "auth/index.ts"));
            copyFile(new File(o + "auth/user.service.ts"),new File(d + "auth/user.service.ts"));
            copyFile(new File(o + "users/user-form.component.css"),new File(d + "users/user-form.component.css"));
            copyFile(new File(o + "users/user-form.component.ts"),new File(d + "users/user-form.component.ts"));
            copyFile(new File(o + "users/user-form.component.html"),new File(d + "users/user-form.component.html"));
            copyFile(new File(o + "users/user-grid.component.css"),new File(d + "users/user-grid.component.css"));
            copyFile(new File(o + "users/user-grid.component.html"),new File(d + "users/user-grid.component.html"));
            copyFile(new File(o + "users/user-grid.component.ts"),new File(d + "users/user-grid.component.ts"));
            copyFile(new File(o + "users/user-upload.component.ts"),new File(d + "users/user-upload.component.ts"));
            copyFile(new File(o + "users/user-upload.component.html"),new File(d + "users/user-upload.component.html"));
            copyFile(new File(o + "users/users.service.ts"),new File(d + "users/users.service.ts"));
        }
        catch (IOException e)
        {
            System.err.println(">>>" + e);

        }        
    }

    private static void backendMigrationToOutput(Projeto projeto)
    {
        String o = "templates/" + TemplatePathHelper.BACKEND_APP_RESOURCE_MIGRA;
        String d = projeto.getOutputDirectory() + "/" + TemplatePathHelper.BACKEND_APP_RESOURCE_MIGRA;
        try
        {
            copyFile(new File(o + "V001__criar_portal.sql"),new File(d + "V001__criar_portal.sql"));
            copyFile(new File(o + "V002__criar_tabela_projeto.sql"),new File(d + "V002__criar_tabela_projeto.sql"));
            copyFile(new File(o + "V003__criar_tabela_artefato.sql"),new File(d + "V003__criar_tabela_artefato.sql"));
            copyFile(new File(o + "V004__criar_tabela_elemento.sql"),new File(d + "V004__criar_tabela_elemento.sql"));
            copyFile(new File(o + "V005__criar_tabela_configuracao.sql"),new File(d + "V005__criar_tabela_configuracao.sql"));
            copyFile(new File(o + "V006__criar_tabelas_security.sql"),new File(d + "V006__criar_tabelas_security.sql"));
            copyFile(new File(o + "V007__insert_dados_security.sql"),new File(d + "V007__insert_dados_security.sql"));
        }
        catch (IOException e)
        {
            System.err.println(">>>" + e);

        }
    }

    private static void backendSecurity(Projeto projeto)
    {
        String o = "templates/" + TemplatePathHelper.BACKEND_SECURITY;
        String d = projeto.getOutputDirectory() + "/" + TemplatePathHelper.BACKEND_SECURITY;
        try
        {
            copyFile(new File(o + "exceptions/AuthenticationException.java"),           new File(d + "exceptions/AuthenticationException.java"));
            copyFile(new File(o + "jwt/JwtAuthenticationEntryPoint.java"),              new File(d + "jwt/JwtAuthenticationEntryPoint.java"));
            copyFile(new File(o + "jwt/JwtAuthenticationRequest.java"),                 new File(d + "jwt/JwtAuthenticationRequest.java"));
            copyFile(new File(o + "jwt/JwtAuthorizationTokenFilter.java"),              new File(d + "jwt/JwtAuthorizationTokenFilter.java"));
            copyFile(new File(o + "jwt/JwtTokenUtil.java"),                             new File(d + "jwt/JwtTokenUtil.java"));   
            copyFile(new File(o + "jwt/JwtUser.java"),                                  new File(d + "jwt/JwtUser.java"));
            copyFile(new File(o + "jwt/JwtUserFactory.java"),                           new File(d + "jwt/JwtUserFactory.java"));
            copyFile(new File(o + "model/Authority.java"),                              new File(d + "model/Authority.java"));
            copyFile(new File(o + "model/AuthorityName.java"),                          new File(d + "model/AuthorityName.java"));
            copyFile(new File(o + "model/Group.java"),                                  new File(d + "model/Group.java"));
            copyFile(new File(o + "model/User.java"),                                   new File(d + "model/User.java"));
            copyFile(new File(o + "repository/UserRepository.java"),                    new File(d + "repository/UserRepository.java"));
            copyFile(new File(o + "rest/auth/AuthenticationRestController.java"),       new File(d + "rest/auth/AuthenticationRestController.java"));
            copyFile(new File(o + "rest/protecteds/MethodProtectedRestController.java"),new File(d + "rest/protecteds/MethodProtectedRestController.java"));
            copyFile(new File(o + "rest/user/UserCrudResource.java"),                   new File(d + "rest/user/UserCrudResource.java"));
            copyFile(new File(o + "rest/user/UserRestController.java"),                 new File(d + "rest/user/UserRestController.java"));
            copyFile(new File(o + "service/JwtAuthenticationResponse.java"),            new File(d + "service/JwtAuthenticationResponse.java"));
            copyFile(new File(o + "service/JwtUserDetailsService.java"),                new File(d + "service/JwtUserDetailsService.java"));
        }
        catch (IOException e)
        {
            System.err.println(">>>" + e);

        }
    }

    private static void frontendInfraComps(Projeto projeto)
    {
        String d = projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_SRC_APP_INFRA_PIPES;
        String o = "templates/" + TemplatePathHelper.FRONTEND_SRC_APP_INFRA_PIPES;
        try
        {
            copyFile(new File(o + "MaxCharPipe.ts"), new File(d + "MaxCharPipe.ts"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void frontendInfraPipes(Projeto projeto)
    {
        String d = projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_SRC_APP_INFRA_COMPS;
        String o = "templates/" + TemplatePathHelper.FRONTEND_SRC_APP_INFRA_COMPS;
        try
        {
            copyFile(new File(o + "dashcard/dashcard.component.ts"),   new File(d + "dashcard/dashcard.component.ts"));
            copyFile(new File(o + "dashcard/dashcard.component.html"), new File(d + "dashcard/dashcard.component.html"));
            copyFile(new File(o + "dashcard/dashcard.component.scss"), new File(d + "dashcard/dashcard.component.scss"));
            copyFile(new File(o + "file-upload/file-upload.component.css"), new File(d + "file-upload/file-upload.component.css"));
            copyFile(new File(o + "file-upload/file-upload.component.html"), new File(d + "file-upload/file-upload.component.html"));
            copyFile(new File(o + "file-upload/file-upload.component.ts"), new File(d + "file-upload/file-upload.component.ts"));

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void frontEnviroment(Projeto projeto)
    {
        String o = TemplatePathHelper.FRONTEND_SRC_ENV;
        String d = projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_SRC_ENV;
        try
        {
            output(d + "environment.prod.ts", fm.process(o + "environment.prod.ts", model(projeto)));
            output(d + "environment.ts", fm.process(o + "environment.ts", model(projeto)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void frontAssets(Projeto projeto)
    {
        String d = projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_SRC_ASSETS;
        String o = "templates/" + TemplatePathHelper.FRONTEND_SRC_ASSETS;
        try
        {
            copyFile(new File(o + "icon/auth.svg"), new File(d + "icon/auth.svg"));
            copyFile(new File(o + "icon/facebook.svg"), new File(d + "icon/facebook.svg"));
            copyFile(new File(o + "icon/github-logo.svg"), new File(d + "icon/github-logo.svg"));
            copyFile(new File(o + "icon/github-plus.png"), new File(d + "icon/github-plus.png"));
            copyFile(new File(o + "icon/google-plus.svg"), new File(d + "icon/google-plus.svg"));
            copyFile(new File(o + "images/face-7.jpg"), new File(d + "images/face-7.jpg"));
            copyFile(new File(o + "images/logo.svg"), new File(d + "images/logo.svg"));
            copyFile(new File(o + "svg-loaders/puff.svg"), new File(d + "svg-loaders/puff.svg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void frontJsonsToOutput(Projeto projeto)
    {
        output(projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_ANGULAR_JSON,
                fm.process(TemplatePathHelper.FRONTEND_ANGULAR_JSON, model(projeto)));
        output(projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_DBJSON,
                fm.process(TemplatePathHelper.FRONTEND_DBJSON, model(projeto)));
        output(projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_PACKAGE_JSON,
                fm.process(TemplatePathHelper.FRONTEND_PACKAGE_JSON, model(projeto)));
        output(projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_TSCONFIG_JSON,
                fm.process(TemplatePathHelper.FRONTEND_TSCONFIG_JSON, model(projeto)));
        output(projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_TSLINT_JSON,
                fm.process(TemplatePathHelper.FRONTEND_TSLINT_JSON, model(projeto)));

        output(projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_SRC_INDEX,
                fm.process(TemplatePathHelper.FRONTEND_SRC_INDEX, model(projeto)));
        output(projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_SRC_STYLE,
                fm.process(TemplatePathHelper.FRONTEND_SRC_STYLE, model(projeto)));
        output(projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_SRC_MAIN,
                fm.process(TemplatePathHelper.FRONTEND_SRC_MAIN, model(projeto)));



        String d = projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_SRC;
        String o = "templates/" + TemplatePathHelper.FRONTEND_SRC;
        try
        {
            copyFile(new File(o + "favicon.ico"), new File(d + "favicon.ico"));
            copyFile(new File(o + "browserslist"), new File(d + "browserslist"));
            copyFile(new File(o + "karma.conf.js"), new File(d + "karma.conf.js"));
            copyFile(new File(o + "polyfills.ts"), new File(d + "polyfills.ts"));
            copyFile(new File(o + "test.ts"), new File(d + "test.ts"));
            copyFile(new File(o + "tsconfig.app.json"), new File(d + "tsconfig.app.json"));
            copyFile(new File(o + "tsconfig.spec.json"), new File(d + "tsconfig.spec.json"));
            copyFile(new File(o + "tslint.json"), new File(d + "tslint.json"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void frontendErp(Projeto projeto)
    {
        if (projeto.getArtefatos() != null)
        {
            for (Artefato artefato : projeto.getArtefatos())
            {
                String folder = artefato.getClassFolder();
                String dir = projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_SRC_APP_ERP + folder
                        + "/";

                criarDir(
                        projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_SRC_APP_ERP + folder + "/");

                if (TipoArtefato.Crud.equals(artefato.getTipo()))
                {
                    output(dir + folder + "-form.component.ts",
                            fm.process(
                                    TemplatePathHelper.FRONTEND_SRC_APP_ERP + "artefato/artefato-form.component.ts",
                                    model(artefato)));
                    output(dir + folder + "-form.component.css",
                            fm.process(
                                    TemplatePathHelper.FRONTEND_SRC_APP_ERP + "artefato/artefato-form.component.css",
                                    model(artefato)));
                    output(dir + folder + "-form.component.html", fm.process(
                            TemplatePathHelper.FRONTEND_SRC_APP_ERP + "artefato/artefato-form.component.html",
                            model(artefato)));

                    output(dir + folder + "-grid.component.ts",
                            fm.process(
                                    TemplatePathHelper.FRONTEND_SRC_APP_ERP + "artefato/artefato-grid.component.ts",
                                    model(artefato)));
                    output(dir + folder + "-grid.component.css",
                            fm.process(
                                    TemplatePathHelper.FRONTEND_SRC_APP_ERP + "artefato/artefato-grid.component.css",
                                    model(artefato)));
                    output(dir + folder + "-grid.component.html", fm.process(
                            TemplatePathHelper.FRONTEND_SRC_APP_ERP + "artefato/artefato-grid.component.html",
                            model(artefato)));

                    output(dir + folder + ".service.ts",
                            fm.process(TemplatePathHelper.FRONTEND_SRC_APP_ERP + "artefato/artefato.service.ts",
                                    model(artefato)));
                }
                else if (TipoArtefato.Template.equals(artefato.getTipo()))
                {
                    if (artefato.getTemplateTs() != null)
                        output(dir + folder + ".component.ts", artefato.getTemplateTs());

                    if (artefato.getTemplateCss() != null)
                        output(dir + folder + ".component.css", artefato.getTemplateCss());

                    if (artefato.getTemplateHtml() != null)
                        output(dir + folder + ".component.html", artefato.getTemplateHtml());
                }
            }
        }

    }

    public static void fromendAppModule(Projeto projeto)
    {
        String d = projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_SRC_APP;
        String o = TemplatePathHelper.FRONTEND_SRC_APP;
        try
        {
            output(d + "app-rotas.module.ts", fm.process(o + "app-rotas.module.ts", model(projeto)));
            output(d + "app.module.ts", fm.process(o + "app.module.ts", model(projeto)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void frontendShared(Projeto projeto)
    {
        String d = projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_SRC_APP_SHARED;
        String o = "templates/" + TemplatePathHelper.FRONTEND_SRC_APP_SHARED;
        try
        {
            copyFile(new File(o + "material.module.ts"), new File(d + "material.module.ts"));
            copyFile(new File(o + "autocomplete/autocomplete-type.component.ts"), new File(d + "autocomplete/autocomplete-type.component.ts"));
            
            copyFile(new File(o + "/models/display-message.ts"), new File(d + "/models/display-message.ts"));
            copyFile(new File(o + "/utilities/loose-invalid.ts"), new File(d + "/utilities/loose-invalid.ts"));
            copyFile(new File(o + "/utilities/serialize.ts"), new File(d + "/utilities/serialize.ts"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void frontendPages(Projeto projeto)
    {
        String d = projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_SRC_APP_PAGES;
        String o = "templates/" + TemplatePathHelper.FRONTEND_SRC_APP_PAGES;
        try
        {
            copyFile(new File(o + "base/base.component.html"), new File(d + "base/base.component.html"));
            copyFile(new File(o + "base/base.component.ts"), new File(d + "base/base.component.ts"));
            copyFile(new File(o + "erro/erro.component.html"), new File(d + "erro/erro.component.html"));
            copyFile(new File(o + "erro/erro.component.css"), new File(d + "erro/erro.component.css"));
            copyFile(new File(o + "erro/erro.component.ts"), new File(d + "erro/erro.component.ts"));
            copyFile(new File(o + "home/home.component.html"), new File(d + "home/home.component.html"));
            copyFile(new File(o + "home/home.component.css"), new File(d + "home/home.component.css"));
            output(d + "home/home.component.ts", fm
                    .process(TemplatePathHelper.FRONTEND_SRC_APP_PAGES + "home/home.component.ts", model(projeto)));
            copyFile(new File(o + "login/login.component.html"), new File(d + "login/login.component.html"));
            copyFile(new File(o + "login/login.component.scss"), new File(d + "login/login.component.scss"));
            copyFile(new File(o + "login/login.component.ts"), new File(d + "login/login.component.ts"));
            copyFile(new File(o + "sidenav/sidenav.component.css"), new File(d + "sidenav/sidenav.component.css"));
            copyFile(new File(o + "sidenav/sidenav.component.ts"), new File(d + "sidenav/sidenav.component.ts"));
            copyFile(new File(o + "sidenav/sidenav.service.ts"), new File(d + "sidenav/sidenav.service.ts"));
            output(d + "sidenav/sidenav.component.html", fm.process(
                    TemplatePathHelper.FRONTEND_SRC_APP_PAGES + "sidenav/sidenav.component.html", model(projeto)));
            output(d + "sobre/sobre.component.html", fm.process(
                    TemplatePathHelper.FRONTEND_SRC_APP_PAGES + "sobre/sobre.component.html", model(projeto)));
            copyFile(new File(o + "sobre/sobre.component.css"), new File(d + "sobre/sobre.component.css"));
            copyFile(new File(o + "sobre/sobre.component.ts"), new File(d + "sobre/sobre.component.ts"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void frontendSecurityFiles(Projeto projeto)
    {
        String d = projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_SRC_APP_INFRA_SECURITY;
        String o = "templates/" + TemplatePathHelper.FRONTEND_SRC_APP_INFRA_SECURITY;
        output( d + "config.service.ts", fm.process(TemplatePathHelper.FRONTEND_SRC_APP_INFRA_SECURITY + "config.service.ts", model(projeto)));        
        try
        {
            copyFile(new File(o + "admin.guard.ts"),    new File(d + "admin.guard.ts"));
            copyFile(new File(o + "auth.guard.ts"),     new File(d + "auth.guard.ts"));
            copyFile(new File(o + "authentication.service.ts"), new File(d + "authentication.service.ts"));
            copyFile(new File(o + "guest.guard.ts"),    new File(d + "guest.guard.ts"));
            copyFile(new File(o + "index.ts"),          new File(d + "index.ts"));
            copyFile(new File(o + "jwt.interceptor.ts"),new File(d + "jwt.interceptor.ts"));
            copyFile(new File(o + "message.service.ts"),new File(d + "message.service.ts"));
            copyFile(new File(o + "resource.service.ts"), new File(d + "resource.service.ts"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void frontReadmeToOutput(Projeto projeto)
    {
        output(projeto.getOutputDirectory() + "/" + TemplatePathHelper.FRONTEND_README,
                frontendReadmeToString(projeto));
    }

    public static String frontendReadmeToString(Projeto projeto)
    {
        return fm.process(TemplatePathHelper.FRONTEND_README, model(projeto));
    }

    public static void backendApplicationToOutput(Projeto projeto)
    {
        output(projeto.getOutputDirectory() + "/" + TemplatePathHelper.BACKEND_APPLICATION,backendApplicationToString(projeto));

        String o = "templates/" + TemplatePathHelper.BACKEND_WEBCONFIG;
        String d = projeto.getOutputDirectory() + "/" + TemplatePathHelper.BACKEND_WEBCONFIG;
        try
        {
            copyFile(new File(o + "WebSecurityConfig.java"), new File(d + "WebSecurityConfig.java"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String backendApplicationToString(Projeto projeto)
    {
        return fm.process(TemplatePathHelper.BACKEND_APPLICATION, model(projeto));
    }

    public static void backendEntityToOutput(Projeto projeto)
    {
        if (projeto.getArtefatos() != null)
        {
            for (Artefato artefato : projeto.getArtefatos())
            {
                if (TipoArtefato.Crud.equals(artefato.getTipo()))
                {
                    String arq = projeto.getOutputDirectory() + "/" + TemplatePathHelper.BACKEND_ENTITY_PATH
                            + artefato.getClassName() + ".java";
                    String rep = projeto.getOutputDirectory() + "/" + TemplatePathHelper.BACKEND_REPOSITORY_PATH
                            + artefato.getClassName() + "Repository.java";
                    String res = projeto.getOutputDirectory() + "/" + TemplatePathHelper.BACKEND_RESOURCE_PATH
                            + artefato.getClassName() + "Resource.java";
                    output(arq, backendEntityToString(artefato));
                    output(rep, backendRepositoryToString(artefato));
                    output(res, backendResourceToString(artefato));
                }
            }
        }
    }

    public static String backendEntityToString(Artefato artefato)
    {
        Map<String, Object> model = model(artefato);
        return fm.process(BACKEND_ENTITY, model);
    }

    public static String backendEntityToString(Projeto projeto, Long artefatoId)
    {
        Artefato artefato = projeto.getArtefatos().get(artefatoId.intValue());
        Map<String, Object> model = model(artefato);
        return fm.process(BACKEND_ENTITY, model);
    }

    public static String backendRepositoryToString(Artefato artefato)
    {
        return fm.process(BACKEND_REPOSITORY, model(artefato));
    }

    public static String backendRepositoryToString(Projeto projeto, Long artefatoId)
    {
        Artefato artefato = projeto.getArtefatos().get(artefatoId.intValue());
        return fm.process(BACKEND_REPOSITORY, model(artefato));
    }

    public static String backendResourceToString(Artefato artefato)
    {
        return fm.process(BACKEND_RESOURCE, model(artefato));
    }

    public String backendResourceToString(Projeto projeto, Long artefatoId)
    {
        Artefato artefato = projeto.getArtefatos().get(artefatoId.intValue());
        return fm.process(BACKEND_RESOURCE, model(artefato));
    }

    public static void backendPomToOutput(Projeto projeto)
    {
        output(projeto.getOutputDirectory() + "/" + TemplatePathHelper.BACKEND_POM,
                backendPomToString(projeto));
    }

    public static String backendPomToString(Projeto projeto)
    {
        return fm.process(BACKEND_POM, model(projeto));
    }

    public static void readmeToOutput(Projeto projeto)
    {
        output(projeto.getOutputDirectory() + "/" + TemplatePathHelper.README,
                readmeToString(projeto));
    }

    public static String readmeToString(Projeto projeto)
    {
        return fm.process(TemplatePathHelper.README, model(projeto));
    }

    public static void backendReadmeToOutput(Projeto projeto)
    {
        output(projeto.getOutputDirectory() + "/" + TemplatePathHelper.BACKEND_README,
                backendReadmeToString(projeto));
    }

    public static String backendReadmeToString(Projeto projeto)
    {
        return fm.process(BACKEND_README, model(projeto));
    }

    public static void backendAppPropertiesToOutput(Projeto projeto)
    {
        String out = projeto.getOutputDirectory() + "/" + TemplatePathHelper.BACKEND_APP_RESOURCE;
        String tpl = TemplatePathHelper.BACKEND_APP_RESOURCE;
        output(out + "application-dev.properties", fm.process(tpl + "application-dev.properties", model(projeto)));
        output(out + "application-prod.properties", fm.process(tpl + "application-prod.properties", model(projeto)));
    }

    public String backendAppPropertiesToString(Projeto projeto)
    {
        return fm.process(BACKEND_APP_PROPERTIES, model(projeto));
    }

    public static Map<String, Object> model(Object o)
    {
        Map<String, Object> model = new HashMap<>();
        model.put(o.getClass().getSimpleName().toLowerCase(), o);
        model.put("ngxbuilder", ngxbuilder);
        return model;
    }

}
