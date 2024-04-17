package blackmhofu.com.media.service;

import blackmhofu.com.media.dto.MediaResDto;
import blackmhofu.com.media.dto.MediaUpDateDto;
import blackmhofu.com.media.model.Media;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

public interface IMediaService {

    public List<MediaResDto> uploadFiles(List<MultipartFile> multipartFiles) throws IOException;

   public Path downloadFile(String filename) throws IOException;

   String update(MediaUpDateDto mediaUpDateDto);

   Media findById(UUID mediaId);

   List<Media> findAllByStepOrderId(UUID stepOrderId);
}
