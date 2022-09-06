package com.service.backoffice.mapper;

import com.service.backoffice.dto.AddressDto;
import com.service.backoffice.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper MAPPER = Mappers.getMapper(AddressMapper.class);

    Address toAddress(AddressDto addressDto);

    AddressDto toAddressDto(Address address);
}
