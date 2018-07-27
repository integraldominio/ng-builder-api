package org.idomine.domain.crud.model.vo;

public enum TipoField
{
    
    BigDecimal, 
    BigInteger,
    Binario, 
    Boolean, 
    Date, 
    DateTime, 
    Decimal, 
    Integer, 
    Long, 
    NotAvailable,
    String, 
    Time; 


    public static String angular(TipoField t)
    {
        if (String.equals(t))
        {
            return "string";
        }
        if (Integer.equals(t) || Long.equals(t) || BigDecimal.equals(t) || BigInteger.equals(t) || Decimal.equals(t))
        {
            return "number";
        }
        if (Date.equals(t) || DateTime.equals(t) || Time.equals(t))
        {
            return "Date";
        }
        if (Boolean.equals(t))
        {
            return "boolean";
        }
        return "string";
    }


}
