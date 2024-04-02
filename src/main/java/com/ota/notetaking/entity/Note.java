package com.ota.notetaking.entity;

import lombok.*;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    private Integer id;
    private String title;
    private String body;

    public static int getNewId(List<Note> noteTakingStorage, Integer currentId) {
        Integer maxId = noteTakingStorage
                .stream()
                .map(Note::getId)
                .max(Integer::compare)
            .orElseGet(() -> 0);

        return Optional.ofNullable(currentId).isPresent() ? currentId : ++maxId;
    }

}
