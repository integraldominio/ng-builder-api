package org.idomine.domain.crud.model.vo;

/**
 * Menu: menu principal só tem 1, as opcoes são acoes, aceita várias.
 * Sidebar: laterial na erqueda ou direita, mesma regra menu
 * Crud: cadastro, vários
 * Report: listagem, vários 
 * Dialgo: janela de acoes: vários
 * Acao: controle sobre um dialogo ou crud, varios
 * Elemento: web elemento ligado a um crude.
 *
 */

public enum TipoArtefato
{
  Menu, Crud, Template, Report, Dialogo;   
}
