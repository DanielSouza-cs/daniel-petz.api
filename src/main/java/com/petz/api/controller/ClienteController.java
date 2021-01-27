package com.petz.api.controller;

import com.petz.api.dto.ClienteEditDTO;
import com.petz.api.entity.Cliente;
import com.petz.api.repository.ClienteRepository;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvarCliente(@RequestBody @Valid Cliente cliente){
        if(!(repository.findById(cliente.getCpf()).isPresent())){
            return repository.save(cliente);
        }
        return repository.findById(cliente.getCpf()).get();
    }

    @GetMapping("{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente obterCliente(@PathVariable String cpf){
        return repository.findById(cpf)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "CLIENTE NÃO ENCONTRADO"
                ));
    }
    @DeleteMapping("{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public void deletaCliente(@PathVariable String cpf){
        repository.findById(cpf)
                .map(cliente -> {
                    repository.deleteById(cpf);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "NÃO É POSSIVEL DELETAR UM CLIENTE NÃO CADASTRADO"
        ));
    }

    @PatchMapping("{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente editaCliente(@PathVariable String cpf,
                                @RequestBody @Valid ClienteEditDTO novosDadosCliente){
        return repository.findById(cpf)
                .map(cliente -> {
                    cliente.setEndereco(novosDadosCliente.getEndereco());
                    return repository.save(cliente);
                }).orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "NÃO É POSSIVEL EDITAR UM CLIENTE NÃO CADASTRADO"
                ));
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> obterTodosClientes(){
        return repository.obterClientes();
    }

}
