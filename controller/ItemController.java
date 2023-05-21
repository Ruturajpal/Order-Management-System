package com.ordermanagement.OM.controller;

import com.ordermanagement.OM.dto.paginated.PaginatedResponseItemDTO;
import com.ordermanagement.OM.dto.request.ItemDTO;
import com.ordermanagement.OM.dto.request.UpdateItemDTO;
import com.ordermanagement.OM.dto.response.GetAllItemsDTO;
import com.ordermanagement.OM.other.responseEntity.StandardResponse;
import com.ordermanagement.OM.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(path = "/save-item-details")
    public ResponseEntity<StandardResponse> addItemDetails(@RequestBody ItemDTO itemDTO){
        String message = itemService.addItemDetails(itemDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(204, "Successful", message), HttpStatus.CREATED);
    }

    @PutMapping(path = "/update-items")
    public ResponseEntity<StandardResponse> updateItem(@RequestBody UpdateItemDTO updateItemDTO) {
        String message = itemService.updateItem(updateItemDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(204, "Successful", message), HttpStatus.OK);
    }

    @PutMapping(path = "/unregister-item",params = "itemCode")
    public ResponseEntity<StandardResponse> updateItem(@RequestParam(value = "itemCode") String itemCode){
        String message = itemService.unregisterItem(itemCode);
        return  new ResponseEntity<StandardResponse>(
                new StandardResponse(204, "Successful", message), HttpStatus.OK);
    }

    @GetMapping(path = "/get-all-items")
    public ResponseEntity<StandardResponse> getAllItems(){
        List<GetAllItemsDTO> message =itemService.getAllActiveItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Successful", message), HttpStatus.OK);
    }

    @GetMapping(path = "/get-all-items-search/{page}")
    public ResponseEntity<StandardResponse> getAllItemsForSearch(@PathVariable(value = "page") int pageNo){
        PaginatedResponseItemDTO paginatedResponseItemDTO =itemService.getAllActiveItemsForSearch(pageNo);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Successful", paginatedResponseItemDTO), HttpStatus.OK
        );
    }

}
