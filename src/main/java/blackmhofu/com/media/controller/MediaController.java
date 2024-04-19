package blackmhofu.com.media.controller;

import blackmhofu.com.media.dto.MediaResDto;
import blackmhofu.com.media.service.MediaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;


import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping("/api/v1/media")
public class MediaController {

    @Autowired
    private MediaServiceImpl mediaService;


    // define method to upload files
    @PostMapping("/upload")
    public ResponseEntity<List<MediaResDto>> uploadFiles(
            @RequestParam("files") List<MultipartFile> multipartFiles
    ) throws IOException {
        List<MediaResDto> mediaResDtos = mediaService.uploadFiles(multipartFiles);
        return ResponseEntity.ok().body(mediaResDtos);
    }

    // download  files

    // Define a method to download files
    @GetMapping("download/{filename}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {

        Path filePath = mediaService.downloadFile(filename);
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }

}


