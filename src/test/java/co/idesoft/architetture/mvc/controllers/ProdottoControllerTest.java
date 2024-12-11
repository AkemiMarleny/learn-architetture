// package co.idesoft.architetture.mvc.controllers;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.BDDMockito.given;
// import static
// org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import
// org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import com.fasterxml.jackson.databind.ObjectMapper;

// import co.idesoft.architetture.mvc.controllers.dto.CreareProdottoDto;
// import co.idesoft.architetture.mvc.controllers.dto.ProdottoCreatoDto;
// import co.idesoft.architetture.mvc.entities.Prodotto;
// import co.idesoft.architetture.mvc.repositories.ProdottoRepository;

// @AutoConfigureMockMvc(addFilters = false)
// @WebMvcTest(ProdottoController.class)
// class ProdottoControllerTest {

// @Autowired
// private MockMvc mockMvc;

// @Autowired
// private ObjectMapper objectMapper;

// @MockBean
// private ProdottoRepository prodottoRepository;

// @Test
// public void dovrebbeCreareIlProdotto() throws Exception {

// Prodotto expectedProdotto = new Prodotto();
// expectedProdotto.setProdottoId(1L);

// given(prodottoRepository.countByChecksum(any(String.class))).willReturn(0L);
// given(prodottoRepository.save(any(Prodotto.class))).willReturn(expectedProdotto);

// CreareProdottoDto prodottoDto = new CreareProdottoDto("Papaya", 1, "Papaya");
// ProdottoCreatoDto prodottoCreatoDto = new ProdottoCreatoDto(1L);
// mockMvc.perform(
// post("/api/prodotti")
// .contentType(MediaType.APPLICATION_JSON)
// .content(objectMapper.writeValueAsString(prodottoDto))
// .with(csrf()))
// .andExpect(status().isCreated())
// .andExpect(content().bytes(objectMapper.writeValueAsBytes(prodottoCreatoDto)));
// }

// @Test
// public void nonDovrebbeAccettareProdottoDuplicato() throws Exception {
// CreareProdottoDto prodottoDto = new CreareProdottoDto("Papaya", 1, "Papaya");
// mockMvc.perform(
// post("/api/prodotti")
// .contentType(MediaType.APPLICATION_JSON)
// .content(objectMapper.writeValueAsString(prodottoDto))
// .with(csrf()))
// .andExpect(status().isConflict())
// .andExpect(jsonPath("$").doesNotExist());
// }
// }