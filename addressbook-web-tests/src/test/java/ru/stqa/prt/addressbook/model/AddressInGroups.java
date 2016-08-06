package ru.stqa.prt.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by natkin on 02.08.2016.
 */
public class AddressInGroups extends ForwardingSet<AddressInGroupsData>{

    private Set<AddressInGroupsData> delegate;

    public AddressInGroups(Collection<AddressInGroupsData> addressInGroups) {
        this.delegate = new HashSet<AddressInGroupsData>(addressInGroups);
    }

    public AddressInGroups() {
        this.delegate = new HashSet<AddressInGroupsData>();
    }


    @Override
    protected Set<AddressInGroupsData> delegate() {
        return delegate;
    }

    public AddressInGroups withAdded(AddressInGroupsData aig){
        AddressInGroups addressInGroups = new AddressInGroups(this);
        addressInGroups.add(aig);
        return addressInGroups;
    }

    public AddressInGroups without(AddressInGroupsData aig){
        AddressInGroups addressInGroups = new AddressInGroups(this);
        addressInGroups.remove(aig);
        return addressInGroups;
    }
}
