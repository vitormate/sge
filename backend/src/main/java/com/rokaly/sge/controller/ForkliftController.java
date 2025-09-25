package com.rokaly.sge.controller;

import com.rokaly.sge.dto.ForkliftDTO;
import com.rokaly.sge.model.Forklift;
import com.rokaly.sge.repository.ForkliftRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("forklifts")
public class ForkliftController {

    @Autowired
    private ForkliftRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ForkliftDTO> create(@RequestBody ForkliftDTO data, UriComponentsBuilder uriBuilder) {
        Forklift forklift = new Forklift(data);
        repository.save(forklift);

        var uri = uriBuilder.path("/forklifts/{id}").buildAndExpand(forklift.getId()).toUri();
        ForkliftDTO dto = new ForkliftDTO(data);
        return ResponseEntity.created(uri).body(dto);
    }
}
