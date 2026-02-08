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

    @Transactional
    public Optional<Tarefa> concluirTarefa(Long id){

        return tarefaRepository.findById(id)
                .map(tarefaExistente ->{
                    tarefaExistente.setConcluido(true);
                    return tarefaExistente;
                });
    }


    public Optional<TarefaResponseDTO> buscarPorId(Long id) {
        // 1. Busque a entidade no repositório.
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);

        // 2. Verifique se o Optional retornado pelo repositório contém um valor.
        if (tarefaOptional.isPresent()) {
            // 3. Se sim, pegue a entidade Tarefa de dentro do Optional.
            Tarefa tarefaEncontrada = tarefaOptional.get();

            // 4. Crie o DTO de resposta a partir da entidade.
            TarefaResponseDTO dto = new TarefaResponseDTO(tarefaEncontrada);

            // 5. Envolva o DTO recém-criado em um novo Optional e retorne.
            return Optional.of(dto);
        } else {
            // 6. Se o Optional do repositório estava vazio, retorne um Optional vazio também.
            return Optional.empty();
        }
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
