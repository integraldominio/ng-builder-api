package com.idomine.helpers;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StreamExamlesTest
{

    @Test
    public void findFirstClasse()
    {
        System.out.println(">>>First");
        
        List<Campo> campos = newCampos();
        for (Campo campo : campos)
        {
            System.out.print(campo.getCampo());
            System.out.print(" (grupo: ");
            Campo cc = campos.stream().filter(c -> c.getGroup().equals(campo.getGroup())).findFirst().get();
            System.out.print(cc.getGroup());
            System.out.println(" first: " + cc.getCampo().equals(campo.getCampo()) + ")");
        }
    }

    @Test
    public void findLastClasse()
    {
        System.out.println(">>>Last");

        List<Campo> campos = newCampos();
        for (Campo campo : campos)
        {
            System.out.print(campo.getCampo());
            System.out.print(" (grupo: ");
            Campo cc = campos.stream().filter(c -> c.getGroup().equals(campo.getGroup())).reduce((a, b) -> b).get();
            System.out.print(cc.getGroup());
            System.out.println(" last: " + cc.getCampo().equals(campo.getCampo()) + ")");
        }
    }

    private List<Campo> newCampos()
    {
        List<Campo> campos = new ArrayList<>();

        Campo c1 = new Campo();
        c1.setGroup(1L);
        c1.setCampo("id");
        campos.add(c1);

        Campo c2 = new Campo();
        c2.setGroup(1L);
        c2.setCampo("nome");
        campos.add(c2);

        Campo c3 = new Campo();
        c3.setGroup(2L);
        c3.setCampo("email");
        campos.add(c3);

        Campo c4 = new Campo();
        c4.setGroup(2L);
        c4.setCampo("telefone");
        campos.add(c4);

        Campo c5 = new Campo();
        c5.setGroup(3L);
        c5.setCampo("observacao");
        campos.add(c5);

        Campo c6 = new Campo();
        c6.setGroup(4L);
        c6.setCampo("foto");
        campos.add(c6);

        return campos;
    }

    private class Campo
    {
        private Long group;
        private String campo;

        public Long getGroup()
        {
            return group;
        }

        public String getCampo()
        {
            return campo;
        }

        public void setGroup(Long group)
        {
            this.group = group;
        }

        public void setCampo(String campo)
        {
            this.campo = campo;
        }

        @Override
        public String toString()
        {
            return " [group=" + group + ", campo=" + campo + "]";
        }

    }

    @Test
    public void findFirstElemento()
    {
        // List<String> list = Arrays.asList("A", "B", "C", "D");
        // list.stream().map( e -> e.equals("B")).collect(Collectors.toList()).forEach( e-> System.out.println(e) );
        // list.stream().filter(e -> e.equals("B")).collect(Collectors.toList()).forEach(e -> System.out.println(e));
    }
}
