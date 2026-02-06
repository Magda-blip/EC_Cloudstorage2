package com.magdalena.cloudstorage2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Tillfälligt för att testa
import com.magdalena.cloudstorage2.models.Folder;
import com.magdalena.cloudstorage2.repositories.FolderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Cloudstorage2Application {

    public static void main(String[] args) {
        SpringApplication.run(Cloudstorage2Application.class, args);
    }

    //Tillfälligt för att testa kod
    @Bean
    CommandLineRunner testFolderSave(FolderRepository folderRepository) {
        return args -> {
            Folder folder = new Folder("Min folder");
            folderRepository.save(folder);
            System.out.print("Folder sparad med ID: " + folder.getId());
        };
    }

}
