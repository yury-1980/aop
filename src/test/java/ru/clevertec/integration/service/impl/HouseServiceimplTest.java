package ru.clevertec.integration.service.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import ru.clevertec.PostgreSQLContainerInitializer;
import ru.clevertec.dto.requestDTO.RequestHouseDTO;
import ru.clevertec.dto.responseDTO.ResponseHouseDTO;
import ru.clevertec.dto.responseDTO.ResponsePersonDTO;
import ru.clevertec.service.HouseService;
import ru.clevertec.util.TestData;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class HouseServiceimplTest extends PostgreSQLContainerInitializer {

    private final HouseService houseService;
    private RequestHouseDTO requestHouseDTO;
    private UUID uuidPersonTwo;
    private UUID uuidHouse;

    @BeforeEach
    void setUp() {
        uuidHouse = TestData.UUID_HOUSE;
        requestHouseDTO = TestData.getRequestHouseDTO();
        uuidPersonTwo = TestData.UUID_PERSON_TWO;
    }

    @Test
    void findByAll() {
        int pageNumber = 0;
        int pageSize = 1;

        List<ResponseHouseDTO> responseHouseDTOs = houseService.findByAll(pageNumber, pageSize);

        assertThat(responseHouseDTOs).isNotEmpty();
    }

    @Test
    void findByUUID() {
        ResponseHouseDTO expected = TestData.getResponseHouseDTO();
        ResponseHouseDTO actual = houseService.findByUUID(uuidHouse);

        assertEquals(expected.getUuid(), actual.getUuid());
    }

    @Test
    void getPersonsByHouse() {
        List<ResponsePersonDTO> personsByHouse = houseService.getPersonsByHouse(uuidHouse);

        assertThat(personsByHouse).isNotEmpty();
    }

    @Test
    void create() {
        assertThatCode(() -> houseService.create(requestHouseDTO))
                .doesNotThrowAnyException();
    }

    @Test
    void addOwnerInHouse() {
        houseService.addOwnerInHouse(uuidHouse, uuidPersonTwo);
    }

    @Test
    void update() {
        UUID expected = uuidHouse;

        UUID actual = houseService.update(requestHouseDTO, uuidHouse);

        assertEquals(expected, actual);
    }

    @Test
    void updatePatch() {
        assertThatCode(() -> houseService.updatePatch(requestHouseDTO, uuidHouse, uuidPersonTwo))
                .doesNotThrowAnyException();
    }

    @Test
    void delete() {
        assertDoesNotThrow(() -> houseService.delete(uuidHouse));
    }
}