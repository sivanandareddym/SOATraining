package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Manufacturer;
import com.example.repository.ManufacturerRepository;

@RestController
public class ManufacturerAPI {

	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	@Autowired
	private OrderClient orderClient;
	
	@RequestMapping(value="/api/manufacturer", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Manufacturer> add(@RequestBody Manufacturer manufacturer)
	{
		manufacturerRepository.save(manufacturer);
		return new ResponseEntity<Manufacturer>(manufacturer, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/api/manufacturer/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Manufacturer> update(@RequestBody Manufacturer manufacturer, @PathVariable Integer id)
	{
		Manufacturer manf = manufacturerRepository.findOne(id);
		if(manf!=null)
		{
			manf.setName(manufacturer.getName());
			manf.setFoundDate(manufacturer.getFoundDate());
			manf.setActive(manufacturer.getActive());
		}
			manufacturerRepository.save(manf);
		return new ResponseEntity<Manufacturer>(manufacturer, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/api/manufacturer", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Manufacturer>> findAll()
	{
		return new ResponseEntity<List<Manufacturer>>(manufacturerRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping("/")
	@ResponseBody
	public List<Order> findAllOrders()
	{
		return orderClient.findAll();
	}
}
