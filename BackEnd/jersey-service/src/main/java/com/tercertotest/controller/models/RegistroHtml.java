package com.tercertotest.controller.models;

public class RegistroHtml {
    private Integer id;
    private String codigo;
    private boolean esCorrecto;

    public boolean EsCorrecto() {
        return this.esCorrecto;
    }

    public boolean getEsCorrecto() {
        return this.esCorrecto;
    }

    public void setEsCorrecto(boolean esCorrecto) {
        this.esCorrecto = esCorrecto;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    

    


}