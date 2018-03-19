package com.kratonsolution.es.cbr.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.kratonsolution.es.cbr.model.Solution;
import com.kratonsolution.es.cbr.repository.SolutionRepository;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class SolutionService {
    
    @Autowired
    private SolutionRepository repo;
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Solution> getAllSolutiones() {
        return repo.findAll(new Sort(Sort.Direction.ASC, "sequence"));
    }
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Solution> getAllSolutiones(@NonNull String key) {
        return repo.findAll(key);
    }
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Solution> getAllSolutiones(int page, int size) {
        return repo.findAll(new PageRequest(page, size)).getContent();
    }
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Solution> getAllSolutiones(@NonNull String key,int page, int size) {
        return repo.findAll(new PageRequest(page, size), key);
    }
    
    public int count() {
        return (int)repo.count();
    }
    
    public int count(@NonNull String key) {
        return repo.count(key).intValue();
    }
    
    public Optional<Solution> getById(@NonNull String id) {
        
        return Optional.ofNullable(repo.findOne(id));
    }
    
    public void create(@NonNull Solution solution) {
        
        repo.save(solution);
    }
    
    public void update(@NonNull String id, Optional<String> note) {
        
        if(note.isPresent()) {
            
            Solution opt = repo.findOne(id);
            Preconditions.checkState(opt != null, "Solution does not exist.");
            opt.setDescription(note.get());
     
            repo.save(opt);
        }
    }
    
    public void delete(@NonNull String id) {
        
        Solution solution = repo.findOne(id);
        Preconditions.checkState(solution != null, "Solution does not exist.");
        repo.delete(solution);
    }
}
