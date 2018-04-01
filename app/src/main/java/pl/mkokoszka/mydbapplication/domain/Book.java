package pl.mkokoszka.mydbapplication.domain;

import java.io.Serializable;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Book implements Serializable {

    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String title;
    private String author;
}
