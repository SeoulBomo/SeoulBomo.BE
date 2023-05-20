package SeoulBomo.SeoulBomoBe.common.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
public class PageResponse<T> {
    private final int totalPages;
    private final long totalElements;
    private final boolean islast;
    private final List<T> content;

    public static <T> PageResponse<T> of(Page<T> page) {
        return PageResponse.<T>builder()
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .islast(page.isLast())
                .content(page.getContent())
                .build();
    }
}