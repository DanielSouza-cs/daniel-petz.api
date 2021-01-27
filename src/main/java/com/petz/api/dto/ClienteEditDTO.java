package com.petz.api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ClienteEditDTO {
    @NotEmpty(message = "CAMPO ENDEREÇO É OBRIGATÓRIO")
    private String endereco;
}
