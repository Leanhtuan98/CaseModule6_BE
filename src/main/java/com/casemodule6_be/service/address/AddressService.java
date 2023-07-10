package com.casemodule6_be.service.address;

import com.casemodule6_be.dto.address.AddressRequest;
import com.casemodule6_be.dto.address.AddressResponse;
import com.casemodule6_be.model.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAll();
    void delete(Long id);
    AddressResponse create(AddressRequest addressRequest);
    AddressResponse update(AddressRequest addressRequest);

}
