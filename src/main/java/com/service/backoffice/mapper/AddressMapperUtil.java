package com.service.backoffice.mapper;

import com.service.backoffice.model.Area;
import com.service.backoffice.repositories.AreaRepo;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AddressMapperUtil {
    private final AreaRepo areaRepo;

    @Named("areaById")
    Area areaById(long value) {
        return areaRepo.findById(value).get();
    }

}
