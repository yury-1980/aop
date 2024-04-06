package ru.clevertec.integration.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import ru.clevertec.PostgreSQLContainerInitializer;
import ru.clevertec.dto.requestDTO.RequestPersonDTO;
import ru.clevertec.dto.responseDTO.ResponsePersonDTO;
import ru.clevertec.service.PersonService;
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
class PersonServiceImplTest extends PostgreSQLContainerInitializer {

    private final PersonService personService;
    private RequestPersonDTO requestPersonDTO;
    private UUID uuidPerson;
    private UUID uuidHouse;

    @BeforeEach
    void setUp() {
        uuidHouse = TestData.UUID_HOUSE;
        uuidPerson = TestData.UUID_PERSON;
        requestPersonDTO = TestData.getRequestPersonDTO();
    }


    @Test
    void findByAll() {
        int pageNumber = 0;
        int pageSize = 1;

        List<ResponsePersonDTO> responsePersonDTOs = personService.findByAll(pageNumber, pageSize);

        assertThat(responsePersonDTOs).isNotEmpty();
    }

    @Test
    @SneakyThrows
    void findByUUID() {
        ResponsePersonDTO expected = TestData.getResponsePersonDTO();
        ResponsePersonDTO actual = personService.findByUUID(uuidPerson);

        assertEquals(expected.getUuid(), actual.getUuid());
    }

    @Test
    void getHousesByOwner() {

        assertThatCode(() -> personService.getHousesByOwner(uuidPerson))
                .doesNotThrowAnyException();
    }

    @Test
    void create() {

        assertThatCode(() -> personService.create(requestPersonDTO, TestData.UUID_HOUSE))
                .doesNotThrowAnyException();
    }

    @Test
    void update() {
        UUID expected = uuidPerson;

        UUID actual = personService.update(requestPersonDTO, uuidPerson);

        assertEquals(expected, actual);
    }

    @Test
    void updatePatch() {

        assertThatCode(() -> personService.updatePatch(requestPersonDTO, uuidPerson, uuidHouse))
                .doesNotThrowAnyException();
    }

    @Test
    void delete() {

        assertDoesNotThrow(() -> personService.delete(uuidPerson));
    }
}