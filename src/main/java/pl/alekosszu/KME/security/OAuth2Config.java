package pl.alekosszu.KME.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
public class OAuth2Config {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(getClientRegistration());
    }

    private ClientRegistration getClientRegistration() {
        // Definiowanie konfiguracji klienta OAuth2
        return ClientRegistration.withRegistrationId("google")
                .clientId("822028440618-vpt6fihn9lmd9nl2g4j5d1rgjl05abib.apps.googleusercontent.com")
                .clientSecret("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")
                .redirectUri("{baseUrl}/oauth2/callback/{registrationId}")
                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .userNameAttributeName("login")
                .clientName("Google")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .build();
    }

    // Dodaj inne beany i konfiguracje, jeśli są potrzebne

}
