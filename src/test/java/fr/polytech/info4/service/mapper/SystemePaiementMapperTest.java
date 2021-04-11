package fr.polytech.info4.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SystemePaiementMapperTest {

    private SystemePaiementMapper systemePaiementMapper;

    @BeforeEach
    public void setUp() {
        systemePaiementMapper = new SystemePaiementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(systemePaiementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(systemePaiementMapper.fromId(null)).isNull();
    }
}
