package br.com.todolist.todolist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TarefaRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    @Size(min=3, message = "O título deve conter no mínimo 4 caracteres")
    private String titulo;

    private String descricao;


    @NotNull(message = "A prioridade deve ser definida")
    private int prioridade;

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getPrioridade() {
        return prioridade;
    }
}
