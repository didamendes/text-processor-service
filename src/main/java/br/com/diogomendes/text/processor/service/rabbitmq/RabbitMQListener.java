package br.com.diogomendes.text.processor.service.rabbitmq;

import br.com.diogomendes.text.processor.service.api.model.PostData;
import br.com.diogomendes.text.processor.service.domain.service.TextProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static br.com.diogomendes.text.processor.service.rabbitmq.RabbitMQConfig.QUEUE_TEXT_PROCESSOR_SERVICE;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final TextProcessorService textProcessorService;

    @RabbitListener(queues = QUEUE_TEXT_PROCESSOR_SERVICE )
    @SneakyThrows
    public void handlePost(@Payload PostData postData) {
        Thread.sleep(Duration.ofSeconds(10));
        log.info("Received post: {}", postData);
        textProcessorService.process(postData);
    }

}
