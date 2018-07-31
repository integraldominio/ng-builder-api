
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

import org.idomine.domain.crud.model.Elemento;
import org.idomine.domain.crud.model.vo.TipoElemento;
import org.idomine.domain.crud.model.vo.TipoField;

public final class FormlyHelper
{
    public static String toFormly(Elemento elemento)
    {
        TipoField tipoField = elemento.getTipoField();
        TipoElemento tipoElemento = elemento.getTipoElemento();

        if (TipoField.Date.equals(tipoField) ||
                TipoField.DateTime.equals(tipoField) ||
                TipoField.Time.equals(tipoField))
        {
            return "datepicker";
        }
        else if (TipoElemento.Autocomplete.equals(tipoElemento))
        {
            return "autocomplete";
        }
        else if (TipoElemento.ButtonToggle.equals(tipoElemento))
        {
            return "toggle";
        }
        else if (TipoElemento.Checkbox.equals(tipoElemento))
        {
            return "checkbox";
        }
        else if (TipoElemento.RadioButton.equals(tipoElemento))
        {
            return "radio";
        }
        else if (TipoElemento.Select.equals(tipoElemento))
        {
            return "select";
        }
        else if (TipoElemento.TextArea.equals(tipoElemento))
        {
            return "textarea";
        }
        else if (TipoElemento.SelectDB.equals(tipoElemento))
        {
            return "select";
        }

        else if (TipoField.Integer.equals(tipoField) ||
                TipoField.String.equals(tipoField) ||
                TipoField.Long.equals(tipoField) ||
                TipoField.Decimal.equals(tipoField) ||
                TipoField.BigDecimal.equals(tipoField) ||
                TipoField.BigInteger.equals(tipoField))
        {
            return "input";
        }

        return "input";
    }

    private FormlyHelper()
    {
    }

}
