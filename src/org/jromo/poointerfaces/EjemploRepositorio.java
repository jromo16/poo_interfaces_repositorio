package org.jromo.poointerfaces;

import org.jromo.poointerfaces.modelo.Cliente;
import org.jromo.poointerfaces.repositorio.*;

import java.util.List;

public class EjemploRepositorio {

    public static void main(String[] args) {

        OrdenablePaginableCrudRepositorio repo = new ClienteListRepositorio();
        repo.crear(new Cliente("Jano", "Perez"));
        repo.crear(new Cliente("Bea", "Gonzales"));
        repo.crear(new Cliente("Luci", "Martinez"));
        repo.crear(new Cliente("Jhon", "Romo"));

        List<Cliente> clientes = repo.listar();
        clientes.forEach(System.out::println);

        System.out.println();
        System.out.println("===== Paginable =====");
        List<Cliente> paginable = repo.listar(1,5);
        paginable.forEach(System.out::println);

        System.out.println();
        System.out.println("===== Ordenar =====");
        List<Cliente> clientesOrdenAsc = repo.listar("apellido", Direccion.ASC);
        for (Cliente c : clientesOrdenAsc) {
            System.out.println(c);
        }

        System.out.println();
        System.out.println("===== Editar =====");
        Cliente beaActualizar = new Cliente("Bea", "Mena");
        beaActualizar.setId(2);
        repo.editar(beaActualizar);
        Cliente bea = repo.porId(2);
        System.out.println(bea);
        repo.listar("apellido", Direccion.ASC).forEach(System.out::println);

        System.out.println();
        System.out.println("===== Eliminar =====");
        repo.eliminar(2);
        repo.listar().forEach(System.out::println);

        System.out.println("===== Total =====");
        System.out.println("Total registros: " + repo.total());
    }

}
