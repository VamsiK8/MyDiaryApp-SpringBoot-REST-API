package com.vk.mydiaryrestapi.respository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vk.mydiaryrestapi.entities.Entry;


public interface EntryRepo extends JpaRepository<Entry, Long> {

	@Query(value="select * from entries where userid =:id", nativeQuery = true) //:id namedParameter
	public List<Entry> findEntriesByUserId(long id);
}
