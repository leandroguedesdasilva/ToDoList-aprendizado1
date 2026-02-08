package br.com.todolist.todolist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository <Tarefa,Long> {

}
