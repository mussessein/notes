package com.rabbitmq.springBootMQ;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    String name;
    String writer;

}
