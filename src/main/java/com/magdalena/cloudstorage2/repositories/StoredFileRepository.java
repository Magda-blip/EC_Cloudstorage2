package com.magdalena.cloudstorage2.repositories;

import com.magdalena.cloudstorage2.models.StoredFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StoredFileRepository extends JpaRepository<StoredFile, UUID> {

    List<StoredFile> findByFolderId(UUID folderId);
}
