package com.ordermanagement.OM.entity;

import com.ordermanagement.OM.other.enums.BranchNames;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "customer_details")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @Column(name = "customer_generated_id", length = 50, nullable = false)
    private String customerGeneratedId;

    @Column(name = "customer_name", length = 100, nullable = false)
    private String customerName;

    @Column(name = "customer_address", length = 255)
    private String customerAddress;

    @Column(name = "customer_nic", length = 12, nullable = false)
    private String customerNic;

    @Column(name = "customer_contact_number",length = 12, nullable = false)
    private String customerContactNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_registered_branch_name", length = 255, nullable = false)
    private BranchNames branchName;

    @Column(name = "customer_active_status", columnDefinition = "TINYINT default 1")
    private boolean customerActiveStatus;

    @Type(type = "json")
    @Column(name = "past_purchase", columnDefinition = "json")
    private ArrayList<ArrayList<Object>> pastPurchases;

}

