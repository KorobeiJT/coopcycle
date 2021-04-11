package fr.polytech.info4.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.polytech.info4.web.rest.TestUtil;

public class CooperativeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CooperativeDTO.class);
        CooperativeDTO cooperativeDTO1 = new CooperativeDTO();
        cooperativeDTO1.setId(1L);
        CooperativeDTO cooperativeDTO2 = new CooperativeDTO();
        assertThat(cooperativeDTO1).isNotEqualTo(cooperativeDTO2);
        cooperativeDTO2.setId(cooperativeDTO1.getId());
        assertThat(cooperativeDTO1).isEqualTo(cooperativeDTO2);
        cooperativeDTO2.setId(2L);
        assertThat(cooperativeDTO1).isNotEqualTo(cooperativeDTO2);
        cooperativeDTO1.setId(null);
        assertThat(cooperativeDTO1).isNotEqualTo(cooperativeDTO2);
    }
}
