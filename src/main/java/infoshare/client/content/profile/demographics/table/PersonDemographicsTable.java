package infoshare.client.content.profile.demographics.table;

import com.vaadin.ui.Table;
import infoshare.app.facade.DemographicsFacade;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.client.content.MainLayout;
import infoshare.domain.demographics.Gender;
import infoshare.domain.person.PersonDemographics;

import java.util.Date;
import java.util.Set;

/**
 * Created by hashcode on 2015/12/20.
 */
public class PersonDemographicsTable extends Table {
    private final MainLayout main;

    public PersonDemographicsTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        final String personId = GetUserCredentials.getUser().getId();

        addContainerProperty("Date Created", Date.class, null);
        addContainerProperty("Gender", String.class, null);
        addContainerProperty("Date of Birth", Date.class, null);


        Set<PersonDemographics> personDemographicses = PeopleFacade.getPersonDemographicsServiceInstance().find_ALL(personId);

        personDemographicses.parallelStream().forEach(item -> {
            addItem(new Object[]{
                    item.getDate(),
                    gender(item.getGenderId()),
                    item.getDateOfBirth(),
            }, item.getId());

        });
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

    }

    private String gender(String genderId) {
        Gender gender = DemographicsFacade.getGenderInstance().findById(genderId);
        if (gender != null)
            return gender.getName();
        return "Type Not Set";

    }



}
