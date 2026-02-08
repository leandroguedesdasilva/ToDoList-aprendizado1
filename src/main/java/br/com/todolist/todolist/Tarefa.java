package br.com.todolist.todolist;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tarefa {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private Boolean concluido;
    private int prioridade;

    public Tarefa(Long id, String titulo, String descricao, Boolean concuido, int prioridade) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluido = false;
        this.prioridade = prioridade;
    }

    protected Tarefa() {

    }

    public Tarefa(String titulo, String descricao, int prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluido = false;
        this.prioridade = prioridade;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Boolean getConcluido() {
        return concluido;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setConcluido(Boolean concuido) {
        this.concluido = concuido;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
}
