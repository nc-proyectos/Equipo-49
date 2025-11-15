package com.nc.g49_smartcrm.mapper;

import com.nc.g49_smartcrm.dto.ContactRequest;
import com.nc.g49_smartcrm.dto.ContactResponse;
import com.nc.g49_smartcrm.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContactMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true) // se setea desde el servicio si aplica
    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.Instant.now())")
    Contact toEntity(ContactRequest request);

    @Mapping(target = "ownerId", source = "owner.id")
    ContactResponse toDto(Contact contact);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.Instant.now())")
    void updateEntityFromRequest(ContactRequest request, @MappingTarget Contact contact);
}