package br.com.todolist.todolist;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    @Transactional
    public Optional<Tarefa> atualizarTarefa(Long id, TarefaRequestDTO tarefaAlterada){

        return tarefaRepository.findById(id)
            .map(tarefaExistente ->{
                tarefaExistente.setDescricao(tarefaAlterada.getDescricao());
                tarefaExistente.setTitulo(tarefaAlterada.getTitulo());
                tarefaExistente.setPrioridade(tarefaAlterada.getPrioridade());
                return tarefaExistente;
            });
    }



    @Transactional
    public Tarefa criarTarefa(TarefaRequestDTO novaTarefaDTO){

        Tarefa tarefaParaSalvar = new Tarefa(
            novaTarefaDTO.getTitulo(),
            novaTarefaDTO.getDescricao(),
            novaTarefaDTO.getPrioridade()
        );
        Tarefa tarefaSalva = tarefaRepository.save(tarefaParaSalvar);

        return tarefaSalva;
    }

    public List<TarefaResponseDTO> listarTodos(){
        return tarefaRepository.findAll()
            .stream()
            .map(TarefaResponseDTO::new)
            .collect(Collectors.toList());
    }
}
