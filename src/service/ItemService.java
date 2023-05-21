package com.ordermanagement.OM.service;

import com.ordermanagement.OM.dto.paginated.PaginatedResponseItemDTO;
import com.ordermanagement.OM.dto.request.ItemDTO;
import com.ordermanagement.OM.dto.request.UpdateItemDTO;
import com.ordermanagement.OM.dto.response.GetAllItemsDTO;
import java.util.List;

public interface ItemService {
    String addItemDetails(ItemDTO itemDTO);
    String updateItem(UpdateItemDTO updateItemDTO);
    String unregisterItem(String itemCode);
    List<GetAllItemsDTO> getAllActiveItems();
    PaginatedResponseItemDTO getAllActiveItemsForSearch(int pageNo);
}
