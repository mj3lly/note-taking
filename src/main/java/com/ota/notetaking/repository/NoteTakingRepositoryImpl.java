package com.ota.notetaking.repository;

import com.ota.notetaking.entity.Note;
import com.ota.notetaking.exception.ExistingResourceException;
import com.ota.notetaking.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import static com.ota.notetaking.service.NoteFileWriter.saveToFile;

@Slf4j
@Repository
public class NoteTakingRepositoryImpl implements NoteTakingRepository {

    private BiFunction<Integer, List<Note>, Optional<Note>> getNoteFunction = (id, noteList) -> noteList
                .stream()
                .filter(note -> note.getId() == id)
            .findFirst();

    private final List<Note> noteList;
    private final String noteTakingStoragePath;

    public NoteTakingRepositoryImpl(List<Note> noteList, String noteTakingStoragePath) {
        this.noteList = noteList;
        this.noteTakingStoragePath = noteTakingStoragePath;
    }

    @Override
    public Note retrieveNoteById(Integer id) {
        return this.getNoteFunction.apply(id, this.noteList)
            .orElseThrow(() -> new ResourceNotFoundException("Note Not Found for id: " + id));
    }

    @Override
    public Note createNote(Note note) {
        Optional<Note> matchedId = this.getNoteFunction.apply(note.getId(), this.noteList);
        matchedId.ifPresent(n
                -> {
            throw new ExistingResourceException("Existing Note for id: " + n.getId() + ", title: " + n.getTitle());
        });

        note.setId(Note.getNewId(this.noteList, note.getId()));
        this.noteList.add(note);

        saveToFile(this.noteList, this.noteTakingStoragePath);
        return note;
    }

    @Override
    public Note updateNote(Note note) {
        Note existingNote = this.getNoteFunction.apply(note.getId(), this.noteList)
            .orElseThrow(()
                    -> new ResourceNotFoundException("Note Not Found for id: " + note.getId() +
                                                     ", title: " + note.getTitle()));

        existingNote.setTitle(note.getTitle());
        existingNote.setBody(note.getBody());

        saveToFile(this.noteList, this.noteTakingStoragePath);
        return existingNote;
    }

    @Override
    public void deleteNode(int id) {
        this.getNoteFunction.apply(id, this.noteList)
            .orElseThrow(() -> new ResourceNotFoundException("Note Not Found for id: " + id));

        this.noteList.removeIf(n -> n.getId() == id);
        saveToFile(this.noteList, this.noteTakingStoragePath);
    }

    @Override
    public List<Note> retrieveAllNotes() {
        return this.noteList;
    }

}
