package io.github.mizaeldouglas.logapi.domain.service;

import io.github.mizaeldouglas.logapi.domain.repository.ClientRepository;
import io.github.mizaeldouglas.logapi.domain.exception.NegocioExecption;
import io.github.mizaeldouglas.logapi.domain.model.Cliente;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

	private ClientRepository clientRepository;

	public  Cliente buscar(Long clineteId){
		return clientRepository.findById(clineteId)
				.orElseThrow(() -> new NegocioExecption("cliente não encontrado"));
	}

	@Transactional
	public Cliente salvar(Cliente cliente){
		boolean emailEmUso = clientRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExixtente -> clienteExixtente.equals(cliente));

		if (emailEmUso) {
			throw  new NegocioExecption("Já exite um cliente cadastrado com este e-mail.");
		}

		return clientRepository.save(cliente);
	}

	@Transactional
	public void excluir(Long clienteId){
		clientRepository.deleteById(clienteId);
	}
}
