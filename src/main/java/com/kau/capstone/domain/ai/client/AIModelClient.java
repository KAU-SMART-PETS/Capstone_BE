package com.kau.capstone.domain.ai.client;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class AIModelClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> analyzeImage(String imageUrl, String petType) {
        String aiModelUrl = "http://43.201.197.176:5000/eye";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(aiModelUrl)
            .queryParam("imageUrl", imageUrl)
            .queryParam("petType", petType);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            // Map<String, Object>로 Json 응답 받기
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Map<String, Object>>() {
                }
            );
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            String errorMessage = extractErrorMessage(e.getResponseBodyAsString());
            log.error("AI 모델 서버에서 오류 발생: {}", e.getResponseBodyAsString());
            throw new RuntimeException(
                "AI 모델 서버에서 오류 발생: " + e.getStatusCode() + " - " + errorMessage);
        } catch (ResourceAccessException e) {
            log.error("AI 모델 서버에 접근할 수 없음: {}", e.getMessage());
            // 상태 코드 503 (Service Unavailable)로 설정
            throw new RuntimeException(
                "AI 모델 서버에서 오류 발생: 503 SERVICE_UNAVAILABLE - " + e.getMessage());
        } catch (RestClientException e) {
            log.error("AI 모델 서버와 통신 중 오류 발생: {}", e.getMessage());
            // 상태 코드 500 (Internal Server Error)로 설정
            throw new RuntimeException(
                "AI 모델 서버에서 오류 발생: 500 INTERNAL_SERVER_ERROR - " + e.getMessage());
        }
    }

    public String registNoseImage(String imageUrl, Long petId) {
        String aiModelUrl = "http://3.35.41.30:5000/nose/train";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(aiModelUrl)
            .queryParam("imageUrl", imageUrl)
            .queryParam("petId", petId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        try {
            // Map<String, Object>로 Json 응답 받기
            ResponseEntity<String> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.POST,
                requestEntity,
                String.class
            );

            return response.getBody();
        } catch (HttpStatusCodeException e) {
            String errorMessage = extractErrorMessage(e.getResponseBodyAsString());
            log.error("AI 모델 서버에서 오류 발생: {}", e.getResponseBodyAsString());
            throw new RuntimeException(
                "AI 모델 서버에서 오류 발생: " + e.getStatusCode() + " - " + errorMessage);
        } catch (ResourceAccessException e) {
            log.error("AI 모델 서버에 접근할 수 없음: {}", e.getMessage());
            // 상태 코드 503 (Service Unavailable)로 설정
            throw new RuntimeException(
                "AI 모델 서버에서 오류 발생: 503 SERVICE_UNAVAILABLE - " + e.getMessage());
        } catch (RestClientException e) {
            log.error("AI 모델 서버와 통신 중 오류 발생: {}", e.getMessage());
            // 상태 코드 500 (Internal Server Error)로 설정
            throw new RuntimeException(
                "AI 모델 서버에서 오류 발생: 500 INTERNAL_SERVER_ERROR - " + e.getMessage());
        }
    }

    public Map<String, Object> testNoseImage(String imageUrl) {
        String aiModelUrl = "http://3.35.41.30:5000/nose/test";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(aiModelUrl)
            .queryParam("imageUrl", imageUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        try {
            // Map<String, Object>로 Json 응답 받기
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Map<String, Object>>() {
                }
            );
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            String errorMessage = extractErrorMessage(e.getResponseBodyAsString());
            log.error("AI 모델 서버에서 오류 발생: {}", e.getResponseBodyAsString());
            throw new RuntimeException(
                "AI 모델 서버에서 오류 발생: " + e.getStatusCode() + " - " + errorMessage);
        } catch (ResourceAccessException e) {
            log.error("AI 모델 서버에 접근할 수 없음: {}", e.getMessage());
            // 상태 코드 503 (Service Unavailable)로 설정
            throw new RuntimeException(
                "AI 모델 서버에서 오류 발생: 503 SERVICE_UNAVAILABLE - " + e.getMessage());
        } catch (RestClientException e) {
            log.error("AI 모델 서버와 통신 중 오류 발생: {}", e.getMessage());
            // 상태 코드 500 (Internal Server Error)로 설정
            throw new RuntimeException(
                "AI 모델 서버에서 오류 발생: 500 INTERNAL_SERVER_ERROR - " + e.getMessage());
        }
    }


    // HTML 데이터 가공 로직
    private String extractErrorMessage(String htmlMessage) {
        // 정규식을 사용하여 <p> 태그 내의 텍스트만 추출
        Pattern pattern = Pattern.compile("<p>(.*?)</p>");
        Matcher matcher = pattern.matcher(htmlMessage);

        if (matcher.find()) {
            return matcher.group(1);  // <p> 태그 안의 메시지 반환
        } else {
            // <p> 태그가 없으면 기본 메시지 반환
            return "알 수 없는 오류가 발생했습니다.";
        }
    }
}
