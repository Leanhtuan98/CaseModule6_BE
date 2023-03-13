package com.casemodule6_be.service;

import com.casemodule6_be.model.Address;
import com.casemodule6_be.model.Category;
import com.casemodule6_be.repository.IAddressRepo;
import com.casemodule6_be.repository.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    IAddressRepo iAddressRepo;
    public List<Address> getAll(){
        return (List<Address>) iAddressRepo.findAll();}
}
