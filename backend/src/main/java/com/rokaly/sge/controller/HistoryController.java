package com.rokaly.sge.controller;

import com.rokaly.sge.dto.GetForkliftDTO;
import com.rokaly.sge.dto.GetHistoryDTO;
import com.rokaly.sge.dto.HistoryDTO;
import com.rokaly.sge.model.Forklift;
import com.rokaly.sge.model.History;
import com.rokaly.sge.repository.ForkliftRepository;
import com.rokaly.sge.repository.HistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<Page<GetHistoryDTO>> read(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagination) {
        Page<GetHistoryDTO> page = repository.findAll(pagination).map(GetHistoryDTO::new);
        return ResponseEntity.ok(page);
    }
}
