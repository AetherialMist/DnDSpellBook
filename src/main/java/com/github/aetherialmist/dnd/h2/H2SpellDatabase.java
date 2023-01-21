package com.github.aetherialmist.dnd.h2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface H2SpellDatabase extends JpaRepository<SpellEntity, Integer> {

}
