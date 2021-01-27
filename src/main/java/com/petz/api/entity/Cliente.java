package com.petz.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @Column(name = "cpf")
    @CPF(message = "CPF INVÁLIDO")
    private String cpf;

    @Column(name = "nome")
    @NotEmpty(message = "CAMPO NOME É OBRIGATÓRIO")
    private String nome;

    @Column(name = "endereco")
    @NotEmpty(message = "CAMPO ENDEREÇO É OBRIGATÓRIO")
    private String endereco;

    @JsonIgnore
    @OneToMany( mappedBy = "cliente" , fetch = FetchType.LAZY )
    private List<Pet> pets;
}
