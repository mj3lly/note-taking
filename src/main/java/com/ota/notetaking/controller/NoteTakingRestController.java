package com.ota.notetaking.controller;

import com.ota.notetaking.entity.Note;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NoteTakingRestController {

    ResponseEntity<Note> retrieveNoteById(Integer id);

    ResponseEntity<Note> createNote(Note note);

    ResponseEntity<Note> updateNote(Note note);

    ResponseEntity<Object> deleteNode(int id);

    ResponseEntity<List<Note>> retrieveAllNotes();

}
