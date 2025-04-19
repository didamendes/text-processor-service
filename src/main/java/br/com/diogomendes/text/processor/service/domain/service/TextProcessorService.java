package br.com.diogomendes.text.processor.service.domain.service;

import br.com.diogomendes.text.processor.service.api.model.PostData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static br.com.diogomendes.text.processor.service.rabbitmq.RabbitMQConfig.FANOUT_EXCHANGE_NAME;

@Service
@RequiredArgsConstructor
public class TextProcessorService {

    private final RabbitTemplate rabbitTemplate;

    public void process(PostData postData) {
        int length = postData.getBody().length();
        BigDecimal calculateValue = calculateValue(length);

        postData.setWordCount(length);
        postData.setCalculatedValue(calculateValue.doubleValue());

        rabbitTemplate.convertAndSend(FANOUT_EXCHANGE_NAME, "processorPost", postData);
    }

    private BigDecimal calculateValue(int wordCount) {
        return BigDecimal.valueOf(wordCount)
                .multiply(BigDecimal.valueOf(0.1))
                .setScale(2, RoundingMode.HALF_UP);
    }

}
