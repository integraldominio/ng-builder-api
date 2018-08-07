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
 
package org.idomine.domain.crud.repository;


import org.idomine.domain.crud.model.Portal;

import org.idomine.domain.crud.model.Projeto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported=false)
public interface ProjetoRepository extends  CrudRepository<Projeto, Long>
{
    // Projeto findByPortal(Portal Portal);
    Projeto findByNome(String nome);
    Projeto findByDescricao(String descricao);
    Projeto findByNomeBackendApp(String nomeBackendApp);
    Projeto findByNomeFrontEndApp(String nomeFrontEndApp);
    Projeto findByIconeApp(String iconeApp);
    Projeto findByImageApp(String imageApp);
    Projeto findByServerLang(String serverLang);
    Projeto findByDatabseFlavor(String databseFlavor);
    Projeto findByServerHost(String serverHost);
    Projeto findByServerPort(Long serverPort);
    Projeto findByFrontHost(String frontHost);
    Projeto findByFrontPort(Long frontPort);
    Projeto findByUseLogin(Boolean useLogin);
    Projeto findByUseRoles(Boolean useRoles);
    Projeto findByOutputDirectory(String outputDirectory);
 
}

