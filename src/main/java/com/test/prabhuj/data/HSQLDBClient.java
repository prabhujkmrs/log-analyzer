package com.test.prabhuj.data;

import com.test.prabhuj.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HSQLDBClient extends JpaRepository<Event,String> {

}
