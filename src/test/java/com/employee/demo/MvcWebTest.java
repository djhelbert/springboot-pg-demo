package com.employee.demo;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

/**
 * @WebMvcTest : @Controller, @ControllerAdvice, @JsonComponent, @Converter, @Filter, @WebMvcController, @HandlerMethodArgument
 *
 * Focus on the web components omits any other beans that are not part of the web layer
 */
@WebMvcTest
public class MvcWebTest {

}
