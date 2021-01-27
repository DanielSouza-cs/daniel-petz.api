package com.petz.api.repository;

import com.petz.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    String OBTER_TODOS_CLIENTES = "SELECT nome, endereco FROM cliente";

    @Query(value = OBTER_TODOS_CLIENTES, nativeQuery = true)
    List<Object[]>obterClientes();
}
