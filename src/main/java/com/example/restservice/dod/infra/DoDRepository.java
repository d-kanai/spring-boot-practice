package com.example.restservice.dod.infra;

import com.example.restservice.dod.domain.DoD;
import com.example.restservice.dod.domain.repository.IDoDRepository;
import com.example.restservice.dodRecord.infra.DoDRecordJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class DoDRepository implements IDoDRepository {

    @Autowired
    private DoDJpaRepository jpaDoD;

    @Autowired
    private DoDRecordJpaRepository dodRecordRepository;

    public List<DoD> findAllWithDoDRecord() {
        List<DoD> dods = new ArrayList<>();
        for (DoDEntity dodEntity : jpaDoD.findAll()) {
            dods.add(new DoD(
                    dodEntity.getId(),
                    dodEntity.getName(),
                    dodRecordRepository.findByDodId(dodEntity.getId()))
            );
        }
        return dods;
    }

    public DoD create(DoD dod) {
        DoDEntity dodEntity = this.jpaDoD.save(
                new DoDEntity(dod.getName())
        );
        return new DoD(
                dodEntity.getId(),
                dodEntity.getName(),
                new ArrayList<>()
        );
    }
}
