package blackmhofu.com.media.repository;

import blackmhofu.com.media.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MediaRepository extends JpaRepository<Media , UUID> {
    List<Media>  findMediaByOrder_step_Id(UUID stepOrderIs);
}
