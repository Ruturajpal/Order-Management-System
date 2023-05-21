package com.ordermanagement.OM.other.mappers;

import com.ordermanagement.OM.dto.request.CustomerDTO;
import com.ordermanagement.OM.dto.request.ItemDTO;
import com.ordermanagement.OM.dto.response.GetAllCustomersDTO;
import com.ordermanagement.OM.dto.response.GetAllItemsDTO;
import com.ordermanagement.OM.dto.response.GetItemListDTO;
import com.ordermanagement.OM.entity.Customer;
import com.ordermanagement.OM.entity.Items;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface MapStructMapper {
    GetAllCustomersDTO allCustomersEntityToDTO(Customer customer);
    Items itemDetailsDtoToEntity(ItemDTO itemDTO);
    GetAllItemsDTO itemsToDTO(Items items);
    Customer customerDetailsDtoToEntity(CustomerDTO customerDTO);
    GetItemListDTO itemToDTOPagination(Items i);
}
