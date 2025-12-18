package com.minio.minio.controller;

import com.minio.minio.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        try{
            String response= fileService.uploadFile(file);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Error uploading file");
        }
    }

    @GetMapping("/download/{fileName}") // Use { } to capture the variable
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) {
        try {
            InputStream fileStream = fileService.downloadFile(fileName);

            // Wrap in InputStreamResource to stream data instead of loading it all into RAM
            InputStreamResource resource = new InputStreamResource(fileStream);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (Exception e) {
            //  Return a proper 404 error if file not found
            return ResponseEntity.notFound().build();
        }
    }

}
