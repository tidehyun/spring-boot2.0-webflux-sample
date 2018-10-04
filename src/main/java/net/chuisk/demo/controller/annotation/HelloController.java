package net.chuisk.demo.controller.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;

@Controller
public class HelloController {

	@GetMapping(value = { "/annotation", "/annotation/main" })
	public Rendering main() {
		return Rendering.view("main").modelAttribute("msg", "annotation based reactive web")
				.modelAttribute("say", "hi!!").build();
	}

	@GetMapping("/annotation/register")
	public Rendering regPerson() {
		return Rendering.view("annotationRegPerson").build();
	}

}
