package br.com.fabricadeprogramador.ws.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fabricadeprogramador.ws.model.Estado;
import br.com.fabricadeprogramador.ws.repository.EstadoRepository;


@Service
public class EstadoService {
	
	@Autowired
	EstadoRepository estadoRepository;
	
	
	//negocio
	public Estado cadastrar(Estado estado) {
		return  estadoRepository.save(estado);
				
	}
	
	
	public void excluir(Estado estado)
	{
		estadoRepository.delete(estado);
	}
	
	
	
	public Estado buscarPorId(Integer id) {
	
		return estadoRepository.findOne(id);
				
	}
	
		
	
	public  Collection<Estado> BuscarTodos()
	{
		return estadoRepository.findAll();
	}
	
	
	public  Estado alterar(Estado estado)
	{
		
		return  estadoRepository.save(estado);
	}
	
	
	

}
