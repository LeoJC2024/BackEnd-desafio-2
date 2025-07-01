// src/main/java/com/example/oncommerce/repositories/EnderecoRepository.java
package com.example.oncommerce.repositories;

import com.example.oncommerce.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    // Você pode adicionar métodos específicos aqui, ex:
    List<Endereco> findByClienteId(Integer clienteId);
}