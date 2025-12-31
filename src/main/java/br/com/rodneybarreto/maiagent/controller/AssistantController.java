package br.com.rodneybarreto.maiagent.controller;

import br.com.rodneybarreto.maiagent.service.AssistantAiService;
import dev.langchain4j.service.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/assistant")
public class AssistantController {

    private final AssistantAiService assistantAiService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public String askAssistant(@RequestBody String userMessage) {
        Result<String> result = assistantAiService.handleRequest(userMessage);
        return result.content();
    }

}
