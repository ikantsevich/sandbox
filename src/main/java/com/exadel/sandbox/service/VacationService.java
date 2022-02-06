package com.exadel.sandbox.service;

import com.exadel.sandbox.entities.Vacation;

import java.util.List;

public interface VacationService {
    List<Vacation> getVacation();

    Vacation getVacationById(int id);

    Vacation create(Vacation vacation);

    void deleteById(int id);

    Vacation update(int id, Vacation vacation);
}
