package chegamais.com.chagamais.controller.Response;

import static chegamais.com.chagamais.security.securityConfig.SecurityConstants.TOKEN_PREFIX;

public class AuthResponse {
    private String tokenDeAcesso;
    private String tokenType = TOKEN_PREFIX;
    private String refreshToken;

    public AuthResponse(String tokenDeAcesso) {
        this.tokenDeAcesso = tokenDeAcesso;
        //this.refreshToken = refreshToken;
    }

    public String getTokenDeAcesso() {
        return tokenDeAcesso;
    }

    public void setTokenDeAcesso(String tokenDeAcesso) {
        this.tokenDeAcesso = tokenDeAcesso;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
