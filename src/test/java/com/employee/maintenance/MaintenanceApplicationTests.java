package com.employee.maintenance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MaintenanceApplicationTests {

	@Autowired
	private JdbcTemplate template;

	@Test
	public void testDefaultSettings() {
		assertThat(this.template.queryForObject("SELECT COUNT(*) from STATION", Integer.class)).isEqualTo(1);
	}

}
