package com.rokaly.sge.controller;

import com.rokaly.sge.dto.HistoryDTO;
import com.rokaly.sge.model.Forklift;
import com.rokaly.sge.model.History;
import com.rokaly.sge.repository.ForkliftRepository;
import com.rokaly.sge.repository.HistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("history")
public class HistoryController {

    @Autowired
    private HistoryRepository repository;

    @Autowired
    private ForkliftRepository repositoryForklift;

    @PostMapping
    @Transactional
    public ResponseEntity<HistoryDTO> create(@RequestBody HistoryDTO data, UriComponentsBuilder uriBuilder) {
        Forklift forklift = repositoryForklift.getReferenceById(data.idForklift());
        History history = new History(data, forklift);
        repository.save(history);

        var uri = uriBuilder.path("/forklifts/{id}").buildAndExpand(forklift.getId()).toUri();
        HistoryDTO dto = new HistoryDTO(data);
        return ResponseEntity.created(uri).body(dto);
    }
}
