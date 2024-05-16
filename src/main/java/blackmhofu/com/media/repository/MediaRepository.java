package blackmhofu.com.media.repository;

import blackmhofu.com.media.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MediaRepository extends JpaRepository<Media , UUID> {




}
