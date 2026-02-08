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

    @GetMapping("/tarefas/{id}")
    public ResponseEntity<TarefaResponseDTO> buscarPorId(@PathVariable Long id){

        Optional<TarefaResponseDTO> responseDTO = tarefaService.buscaPorId(id);
        if(responseDTO.isPresent()){
            TarefaResponseDTO tarefaDTO = responseDTO.get();
            return ResponseEntity.ok(tarefaDTO);
        }else{
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
