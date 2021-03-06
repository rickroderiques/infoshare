package infoshare.client.content.account.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.OrganisationFacade;
import infoshare.app.util.DomainState;
import infoshare.client.content.MainLayout;
import infoshare.client.content.account.AccountMenu;
import infoshare.client.content.account.views.DisabledOrganisationTab;
import infoshare.domain.organisation.Organisation;

import java.util.Date;
import java.util.Set;

/**
 * Created by hashcode on 2015/11/30.
 */
public class DisabledOrganisationTable extends Table {
    private final MainLayout main;


    public DisabledOrganisationTable(MainLayout main, DisabledOrganisationTab tab, Set<Organisation> companies) {
        this.main = main;
        setSizeFull();
        addContainerProperty("Date", Date.class, null);
        addContainerProperty("Name", String.class, null);
        addContainerProperty("Code", String.class, null);
        addContainerProperty("Details", Button.class, null);
        addContainerProperty("Enable", Button.class, null);

        companies.parallelStream().forEach(item -> {
            Button details = new Button("Details");
            details.setStyleName(ValoTheme.BUTTON_LINK);
            details.setData(item.getId());
            details.addClickListener(event -> {
            });

            Button enable = new Button("Enable");
            enable.setStyleName(ValoTheme.BUTTON_LINK);
            enable.setData(item.getId());
            enable.addClickListener(event -> {
                Organisation company = OrganisationFacade.getOrganisationServiceInstance().findById(item.getId());
                Organisation updatedCompany = new Organisation
                        .Builder()
                        .copy(company)
                        .state(DomainState.ACTIVE.name())
                        .build();
                OrganisationFacade.getOrganisationServiceInstance().update(updatedCompany);
                getHome();

            });

            addItem(new Object[]{
                    item.getDate(),
                    item.getName(),
                    item.getId(),
                    details,
                    enable
            }, item.getId());

        });

        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

    }

    private void getHome() {
        main.content.setSecondComponent(new AccountMenu(main, "DISABLED"));
    }
}