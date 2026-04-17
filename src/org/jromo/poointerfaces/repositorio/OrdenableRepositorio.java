package org.jromo.poointerfaces.repositorio;

import org.jromo.poointerfaces.modelo.Cliente;

import java.util.List;

public interface OrdenableRepositorio {
    List<Cliente> listar(String campo, Direccion dir);

}
