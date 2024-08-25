package com.vk.mydiaryrestapi.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.mydiaryrestapi.entities.Entry;
import com.vk.mydiaryrestapi.respository.EntryRepo;


@Service
public class EntryServiceImpl implements EntryService {

	@Autowired
	private EntryRepo entryRepo;
	
	@Override
	public Entry saveEntry(Entry entry) {
		return entryRepo.save(entry);
	}

	@Override
	public Entry updateEntry(Entry entry) {
		return entryRepo.save(entry);
	}

	@Override
	public void deleteEntry(Entry entry) {
		entryRepo.delete(entry);

	}

	@Override
	public Entry findEntryById(long id) {
		return entryRepo.findById(id).get();
	}

	@Override
	public List<Entry> findAll() {
		return entryRepo.findAll();
	}

	@Override
	public List<Entry> findEntriesByUserId(long id) {
		return entryRepo.findEntriesByUserId(id);
	}

}
