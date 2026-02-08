package br.com.todolist.todolist;

public class TarefaResponseDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private Boolean concluido;
    private int prioridade;

    public TarefaResponseDTO(Tarefa tarefaEntidade) {
        this.id =  tarefaEntidade.getId();
        this.titulo = tarefaEntidade.getTitulo();
        this.descricao = tarefaEntidade.getDescricao();
        this.concluido = tarefaEntidade.getConcluido();
        this.prioridade = tarefaEntidade.getPrioridade();
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
}
