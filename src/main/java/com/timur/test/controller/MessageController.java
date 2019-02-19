package com.timur.test.controller;

import com.timur.test.domain.Message;
import com.timur.test.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/message")
@Api(value = "Message Controller", description = "Controller for message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @ApiOperation(value = "Get all messages")
    @GetMapping("/getMessages")
    public ResponseEntity<?> getMessages(){
        return Optional
                .ofNullable(messageService.getMessages())
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Send message")
    @PostMapping("/sendMessage")
    public ResponseEntity<?> addMessage(@RequestBody Message message) {
        return Optional
                .ofNullable(messageService.saveMessage(message))
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Delete message by id")
    @DeleteMapping("/deleteMessage")
    public ResponseEntity<?> deleteMessage(@RequestBody Long id) {
        boolean isExist = messageService.isExistMessageInDb(id);
        if (isExist) {
            messageService.deleteMessage(id);
            return ResponseEntity.ok("Message has been deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Change message")
    @PutMapping("/changeMessage")
    public ResponseEntity<?> changeMessage(@RequestBody Long id,
                                           @RequestBody String message) {
        boolean isExist = messageService.isExistMessageInDb(id);
        if (!isExist) {
            return ResponseEntity.notFound().build();
        }
        Optional<Message> optionalMessage = messageService.getMessage(id);
        messageService.changeMessage(optionalMessage.get(), message);
        return ResponseEntity.ok("Message has been changed successfully");
    }
}
