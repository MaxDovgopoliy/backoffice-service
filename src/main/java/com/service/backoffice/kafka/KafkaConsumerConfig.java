package com.service.backoffice.kafka;

import com.service.backoffice.dto.OrderDtoFromTripService;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public ConsumerFactory<String, OrderDtoFromTripService> consumerFactory() {
        JsonDeserializer<OrderDtoFromTripService> deserializer =
                new JsonDeserializer<>(OrderDtoFromTripService.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                deserializer);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderDtoFromTripService>
            orderKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, OrderDtoFromTripService> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}