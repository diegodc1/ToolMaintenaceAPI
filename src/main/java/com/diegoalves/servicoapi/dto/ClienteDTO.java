package com.diegoalves.servicoapi.dto;

import com.diegoalves.servicoapi.models.Cliente;
import com.diegoalves.servicoapi.repositories.ClienteRepository;

import java.util.List;

public class ClienteDTO {

    private Long id;
    private String nome;

    private String endereco;

    private String telefone;
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nome, String endereco, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public ClienteDTO(Cliente cliente) {
        id = cliente.getId();
        nome = cliente.getNome();
        endereco = cliente.getEndereco();
        telefone = cliente.getTelefone();
        email = cliente.getEmail();
    }

    public Cliente newCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setEmail(this.email);
        cliente.setEndereco(this.endereco);
        cliente.setTelefone(this.telefone);

        return cliente;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
