// src/main/java/com/example/oncommerce/controllers/CategoriaController.java
package com.example.oncommerce.controllers;

import com.example.oncommerce.models.Categoria;
import com.example.oncommerce.models.Produto; // Para retornar produtos ao obter categoria
import com.example.oncommerce.services.CategoriaService;
import com.example.oncommerce.services.ProdutoService; // Para obter produtos da categoria
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProdutoService produtoService; // Para o endpoint de produtos por categoria

    // GET: Obter todas as categorias
    @GetMapping
    public ResponseEntity<List<Categoria>> obterTodasCategorias() {
        var categorias = categoriaService.obterTodasCategorias();
        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categorias);
    }

    // GET: Obter categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obterCategoriaPorId(@PathVariable Integer id) {
        Optional<Categoria> categoria = categoriaService.obterCategoriaPorId(id);
        return categoria.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Adicionar uma nova categoria
    @PostMapping
    public ResponseEntity<Categoria> adicionarCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaService.adicionarCategoria(categoria);
        return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
    }

    // PUT: Atualizar uma categoria existente
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        try {
            Categoria categoriaAtualizada = categoriaService.atualizarCategoria(id, categoria);
            return ResponseEntity.ok(categoriaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Deletar uma categoria por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCategoria(@PathVariable Integer id) {
        try {
            categoriaService.deletarCategoria(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // NOVO ENDPOINT: Consultar produtos por categoria
    @GetMapping("/{categoriaId}/produtos")
    public ResponseEntity<List<Produto>> obterProdutosPorCategoria(@PathVariable Integer categoriaId) {
        try {
            List<Produto> produtos = produtoService.obterProdutosPorCategoria(categoriaId);
            if (produtos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(produtos);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Categoria não encontrada
        }
    }
}
