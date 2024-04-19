package blackmhofu.com.media.service;

import blackmhofu.com.media.dto.MediaResDto;
import blackmhofu.com.media.dto.MediaUpDateDto;
import blackmhofu.com.media.mapper.MediaMapper;
import blackmhofu.com.media.model.Media;
import blackmhofu.com.media.repository.MediaRepository;
import blackmhofu.com.order_step.model.Order_Step;
import blackmhofu.com.order_step.service.OrderStepServiceImpl;
import blackmhofu.com.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


@Service
public class MediaServiceImpl implements IMediaService {

    @Autowired
    private MediaMapper mediaMapper;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private OrderStepServiceImpl orderStepService;

    // define a location
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/tihmedia";

    // define method to upload files
    public List<MediaResDto> uploadFiles(List<MultipartFile> multipartFiles) throws IOException {


        List<MediaResDto> savedMediaList = new ArrayList<>();

        for (MultipartFile file : multipartFiles) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = get(DIRECTORY, fileName).toAbsolutePath().normalize();

            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);


            // add that file to  documents
            Media mediaToSave = Media.builder().name(fileName).size(file.getSize()).build();

            Media savedMedia = mediaRepository.save(mediaToSave);

            // creating dto
            MediaResDto mediaResDto = mediaMapper.dto(savedMedia);

            // add to list
            savedMediaList.add(mediaResDto);

        }
        return savedMediaList;
    }

    // download  files

    // Define a method to download files

    public Path downloadFile(String filename) throws IOException {
        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on the server");
        }
        return filePath;
    }

    @Override
    public String update(MediaUpDateDto mediaUpDateDto) {

        Media foundMedia = findById(mediaUpDateDto.getMediaId());

        boolean changes = false;

        if (mediaUpDateDto.getMediaStepId() != null && mediaUpDateDto.getMediaStepId() != foundMedia.getId()) {
            Order_Step foundOrderStep = orderStepService.findById(mediaUpDateDto.getMediaStepId());

            foundMedia.setOrder_step(foundOrderStep);

            changes = true;

        }

        if (changes) {
            mediaRepository.save(foundMedia);
            return "Successfully changes were done successfully ";
        }
        return "Error while making changes ";
    }

    @Override
    public Media findById(UUID mediaId) {
        return mediaRepository.findById(mediaId).orElseThrow(() -> new ResourceNotFoundException("Media with id [ %s ] not found ".formatted(mediaId)));
    }

    @Override
    public List<Media> findAllByStepOrderId(UUID stepOrderId) {
        return  mediaRepository.findMediaByOrderStep(stepOrderId);
    }

}


