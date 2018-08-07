
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
    
    
    public static String mysql(TipoField t)
    {
        if (String.equals(t))
        {
            return "varchar(255)";
        }
        if (Integer.equals(t) || Long.equals(t) || BigDecimal.equals(t) || BigInteger.equals(t) || Decimal.equals(t))
        {
            return "bigint(20)";
        }
        if ( BigDecimal.equals(t) || Decimal.equals(t))
        {
            return "decimal(10,2)";
        }
        if (Date.equals(t) || DateTime.equals(t) || Time.equals(t))
        {
            return "datetime";
        }
        if (Boolean.equals(t))
        {
            return " bit(1)";
        }
        return "varchar(255)";
    }
    


}
