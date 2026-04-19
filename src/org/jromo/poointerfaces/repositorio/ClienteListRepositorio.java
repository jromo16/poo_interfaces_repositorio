package org.jromo.poointerfaces.repositorio;

import org.jromo.poointerfaces.modelo.Cliente;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClienteListRepositorio implements CrudRepositorio, OrdenableRepositorio, PaginableRepositorio{

    public List<Cliente> dataSource;

    public ClienteListRepositorio() {
        this.dataSource = new ArrayList<>();
    }

    @Override
    public List<Cliente> listar() {
        return dataSource;
    }

    @Override
    public Cliente porId(Integer id) {
        Cliente resultado = null;
        for(Cliente cli : this.dataSource){
            if( cli.getId() != null && cli.getId().equals(id)){
                resultado = cli;
                break;
            }
        }
        return resultado;
    }

    @Override
    public void crear(Cliente cliente) {
        this.dataSource.add(cliente);
    }

    @Override
    public void editar(Cliente cliente) {
        // 1. Buscamos el objeto original en el dataSource usando el ID
        Cliente c = this.porId(cliente.getId());
        // 2. Si no existe, lanzamos una excepción para detener la ejecución
        if (c == null) {
            throw new RuntimeException("Error: El cliente con ID " + cliente.getId() + " no existe.");
        }
        // 3. Actualizamos los atributos del objeto que ya está en la lista
        // Como 'c' apunta al mismo espacio de memoria que el objeto en el dataSource,
        // los cambios son "persistentes" en la lista.
        c.setNombre(cliente.getNombre());
        c.setApellido(cliente.getApellido());
    }

    @Override
    public void eliminar(Integer id) {
        this.dataSource.remove(this.porId(id));
    }

    @Override
    public List<Cliente> listar(String campo, Direccion dir) {
        // 1. Creamos una copia para no alterar el orden original del dataSource (opcional)
        List<Cliente> listaOrdenada = new ArrayList<>(this.dataSource);

        listaOrdenada.sort((a, b) -> {
            int resultado = 0;
            // Solo definimos el orden natural (ASC) una vez
            switch (campo) {
                case "id"       -> resultado = a.getId().compareTo(b.getId());
                case "nombre"   -> resultado = a.getNombre().compareTo(b.getNombre());
                case "apellido" -> resultado = a.getApellido().compareTo(b.getApellido());
            }
            // Si es DESC, simplemente multiplicamos por -1 para invertir el resultado
            return (dir == Direccion.ASC) ? resultado : resultado * -1;
        });
        return listaOrdenada;
    }

    @Override
    public List<Cliente> listar(int desde, int hasta) {
        // 1. Obtenemos el tamaño real de la lista
        int total = dataSource.size();

        // 2. Si el usuario pide más de lo que hay, lo topamos al total
        // Ejemplo: Si hasta es 5 y total es 4, 'fin' será 4.
        int fin = Math.min(hasta, total);

        // 3. Validación de seguridad para el inicio
        if (desde > total || desde > fin) {
            return new ArrayList<>(); // Retornamos lista vacía si el rango es absurdo
        }

        return dataSource.subList(desde, fin);
    }
}
