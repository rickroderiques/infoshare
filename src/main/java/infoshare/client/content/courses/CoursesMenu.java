package infoshare.client.content.courses;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import infoshare.client.content.MainLayout;

/**
 * Created by hashcode on 2015/06/23.
 */
public class CoursesMenu extends VerticalLayout {
    private MainLayout main;
    private TabSheet tab;

    public CoursesMenu(MainLayout main,String selectedTab) {
        this.main = main;
    }
}