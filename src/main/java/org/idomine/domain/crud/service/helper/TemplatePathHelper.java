
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

public final class TemplatePathHelper
{
    public static final String BACKEND_DOMAIN = "src/main/java/org/idomine/";
    public static final String BACKEND = "backend/";
    public static final String FRONTEND = "frontend/";
    public static final String README = "README.md";
    public static final String BACKEND_POM = BACKEND + "pom.xml";
    public static final String BACKEND_README = BACKEND + "README.md";
    public static final String BACKEND_APP_PROPERTIES = BACKEND + "src/main/resources/application.properties";
    public static final String BACKEND_APP_RESOURCE = BACKEND + "src/main/resources/";
    public static final String BACKEND_APP_RESOURCE_MIGRA = BACKEND + "src/main/resources/db/migration/";
    public static final String BACKEND_APPLICATION = BACKEND + BACKEND_DOMAIN + "Application.java";
    public static final String BACKEND_WEBCONFIG = BACKEND + BACKEND_DOMAIN+ "application/";
    public static final String BACKEND_ENTITY = BACKEND + BACKEND_DOMAIN + "domain/crud/model/Entidade.java";
    public static final String BACKEND_ENTITY_PATH = BACKEND + BACKEND_DOMAIN + "domain/crud/model/";
    public static final String BACKEND_REPOSITORY = BACKEND + BACKEND_DOMAIN + "domain/crud/repository/Repository.java";
    public static final String BACKEND_REPOSITORY_PATH = BACKEND + BACKEND_DOMAIN + "domain/crud/repository/";
    public static final String BACKEND_RESOURCE = BACKEND + BACKEND_DOMAIN + "domain/crud/resource/Resource.java";
    public static final String BACKEND_RESOURCE_PATH = BACKEND + BACKEND_DOMAIN + "domain/crud/resource/";
    public static final String BACKEND_INFRA = BACKEND + BACKEND_DOMAIN + "infrastructure/";
    public static final String BACKEND_SECURITY = BACKEND + BACKEND_DOMAIN +"security/";

    public static final String FRONTEND_README = FRONTEND + "README.md";
    public static final String FRONTEND_ANGULAR_JSON = FRONTEND + "angular.json";
    public static final String FRONTEND_PACKAGE_JSON = FRONTEND + "package.json";
    public static final String FRONTEND_TSLINT_JSON = FRONTEND + "tslint.json";
    public static final String FRONTEND_TSCONFIG_JSON = FRONTEND + "tsconfig.json";
    public static final String FRONTEND_DBJSON = FRONTEND + "db.json";
    public static final String FRONTEND_SRC = FRONTEND + "src/";
    public static final String FRONTEND_SRC_TSLINT = FRONTEND_SRC + "tslint.json";
    public static final String FRONTEND_SRC_TSCONFIG_SPEC = FRONTEND_SRC + "tsconfig.spec.json";
    public static final String FRONTEND_SRC_TSCONFIG_APP = FRONTEND_SRC + "tsconfig.app.json";
    public static final String FRONTEND_SRC_TEST = FRONTEND_SRC + "test.ts";
    public static final String FRONTEND_SRC_STYLE = FRONTEND_SRC + "styles.css";
    public static final String FRONTEND_SRC_INDEX = FRONTEND_SRC + "index.html";
    public static final String FRONTEND_SRC_MAIN = FRONTEND_SRC + "main.ts";
    public static final String FRONTEND_SRC_ASSETS = FRONTEND_SRC + "assets/";
    public static final String FRONTEND_SRC_ENV = FRONTEND_SRC + "environments/";
    public static final String FRONTEND_SRC_APP = FRONTEND_SRC + "app/";
    public static final String FRONTEND_SRC_APP_ERP = FRONTEND_SRC_APP + "erp/";
    public static final String FRONTEND_SRC_APP_INFRA = FRONTEND_SRC_APP + "infra/";
    public static final String FRONTEND_SRC_APP_INFRA_PIPES = FRONTEND_SRC_APP + "infra/pipes/";
    public static final String FRONTEND_SRC_APP_INFRA_COMPS = FRONTEND_SRC_APP + "infra/comps/";
    public static final String FRONTEND_SRC_APP_PAGES = FRONTEND_SRC_APP + "pages/";
    public static final String FRONTEND_SRC_APP_SHARED = FRONTEND_SRC_APP + "shared/";
    public static final String FRONTEND_SRC_APP_INFRA_SECURITY = FRONTEND_SRC_APP_INFRA + "security/";
}
