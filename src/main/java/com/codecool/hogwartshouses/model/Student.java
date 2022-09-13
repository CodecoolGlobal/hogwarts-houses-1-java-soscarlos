package com.codecool.hogwartshouses.model;

import com.codecool.hogwartshouses.model.types.HouseType;
import com.codecool.hogwartshouses.model.types.PetType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @NonNull
    private long id;
    @NonNull
    private String name;
    @NonNull
    private HouseType house;
    @NonNull
    private PetType type;
}
