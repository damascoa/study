/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metre.domain.enums;

/**
 *
 * @author Renato
 */
public enum TipoCliente {
    PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

    private int codigo;
    private String descricao;

    private TipoCliente(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }
        for (TipoCliente value : TipoCliente.values()) {
            if (codigo.equals(value.getCodigo())) {
                return value;
            }
        }
        throw new IllegalArgumentException("CODIGO INVADILO " + codigo);
    }
    public static TipoCliente toEnum(String descricao) {
        if (descricao == null) {
            return null;
        }
        for (TipoCliente value : TipoCliente.values()) {
            if (descricao.equals(value.getDescricao())) {
                return value;
            }
        }
        throw new IllegalArgumentException("DESCRICAO INVADILO " + descricao);
    }

}
