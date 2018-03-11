package com.kratonsolution.es.cbr.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.kratonsolution.es.cbr.model.Fitur;
import com.kratonsolution.es.cbr.repository.FiturRepository;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class FiturService {
    
    @Autowired
    private FiturRepository repo;
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Fitur> getAllFitures() {
        return repo.findAll(new Sort(Sort.Direction.ASC, "sequence"));
    }
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Fitur> getAllFitures(@NonNull String key) {
        return repo.findAll(key);
    }
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Fitur> getAllFitures(int page, int size) {
        return repo.findAll(new PageRequest(page, size, new Sort(Direction.ASC, "sequence"))).getContent();
    }
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Fitur> getAllFitures(@NonNull String key,int page, int size) {
        return repo.findAll(new PageRequest(page, size, new Sort(Direction.ASC, "sequence")), key);
    }
    
    public int count() {
        return (int)repo.count();
    }
    
    public int count(@NonNull String key) {
        return repo.count(key).intValue();
    }
    
    public Optional<Fitur> getById(@NonNull String id) {
        
        return Optional.ofNullable(repo.findOne(id));
    }
    
    public Optional<Fitur> getByName(@NonNull String name) {
        
        return repo.findOneByName(name);
    }
    
    public void create(@NonNull Fitur fitur) {
        
        Optional<Fitur> opt = repo.findOneByName(fitur.getName());
        Preconditions.checkState(!opt.isPresent(), "Fitur with name [%s] already exist", fitur.getName());
        repo.save(fitur);
    }
    
    public void update(@NonNull Fitur fitur) {
        
        Optional<Fitur> opt = repo.findOneByName(fitur.getName());
        Preconditions.checkState(opt.isPresent(), "Fitur does not exist.");
        opt.get().setNote(fitur.getNote());
        repo.save(opt.get());
    }
    
    public void delete(@NonNull String id) {
        
        Fitur fitur = repo.findOne(id);
        Preconditions.checkState(fitur != null, "Fitur does not exist.");
        repo.delete(fitur);
    }
}
