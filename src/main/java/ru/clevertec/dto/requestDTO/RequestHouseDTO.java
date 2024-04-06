package ru.clevertec.dto.requestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.clevertec.entity.Person;
import ru.clevertec.util.ConstFormatDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestHouseDTO {

    private String area;
    private String country;
    private String city;
    private String street;
    private Long number;

    @Schema(description = "Дата создания House.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConstFormatDate.FORMAT)
    private LocalDateTime createDate;

    @Builder.Default
    @Schema(description = "Список владельцев House.")
    private List<Person> ownerList = new ArrayList<>();

    @Builder.Default
    @Schema(description = "Список жильцов House.")
    private List<Person> residentsList = new ArrayList<>();
}
