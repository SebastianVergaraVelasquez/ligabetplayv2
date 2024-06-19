package com.ligabetplay.Region.adapters.in;

import java.util.Optional;
import java.util.Scanner;

import com.ligabetplay.Region.application.RegionService;
import com.ligabetplay.Region.domain.models.Region;

public class RegionConsoleAdapter {

    private final RegionService regionService;

    public RegionConsoleAdapter(RegionService regionService){
        this.regionService = regionService;
    }

    public void start(){
        boolean executing = true;
        Scanner scanner = new Scanner(System.in);

        while(executing){
            System.out.println("1. Crear Region");
            System.out.println("2. Actualizar Region");
            System.out.println("3. Buscar Region por ID");
            System.out.println("4. Eliminar Region");
            System.out.println("5. Listar todas las regiones");
            System.out.println("6. Salir");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Ingrese el nombre de la region");
                    String nameRegion = scanner.nextLine();
                    Region newRegion = new Region(nameRegion);
                    regionService.createRegion(newRegion);
                    break;

                case 2:
                    System.out.print("Ingrese  ID a actualizar: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el nuevo nombre: ");
                    String updateName = scanner.nextLine();

                    Region updateRegion = new Region(updateId, updateName);
                    regionService.updateRegion(updateRegion);
                    break;

                case 3:
                    System.out.print("Ingrese el Id de la reigon a buscar: ");
                    int findId = scanner.nextInt();
                    scanner.nextLine();

                    Optional<Region> foundRegion = regionService.getRegionById(findId);
                    foundRegion.ifPresentOrElse(
                        p -> System.out.println("ID: " + p.getId() + ", Nombre: " + p.getNombre()),
                        () -> System.out.println("Pais no encontrado")
                    );
                    break;

                case 4:
                    System.out.print("Ingrese el Id del pais a borrar: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    regionService.deleteRegion(deleteId);
                    break;

                case 5:
                    regionService.getAllRegiones().forEach(p -> {
                        System.out.println("ID: " + p.getId() + ", Nombre: " + p.getNombre());
                    });
                    break;
                case 6:
                    executing = false;
                    break;
                default:
                    System.out.println("Ingrese una opcion de las mostradas");
                    break;
            }
        }
    }

}
