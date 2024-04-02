package com.ota.notetaking.controller;

import com.ota.notetaking.entity.Note;
import com.ota.notetaking.exception.BodyRequestException;
import com.ota.notetaking.service.NoteTakingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Predicate;

import static org.apache.commons.lang3.StringUtils.*;

@RestController
@RequestMapping("/notes")
public class NoteTakingRestControllerImpl implements NoteTakingRestController {

    private final Predicate<Note> noteValidationFunction = note -> isBlank(note.getTitle()) || isBlank(note.getBody());
    private final NoteTakingService noteTakingService;

    public NoteTakingRestControllerImpl(NoteTakingService noteTakingService) {
        this.noteTakingService = noteTakingService;
    }

    @Override
    @GetMapping("/{noteId}")
    public ResponseEntity<Note> retrieveNoteById(@PathVariable("noteId") Integer id) {
        return ResponseEntity.ok(this.noteTakingService.retrieveNoteById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {

        if (noteValidationFunction.test(note)) {
            throw new BodyRequestException("Title and body are required!");
        }

        return ResponseEntity.ok(this.noteTakingService.createNote(note));
    }

    @Override
    @PutMapping
    public ResponseEntity<Note> updateNote(@RequestBody Note note) {

        if (noteValidationFunction.test(note)) {
            throw new BodyRequestException("Title and body are required!");
        }

        return ResponseEntity.ok(this.noteTakingService.updateNote(note));
    }

    @Override
    @DeleteMapping("/{noteId}")
    public ResponseEntity<Object> deleteNode(@PathVariable("noteId") int id) {
        this.noteTakingService.deleteNode(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Override
    public ResponseEntity<List<Note>> retrieveAllNotes() {
        return ResponseEntity.ok(noteTakingService.retrieveAllNotes());
    }

}
