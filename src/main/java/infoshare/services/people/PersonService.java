/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoshare.services.people;



import infoshare.domain.Person;
import infoshare.domain.Role;

import java.util.Set;


public interface PersonService {
    Person getUserByEmail(String email);

    Person getPersonById(String company, String id);

    Person save(Person entity);

    Person update(Person entity);

    void delete(Person entity);

    Set<Role> getRoles(String userId);

    Set<Person> getPersonsWithRole(String company, String role);

    boolean isThisPersonInThis(String personId, String role);

}