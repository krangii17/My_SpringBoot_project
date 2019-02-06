package com.timur.test.controller;

import com.timur.test.domain.Message;
import com.timur.test.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/message")
@Api(value = "Message Controller", description = "Controller for message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @ApiOperation(value = "Send message")
    @PostMapping("/sendMessage")
    public HttpEntity<?> addMessage(Message message) {
        messageService.saveMessage(message);
        return ResponseEntity.ok("Message has been sent");
    }

    @ApiOperation(value = "Delete message by id")
    @DeleteMapping("/deleteMessage")
    public HttpEntity<?> deleteMessage(@RequestParam(required = true) Long id) {
        boolean isExist = messageService.isExistMessageInDb(id);
        if (isExist) {
            messageService.deleteMessage(id);
            return ResponseEntity.ok("Message has been deleted successfully");
        }
        return ResponseEntity.EMPTY;
    }

    @ApiOperation(value = "Change message")
    @PutMapping("/changeMessage")
    public HttpEntity<?> changeMessage(@RequestParam(required = true) Long id,
                                       @RequestParam(required = true) String message) {
        boolean isExist = messageService.isExistMessageInDb(id);
        if (!isExist) {
            return ResponseEntity.EMPTY;
        }
        Optional<Message> optionalMessage = messageService.getMessage(id);
        messageService.changeMessage(optionalMessage.get(), message);
        return ResponseEntity.ok("Message has been changed successfully");
    }
}
