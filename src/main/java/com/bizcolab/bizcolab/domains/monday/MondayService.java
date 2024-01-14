package com.bizcolab.bizcolab.domains.monday;

import com.bizcolab.bizcolab.domains.task_integration_configurations.TaskConfigService;
import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.json.JsonParser;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class MondayService {
    private final WebClient webClient;
    public void addInitialDataByApiKey(String apiKey) {
        GraphQLRequest request = GraphQLRequest.builder()
                .query("query { boards { name groups { id title } } }").build();
        Map<String, Object> block = webClient.post()
             .header("Authorization", apiKey)
             .bodyValue(request.getRequestBody()).retrieve().bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {}).block();
         System.out.println(block.get("data"));
    }
}
