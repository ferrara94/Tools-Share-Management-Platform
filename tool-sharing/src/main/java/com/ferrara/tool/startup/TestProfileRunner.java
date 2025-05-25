package com.ferrara.tool.startup;

import com.ferrara.tool.role.Role;
import com.ferrara.tool.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
@RequiredArgsConstructor
public class TestProfileRunner implements CommandLineRunner {

    final private RoleRepository repository;

    @Override
    public void run(String... args) throws Exception {
        String roleName = "USER";
        Role role = new Role();
        role.setName(roleName);
        repository.save(role);
    }
}
