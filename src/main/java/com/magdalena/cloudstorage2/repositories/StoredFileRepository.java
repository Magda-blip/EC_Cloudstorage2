package com.magdalena.cloudstorage2.repositories;

import com.magdalena.cloudstorage2.dto.FileResponse;
import com.magdalena.cloudstorage2.models.StoredFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface StoredFileRepository extends JpaRepository<StoredFile, UUID> {

    @Query("""
    select new com.magdalena.cloudstorage2.dto.FileResponse(f.id, f.filename)
    from StoredFile f
    where f.folder.id = :folderId
""")
    List<FileResponse> findFileResponsesByFolderId(@Param("folderId") UUID folderId);
}
