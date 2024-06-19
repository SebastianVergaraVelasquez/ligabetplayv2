package com.ligabetplay;

import java.util.Scanner;

import com.ligabetplay.Pais.adapters.in.PaisConsoleAdapter;
import com.ligabetplay.Pais.adapters.out.PaisMySQLRepository;
import com.ligabetplay.Pais.application.PaisService;
import com.ligabetplay.Region.adapters.in.RegionConsoleAdapter;
import com.ligabetplay.Region.adapters.out.RegionMYSQLRepository;
import com.ligabetplay.Region.application.RegionService;

public class Main {
    public static void main(String[] args) {
        boolean executing = true;
        Scanner scanner = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/ligabetplay";
        String user = "root";
        String password = "123456";


        while (executing) {
            System.out.println("***LigaBetplay");
            System.out.println("1. Apartado de paises");
            System.out.println("2. Apartado de regiones");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:       
                    PaisMySQLRepository sqlPais = new PaisMySQLRepository(url,user,password);
                    PaisService ps = new PaisService(sqlPais);
                    PaisConsoleAdapter consolePais = new PaisConsoleAdapter(ps);
                    consolePais.start();
                    break;
                case 2:
                    RegionMYSQLRepository sqlRegion = new RegionMYSQLRepository(url, user, password);
                    RegionService regionService = new RegionService(sqlRegion);
                    RegionConsoleAdapter consoleRegion = new RegionConsoleAdapter(regionService);
                    consoleRegion.start();
                    break;    
                case 3:
                    executing = false;
                    break;             
                default:
                    System.out.println("Ingrese una opcion de las mostradas");
                    break;
            }
        }
    }


}