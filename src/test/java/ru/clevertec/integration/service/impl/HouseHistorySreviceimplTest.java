package ru.clevertec.integration.service.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import ru.clevertec.PostgreSQLContainerInitializer;
import ru.clevertec.dto.responseDTO.ResponseHouseDTO;
import ru.clevertec.entity.type.PersonType;
import ru.clevertec.service.HouseHistorySrevice;
import ru.clevertec.util.TestData;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class HouseHistorySreviceimplTest extends PostgreSQLContainerInitializer {

    private final HouseHistorySrevice houseHistorySrevice;
    private UUID uuidPerson;

    @BeforeEach
    void setUp() {
        uuidPerson = TestData.UUID_PERSON;
    }

    @Test
    void findAllHousesTenantOrOwner() {
        PersonType personType = PersonType.TENANT;

        List<ResponseHouseDTO> allHousesTenantOrOwner = houseHistorySrevice.findAllHousesTenantOrOwner(uuidPerson,
                                                                                                       personType);
        assertThat(allHousesTenantOrOwner).isNotEmpty();
    }
}