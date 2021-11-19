package de.fraunhofer.dataspaces.iese.systemadapter.rest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import static de.fraunhofer.dataspaces.iese.systemadapter.configuration.security.role.ApplicationUserRole.*;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.fraunhofer.dataspaces.iese.systemadapter.configuration.database.PersistanceMysqlConfiguration;
import de.fraunhofer.dataspaces.iese.systemadapter.data.FraunhoferDataSpace;
import de.fraunhofer.dataspaces.iese.systemadapter.hashing.BCrypt;
import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Payload;
import de.fraunhofer.dataspaces.iese.systemadapter.service.mysql.PayloadMysqlService;

@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@SpringBootTest(classes = {
		PersistanceMysqlConfiguration.class, 
		PayloadMysqlService.class, 
		PayloadMysqlController.class
		}
)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(value = MethodName.class)
@EnableWebMvc
@Import(BCrypt.class)
public class PayloadMysqlControllerTest {
	
	@Autowired
	private PayloadMysqlService payloadMysqlService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private PayloadMysqlController payloadMysqlController;
	
	private Payload payload;
	
	private final UUID HEADER_ID = UUID.randomUUID();
	
	private final String DATA;
	
	private final String USERNAME = "arianajdari94@gmail.com";
	private final String PASSWORD = "changeme";
	private final String STUDENT_ROLE = STUDENT.name();
	private final String ADMIN_ROLE = ADMIN.name();
	private final String ADMINTRAINEE_ROLE = ADMINTRAINEE.name();
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders
				.standaloneSetup(payloadMysqlController)
				.apply(springSecurity())
				.build();
	}
	
	public PayloadMysqlControllerTest() throws JsonProcessingException {
	
		this.DATA = new ObjectMapper()
				.writeValueAsString(new FraunhoferDataSpace()
						.setName("Policy")
						.setType("Restriction")
						.setDuration("2 Days"));
	}
	
	@PostConstruct
	public void init() {
		payload = new Payload();
		
		payload.setHeaderId(HEADER_ID);
		payload.setData(DATA);
		
		payloadMysqlService.save(payload);
	}
	
	@Test
	public void A_checkMysqlPayloadsFindAllStudent() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/mysql/payloads/findAll").with(setUpMockUserStudent())
		).andExpect(MockMvcResultMatchers.status().isOk());	
	}
	
	@Test
	public void B_checkMysqlPayloadsFindAllAdmin() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/mysql/payloads/findAll").with(setUpMockUserAdmin())
		).andExpect(MockMvcResultMatchers.status().isOk());	
	}
	
	@Test
	public void C_checkMysqlPayloadsFindAllAdminTrainee() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/mysql/payloads/findAll").with(setUpMockUserAdminTrainee())
		).andExpect(MockMvcResultMatchers.status().isOk());	
	}
	
	@Test
	public void D_checkMysqlPayloadsFindStudent() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/mysql/payloads/" + payload.getId() + "").with(setUpMockUserStudent())
		).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void E_checkMysqlPayloadsFindAdmin() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/mysql/payloads/" + payload.getId() + "").with(setUpMockUserAdmin())
		).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void F_checkMysqlPayloadsFindAdminTrainee() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/mysql/payloads/" + payload.getId() + "").with(setUpMockUserAdminTrainee())
		).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	private UserRequestPostProcessor setUpMockUserStudent() {
		return user(USERNAME).password(passwordEncoder.encode(PASSWORD)).roles(STUDENT_ROLE);
	}
	
	private UserRequestPostProcessor setUpMockUserAdmin() {
		return user(USERNAME).password(passwordEncoder.encode(PASSWORD)).roles(ADMIN_ROLE);
	}
	
	private UserRequestPostProcessor setUpMockUserAdminTrainee() {
		return user(USERNAME).password(passwordEncoder.encode(PASSWORD)).roles(ADMINTRAINEE_ROLE);
	}
}
