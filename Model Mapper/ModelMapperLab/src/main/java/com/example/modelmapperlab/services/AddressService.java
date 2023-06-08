package com.example.modelmapperlab.services;

import com.example.modelmapperlab.entities.Address;
import com.example.modelmapperlab.entities.dto.AddressDTO;

public interface AddressService {
    Address create(AddressDTO data);
}
