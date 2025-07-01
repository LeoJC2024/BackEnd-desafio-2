// src/main/java/com/example/oncommerce/repositories/CategoriaRepository.java
package com.example.oncommerce.repositories;

import com.example.oncommerce.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}