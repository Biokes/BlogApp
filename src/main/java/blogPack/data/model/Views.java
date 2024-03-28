package blogPack.data.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Views{
    private LocalDateTime timeViewed;
    private User viewer;
}
