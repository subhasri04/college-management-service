package in.gopal.college.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.gopal.college.dto.AuthResponse;
import in.gopal.college.dto.RefreshRequest;
import in.gopal.college.entity.RefreshToken;
import in.gopal.college.security.JwtService;
import in.gopal.college.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final RefreshTokenService refreshTokenService;
	private final JwtService jwtService;
	
	@PostMapping("/refresh")
	public ResponseEntity<?>refresh(@RequestBody RefreshRequest request){
		
		 // 1️⃣ Verify refresh token
        RefreshToken refreshToken =
                refreshTokenService.verify(request.getRefreshToken());

        // 2️⃣ Generate new access token
        String newAccessToken =
                jwtService.generateToken(refreshToken.getUser());

        // 3️⃣ Return response
        return ResponseEntity.ok(
                new AuthResponse(
                        newAccessToken,
                        request.getRefreshToken()
                )
        );
    }
}
