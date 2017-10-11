package br.com.fabricadeprogramador.ws.controller;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.fabricadeprogramador.ws.model.Cliente;
import br.com.fabricadeprogramador.ws.service.ClienteService;

@RestController()
@RequestMapping(value="/admin")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	//end point
	@RequestMapping(method=RequestMethod.POST, value="/clientes", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente)
	
	{
		Cliente clienteCadastrado = clienteService.cadastrar (cliente);
		
		System.out.println("Chamou/clientes");
		
		return new ResponseEntity<Cliente>(clienteCadastrado, HttpStatus.CREATED);
		
		
	}
	
	
	
	//end point
	
	@RequestMapping(method=RequestMethod.GET, value="/clientes", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection> buscarTodosClientes()
	
	{
		Collection<Cliente> clientesBuscados = clienteService.BuscarTodos();
		
	
		
		return new ResponseEntity<>(clientesBuscados, HttpStatus.OK);
		
		
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/clientes/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> buscarsClientesPorId(@PathVariable Integer id)
	
	{
		Cliente clientesBuscados = clienteService.buscarPorId(id);
		
	
		
		return new ResponseEntity<>(clientesBuscados, HttpStatus.OK);
		
		
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/clientes/{id}")
	public ResponseEntity<Cliente> excluirCliente(@PathVariable Integer id)
	
	{
		
		Cliente clienteEncontrado = clienteService.buscarPorId(id);
	
		if(clienteEncontrado==null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		clienteService.excluir(clienteEncontrado);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
		
	}


	
	//end point
		@RequestMapping(method=RequestMethod.PUT, value="/clientes", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente)
		
		{
			Cliente clienteAlterado = clienteService.alterar(cliente);
			
			System.out.println("Chamou/clientes");
			
			return new ResponseEntity<Cliente>(clienteAlterado, HttpStatus.OK);
			
			
		}

	

}
