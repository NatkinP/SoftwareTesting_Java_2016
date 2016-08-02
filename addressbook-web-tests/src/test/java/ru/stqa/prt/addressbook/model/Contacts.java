package ru.stqa.prt.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by natkin on 02.08.2016.
 */
public class Contacts extends ForwardingSet<ContactNamesData> {

  private Set<ContactNamesData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<ContactNamesData>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<ContactNamesData>();
  }

  @Override
  protected Set<ContactNamesData> delegate() {
    return delegate;
  }

  public Contacts withAdded(ContactNamesData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(ContactNamesData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}
