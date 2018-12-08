/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metre.resources;

import com.metre.domain.Cliente;
import com.metre.dto.ClienteDTO;
import com.metre.dto.ClienteNewDTO;
import com.metre.services.ClienteService;
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
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> listar(@PathVariable Integer id) {
        Cliente c = clienteService.buscarPorID(id);
        return ResponseEntity.ok().body(c);
    }
    
    
     @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> inserir(@Valid @RequestBody ClienteNewDTO obj) {
        Cliente cat = clienteService.fromDTO(obj);
        clienteService.inserir(cat);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ui}")
                .buildAndExpand(cat.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO obj) {
        obj.setId(id);
        Cliente cat = clienteService.fromDTO(obj); 
        clienteService.update(cat);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> update(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> cats = clienteService.findAll();
        List<ClienteDTO> catsd = cats.stream().map(m -> new ClienteDTO(m)).collect(Collectors.toList()); 
        return ResponseEntity.ok().body(catsd);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Cliente> cats = clienteService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> catsd = cats.map(m -> new ClienteDTO(m));
        return ResponseEntity.ok().body(catsd);
    }

}
