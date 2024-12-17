package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.entity.generated.Skill;
import org.example.eventmanagement.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;


    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(long id) {
        Skill skill = skillRepository.findById(id);
        if (skill == null) {
            throw new NoSuchElementException("Skill not found with ID: " + id);
        }
        return skill;
    }

    public Skill getSkillByName(String name) {
        Skill skill = skillRepository.findByName(name);
        if (skill == null) {
            throw new NoSuchElementException("Skill not found with name: " + name);
        }
        return skill;
    }

    public List<Skill> searchSkillsByName(String keyword) {
        return skillRepository.findByNameContaining(keyword);
    }

    public Skill createSkill(Skill skill) {
        skillRepository.save(skill);
        return skill;
    }

    public Skill updateSkill(Skill skill) {
        Skill existingSkill = getSkillById(skill.getId());
        skillRepository.update(skill);
        return skill;
    }

    public void deleteSkillById(long id) {
        Skill existingSkill = getSkillById(id);
        skillRepository.deleteById(id);
    }

}
