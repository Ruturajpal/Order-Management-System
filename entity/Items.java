package com.ordermanagement.OM.entity;

import com.ordermanagement.OM.other.enums.MeasuringUnitType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import javax.persistence.*;

@Entity
@Table(name = "item_details")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Items {

    @Id
    @Column(name = "generated_code", length = 50)
    private String itemGeneratedCode;

    @Column(name = "name", length = 100, nullable = false)
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "measure_type", length = 100, nullable = false)
    private MeasuringUnitType measuringUnitType;

    @Column(name = "note", length = 500, nullable = false)
    private String note;

    @Column(name = "supplier_price", length = 10, nullable = false)
    private float supplierPrice;

    @Column(name = "selling_price", length = 10, nullable = false)
    private float sellingPrice;

    @Column(name = "active_status", columnDefinition = "TINYINT default 1")
    private boolean itemActiveStatus;

}
