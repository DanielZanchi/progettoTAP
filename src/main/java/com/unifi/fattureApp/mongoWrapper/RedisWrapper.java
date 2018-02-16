package com.unifi.fattureApp.mongoWrapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.Invoice;

public class RedisWrapper implements Database{
	private RedisTemplate<String, Invoice> redisTemplate;
	
	private HashOperations<String, String, Object> hashOps;
	
	private static final String CLIENTKEY = "client_key_redis"; 
	private static final String COMPANYKEY = "company_key_redis"; 
	private static final String INVOICEKEY = "invoice_key_redis"; 
	
	public RedisTemplate<String, Invoice> getRedisTemplate(){
	    return redisTemplate;
	}
	
	public void setRedisTemplate(RedisTemplate<String, Invoice> redisTemplate){
	    this.redisTemplate = redisTemplate;
	}


	@Override
	public List<Client> getAllClientsList() {
		List<Client> clients = new ArrayList<>();
		return clients;
		
	}

	@Override
	public Client findClientById(String id) {
		return (Client) hashOps.get(CLIENTKEY, id);
	}

	@Override
	public void saveClient(Client client) {
	}

	@Override
	public List<Company> getAllCompaniesList() {
		LinkedList<Company> companies = new LinkedList<>();
		Iterator<String> iterator = hashOps.entries(COMPANYKEY).keySet().iterator();
		while(iterator.hasNext()) {
			companies.add((Company) hashOps.entries(COMPANYKEY).get(iterator.next()));
		}
		return companies;
	}

	@Override
	public Company findCompanyById(String id) {
		return (Company) hashOps.get(COMPANYKEY, id);
	}

	@Override
	public void saveCompany(Company company) {
		hashOps.put(COMPANYKEY, company.getId(), company);
	}

	

	@Override
	public void removeCompanyById(String id) {
		hashOps.delete(COMPANYKEY, id);
	}

	@Override
	public void removeClientById(String id) {
		hashOps.delete(CLIENTKEY, id);
	}

	//Invoice
	
	@Override
	public Invoice findInvoiceById(String id) {
		return (Invoice) redisTemplate.opsForHash().get(INVOICEKEY, id);
	}
	
	
	@Override
	public void saveInvoice(Invoice invoice) {
		redisTemplate.opsForHash().put(INVOICEKEY, invoice.getId(), invoice);
	}
	
	@Override
	public List<Invoice> getAllInvoicesList() {
		LinkedList<Invoice> invoices = new LinkedList<>();
		Iterator<Object> iterator = redisTemplate.opsForHash().entries(INVOICEKEY).keySet().iterator();
		while(iterator.hasNext()) {
			invoices.add((Invoice) redisTemplate.opsForHash().entries(INVOICEKEY).get(iterator.next()));
		}
		return invoices;
	}
	
	@Override
	public void removeInvoiceById(String id) {
		redisTemplate.opsForHash().delete(INVOICEKEY, id);
	}
}