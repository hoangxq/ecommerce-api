package com.demo.ecommerce_api.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@TypeDefs({
        @TypeDef(name = "jsonType", typeClass = JsonType.class)
})
public abstract class AbstractModel {

    @CreationTimestamp
    @Column(name = "created_at")
    protected Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    protected Instant updatedAt;

}
