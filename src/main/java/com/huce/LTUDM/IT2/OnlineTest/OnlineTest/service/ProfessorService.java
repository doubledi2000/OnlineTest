package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.service;

import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Answer;
import com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity.Professor;

import java.util.Collection;

public interface ProfessorService {
    public abstract void createProfessor(Professor professor);

    public abstract void updateProfessor(String id, Professor professor);

    public abstract void deleteProfessor(String id);

    public abstract Professor getProfessorByID(String id);

    public abstract Collection<Professor> getAll();

}
