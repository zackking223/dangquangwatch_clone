package edu.it10.vuquangdung.spring.AppCustomException;

import edu.it10.vuquangdung.spring.entity.enumeration.EntityName;

public class EntityNotFoundException extends ServiceException {
  private Integer id;
  private EntityName entityName;

  public EntityNotFoundException(Integer id, EntityName entityName) {
    this.id = id;
    this.entityName = entityName;
  }

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getEntityName() {
    return entityName.getValue();
  }
  public void setEntityName(EntityName type) {
    this.entityName = type;
  }
  @Override
  public String getMessage() {
    return "Không tìm thấy " + getEntityName() + " với mã " + getId();
  }
}
