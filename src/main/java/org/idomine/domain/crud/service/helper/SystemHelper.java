
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

import java.io.File;

public final class SystemHelper
{
    private static final String separador = File.separator;

    private SystemHelper()
    {
    }

    public static String versaoSistemaOperacional()
    {
        return System.getProperty("os.name").toLowerCase();
    }

    public static boolean sistemaOperacionalWindows()
    {
        return versaoSistemaOperacional().contains("windows");
    }

    public static boolean sistemaOperacionalLinux()
    {
        return !sistemaOperacionalWindows();
    }

    public static String diretorioOutputLinux()
    {
        return separador + "opt" + separador + "ng-builder-api" + separador + "output" + separador;
    }

    public static String diretorioOutputWindows()
    {
        String diretorio = System.getProperty("user.dir");
        if (diretorio.contains("bin"))
        {
            diretorio = diretorio.replace("bin", "standalone");
        }
        return diretorio + separador + "output" + separador;
    }

}
