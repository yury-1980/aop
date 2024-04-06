package ru.clevertec.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.dto.requestDTO.RequestPersonDTO;
import ru.clevertec.dto.responseDTO.ResponseHouseDTO;
import ru.clevertec.dto.responseDTO.ResponsePersonDTO;
import ru.clevertec.service.PersonService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private final PersonService services;

    @GetMapping
    @Operation(summary = "Выбор всех Persons из заданной страницы.")
    public ResponseEntity<List<ResponsePersonDTO>> getAllPerson(@RequestParam(defaultValue = "0") int pageNumber,
                                                                @RequestParam(defaultValue = "15") int pageSize) {
        return ResponseEntity.ok(services.findByAll(pageNumber, pageSize));
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Выбор заданного Person, по его uuid.")
    public ResponseEntity<ResponsePersonDTO> getPerson(@PathVariable("uuid") UUID uuid) {

        return ResponseEntity.ok(services.findByUUID(uuid));
    }

    @GetMapping("/houses/{uuid}")
    @Operation(summary = "Получение всех текущих домов у заданного владельца.")
    public ResponseEntity<List<ResponseHouseDTO>> getHousesByOwner(@PathVariable("uuid") UUID personUuid) {

        return ResponseEntity.ok(services.getHousesByOwner(personUuid));
    }

    @PostMapping("/{uuid}")
    @Operation(summary = "Создание Person и заселение в House.")
    public ResponseEntity<UUID> createPerson(@RequestBody RequestPersonDTO requestPersonDTO,
                                             @PathVariable("uuid") UUID uuidHouse) {

        return ResponseEntity.ok(services.create(requestPersonDTO, uuidHouse));
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Обновление Person целеком.")
    public ResponseEntity<UUID> updatePerson(@RequestBody RequestPersonDTO requestPersonDTO,
                                             @PathVariable("uuid") UUID uuid) {

        return ResponseEntity.ok(services.update(requestPersonDTO, uuid));
    }

    @PatchMapping
    @Operation(summary = "Обновление Person и смена жительства.")
    public ResponseEntity<ResponsePersonDTO> updatePatchPerson(@RequestBody RequestPersonDTO requestPersonDTO,
                                                               @RequestParam UUID person, @RequestParam UUID house) {

        return ResponseEntity.ok(services.updatePatch(requestPersonDTO, person, house));
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Удаление Person по его UUID.")
    public ResponseEntity<Void> deletePerson(@PathVariable("uuid") UUID uuid) {
        services.delete(uuid);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
