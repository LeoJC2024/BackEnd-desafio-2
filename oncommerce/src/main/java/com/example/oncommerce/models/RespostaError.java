package com.example.oncommerce.models;

public class RespostaError {

    private String dataHora;
    private Integer status;
    private String titulo;
    private String detalhes;
    
    public RespostaError(String dataHora, Integer status, String titulo, String detalhes) {
        this.dataHora = dataHora;
        this.status = status;
        this.titulo = titulo;
        this.detalhes = detalhes;
    }

    public String getDataHora() {
        return dataHora;
    }
    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDetalhes() {
        return detalhes;
    }
    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }    
    
}
