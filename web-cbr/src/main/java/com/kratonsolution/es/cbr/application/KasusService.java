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
import com.kratonsolution.es.cbr.model.KasusSolusi;
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
        
        boolean duplicate = repo.findAll(false).stream().anyMatch(p -> p.binaries().equals(kasus.binaries()));
        Preconditions.checkState(!duplicate, "Kasus with binaries [%s] already exist", kasus.binaries());
        
        repo.save(kasus);
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
    
    public void update(@NonNull String id,
            boolean fitur1, 
            boolean fitur2, 
            boolean fitur3, 
            boolean fitur4, 
            boolean fitur5, 
            boolean fitur6, 
            boolean fitur7, 
            boolean fitur8, 
            boolean fitur9, 
            boolean fitur10, 
            boolean fitur11, 
            boolean fitur12, 
            boolean fitur13, 
            boolean fitur14, 
            boolean fitur15, 
            boolean fitur16, 
            boolean fitur17,
            boolean fitur18,
            boolean fitur19,
            boolean fitur20,
            boolean fitur21,
            boolean fitur22,
            boolean fitur23,
            boolean fitur24,
            boolean fitur25,
            boolean fitur26,
            boolean fitur27,
            boolean fitur28,
            boolean fitur29,
            boolean fitur30,
            boolean fitur31,
            boolean fitur32,
            boolean fitur33,
            boolean fitur34,
            boolean fitur35,
            @NonNull boolean[] selecteds,
            @NonNull String[] solutionIDs) {
        
        Kasus out = repo.findOne(id);
        Preconditions.checkState(out != null, "Kasus with id [%s] does not exist.");
        
        Kasus buffer = new Kasus(fitur1, fitur2, fitur3, fitur4, 
                fitur5, fitur6, fitur7, fitur8, fitur9, fitur10, 
                fitur11, fitur12, fitur13, fitur14, fitur15, fitur16, 
                fitur17, fitur18, fitur19, fitur20, fitur21, fitur22, 
                fitur23, fitur24, fitur25, fitur26, fitur27, fitur28, 
                fitur29, fitur30, fitur31, fitur32, fitur33, fitur34, fitur35);
        
        boolean duplicate = repo.findAll(false).stream().anyMatch(p -> !p.getId().equals(id) && p.binaries().equals(buffer.binaries()));
        Preconditions.checkState(!duplicate, "Kasus with binaries [%s] already exist", buffer.binaries());
        
        out.setFitur1(fitur1);
        out.setFitur2(fitur2);
        out.setFitur3(fitur3);
        out.setFitur4(fitur4);
        out.setFitur5(fitur5);
        out.setFitur6(fitur6);
        out.setFitur7(fitur7);
        out.setFitur8(fitur8);
        out.setFitur9(fitur9);
        out.setFitur10(fitur10);
        out.setFitur11(fitur11);
        out.setFitur12(fitur12);
        out.setFitur13(fitur13);
        out.setFitur14(fitur14);
        out.setFitur15(fitur15);
        out.setFitur16(fitur16);
        out.setFitur17(fitur17);
        out.setFitur18(fitur18);
        out.setFitur19(fitur19);
        out.setFitur20(fitur20);
        out.setFitur21(fitur21);
        out.setFitur22(fitur22);
        out.setFitur23(fitur23);
        out.setFitur24(fitur24);
        out.setFitur25(fitur25);
        out.setFitur26(fitur26);
        out.setFitur27(fitur27);
        out.setFitur28(fitur28);
        out.setFitur29(fitur29);
        out.setFitur30(fitur30);
        out.setFitur31(fitur31);
        out.setFitur32(fitur32);
        out.setFitur33(fitur33);
        out.setFitur34(fitur34);
        out.setFitur35(fitur35);
        
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
