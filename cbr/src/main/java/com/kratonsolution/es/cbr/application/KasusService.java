package com.kratonsolution.es.cbr.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.kratonsolution.es.cbr.model.Kasus;
import com.kratonsolution.es.cbr.model.KasusFitur;
import com.kratonsolution.es.cbr.repository.KasusRepository;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class KasusService {
 
    @Autowired
    private KasusRepository repo;

    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Kasus> getAllKasuses() {
        return repo.findAll();
    }

    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Kasus> getAllKasuses(@NonNull String key) {
        return repo.findAllByCodeLike(key);
    }
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Kasus> getAllKasuses(int page, int size) {
        return repo.findAll(new PageRequest(page, size)).getContent();
    }
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Kasus> getAllKasuses(@NonNull String key,int page, int size) {
        return repo.findAllByCodeLike(new PageRequest(page, size), key);
    }
    
    public Optional<Kasus> getById(@NonNull String id) {
        
        return Optional.ofNullable(repo.findOne(id));
    }
    
    public void create(@NonNull Kasus kasus) {
        
        Optional<Kasus> opt = repo.findOneByCode(kasus.getCode());
        Preconditions.checkState(!opt.isPresent(), "Kasus with name [%s] already exist", kasus.getCode());
        repo.save(kasus);
    }
    
    public void update(@NonNull Kasus kasus) {
        
        Optional<Kasus> opt = repo.findOneByCode(kasus.getCode());
        Preconditions.checkState(opt.isPresent(), "Kasus does not exist.");
        
        kasus.getFitures().stream().forEach(fitur -> {
            
            Optional<KasusFitur> db = opt.get().getFitures().stream().filter(k -> k.getFitur().equals(fitur.getFitur()) && k.isValue() != fitur.isValue()).findFirst();
            if(db.isPresent()) {
                db.get().setValue(fitur.isValue());
            }
        });
        
        repo.save(opt.get());
    }
    
    public void delete(@NonNull String id) {
        
        Kasus kasus = repo.findOne(id);
        Preconditions.checkState(kasus != null, "Kasus does not exist.");
        repo.delete(kasus);
    }
}
