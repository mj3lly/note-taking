package com.ota.notetaking.service;


import com.ota.notetaking.entity.Note;
import com.ota.notetaking.repository.NoteTakingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteTakingServiceImpl implements NoteTakingService {

    private final NoteTakingRepository noteTakingRepository;

    public NoteTakingServiceImpl(NoteTakingRepository noteTakingRepository) {
        this.noteTakingRepository = noteTakingRepository;
    }

    @Override
    public Note retrieveNoteById(Integer id) {
        return this.noteTakingRepository.retrieveNoteById(id);
    }

    @Override
    public Note createNote(Note note) {
        return this.noteTakingRepository.createNote(note);
    }

    @Override
    public Note updateNote(Note note) {
        return this.noteTakingRepository.updateNote(note);
    }

    @Override
    public void deleteNode(int id) {
        this.noteTakingRepository.deleteNode(id);
    }

    @Override
    public List<Note> retrieveAllNotes() {
        return this.noteTakingRepository.retrieveAllNotes();
    }
}
