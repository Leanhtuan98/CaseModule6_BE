package com.casemodule6_be.service.address.impl;

import com.casemodule6_be.common.constant.Constant;
import com.casemodule6_be.common.enums.EnumSSWException;
import com.casemodule6_be.common.exception.SSWException;
import com.casemodule6_be.common.utils.ObjectMapperUtils;
import com.casemodule6_be.dto.address.AddressRequest;
import com.casemodule6_be.dto.address.AddressResponse;
import com.casemodule6_be.model.Address;
import com.casemodule6_be.repository.AddressRepository;
import com.casemodule6_be.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent()) {
            throw new SSWException(EnumSSWException.ADDRESS_NOT_EXISTED);
        }
        Address deleteAddress = address.get();
        deleteAddress.setStatus(Constant.DELETE_STATUS);
        addressRepository.save(deleteAddress);
    }

    @Override
    public AddressResponse create(AddressRequest addressRequest) {
        Address address = Address.builder()
                .name(addressRequest.getName())
                .status(Constant.DEFAULT_STATUS)
                .build();
        address = addressRepository.save(address);
        return ObjectMapperUtils.map(address, AddressResponse.class);
    }

    @Override
    public AddressResponse update(AddressRequest addressRequest) {
        Optional<Address> address = addressRepository.findById(addressRequest.getId());
        if (!address.isPresent()) {
            throw new SSWException(EnumSSWException.ADDRESS_NOT_EXISTED);
        }

        Address updateAddress = address.get();
        updateAddress.setName(addressRequest.getName());
        updateAddress = addressRepository.save(updateAddress);
        return ObjectMapperUtils.map(updateAddress,AddressResponse.class);

    }


}
