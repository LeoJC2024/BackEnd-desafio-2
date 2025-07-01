// src/main/java/com/example/oncommerce/services/ProdutoService.java
package com.example.oncommerce.services;

import com.example.oncommerce.models.Categoria;
import com.example.oncommerce.models.Produto;
import com.example.oncommerce.repositories.CategoriaRepository;
import com.example.oncommerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository; // Para associar produtos a categorias

    public List<Produto> obterTodosProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> obterProdutoPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    // Adiciona um produto e o associa a uma categoria existente
    public Produto adicionarProduto(Integer categoriaId, Produto produto) {
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(categoriaId);
        if (categoriaOpt.isEmpty()) {
            throw new RuntimeException("Categoria não encontrada com o ID: " + categoriaId);
        }
        Categoria categoria = categoriaOpt.get();
        produto.setCategoria(categoria); // Associa o produto à categoria
        return produtoRepository.save(produto);
    }

    public Produto atualizarProduto(Integer id, Produto produtoAtualizado) {
        return produtoRepository.findById(id).map(produtoExistente -> {
            produtoExistente.setNome(produtoAtualizado.getNome());
            produtoExistente.setDescricao(produtoAtualizado.getDescricao());
            produtoExistente.setPreco(produtoAtualizado.getPreco());
            produtoExistente.setEstoque(produtoAtualizado.getEstoque());
            // Você pode adicionar lógica para mudar a categoria do produto aqui se necessário
            return produtoRepository.save(produtoExistente);
        }).orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
    }

    public void deletarProduto(Integer id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
        produtoRepository.deleteById(id);
    }

    // Novo método para consultar produtos por categoria
    public List<Produto> obterProdutosPorCategoria(Integer categoriaId) {
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new RuntimeException("Categoria não encontrada com o ID: " + categoriaId);
        }
        return produtoRepository.findByCategoriaId(categoriaId);
    }
}