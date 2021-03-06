package infoshare.client.content.system.table;

import com.vaadin.ui.Table;
import infoshare.app.facade.LocationFacade;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.client.content.MainLayout;
import infoshare.domain.organisation.Location;

import java.util.Set;

/**
 * Created by hashcode on 2015/09/07.
 */
public class LocationTable extends Table {
    
    private final MainLayout main;

    public LocationTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Name", String.class, null);
        addContainerProperty("Code", String.class, null);
        addContainerProperty("Latitude", String.class, null);
        addContainerProperty("Longitude", String.class, null);
        addContainerProperty("Type", String.class, null);
        addContainerProperty("Parent", String.class, null);
        addContainerProperty("Children", Long.class, null);
        // Add Data Columns
        Set<Location> locations = LocationFacade.getLocationServiceInstance().findAll(OrganisationUtil.getCompanyCode());
        for (Location location : locations) {
            addItem(new Object[]{location.getName(),
                    location.getCode(),
                    location.getLatitude(),
                    location.getLongitude(),
                    getLocationType(location.getLocationTypeId()),
                    getParentName(location.getParentId()),
                    LocationFacade.getLocationServiceInstance().numberofChildred(location.getId())}, location.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }

    private String getParentName(String parentId) {
        if (parentId != null) {
            return LocationFacade.getLocationServiceInstance().findById(OrganisationUtil.getCompanyCode(),parentId).getName();
        }
        return null;
    }

    private String getLocationType(String locationTypeId) {
        if (locationTypeId != null) {
            return LocationFacade.getLocationTypeServiceInstance().findById(locationTypeId).getName();
        }
        return null;
    }
}