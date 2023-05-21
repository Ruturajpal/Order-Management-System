package com.ordermanagement.OM.dto.paginated;

import com.ordermanagement.OM.dto.response.GetItemListDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseItemDTO {
    private List<GetItemListDTO> getItemListDTOList;
    private long dataCount;
}
