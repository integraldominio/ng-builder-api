package com.idomine.freemaker;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.idomine.domain.crud.service.helper.FreeMarkerEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FreeMarkerEngine.class) 
@ActiveProfiles("tdd")
public class FreeMakerTeste01
{

    @Autowired
    private FreeMarkerEngine freeMarkerEngine;
    
    @Test
    public void teste()
    {
        String expected = "Big Joe";
        Map<String, Object> model = new HashMap<>();
        model.put("name", "Big Joe");
        String result = freeMarkerEngine.process("test", model);
        assertEquals(expected, result);
    }
}
