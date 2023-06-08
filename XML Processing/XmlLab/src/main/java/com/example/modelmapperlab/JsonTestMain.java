package com.example.modelmapperlab;

import com.example.modelmapperlab.entities.dto.AddressDTO;
import com.example.modelmapperlab.entities.dto.CreateEmployeeDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class JsonTestMain implements CommandLineRunner {
    private final Scanner scanner;

    public JsonTestMain(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void run(String... args) throws Exception {

        Gson gson = new GsonBuilder()
                //.excludeFieldsWithoutExposeAnnotation()
                //.setPrettyPrinting()
                .create();

        AddressDTO addressDTO = new AddressDTO("Poland", "Warsaw");

        String json = gson.toJson(addressDTO);
        System.out.println(json);

       String input = this.scanner.nextLine();
     // CreateEmployeeDTO parsedDTO = gson.fromJson(input, CreateEmployeeDTO.class);
        AddressDTO[] list = gson.fromJson(input,AddressDTO[].class);

    }
}
