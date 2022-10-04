package com.service.backoffice.mapper;

import com.service.backoffice.dto.AddressDto;
import com.service.backoffice.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AddressMapperUtil.class})
public abstract class MapperForAddress {

    @Mapping(source = "areaId", target = "area", qualifiedByName = "areaById")
    public abstract Address toAddress(AddressDto addressDto);

    public abstract AddressDto toAddressDto(Address address);
}
