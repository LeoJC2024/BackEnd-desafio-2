// src/main/java/com/example/oncommerce/controllers/ProdutoController.java
package com.example.oncommerce.controllers;

import com.example.oncommerce.models.Produto;
import com.example.oncommerce.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // GET: Obter todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> obterTodosProdutos() {
        var produtos = produtoService.obterTodosProdutos();
        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(produtos);
    }

    // GET: Obter produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> obterProdutoPorId(@PathVariable Integer id) {
        Optional<Produto> produto = produtoService.obterProdutoPorId(id);
        return produto.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Adicionar um novo produto para uma categoria específica
    // Ex: POST /api/produtos/categoria/1
    @PostMapping("/categoria/{categoriaId}")
    public ResponseEntity<Produto> adicionarProduto(@PathVariable Integer categoriaId, @RequestBody Produto produto) {
        try {
            Produto novoProduto = produtoService.adicionarProduto(categoriaId, produto);
            return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Retorna 400 Bad Request se a categoria não for encontrada
        }
    }

    // PUT: Atualizar um produto existente
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto) {
        try {
            Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Deletar um produto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Integer id) {
        try {
            produtoService.deletarProduto(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}