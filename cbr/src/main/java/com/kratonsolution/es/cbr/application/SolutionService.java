package com.kratonsolution.es.cbr.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.kratonsolution.es.cbr.application.SolutionEvent.Type;
import com.kratonsolution.es.cbr.model.Solution;
import com.kratonsolution.es.cbr.repository.SolutionRepository;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Slf4j
@Service
@Transactional(rollbackFor=Exception.class)
public class SolutionService {
    
    @Autowired
    private SolutionRepository repo;
    
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Solution> getAllSolutiones() {
        return repo.findAll();
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
        publisher.publishEvent(new SolutionEvent(Type.ADD, solution));
    }
    
    public void update(@NonNull String id, Optional<String> note) {
        
        if(note.isPresent()) {
            
            Solution solution = repo.findOne(id);
            Preconditions.checkState(solution != null, "Solution does not exist.");
            solution.setDescription(note.get());
     
            repo.save(solution);
            
            publisher.publishEvent(new SolutionEvent(Type.EDIT, solution));
        }
    }
    
    public void delete(@NonNull String id) {
        
        Solution solution = repo.findOne(id);
        Preconditions.checkState(solution != null, "Solution does not exist.");
        repo.delete(solution);
        publisher.publishEvent(new SolutionEvent(Type.DELETE, solution));
        log.info("Removing solution", solution);
    }
}
