package com.petz.api.dto;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

@Data
public class PetSalvarDTO {
    @NotEmpty(message = "CAMPO NOME É OBRIGATÓRIO")
    private String nome;

    @NotEmpty(message = "CAMPO PROPRIETÁRIO_CPF É OBRIGATÓRIO")
    @CPF(message = "CPF DO PROPRIETÁRIO É INVÁLIDO")
    private String proprietario_cpf;
}
