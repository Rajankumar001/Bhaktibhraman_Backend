package com.bhaktiBhraman.app.features.temple.repository;

import com.bhaktiBhraman.app.features.temple.entity.Temple;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempleRepository extends JpaRepository<Temple,Long> {
}
