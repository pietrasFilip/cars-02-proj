package com.app.service.token;

import com.app.persistence.model.token.dto.AuthorizationDto;
import com.app.persistence.model.token.dto.RefreshTokenDto;
import com.app.persistence.model.token.dto.TokensDto;

public sealed interface TokensService permits TokensServiceImpl{
    TokensDto generateTokens(Long userId);
    AuthorizationDto parseTokens(String token);
    TokensDto refreshTokens(RefreshTokenDto refreshTokenDto);
}
