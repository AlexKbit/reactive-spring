package com.alexkbit.intro.reactivespring.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@DataMongoTest
@RunWith(SpringRunner.class)
public class ServerLauncherTests {

	@Test
	public void contextLoads() {
	}

}
