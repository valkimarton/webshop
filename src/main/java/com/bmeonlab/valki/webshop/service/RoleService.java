package com.bmeonlab.valki.webshop.service;

import com.bmeonlab.valki.webshop.model.Role;
import com.bmeonlab.valki.webshop.model.enums.RoleType;
import com.bmeonlab.valki.webshop.repository.RoleRepository;
import com.bmeonlab.valki.webshop.utils.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Role createRole(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    public Role getRoleById(Long id) { return roleRepository.getOne(id); }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    public Role updateRole(Long id, Role role) {
        Role existingRole = roleRepository.findById(id).orElse(new Role());
        NullAwareBeanUtils.copyNonNullProperties(role, existingRole);
        return roleRepository.saveAndFlush(existingRole);
    }

    @Transactional
    public void deleteRole(Long id) { roleRepository.deleteById(id); }
    
    public Role findByRoleName(RoleType roleName) {
        return roleRepository.findByName(roleName).orElse(null);
    }
}
