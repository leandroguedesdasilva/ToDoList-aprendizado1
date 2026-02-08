package br.com.todolist.todolist;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("/tarefas")
    public ResponseEntity<List<TarefaResponseDTO>> listarTodos(){
        List<TarefaResponseDTO> tarefas = tarefaService.listarTodos();
        if (tarefas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}" )
    public ResponseEntity<TarefaResponseDTO> buscarPorId(@PathVariable Long id) {
        // 1. Chame o serviço, que retorna um Optional de DTO.
        Optional<TarefaResponseDTO> tarefaDTO = tarefaService.buscarPorId(id);

        // 2. Verifique se o Optional retornado pelo serviço contém um valor.
        if (tarefaDTO.isPresent()) {
            // 3. Se sim, pegue o DTO de dentro do Optional.
            TarefaResponseDTO tarefaEncontrada = tarefaDTO.get();

            // 4. Crie uma resposta HTTP 200 OK com o DTO no corpo.
            return ResponseEntity.ok(tarefaEncontrada);
        } else {
            // 5. Se o Optional estava vazio, crie uma resposta HTTP 404 Not Found.
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/tarefas/{id}/concluir")
    public ResponseEntity<Tarefa> concluirTarefaPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefaDTO = tarefaService.concluirTarefa(id);
        if (tarefaDTO.isPresent()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/tarefas")
    public ResponseEntity<TarefaResponseDTO> criarTarefa (@Valid @RequestBody TarefaRequestDTO novaTarefaDTO){

        Tarefa tarefaSalva = tarefaService.criarTarefa(novaTarefaDTO);
        TarefaResponseDTO responseDTO = new TarefaResponseDTO(tarefaSalva);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

    @PutMapping("/tarefas/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizarTarefa(@PathVariable Long id, @Valid @RequestBody TarefaRequestDTO tarefaAlteradaDTO){

        Optional<Tarefa> tarefaOptional = tarefaService.atualizarTarefa(id,tarefaAlteradaDTO);
        if(tarefaOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Tarefa tarefaSalva = tarefaOptional.get();
        TarefaResponseDTO responseDTO = new TarefaResponseDTO(tarefaSalva);
        return ResponseEntity.ok(responseDTO);

    }
}
