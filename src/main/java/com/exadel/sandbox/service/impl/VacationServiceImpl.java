package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.entities.Vacation;
import com.exadel.sandbox.repository.VacationRepository;
import com.exadel.sandbox.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VacationServiceImpl implements VacationService {
    @Autowired
    VacationRepository vacationRepository;

    @Override
    public List<Vacation> getVacation() {
        return vacationRepository.findAll();
    }

    @Override
    public Vacation getVacationById(int id) {
        return vacationRepository.getOne(id);
    }

    @Override
    public Vacation create(Vacation vacation) {
        return vacationRepository.save(vacation);
    }

    @Override
    public void deleteById(int id) {
        vacationRepository.deleteById(id);
    }

    @Override
    public Vacation update(int id, Vacation vacation) {
        Vacation newVacation = vacationRepository.getOne(id);

        newVacation.setVa_modified(vacation.getVa_modified());
        newVacation.setVa_start(vacation.getVa_start());
        newVacation.setEmployee(vacation.getEmployee());
        newVacation.setVa_end(vacation.getVa_end());


        return vacationRepository.save(newVacation);
    }
}
