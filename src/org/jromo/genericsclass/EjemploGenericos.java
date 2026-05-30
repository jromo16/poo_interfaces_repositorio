package org.jromo.genericsclass;

public class EjemploGenericos {
    public static void main(String[] args) {

        Camion transporteCaballos = new Camion(5);
        transporteCaballos.add(new Animal("Peregrino", "Caballo"));
        transporteCaballos.add(new Animal("Grillo", "Caballo"));
        transporteCaballos.add(new Animal("Tunquen", "Caballo"));
        transporteCaballos.add(new Animal("Topocalma", "Caballo"));
        transporteCaballos.add(new Animal("Longotoma", "Caballo"));

        for (Object o: transporteCaballos){
            Animal animal = (Animal) o;
            System.out.println(animal.getNombre() + " tipo " + animal.getTipo());

        }

        Camion transMaquinas = new Camion(3);
        transMaquinas.add(new Maquinaria("Bulldozer"));
        transMaquinas.add(new Maquinaria("Grua Horquilla"));
        transMaquinas.add(new Maquinaria("Perforadora"));

        for (Object o: transMaquinas){
            Maquinaria ma = (Maquinaria) o;
            System.out.println(ma.getTipo());
        }

        Camion transAuto = new Camion(3);
        transAuto.add(new Automovil("Toyota"));
        transAuto.add(new Automovil("Mitsubishi"));
        transAuto.add(new Automovil("Chevrolet"));

        for (Object o: transAuto){
            Automovil auto = (Automovil) o;
            System.out.println(auto.getMarca());
        }

    }
}
