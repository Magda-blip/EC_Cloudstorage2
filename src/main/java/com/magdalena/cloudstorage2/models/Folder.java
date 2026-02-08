package com.magdalena.cloudstorage2.models;

//Create table if not exist, create folder upon request
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "folders")
@Getter
@Setter
@NoArgsConstructor
public class Folder {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;


    //One user - many folders, folder must have user, create user_id
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL)
    private List<StoredFile> files;

}
