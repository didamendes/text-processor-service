package br.com.diogomendes.text.processor.service.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostData {
    private UUID id;
    private String title;
    private String body;
    private String author;
    private Integer wordCount;
    private Double calculatedValue;
}
