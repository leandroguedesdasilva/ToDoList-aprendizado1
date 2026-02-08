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
    public List<TarefaResponseDTO> listarTodos(){
        return tarefaService.listarTodos();
    }

    @PostMapping("/tarefas")
    public ResponseEntity<TarefaResponseDTO> criarTarefa (@Valid @RequestBody TarefaRequestDTO novaTarefaDTO){

        Tarefa tarefaSalva = tarefaService.criarTarefa(novaTarefaDTO);
        TarefaResponseDTO responseDTO = new TarefaResponseDTO(tarefaSalva);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

    @PutMapping("/tarefas/{id}")
    public ResponseEntity<Tarefa> atualizaTarefa (@PathVariable Long id, @Valid @RequestBody TarefaRequestDTO tarefaAlteradaDTO){

        Optional<Tarefa> tarefaSalva = tarefaService.atualizarTarefa(id,tarefaAlteradaDTO);
        if (tarefaSalva.isEmpty()){
            ResponseEntity.notFound().build();
            return ResponseEntity.ok(tarefaSalva.get());
        } else {
            return ResponseEntity.ok(tarefaSalva.get());
        }

    }
}
