package ru.clevertec.dto.requestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.clevertec.entity.House;
import ru.clevertec.entity.type.Sex;
import ru.clevertec.util.ConstFormatDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestPersonDTO {

    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String passportSeries;
    private Long passportNumber;

    @Schema(description = "Дата создания Person.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConstFormatDate.FORMAT)
    private LocalDateTime createDate;

    @Schema(description = "Дата обновления Person.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConstFormatDate.FORMAT)
    private LocalDateTime updateDate;

    @Builder.Default
    @Schema(description = "Спиок домов во владении Person.")
    private List<House> housesOwner = new ArrayList<>();

    @Builder.Default
    @Schema(description = "House в котором проживает Person.")
    private House house = new House();
}
