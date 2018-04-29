package com.kratonsolution.es.hamming.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.kratonsolution.es.hamming.model.Kasus;
import com.kratonsolution.es.hamming.model.KasusSolusi;
import com.kratonsolution.es.hamming.repository.KasusRepository;

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
        return repo.findAll(false);
    }
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Kasus> getAllKasusBaru() {
        return repo.findAll(true);
    }
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Kasus> getAllKasuses(@NonNull String key) {
        return repo.findAll(key);
    }
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Kasus> getAllKasuses(int page, int size) {
        return repo.findAll(new PageRequest(page, size)).getContent();
    }
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<Kasus> getAllKasuses(@NonNull String key,int page, int size) {
        return repo.findAll(new PageRequest(page, size), key);
    }
    
    public int count() {
        return (int)repo.count();
    }
    
    public int count(@NonNull String key) {
        return repo.count(key).intValue();
    }
    
    public Optional<Kasus> getById(@NonNull String id) {
        
        return Optional.ofNullable(repo.findOne(id));
    }
    
    public Optional<Kasus> getByBinaries(@NonNull String binaries) {
        
        return repo.findAll(false).stream().filter(p -> p.binaries().equals(binaries)).findFirst();
    }
    
    public void create(@NonNull Kasus kasus) {
        
        checkDuplication(kasus);
        repo.save(kasus);
    }

    private void checkDuplication(Kasus kasus) {
        boolean duplicate = repo.findAll(false).stream().anyMatch(p -> p.binaries().equals(kasus.binaries()));
        Preconditions.checkState(!duplicate, "Kasus with binaries [%s] already exist", kasus.binaries());
    }
    
    public void update(@NonNull Kasus kasus) {
        repo.save(kasus);
    }
    
    public void acceptKasus(@NonNull String id,
            @NonNull boolean[] selecteds,
            @NonNull String[] solutionIDs) {
        
        Kasus out = repo.findOne(id);
        Preconditions.checkState(out != null, "Kasus with id [%s] does not exist.");
        
        for(int idx=0;idx<selecteds.length;idx++) {
            setSelected(out, selecteds[idx], solutionIDs[idx]);
        }
        
        out.setIncubated(false);
        
        repo.save(out);
    }
    
    public void acceptKasus(@NonNull Kasus kasus) {
        
        checkDuplication(kasus);
        kasus.setIncubated(false);
        repo.save(kasus);
    }
    
    public void update(@NonNull String id,
            String fitur1, 
            String fitur2, 
            String fitur3, 
            String fitur4, 
            String fitur5, 
            String fitur6, 
            String fitur7,
            @NonNull boolean[] selecteds,
            @NonNull String[] solutionIDs) {
        
        Kasus out = repo.findOne(id);
        Preconditions.checkState(out != null, "Kasus with id [%s] does not exist.");
        
        Kasus buffer = new Kasus(fitur1, fitur2, fitur3, fitur4, fitur5, fitur6, fitur7);
        
        boolean duplicate = repo.findAll(false).stream().anyMatch(p -> !p.getId().equals(id) && p.binaries().equals(buffer.binaries()));
        Preconditions.checkState(!duplicate, "Kasus with binaries [%s] already exist", buffer.binaries());
        
        out.setFitur1(fitur1);
        out.setFitur2(fitur2);
        out.setFitur3(fitur3);
        out.setFitur4(fitur4);
        out.setFitur5(fitur5);
        out.setFitur6(fitur6);
        out.setFitur7(fitur7);
        
        for(int idx=0;idx<selecteds.length;idx++) {
            setSelected(out, selecteds[idx], solutionIDs[idx]);
        }
        
        repo.save(out);
    }
    
    private void setSelected(Kasus kasus, final boolean selected, final String id) {
        
        Optional<KasusSolusi> opt = kasus.getSolutions().stream().filter(p -> p.getId().equals(id)).findFirst();
        if(opt.isPresent()) {
            opt.get().setSelected(selected);
        }
    }
    
    public void setIncubated(@NonNull String id, boolean incubated) {
        
        Kasus out = repo.findOne(id);
        Preconditions.checkState(out != null, "Kasus with id [%s] does not exist.");
        
        out.setIncubated(incubated);
        repo.save(out);
    }
    
    public void delete(@NonNull String id) {
        
        Kasus kasus = repo.findOne(id);
        Preconditions.checkState(kasus != null, "Kasus does not exist.");
        repo.delete(kasus);
    }
}
