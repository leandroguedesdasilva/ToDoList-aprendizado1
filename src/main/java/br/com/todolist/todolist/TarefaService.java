package br.com.todolist.todolist;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Optional<TarefaResponseDTO> buscaPorId(Long id){

        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
        Optional<TarefaResponseDTO> responseDTO = tarefaOptional.map(TarefaResponseDTO::new);
        return responseDTO;
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
