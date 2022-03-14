package ordilov.lissn.member.adapter.in.web;

import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ordilov.lissn.common.WithMockCustomUser;
import ordilov.lissn.member.application.service.UpdateMemberService;
import ordilov.lissn.security.config.SecurityConfig;
import ordilov.lissn.security.filter.TokenAuthenticationFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = UpdateMemberController.class,
    excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        TokenAuthenticationFilter.class,
        SecurityConfig.class})})
public class UpdateMemberControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UpdateMemberService updateMemberService;

  @Test
  @WithMockCustomUser
  void updateName() throws Exception {
    mockMvc.perform(patch("/members/{memberId}/name", 1)
            .with(csrf())
            .header("Authorization", "Bearer " + "TOKEN")
            .param("name", "new name"))
        .andExpect(status().isOk());

    then(updateMemberService).should()
        .updateName(1L, "new name");
  }
}
