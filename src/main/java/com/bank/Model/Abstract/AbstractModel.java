package com.bank.Model.Abstract;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractModel implements Serializable {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name = "id", nullable = false, updatable = false, length = 36)
  private final String id;

  protected AbstractModel() {
    this.id = null;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof AbstractModel)) {
      return false;
    }
    AbstractModel entity = (AbstractModel) other;
    return Objects.equals(id, entity.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}