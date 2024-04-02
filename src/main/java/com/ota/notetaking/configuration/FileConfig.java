package com.ota.notetaking.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ota.notetaking.entity.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.ota.notetaking.service.NoteFileWriter.saveToFile;

@Slf4j
@Configuration
public class FileConfig {

    private static final String NOTE_FILE_NAME =  "/note-taking.json";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Bean
    String storage() throws IOException {
        return Files.createDirectories(Path.of("/storage")).toString();
    }

    @Bean
    boolean isNoteStorageExists(String storage) {
        return Path.of(storage + NOTE_FILE_NAME).toFile().exists();
    }

    @Bean
    @DependsOn({ "storage", "isNoteStorageExists" })
    List<Note> noteList(@Value("classpath:storage" + NOTE_FILE_NAME) Resource noteTakingResource, String storage,
                        boolean isNoteStorageExists) throws IOException {
        if (isNoteStorageExists) {
            return OBJECT_MAPPER.readValue(Files.newBufferedReader(Path.of(storage + NOTE_FILE_NAME)),
                    new TypeReference<List<Note>>() {});
        }

        return OBJECT_MAPPER.readValue(noteTakingResource.getInputStream(), new TypeReference<List<Note>>() {});
    }

    @Bean
    @DependsOn({ "storage", "noteList" })
    String noteTakingStoragePath(String storage, List<Note> noteList) {
        String filePath = storage + NOTE_FILE_NAME;
        saveToFile(noteList, filePath);

        return filePath;
    }

}
