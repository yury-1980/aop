package ru.clevertec.integration.service.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import ru.clevertec.dto.responseDTO.ResponsePersonDTO;
import ru.clevertec.entity.type.PersonType;
import ru.clevertec.service.HouseHistoryPersonSrevice;
import ru.clevertec.util.TestData;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class HouseHistoryPersonSreviceimplTest {

    private final HouseHistoryPersonSrevice houseHistoryPersonSrevice;

    @Test
    void findAllPersonsTenantsOrPersonsOwnersInHouse() {
        UUID uuidHouse = TestData.UUID_HOUSE;
        PersonType personType = PersonType.TENANT;

        List<ResponsePersonDTO> allPersonsTenantsOrPersonsOwnersInHouse =
                houseHistoryPersonSrevice.findAllPersonsTenantsOrPersonsOwnersInHouse(uuidHouse, personType);

        assertThat(allPersonsTenantsOrPersonsOwnersInHouse).isNotEmpty();
    }
}