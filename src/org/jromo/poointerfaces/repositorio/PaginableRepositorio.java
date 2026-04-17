package org.jromo.poointerfaces.repositorio;

import org.jromo.poointerfaces.modelo.Cliente;

import java.util.List;

public interface PaginableRepositorio {
    List<Cliente> listar(int desde, int hasta);
}
