package com.vk.mydiaryrestapi.controllers;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vk.mydiaryrestapi.entities.Entry;
import com.vk.mydiaryrestapi.service.EntryService;

// server.servlet.context-path=/mydiaryapi --> the 'context path' is used to set a base URL for all endpoints in the application.

@RestController // used when no 'views' or when 'REST' api used
@RequestMapping("/entries")
public class EntryController {
	
	
	@Autowired
	private EntryService entryService;

	@GetMapping("/hello ")
	public String Hello() 
	{
		return "Welcome................";
	} 
	
	// @GetMapping("/entries/") like this not used here because @RequestMapping("/entries") is mapped/set at class level, no need to mention at method, so("/") == ("/entries/")
	@GetMapping("/")
	public List<Entry> findAllEntries() 
	{
		return entryService.findAll();
	}
	
	@PostMapping("/")
	public Entry saveEntry(@RequestBody Entry entry)  //@RequestBody used to get 'body' requested via postman
	{
		Entry savedEntry =  entryService.saveEntry(entry);	
		return savedEntry;
	}
	
	@PutMapping("/")
	public Entry updateEntry(@RequestBody Entry entry)  //@RequestBody used to get 'body' requested via postman
	{
		Entry updatedEntry =  entryService.updateEntry(entry);	
		return updatedEntry;
	}
	
	//@GetMapping("/entries/{id}") not used because @RequestMapping("/entries") is already mapped at class level, no need to mention at method, so("/{id}") == ("/entries/{id}")
	@GetMapping("/{id}")
	public Entry findEntryById(@PathVariable("id") long id) {
		Entry entry = entryService.findEntryById(id);
		return entry;
	}
	
	@DeleteMapping("/{id}")
	public void deleteEntry(@PathVariable("id") long id) {
		Entry entry = entryService.findEntryById(id);
		entryService.deleteEntry(entry);
	}
	
	// update for 'id'
	@PutMapping("/{id}")
	public Entry updateEntryById(@PathVariable("id") long id, @RequestBody Entry entry)  //@RequestBody used to get 'body' requested via postman
	{
		Entry dbentry  = entryService.findEntryById(id); // entry from db
		dbentry.setEntrydate(entry.getEntrydate());
		dbentry.setDescription(entry.getDescription());
		dbentry.setUserid(entry.getUserid());
		
		Entry updatedEntry = entryService.updateEntry(dbentry);
		return updatedEntry;
	}
	
	// Patch -  we can updated only req fields
	@PatchMapping("/{id}")
	public Entry updateEntryByPatch(@PathVariable("id") long id, @RequestBody Entry entry)  //@RequestBody used to get 'body' requested via postman
	{
		Entry dbentry  = entryService.findEntryById(id); // entry from db

		String desc = entry.getDescription();
		Date dt = entry.getEntrydate();
		long uid = entry.getUserid();
		
		if(dt!=null)
			dbentry.setEntrydate(entry.getEntrydate());
		
		if(desc!=null && desc.length()>0)
			dbentry.setDescription(entry.getDescription());
		
		if(uid>0)
			dbentry.setUserid(entry.getUserid());
		
		Entry updatedEntry = entryService.updateEntry(dbentry);
		return updatedEntry;
	}
	
	
	
}
