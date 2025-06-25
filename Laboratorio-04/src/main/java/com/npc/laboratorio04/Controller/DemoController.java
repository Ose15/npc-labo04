package com.npc.laboratorio04.Controller;


import com.npc.laboratorio04.DTO.CreateUserDTO;
import com.npc.laboratorio04.DTO.KeycloakTokenResponse;
import com.npc.laboratorio04.Service.iAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/demo")
public class DemoController {
    private final iAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<KeycloakTokenResponse> register(@RequestBody @Valid CreateUserDTO user) throws Exception {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<KeycloakTokenResponse> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return ResponseEntity.ok(authService.login(username, password));
    }
    @PreAuthorize("hasRole('rol-example')")
    @GetMapping("/auth-test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test endpoint is working!");
    }
}
