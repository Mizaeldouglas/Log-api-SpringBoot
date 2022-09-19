package io.github.mizaeldouglas.logapi.controller;

import io.github.mizaeldouglas.logapi.domain.ClientRepository;
import io.github.mizaeldouglas.logapi.domain.model.Cliente;
import io.github.mizaeldouglas.logapi.domain.service.CatalogoClienteService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	private ClientRepository clientRepository;
	private CatalogoClienteService catalogoClienteService;

	@GetMapping
	public List<Cliente> listar(){
		return clientRepository.findAll();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){

		return clientRepository.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adionarCliente(@Valid @RequestBody Cliente cliente){
//		return clientRepository.save(cliente);
		return catalogoClienteService.salvar(cliente);
	}

	@PutMapping("/{clienteId}")
	public  ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId,@RequestBody Cliente cliente){
		if (!clientRepository.existsById(clienteId)){
			return ResponseEntity.notFound().build();
		}

		cliente.setId(clienteId);
		cliente = catalogoClienteService.salvar(cliente);

		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{clienteId}")
	public  ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		if (!clientRepository.existsById(clienteId)){
			return ResponseEntity.notFound().build();
		}
		catalogoClienteService.excluir(clienteId);
		return ResponseEntity.noContent().build();
	}

}
