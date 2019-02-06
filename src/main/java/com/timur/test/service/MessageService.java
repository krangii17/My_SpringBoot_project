package com.timur.test.service;

import com.timur.test.domain.Message;
import com.timur.test.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    MessageRepo messageRepo;

    public boolean isExistMessageInDb(Long id) {
        return messageRepo.existsById(id);
    }

    public void saveMessage(Message message) {
        messageRepo.save(message);
    }

    public void changeMessage(Message message, String text) {
        message.setText(text);
        messageRepo.save(message);
    }

    public Optional<Message> getMessage(Long id) {
        Optional<Message> message = messageRepo.findById(id);
        return message;
    }

    public void deleteMessage(Long id) {
        messageRepo.deleteById(id);
    }
}
