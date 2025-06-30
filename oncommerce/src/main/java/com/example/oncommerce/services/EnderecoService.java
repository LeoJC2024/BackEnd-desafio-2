// src/main/java/com/example/oncommerce/services/EnderecoService.java
package com.example.oncommerce.services;

import com.example.oncommerce.models.Cliente;
import com.example.oncommerce.models.Endereco;
import com.example.oncommerce.repositories.ClienteRepository; // Assumindo que você tem um ClienteRepository
import com.example.oncommerce.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository; // Para buscar o cliente ao associar o endereço

    public List<Endereco> obterTodosEnderecos() {
        return enderecoRepository.findAll();
    }

    public List<Endereco> obterEnderecosPorCliente(Integer clienteId) {
        return enderecoRepository.findByClienteId(clienteId);
    }

    public Optional<Endereco> obterEnderecoPorId(Integer id) {
        return enderecoRepository.findById(id);
    }

    public Endereco adicionarEndereco(Integer clienteId, Endereco endereco) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
        if (clienteOpt.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado com o ID: " + clienteId);
        }
        Cliente cliente = clienteOpt.get();
        endereco.setCliente(cliente); // Associa o endereço ao cliente
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizarEndereco(Integer id, Endereco enderecoAtualizado) {
        return enderecoRepository.findById(id).map(enderecoExistente -> {
            enderecoExistente.setRua(enderecoAtualizado.getRua());
            enderecoExistente.setNumero(enderecoAtualizado.getNumero());
            enderecoExistente.setComplemento(enderecoAtualizado.getComplemento());
            enderecoExistente.setBairro(enderecoAtualizado.getBairro());
            enderecoExistente.setCidade(enderecoAtualizado.getCidade());
            enderecoExistente.setEstado(enderecoAtualizado.getEstado());
            enderecoExistente.setCep(enderecoAtualizado.getCep());
            enderecoExistente.setPais(enderecoAtualizado.getPais());
            enderecoExistente.setTipoEndereco(enderecoAtualizado.getTipoEndereco());
            // Não atualize o cliente aqui diretamente, a menos que seja para mudar o proprietário
            return enderecoRepository.save(enderecoExistente);
        }).orElseThrow(() -> new RuntimeException("Endereço não encontrado com o ID: " + id));
    }

    public void deletarEndereco(Integer id) {
        if (!enderecoRepository.existsById(id)) {
            throw new RuntimeException("Endereço não encontrado com o ID: " + id);
        }
        enderecoRepository.deleteById(id);
    }
}
