package ru.kata.spring.boot_security.service;

import ru.kata.spring.boot_security.model.Role;
import ru.kata.spring.boot_security.repository.RoleRepository;

public class RoleServiceImp implements RoleService{
    private final RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findById(long role_id) {
        return roleRepository.findById(role_id);
    }
}
