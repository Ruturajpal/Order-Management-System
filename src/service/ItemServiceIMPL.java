package com.ordermanagement.OM.service.impl;

import com.ordermanagement.OM.dto.paginated.PaginatedResponseItemDTO;
import com.ordermanagement.OM.dto.request.ItemDTO;
import com.ordermanagement.OM.dto.request.UpdateItemDTO;
import com.ordermanagement.OM.dto.response.GetAllItemsDTO;
import com.ordermanagement.OM.dto.response.GetItemListDTO;
import com.ordermanagement.OM.entity.Items;
import com.ordermanagement.OM.other.exception.NotFoundException;
import com.ordermanagement.OM.other.mappers.MapStructMapper;
import com.ordermanagement.OM.repo.ItemRepo;
import com.ordermanagement.OM.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private MapStructMapper mapStructMapper;

    @Override
    public String addItemDetails(ItemDTO itemDTO) {

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        int i = Math.abs(random.nextInt()%25);    int j = Math.abs(random.nextInt()%25);
        String itemGenCode = "ITM"+String.valueOf(year).substring(2)+String.valueOf(itemRepo.count()+1) + alphabet.charAt(i)+alphabet.charAt(j);

        Items items = mapStructMapper.itemDetailsDtoToEntity(itemDTO);
        items.setItemActiveStatus(true);
        items.setItemGeneratedCode(itemGenCode);
        itemRepo.save(items);
        return items.getItemName()+" item was added successfully";
    }

    @Override
    public String updateItem(UpdateItemDTO updateItemDTO) {
        if (itemRepo.existsByItemGeneratedCodeAndItemActiveStatus(updateItemDTO.getItemGeneratedCode(), true)){
            Items item = itemRepo.getByItemGeneratedCodeAndItemActiveStatus(updateItemDTO.getItemGeneratedCode(),true);
//          item = mapStructMapper.itemUpdateDtoToEntity(updateItemDTO);  please  check the logic difference
            item.setItemGeneratedCode(updateItemDTO.getItemGeneratedCode());
            item.setItemName(updateItemDTO.getItemName());
            item.setMeasuringUnitType(updateItemDTO.getMeasuringUnitType());
            item.setNote(updateItemDTO.getNote());
            item.setSupplierPrice(updateItemDTO.getSupplierPrice());
            item.setSellingPrice(updateItemDTO.getSellingPrice());
            itemRepo.save(item);
            return "item updated successfully";
        }
        else{
            throw new NotFoundException("Id does not exist");
        }
    }

    @Override
    public String unregisterItem(String itemCode) {
        if (itemRepo.existsByItemGeneratedCodeAndItemActiveStatus(itemCode,true)){
            Items item =itemRepo.getByItemGeneratedCodeAndItemActiveStatus(itemCode,true);
            item.setItemActiveStatus(false);
            itemRepo.save(item);
            return "unregistered successfully";
        }
        else{
            return "Item code does not exist";
        }
    }

    @Override
    public List<GetAllItemsDTO> getAllActiveItems() {
        if(itemRepo.count()>0){
            List<Items> item = itemRepo.getAllByItemActiveStatus(true);
            List<GetAllItemsDTO> getAllItemsDTOList = new ArrayList<>();
            for(Items items:item) {
                GetAllItemsDTO getAllItemsDTO = mapStructMapper.itemsToDTO(items);
                getAllItemsDTOList.add(getAllItemsDTO);
            }
            return getAllItemsDTOList;
        }
        else{
            return null;
        }
    }

    @Override
    public PaginatedResponseItemDTO getAllActiveItemsForSearch(int pageNo) {
        int pageContentSize = 5;
        Page<Items> items = itemRepo.findAllByItemActiveStatusEquals(true, PageRequest.of(pageNo,pageContentSize));
        int dataCount = itemRepo.countAllByItemActiveStatus(true);
        List<GetItemListDTO> getItemListDTOList = new ArrayList<>();
        for (Items i: items){
            GetItemListDTO getItemListDTO = mapStructMapper.itemToDTOPagination(i);
            getItemListDTOList.add(getItemListDTO);
        }
        PaginatedResponseItemDTO paginatedResponseItemDTO = new PaginatedResponseItemDTO(
                getItemListDTOList, dataCount);
        return paginatedResponseItemDTO;
    }

}
