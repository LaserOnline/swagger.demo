package com.spring.boot.swagger.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.swagger.demo.dto.MessageRequest;
import com.spring.boot.swagger.demo.dto.MessageResponse;
import com.spring.boot.swagger.demo.dto.MessageUpdate;
import com.spring.boot.swagger.demo.service.DB;
import com.spring.boot.swagger.demo.service.Entity;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;






@RestController
public class AppController {

    @Autowired
    private DB databases;

    @GetMapping("/hello")
    public ResponseEntity<MessageResponse> getHello() {
        MessageResponse response = new MessageResponse();
        response.setMessage("HelloWorld");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/request")
    @Operation(summary = "Create Data", description = "response data fromm requesrt")
    public ResponseEntity<MessageResponse> createMessage(@RequestBody @Valid MessageRequest request) {
        MessageResponse response = new MessageResponse();
        response.setMessage(request.getMessage());
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addData (@RequestBody @Valid MessageRequest request) {
    Entity added = databases.add(request.getMessage());

    MessageResponse response = new MessageResponse();

    response.setMessage("Create Data successfully With ID "+ added.getId());
    
    return ResponseEntity.ok(response);

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<Entity> allData = databases.getAll();

        if (allData.isEmpty()) {
            MessageResponse response = new MessageResponse();
            response.setMessage("data is empty");
            return ResponseEntity.ok(response); // ส่ง message แทน
        }

        return ResponseEntity.ok(allData); // ส่ง list ปกติ
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MessageResponse> updateMessage(
        @PathVariable int id,
        @RequestBody @Valid MessageUpdate updateRequest) {

        Entity updated = databases.updateById(id, updateRequest.getMessage());

        if (updated == null) {
            MessageResponse notFound = new MessageResponse();
            notFound.setMessage("Entity with ID " + id + " not found");
            return ResponseEntity.status(404).body(notFound);
        }

        MessageResponse response = new MessageResponse();
        response.setMessage("Updated entity with ID " + updated.getId() + " successfully");
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteMessage(@PathVariable int id) {
        boolean deleted = databases.deleteById(id);

        MessageResponse response = new MessageResponse();
        if (!deleted) {
            response.setMessage("Entity with ID " + id + " not found");
            return ResponseEntity.status(404).body(response);
        }

        response.setMessage("Deleted entity with ID " + id + " successfully");
        return ResponseEntity.ok(response);
    }
}
