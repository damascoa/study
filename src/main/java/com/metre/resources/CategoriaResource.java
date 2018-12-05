/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metre.resources;

import com.metre.domain.Categoria;
import com.metre.dto.CategoriaDTO;
import com.metre.services.CategoriaService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Renato
 */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> listar(@PathVariable Integer id) {
        Categoria c = categoriaService.find(id);
        return ResponseEntity.ok().body(c);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> inserir(@Valid @RequestBody CategoriaDTO obj) {
        Categoria cat = categoriaService.fromDTO(obj);
        categoriaService.inserir(cat);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ui}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO obj) {
        obj.setId(id);
        Categoria cat = categoriaService.fromDTO(obj);
        categoriaService.update(cat);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> update(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> cats = categoriaService.findAll();
        List<CategoriaDTO> catsd = cats.stream().map(m -> new CategoriaDTO(m)).collect(Collectors.toList());
        return ResponseEntity.ok().body(catsd);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Categoria> cats = categoriaService.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoriaDTO> catsd = cats.map(m -> new CategoriaDTO(m));
        return ResponseEntity.ok().body(catsd);
    }

}
