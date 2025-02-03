package com.example.testjdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

@Getter
@AllArgsConstructor
public class Memo {

    @Setter
    private Long id;
    private String content;

    public Memo(String content){
        this.content = content;

    }
}
