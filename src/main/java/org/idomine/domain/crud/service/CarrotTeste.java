package org.idomine.domain.crud.service;

import au.com.codeka.carrot.CarrotEngine;
import au.com.codeka.carrot.CarrotException;
import au.com.codeka.carrot.Configuration;
import au.com.codeka.carrot.bindings.EmptyBindings;
import au.com.codeka.carrot.resource.FileResourceLocator;

public class CarrotTeste
{
    public static void main(String[] args) throws CarrotException
    {
        CarrotEngine engine = new CarrotEngine(new Configuration.Builder()
                .setResourceLocator(new FileResourceLocator.Builder("/carrot/"))
                .build());
        
        System.out.println(engine.process("/carrot/index.html", new EmptyBindings()));
    }
}
