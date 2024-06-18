package com.ligabetplay;

import com.ligabetplay.Pais.adapters.in.PaisConsoleAdapter;
import com.ligabetplay.Pais.adapters.out.PaisMySQLRepository;
import com.ligabetplay.Pais.application.PaisService;

public class Main {
    public static void main(String[] args) {
        PaisMySQLRepository sqlPais = new PaisMySQLRepository("jdbc:mysql://localhost:3306/ligabetplay", "root", "123456");
        PaisService ps = new PaisService(sqlPais);
        PaisConsoleAdapter console = new PaisConsoleAdapter(ps);
        console.start();
    }
}