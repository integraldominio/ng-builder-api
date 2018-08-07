package org.idomine.domain.crud.service.helper;

public final class StringsHelper
{

    private StringsHelper()
    {
    }

    public static String camelToUnderscore(String value)
    {
        String result = value.replace(" ", "_");
        result = result.replaceAll("([a-z])([A-Z])", "$1_$2");
        result = result.replace(".", "_");
        result = result.toLowerCase();
        return result;
    }

}
