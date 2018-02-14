package com.unifi.fattureApp.mongoWrapper;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unifi.fattureApp.App.Client;
@Repository
public interface ClientRepository extends CrudRepository<Client, String>{

}
