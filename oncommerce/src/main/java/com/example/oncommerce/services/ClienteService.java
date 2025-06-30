package com.example.oncommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.oncommerce.models.Cliente;
import com.example.oncommerce.models.exceptions.ResourceBadRequestException;
import com.example.oncommerce.models.exceptions.ResourceNotFoundException;
import com.example.oncommerce.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obterTodos(){
        return clienteRepository.findAll();
        //select + from cliente;
    }
    
    public Optional<Cliente> obterPorId(int id){
        return clienteRepository.findById(id);
        //select * from cliente where idCliente = ?;
    }

public int adicionar(Cliente cliente) {
    List<String> camposInvalidos = new ArrayList<>();

    if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
        camposInvalidos.add("nome");
    }

    if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()) {
        camposInvalidos.add("email");
    }

    if (!camposInvalidos.isEmpty()) {
        String campos = String.join(" e ", camposInvalidos);
        String mensagem;

        if (camposInvalidos.size() == 1) {
            mensagem = "O campo " + campos + " é obrigatório.";
        } else {
            mensagem = "Os campos " + campos + " são obrigatórios.";
        }

        throw new ResourceBadRequestException(mensagem);
    }

    cliente.setId(0); // Garante que o ID seja 0 para nova inserção
    var clienteAdicionado = clienteRepository.save(cliente);
    return clienteAdicionado.getId();
}



    public int atualizar(int id, Cliente cliente){

        var entidade = obterPorId(id);
        
        if(entidade.isEmpty())
        throw new ResourceNotFoundException("Impossivel atualizar! Cliente com ID: " + id + " não existe");

        cliente.setId(id);//garante que o ID seja o mesmo da entidade existente

        var clienteAdicionada = clienteRepository.save(cliente);
        return clienteAdicionada.getId();
        //update cliente set nome = ?, email = ?, telefone = ? where idCliente = ?;
    }

    public void deletar(int id){
        clienteRepository.deleteById(id);
        //delete from cliente where idCliente = ?;
    }
}
