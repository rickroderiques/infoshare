package infoshare.client.content.common.location;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;
import infoshare.client.content.common.location.views.AddressTypeTab;
import infoshare.client.content.common.location.views.ContactTypeTab;
import infoshare.client.content.common.location.views.LocationTypeTab;


/**
 * Created by hashcode on 2015/12/12.
 */
public class CommonLocationMenu extends VerticalLayout {
    private final MainLayout main;
    private TabSheet tab;

    public CommonLocationMenu(MainLayout main, String selectedTab) {
        this.main = main;
        setSizeFull();
        VerticalLayout addressTypeTab = new VerticalLayout();
        addressTypeTab.setMargin(true);
        addressTypeTab.addComponent(new AddressTypeTab(main));

        VerticalLayout contactTypeTab = new VerticalLayout();
        contactTypeTab.setMargin(true);
        contactTypeTab.addComponent(new ContactTypeTab(main));

        VerticalLayout locationTypeTab = new VerticalLayout();
        locationTypeTab.setMargin(true);
        locationTypeTab.addComponent(new LocationTypeTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(locationTypeTab, "Location TYPES ", null);
        tab.addTab(addressTypeTab, "Address TYPES", null);
        tab.addTab(contactTypeTab, "Contact TYPES", null);

        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(locationTypeTab);
        } else if (selectedTab.equals("ADDRESS")) {
            tab.setSelectedTab(addressTypeTab);
        } else if (selectedTab.equals("CONTACT")) {
            tab.setSelectedTab(contactTypeTab);
        }

        addComponent(tab);
    }
}
