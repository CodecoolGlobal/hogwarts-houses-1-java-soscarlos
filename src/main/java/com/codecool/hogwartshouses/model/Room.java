package com.codecool.hogwartshouses.model;

import com.codecool.hogwartshouses.model.types.HouseType;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    @NonNull
    private long id;
    @NonNull
    private String name;
    @NonNull
    private HouseType house;
    @NonNull
    private Set<Student> roommates;
}
