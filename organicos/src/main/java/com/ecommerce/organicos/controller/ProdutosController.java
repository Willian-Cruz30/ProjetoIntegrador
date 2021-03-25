package com.ecommerce.organicos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.organicos.model.Produtos;
import com.ecommerce.organicos.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutosController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping
	public ResponseEntity<List<Produtos>> listarTodos(){
		return new ResponseEntity <List<Produtos>> (service.listarTodos(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Produtos>> buscarPorId(@PathVariable Long id){
		return new ResponseEntity <Optional<Produtos>> (service.buscarPorId(id),HttpStatus.OK);
		
	}
	
	
	@GetMapping("/organicos")
	public ResponseEntity <List<Produtos>> listarOrganicos(@RequestParam(defaultValue = "") boolean organicos){
		return new ResponseEntity <List<Produtos>> (service.listarOrganicos(organicos),HttpStatus.ACCEPTED);
	}
	
	@PostMapping
	public ResponseEntity <Produtos> postar(@RequestBody Produtos produtos){
		return new ResponseEntity <Produtos> (service.postar(produtos),HttpStatus.CREATED);
		
	}
		
	@PutMapping
	public ResponseEntity<?> alterar(@RequestBody Produtos produtos){
		Optional<Produtos> alterado = service.alterar(produtos);
		if (alterado.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto Inexistente");
	    }	
		else {
			return ResponseEntity.status(HttpStatus.CREATED).body(alterado.get());
		}
	}
		
	@DeleteMapping("/deletar/{id}")
	public void deletar(Produtos produtos) {
		service.deletar(produtos);
	}
	
}
