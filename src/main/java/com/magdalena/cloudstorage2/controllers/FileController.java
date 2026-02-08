package com.magdalena.cloudstorage2.controllers;


import com.magdalena.cloudstorage2.dto.FileResponse;
import com.magdalena.cloudstorage2.models.StoredFile;
import com.magdalena.cloudstorage2.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public FileResponse uploadFile(
            @RequestParam UUID folderId,
            @RequestParam MultipartFile file
    ) {
        return fileService.uploadFile(folderId, file);
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable UUID fileId) {

        StoredFile file = fileService.getFile(fileId);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\""
                )
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file.getContent());
    }

}
