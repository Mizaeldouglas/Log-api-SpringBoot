package io.github.mizaeldouglas.logapi.domain.service;

import io.github.mizaeldouglas.logapi.domain.exception.NegocioExecption;
import io.github.mizaeldouglas.logapi.domain.model.Cliente;
import io.github.mizaeldouglas.logapi.domain.model.Entrega;
import io.github.mizaeldouglas.logapi.domain.model.StatusEntrega;
import io.github.mizaeldouglas.logapi.domain.repository.ClientRepository;
import io.github.mizaeldouglas.logapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {
	private CatalogoClienteService catalogoClienteService;
	private EntregaRepository entregaRepository;

	@Transactional
	public Entrega solicitar(Entrega entrega){

		Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());

		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());

		return entregaRepository.save(entrega);
	}

}
