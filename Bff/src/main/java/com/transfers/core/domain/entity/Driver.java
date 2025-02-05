package com.transfers.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transfers")
public class Driver {

    @Id
    private ObjectId id;
    private String fullName;
    private Transfer transfer;
    private Details details;
}
