package SeoulBomo.SeoulBomoBe.common.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {

    protected String createdAt;
    protected String modifiedAt;
    protected boolean isVisible = true;

    private String customFormat() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

    @PrePersist
    protected void onPrePersist() {
        createdAt = customFormat();
        modifiedAt = createdAt;
    }

    @PreUpdate
    protected void onPreUpdate() {
        modifiedAt = customFormat();
    }

    public void softDelete() {
        isVisible = false;
    }

    public void setVisible() {
        isVisible = true;
    }

}