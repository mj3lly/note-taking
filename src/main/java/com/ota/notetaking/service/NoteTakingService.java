package com.ota.notetaking.service;

import com.ota.notetaking.entity.Note;

import java.util.List;

public interface NoteTakingService {

    Note retrieveNoteById(Integer id);

    Note createNote(Note note);

    Note updateNote(Note note);

    void deleteNode(int id);

    List<Note> retrieveAllNotes();

}
