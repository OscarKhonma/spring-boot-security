package ru.kata.spring.boot_security.demo.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElse(null);
    }

    @Override
    public Set<Role> getAllRoles() {
        return roleRepository.findAll().stream().collect(Collectors.toSet());
    }
}
