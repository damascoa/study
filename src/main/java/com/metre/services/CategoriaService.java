/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metre.services;

import com.metre.repositories.CategoriaRepository;
import com.metre.domain.Categoria;
import com.metre.dto.CategoriaDTO;
import com.metre.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Renato
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository cRepository;

    public Categoria find(Integer id) {
        Optional<Categoria> cat = cRepository.findById(id);
        return cat.orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado!" + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria inserir(Categoria obj) {
        return cRepository.save(obj);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId());
        return cRepository.save(obj);
    }

    public void delete(Integer Id) {
        try {
            cRepository.deleteById(Id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possivel excluir uma categoria com produtos!");
        }
    }

    public List<Categoria> findAll() {
        return cRepository.findAll();
    }
    
    public Page<Categoria> findPage(Integer page, Integer linesPerPage,String orderBy, String direction){
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return cRepository.findAll(pageRequest); 
    }
    
    public Categoria fromDTO(CategoriaDTO categoriaDTO){
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }
}
