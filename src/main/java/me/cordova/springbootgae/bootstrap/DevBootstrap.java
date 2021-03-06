package me.cordova.springbootgae.bootstrap;

import me.cordova.springbootgae.model.Message;
import me.cordova.springbootgae.repositories.MessageRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private MessageRepository messageRepository;

    public DevBootstrap(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        for (int i=1; i<11; i++){
            Message message = new Message();
            message.setTime(LocalDateTime.now());
            message.setPayload(String.format("Test message # %d", i));
            messageRepository.save(message);
        }
    }
}