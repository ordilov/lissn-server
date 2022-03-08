package ordilov.lissn.member.adapter.in.web;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ordilov.lissn.common.WithMockCustomUser;
import ordilov.lissn.member.application.service.GetMemberService;
import ordilov.lissn.security.config.SecurityConfig;
import ordilov.lissn.security.filter.TokenAuthenticationFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = GetMemberController.class,
    excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        TokenAuthenticationFilter.class,
        SecurityConfig.class})})
class GetMemberControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GetMemberService getMemberService;

  @Test
  @WithMockCustomUser
  void getCurrentMember() throws Exception {
    mockMvc.perform(get("/members/me")
            .header("Authorization", "Bearer " + "TOKEN"))
        .andExpect(status().isOk());

    then(getMemberService).should()
        .getMember(1L);
  }
}