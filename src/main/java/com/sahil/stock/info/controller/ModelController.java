package com.sahil.stock.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahil.stock.info.model.Model;
import com.sahil.stock.info.service.ModelService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/model")
@Slf4j
public class ModelController {
    @Autowired
    private ModelService modelService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String home() {
        log.info("Received request to GET /home.");
        return String.format(
                "Microservice Template%n%n" +
                        "Welcome to the model endpoint, you can make the following requests:%n" +
                        "- POST /new-model%n" +
                        "- GET /get-model%n" +
                        "- DELETE /delete-model%n");
    }

    @PostMapping(value = "/new-model", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> createModel(@RequestBody String name) {
        log.info("Received request to POST /new-model with argument: " + name.trim());
        Model model = modelService.createModel(name.trim());
        if (model != null) {
            return ResponseEntity.ok(model);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping(value = "/get-model", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> getModel(@RequestBody String name) {
        log.info("Received request to GET /get-model with argument: " + name.trim());
        Model model = modelService.getModel(name.trim());
        if (model != null) {
            return ResponseEntity.ok(model);            
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping(value = "/delete-model", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteModel(@RequestBody String name) {
        log.info("Received request to DELETE /delete-model with argument: " + name.trim());
        boolean response = modelService.deleteModel(name.trim());
        if (response) {
            return ResponseEntity.ok("Model deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
