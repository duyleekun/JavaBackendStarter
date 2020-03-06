package vn.tripath.backend_demo.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostRequest {
    String title;
    String content;
}
