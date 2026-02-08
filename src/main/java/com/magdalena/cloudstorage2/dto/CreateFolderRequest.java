package com.magdalena.cloudstorage2.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateFolderRequest {

    private String name;
    private UUID userId;
}
