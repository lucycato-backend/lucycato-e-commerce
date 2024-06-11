package org.lucycato.notificationcommandservice.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.notificationcommandservice.adapter.out.externalsystem.FCMAdapter;
import org.lucycato.notificationcommandservice.application.port.in.FCMUseCase;
import org.lucycato.notificationcommandservice.application.port.in.command.NotificationToSpecificDeviceByTargetTokenCommand;
import org.lucycato.notificationcommandservice.application.port.in.command.NotificationToSpecificDeviceByUserIdCommand;
import org.lucycato.notificationcommandservice.application.port.out.QueryAppUserAuthPort;
import org.lucycato.notificationcommandservice.application.port.out.result.AppUserFirebaseTokenResult;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

// @Transactional // db 의존성 추가 이후 추가
@Service
@RequiredArgsConstructor
@Slf4j
public class FCMService implements FCMUseCase {
    private final FirebaseMessaging firebaseMessaging;
    private final QueryAppUserAuthPort queryAppUserAuthPort;
    private final ObjectMapper objectMapper;

    @Override
    public String sendNotificationToSpecificDevice(NotificationToSpecificDeviceByUserIdCommand command) {
        //TODO: targetUserId -> 유저 서버 찔러서 db에 FirebaseToken 저장되어 있는지 확인 후 가져오기
        try {
            AppUserFirebaseTokenResult appUserFirebaseTokenResult = queryAppUserAuthPort.getAppUserFirebaseTokenByUserId(command.getTargetUserId());
            // TODO: 유저가 존재하지 않는 경우와 유저의 토큰이 존재하지 않는 경우의 예외처리 분리
            Notification notification = Notification.builder()
                    .setBody(command.getBody())
                    .setTitle(command.getTitle())
                    .build();
            Message message = Message.builder()
                    .setToken(appUserFirebaseTokenResult.getAppUserToken())
                    .setNotification(notification)
                    .build();

            try {
                firebaseMessaging.send(message);
                return "알림을 성공적으로 전송했습니다. targetUserId="+command.getTargetUserId();
            } catch (FirebaseMessagingException e) {
                log.error("Exception[Err_Msg]: {}",e.getStackTrace()[0]);
                return "알림 보내기를 실패했습니다. targetUserId="+command.getTargetUserId();
            }
        } catch (ApiExceptionImpl e) {
            return "해당 유저가 존재하지 않거나 해당 유저의 FirebaseToken 이 존재하지 않습니다. targetUserId="+command.getTargetUserId();
        }

    }

    @Override
    public String sendMessageToSpecificDevice(NotificationToSpecificDeviceByTargetTokenCommand command) throws IOException {
        try {
            String message = makeMessage(command.getTargetToken(), command.getTitle(), command.getBody());
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));
            String API_URL = "https://fcm.googleapis.com/v1/projects/lucycato-e-commerce/messages:send";
            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(requestBody)
                    .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                    .build();

            Response response = client.newCall(request).execute();
            log.info(response.body().string());
            return "알림을 성공적으로 전송했습니다.";
        } catch (ApiExceptionImpl e) {
            return "해당 유저가 존재하지 않거나 해당 유저의 FirebaseToken 이 존재하지 않습니다.";
        }
    }

    private String makeMessage(String targetToken, String title, String body) throws JsonProcessingException {
        FCMAdapter fcmAdapter = FCMAdapter.builder()
                .message(FCMAdapter.Message.builder()
                        .token(targetToken)
                        .notification(FCMAdapter.Notification.builder()
                                .title(title)
                                .body(body)
                                .image(null)
                                .build()
                        ).build()).validate_only(false).build();
        return objectMapper.writeValueAsString(fcmAdapter);
    }

    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "firebase/serviceAccountKey.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }
}
