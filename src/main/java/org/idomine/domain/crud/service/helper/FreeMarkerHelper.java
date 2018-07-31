
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
import java.io.IOException;
import java.io.StringWriter;

import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@Component
public class FreeMarkerHelper
{

    Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);

    public FreeMarkerHelper() throws IOException
    {

        cfg.setDirectoryForTemplateLoading(new File("templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
    }

    public String process(String templateName, Object model)
    {
        try
        {

            Template template = cfg.getTemplate(templateName + ".ftl");
            StringWriter stringWriter = new StringWriter();
            template.process(model, stringWriter);

            return stringWriter.toString();

        }
        catch (IOException | TemplateException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static FreeMarkerHelper of()
    {
        try
        {
            return new FreeMarkerHelper();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
