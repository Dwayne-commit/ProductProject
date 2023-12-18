package com.example.ProductProject.service;

import com.example.ProductProject.model.Owner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OwnerService {

    public Owner createOwner(Owner owner);

    public List<Owner> getOwners();

    public Owner getOwnerById(Long id);

    public Owner updateOwner(Owner owner, Long id);

    public Owner login(String username, String password);
}
