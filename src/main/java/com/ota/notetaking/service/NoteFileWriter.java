package com.ota.notetaking.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.FileWriter;

@Slf4j
public class NoteFileWriter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private NoteFileWriter() {}

    public static void saveToFile(Object object, String filePath) {
        try {
            String objectString = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(objectString);
            writer.close();
        } catch (Exception exception) {
            log.info("Exception when writing to file!", exception);
        }
    }

}
