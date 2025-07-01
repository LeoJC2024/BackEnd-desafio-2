
// src/main/java/com/example/oncommerce/controllers/EnderecoController.java
package com.example.oncommerce.controllers;

import com.example.oncommerce.models.Endereco;
import com.example.oncommerce.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    // Obter todos os endereços
    @GetMapping
    public ResponseEntity<List<Endereco>> obterTodosEnderecos() {
        var enderecos = enderecoService.obterTodosEnderecos();
        if (enderecos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(enderecos);
    }

    // Obter endereços por ID do cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Endereco>> obterEnderecosPorCliente(@PathVariable Integer clienteId) {
        var enderecos = enderecoService.obterEnderecosPorCliente(clienteId);
        if (enderecos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(enderecos);
    }

    // Obter um endereço por ID
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> obterEnderecoPorId(@PathVariable Integer id) {
        Optional<Endereco> endereco = enderecoService.obterEnderecoPorId(id);
        return endereco.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Adicionar um endereço para um cliente específico
    @PostMapping("/cliente/{clienteId}")
    public ResponseEntity<Endereco> adicionarEndereco(@PathVariable Integer clienteId, @RequestBody Endereco endereco) {
        try {
            Endereco novoEndereco = enderecoService.adicionarEndereco(clienteId, endereco);
            return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Ou um DTO de erro mais detalhado
        }
    }

    // Atualizar um endereço
    @PutMapping("/{ID}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Integer id, @RequestBody Endereco endereco) {
        try {
            Endereco enderecoAtualizado = enderecoService.atualizarEndereco(id, endereco);
            return ResponseEntity.ok(enderecoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar um endereço
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEndereco(@PathVariable Integer id) {
        try {
            enderecoService.deletarEndereco(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}