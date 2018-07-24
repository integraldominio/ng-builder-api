package org.idomine.domain.crud.service.helper;

import org.idomine.domain.crud.model.vo.TipoElemento;
import org.idomine.domain.crud.model.vo.TipoField;

public final class FormlyHelper
{
    public static String toFormly(TipoElemento tipoElemento, TipoField tipoField)
    {
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
