package com.example.oncommerce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oncommerce.models.Cliente;
import com.example.oncommerce.services.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Método para obter todas as pessoas
    @GetMapping
    public ResponseEntity<List<Cliente>> obterTodos(){

        var clientes = clienteService.obterTodos();

        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();//Retorna 204 No Content se não houver clientes
        }

        return ResponseEntity.ok(clientes);//Retorn 200 ok lista de clientes
    
    }

    //Método para obter uma pessoa por id
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> obterPorId(@PathVariable int id) {

        var cliente = clienteService.obterPorId(id);

        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    //Método para adiconar uma pessoa
    @PostMapping
    public ResponseEntity<Integer> adicionar(@RequestBody Cliente cliente) {

        var id = clienteService.adicionar(cliente);           
        return new ResponseEntity<>(id, HttpStatus.CREATED);//Retorna 201 Created com id que foi criado
    }

    //Método para atualizar uma pessoa
    @PutMapping("/{id}")
    public ResponseEntity<Integer> atualizar(@PathVariable int id, @RequestBody Cliente cliente) {

        clienteService.atualizar(id, cliente);

        return ResponseEntity.ok(id);
    }

    //Método para deletar uma pessoa por id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable int id) {
        
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();//Retorna 204 No Content
        }
}
