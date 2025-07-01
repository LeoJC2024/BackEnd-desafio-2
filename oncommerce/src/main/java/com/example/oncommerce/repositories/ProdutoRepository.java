// src/main/java/com/example/oncommerce/repositories/ProdutoRepository.java
package com.example.oncommerce.repositories;

import com.example.oncommerce.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    // Novo método para encontrar produtos por ID de categoria
    List<Produto> findByCategoriaId(Integer categoriaId);
}