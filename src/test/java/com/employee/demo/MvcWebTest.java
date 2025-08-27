package com.employee.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.employee.demo.controller.ProjectController;
import com.employee.demo.model.Project;
import com.employee.demo.repo.ProjectRepository;
import com.employee.demo.service.ProjectService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @WebMvcTest : @Controller, @ControllerAdvice, @JsonComponent, @Converter, @Filter, @WebMvcController, @HandlerMethodArgument
 *
 * Focus on the web components omits any other beans that are not part of the web layer
 */
@WebMvcTest(ProjectController.class)
//@WebMvcTest
public class MvcWebTest {

  @MockBean
  ProjectRepository repository;

  @MockBean
  private ProjectService projectService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldDoSomething() throws Exception {
    when(repository.findAll()).thenReturn(List.of(new Project()));

    mockMvc.perform(MockMvcRequestBuilders
        .get("/api/v1/projects")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

}
