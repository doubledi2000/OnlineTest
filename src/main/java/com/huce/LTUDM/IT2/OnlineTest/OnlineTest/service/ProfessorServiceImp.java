package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;


import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Professor;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProfessorServiceImp implements ProfessorService {

    @Autowired
    ProfessorRepository repo;

    @Override
    public void createProfessor(Professor professor) {
        repo.save(professor);
    }

    @Override
    public void updateProfessor(String id, Professor professor) {
        professor.setProfessorCode(id);
        repo.save(professor);
    }

    @Override
    public void deleteProfessor(String id) {
        repo.deleteById(id);
    }

    @Override
    public Professor getProfessorByID(String id) {
        return repo.findById(id).get();
    }

    @Override
    public Collection<Professor> getAll() {
        return (Collection<Professor>) repo.findAll();
    }
}
