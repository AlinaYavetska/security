package edu.yavetska.security.book;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Objects;

/*
@author   Admin
@project   security
@class  Book
@version  1.0.0
@since 21.03.2025 - 16.10
*/
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Book {
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        return Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Id
    private String id;
    private String name;
    private String description;

    public Book(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
