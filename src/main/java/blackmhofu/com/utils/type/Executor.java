package blackmhofu.com.utils.type;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Executor {
    private String executorName;
    private String role;
    private ExecutorType  executorType;
}


enum  ExecutorType{
    USER,
    ORGANISATION
}

