package com.petz.api.controller;

import com.petz.api.dto.PetSalvarDTO;
import com.petz.api.entity.Cliente;
import com.petz.api.entity.Pet;
import com.petz.api.repository.ClienteRepository;
import com.petz.api.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {
    private final PetRepository petRepository;
    private final ClienteRepository clienteRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet salvarPet(@RequestBody @Valid PetSalvarDTO petDto){
        Cliente cliente = clienteRepository.findById(petDto.getProprietario_cpf())
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "NÃO É POSSIVEL REGISTRAR UM PET COM CLIENTE NÃO CADASTRADO"
                ));
        Pet pet = new Pet();
        pet.setCliente(cliente);
        pet.setNome(petDto.getNome());
        petRepository.save(pet);
        return pet;
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pet obterPet(@PathVariable Integer id){
        return petRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "PET NÃO CADASTRADO"
                ));
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarPet(@PathVariable Integer id){
        petRepository.findById(id)
                .map(pet -> {
                    petRepository.delete(pet);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "NÃO É POSSIVEL DELETAR UM PET NÃO CADASTRADO"
        ));
    }
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pet editarPet(@PathVariable Integer id,
                         @RequestBody @Valid PetSalvarDTO petDto){
        Cliente cliente = clienteRepository.findById(petDto.getProprietario_cpf())
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "NÃO É POSSIVEL EDITAR UM PET COM CLIENTE NÃO CADASTRADO"
                ));
        return petRepository.findById(id)
                .map(pet -> {
                    pet.setNome(petDto.getNome());
                    pet.setCliente(cliente);
                    return petRepository.save(pet);
                }).orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "NÃO É POSSIVEL EDITAR UM PET NÃO CADASTRADO"
                ));
    }
}
