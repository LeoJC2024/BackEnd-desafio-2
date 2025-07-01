// src/main/java/com/example/oncommerce/services/CategoriaService.java
package com.example.oncommerce.services;

import com.example.oncommerce.models.Categoria;
import com.example.oncommerce.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> obterTodasCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> obterCategoriaPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    public Categoria adicionarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizarCategoria(Integer id, Categoria categoriaAtualizada) {
        return categoriaRepository.findById(id).map(categoriaExistente -> {
            categoriaExistente.setNome(categoriaAtualizada.getNome());
            return categoriaRepository.save(categoriaExistente);
        }).orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));
    }

    public void deletarCategoria(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada com o ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
