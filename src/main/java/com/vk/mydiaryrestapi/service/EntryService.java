package com.vk.mydiaryrestapi.service;

import java.util.List;

import com.vk.mydiaryrestapi.entities.Entry;



public interface EntryService {
	public Entry saveEntry(Entry entry);
	public Entry updateEntry(Entry entry);
	public void deleteEntry(Entry entry);
	public Entry findEntryById(long id);
	public List<Entry> findAll();
	
	public List<Entry> findEntriesByUserId(long id);
}
