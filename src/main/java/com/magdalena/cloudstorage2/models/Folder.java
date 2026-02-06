package com.magdalena.cloudstorage2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;


    @Entity
    @Table(name = "folders")


    public class Folder {

        @Id
        @GeneratedValue
        private UUID id; // UUID
        private String name;

        // Empty Constructor for JPA
        public Folder() {

        }
        // Egen konstruktor för att skapa folders i koden
        public Folder(String name) {
            this.name = name;
        }


        // JPA Getter&Setters
        public UUID getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



