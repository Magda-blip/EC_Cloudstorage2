package com.magdalena.cloudstorage2.repositories;

import com.magdalena.cloudstorage2.models.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FolderRepository extends JpaRepository <Folder, UUID> {
}
