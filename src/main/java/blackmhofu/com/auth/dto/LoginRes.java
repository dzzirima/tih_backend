package blackmhofu.com.auth.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRes {
    String token;
}
