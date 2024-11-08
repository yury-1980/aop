package ru.clevertec.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.dto.requestDTO.RequestHouseDTO;
import ru.clevertec.dto.responseDTO.ResponseHouseDTO;
import ru.clevertec.dto.responseDTO.ResponsePersonDTO;
import ru.clevertec.service.HouseService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/houses")
public class HouseController {

    private final HouseService services;

    @GetMapping
    @Operation(summary = "Выбор всех House из заданной страницы.")
    public ResponseEntity<List<ResponseHouseDTO>> getAllHouse(@RequestParam(defaultValue = "0") int pageNumber,
                                                              @RequestParam(defaultValue = "15") int pageSize) {
        return ResponseEntity.ok(services.findByAll(pageNumber, pageSize));
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Выбор заданного House, по его uuid.")
    public ResponseEntity<ResponseHouseDTO> getHouse(@PathVariable("uuid") UUID uuid) {

        return ResponseEntity.ok(services.findByUUID(uuid));
    }

    @GetMapping("/persons/{uuid}")
    @Operation(summary = "Выбирает всех жильцов House, по uuid House.")
    public ResponseEntity<List<ResponsePersonDTO>> getHousePersons(@PathVariable("uuid") UUID uuid) {

        return ResponseEntity.ok(services.getPersonsByHouse(uuid));
    }

    @PostMapping
    @Operation(summary = "Создание House.")
    public ResponseEntity<UUID> createHouse(@RequestBody RequestHouseDTO requestHouseDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(services.create(requestHouseDTO));
    }

    @PostMapping("/owners")
    @Operation(summary = "Добавление владельца (Owner) House.")
    public ResponseEntity<Void> addHouseAndOwner(@RequestParam UUID house, @RequestParam UUID person) {
        services.addOwnerInHouse(house, person);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Обновление House целеком.")
    public ResponseEntity<UUID> updateHouse(@RequestBody RequestHouseDTO requestHouseDTO,
                                            @PathVariable("uuid") UUID uuid) {

        return ResponseEntity.ok(services.update(requestHouseDTO, uuid));
    }

    @PatchMapping
    @Operation(summary = "Обновление всех характеристик House и добавление владельца.")
    public ResponseEntity<ResponseHouseDTO> updatePatcHouse(@RequestBody RequestHouseDTO requestHouseDTO,
                                                            @RequestParam UUID house, @RequestParam UUID person) {

        return ResponseEntity.ok(services.updatePatch(requestHouseDTO, house, person));
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Удаление House по UUID.")
    public ResponseEntity<Void> deleteHouse(@PathVariable("uuid") UUID uuid) {
        services.delete(uuid);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
