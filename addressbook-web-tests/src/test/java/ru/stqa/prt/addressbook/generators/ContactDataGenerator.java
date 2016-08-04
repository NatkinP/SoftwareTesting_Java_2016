package ru.stqa.prt.addressbook.generators;

import ru.stqa.prt.addressbook.model.ContactData;
import ru.stqa.prt.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by natkin on 04.08.2016.
 */
public class ContactDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<ContactData> contacts = generateContact(count);
    save(contacts, file);
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s\n",  contact.getFirstname(),
                                                      contact.getLastname(),
                                                      contact.getAddress(),
                                                      contact.getMobile(),
                                                      contact.getEmail2()));
    }
    writer.close();
  }

  private static List<ContactData> generateContact(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++){
      contacts.add(new ContactData().withFirstname(String.format("Firstname %s", i))
                                    .withLastname(String.format("Lastname %s", i))
                                    .withAddress(String.format("Address %s", i))
                                    .withMobile(String.format("MobilePhone %s", i))
                                    .withEmail2(String.format("Email2 %s", i)));
    }
    return contacts;
  }
}
