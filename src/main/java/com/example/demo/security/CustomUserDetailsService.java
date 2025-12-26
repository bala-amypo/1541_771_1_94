package com.example.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

public class CustomUserDetailsService implements UserDetailsService {

    public static class DemoUser {
        private Long id;
        private String fullName;
        private String email;
        private String password;
        private String role;

        public DemoUser(Long id, String fullName, String email, String password, String role) {
            this.id = id;
            this.fullName = fullName;
            this.email = email;
            this.password = password;
            this.role = role;
        }

        public Long getId() { return id; }
        public String getFullName() { return fullName; }
        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public String getRole() { return role; }
    }

    private final Map<String, DemoUser> users = new HashMap<>();
    private long idCounter = 1L;

    public CustomUserDetailsService() {
        // default admin account used in tests
        DemoUser admin = new DemoUser(
                idCounter++,
                "Admin",
                "admin@city.com",
                "admin123",
                "ADMIN"
        );
        users.put(admin.getEmail(), admin);
    }

    public DemoUser registerUser(String name, String email, String password) {
        if (users.containsKey(email)) {
            throw new RuntimeException("User already exists with email: " + email);
        }

        DemoUser u = new DemoUser(idCounter++, name, email, password, "USER");
        users.put(email, u);
        return u;
    }

    public DemoUser getByEmail(String email) {
        return users.get(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DemoUser user = users.get(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
