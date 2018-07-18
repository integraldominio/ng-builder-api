package org.idomine.domain.crud.service;

import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Collection;

import org.watertemplate.Template;

public class MonthsGrid extends Template
{

    // https://github.com/tiagobento/watertemplate-engine
    private static final Collection<Month> months = Arrays.asList(Month.values());

    MonthsGrid(final Year year)
    {
        add("year", year.toString());
        addCollection("months", months, (month, map) -> {
            map.add("lowerName", month.name().toLowerCase());
            map.add("daysCount", month.length(year.isLeap()) + "");
        });
    }

    @Override
    protected String getFilePath()
    {
        return "months_grid.html";
    }

    public static void main(String[] args)
    {
        MonthsGrid monthsGrid = new MonthsGrid(Year.of(2015));
        System.out.println(monthsGrid.render());
    }

}
