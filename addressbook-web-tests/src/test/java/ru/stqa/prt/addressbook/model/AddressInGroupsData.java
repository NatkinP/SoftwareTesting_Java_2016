package ru.stqa.prt.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by natkin on 06.08.2016.
 */

@XStreamAlias("aid")
@Entity
@Table(name = "address_in_groups")
public class AddressInGroupsData {
  @Id
  @Column(name = "id")
  private int id;
  @Column(name = "group_id")
  private int group_id;

  public AddressInGroupsData withId(int id) {
    this.id = id;
    return this;
  }

  public AddressInGroupsData withGroup_id(int group_id) {
    this.group_id = group_id;
    return this;
  }

  public int getId() {
    return id;
  }

  public int getGroup_id() {
    return group_id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AddressInGroupsData that = (AddressInGroupsData) o;

    if (id != that.id) return false;
    return group_id == that.group_id;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + group_id;
    return result;
  }

}
