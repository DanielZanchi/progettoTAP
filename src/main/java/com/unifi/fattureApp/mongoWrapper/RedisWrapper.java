package com.unifi.fattureApp.mongoWrapper;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.Invoice;

public class RedisWrapper implements Database{
	
	private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOps;
    
    private static final String clientKEY = "client"; 
    private static final String companyKEY = "company"; 
    private static final String invoiceKEY = "invoice"; 
	
	public RedisWrapper() {
		this.redisTemplate = redisTemplate();
		hashOps = redisTemplate.opsForHash();
	}
	
	private JedisConnectionFactory jedisConnectionFactory() {
		 JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		    jedisConFactory.setHostName("localhost");
		    jedisConFactory.setPort(6379);
		    return jedisConFactory;
	}
	 
	private RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}

	@Override
	public List<Client> getAllClientsList() {
		LinkedList<Client> clients=new LinkedList<>();
		Iterator<Client> iterator=hashOps.entries(clientKEY).keySet().iterator();
		while(iterator.hasNext()) {
			clients.add((Client) hashOps.entries(clientKEY).get(iterator.next()));
		}
		
		return clients;
	}

	@Override
	public Client findClientById(String id) {
		return (Client) hashOps.get(clientKEY, id);
	}

	@Override
	public void saveClient(Client client) {
		 hashOps.put(clientKEY, client.getId(), client);
	}

	@Override
	public List<Company> getAllCompaniesList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company findCompanyById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCompany(Company company) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Invoice> getAllInvoicesList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invoice findInvoiceById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		
	}

}
